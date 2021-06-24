/*******************************************************************************
 * Copyright 2021 Konexios, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.konexios.acn.client.cloud;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.codec.binary.StringUtils;

import com.konexios.acn.client.IotParameters;
import com.konexios.acn.client.api.AcnClient;
import com.konexios.acn.client.api.CoreEventApi;
import com.konexios.acn.client.model.DeviceStateRequestModel;
import com.konexios.acn.client.model.DeviceStateUpdateModel;
import com.konexios.acs.AcsLogicalException;
import com.konexios.acs.AcsUtils;
import com.konexios.acs.GatewayPayloadSigner;
import com.konexios.acs.JsonUtils;
import com.konexios.acs.Loggable;
import com.konexios.acs.client.api.ApiConfig;
import com.konexios.acs.client.model.CloudResponseModel;
import com.konexios.acs.client.model.GatewayEventModel;

public abstract class CloudConnectorAbstract extends Loggable {
	private String gatewayHid;
	protected MessageListener listener;
	protected AcnClient acnClient;
	protected DeviceStateRequestListener deviceStateRequestListener;
	protected Set<String> deviceHids = new HashSet<>();
	protected ExecutorService service;
	protected boolean terminating = false;
	protected Map<String, CloudResponseWrapper> responseMap = new HashMap<>();

	protected CloudConnectorAbstract(AcnClient acnClient, String gatewayHid) {
		this.acnClient = acnClient;
		this.gatewayHid = gatewayHid;
		this.service = Executors.newCachedThreadPool();
	}

	public String getGatewayHid() {
		return gatewayHid;
	}

	public void setGatewayHid(String gatewayHid) {
		this.gatewayHid = gatewayHid;
	}

	public Set<String> getDeviceHids() {
		return deviceHids;
	}

	public void setDeviceHids(Set<String> deviceHids) {
		this.deviceHids = deviceHids;
	}

	public boolean addDeviceHid(String deviceHid) {
		return deviceHids.add(deviceHid);
	}

	public void setListener(MessageListener listener) {
		this.listener = listener;
	}

	public void setDeviceStateRequestListener(DeviceStateRequestListener deviceStateRequestListener) {
		this.deviceStateRequestListener = deviceStateRequestListener;
	}

	public void start() {
	}

	public void stop() {
		terminating = true;
		if (service != null) {
			try {
				service.shutdownNow();
			} catch (Exception e) {
			}
			service = null;
		}
	}

	public abstract void send(IotParameters payload);

	public abstract void sendBatch(List<IotParameters> batch, TransferMode transferMode);

	protected void sendDeviceStateUpdate(String deviceHid, DeviceStateUpdateModel model) {
		throw new AcsLogicalException("this feature is not yet implemented!");
	}

	protected void receiveDeviceStateRequest(String deviceHid, DeviceStateRequestModel model) {
		if (deviceStateRequestListener != null) {
			deviceStateRequestListener.receive(deviceHid, model);
		}
	}

	protected void validateAndProcessEvent(String topic, byte[] payload) {
		String method = "validateAndProcessEvent";
		GatewayEventModel model = JsonUtils.fromJson(new String(payload, StandardCharsets.UTF_8),
				GatewayEventModel.class);
		logInfo(method, "payload model: %s", JsonUtils.toJson(model));

		if (model == null || AcsUtils.isEmpty(model.getName())) {
			logError(method, "ignore invalid payload: %s", payload);
			return;
		}

		if (isSignatureValid(model)) {
			logInfo(method, "signature is valid");
			if (listener == null) {
				logError(method, "listener is not defined");
				markFailedEvent(model.getHid(), "Listener is not defined");
			} else {
				try {
					listener.processMessage(topic, payload);
				} catch (Exception e) {
					markFailedEvent(model.getHid(), e.getMessage());
				}
			}
		} else {
			logWarn(method, "signature is invalid, even with UTF-8 charset");
			markFailedEvent(model.getHid(), "Signature is invalid");
		}
	}

	private void markFailedEvent(String hid, String message) {
		String method = "markFailedEvent";
		logInfo(method, "hid: %s, message: %s", hid, message);
		CompletableFuture.runAsync(() -> {
			CoreEventApi eventApi = acnClient.getCoreEventApi();
			eventApi.putReceived(hid);
			eventApi.putFailed(hid, message);
		});
	}

	private boolean isSignatureValid(GatewayEventModel model) {
		String method = "isSignatureValid";
		logInfo(method, "...");
		if (AcsUtils.isEmpty(model.getSignature())) {
			logDebug(method, "skipping signature validation");
			return true;
		}
		ApiConfig apiConfig = acnClient.getApiConfig();
		GatewayPayloadSigner signer = GatewayPayloadSigner.create(apiConfig.getSecretKey())
				.withApiKey(apiConfig.getApiKey()).withHid(model.getHid()).withName(model.getName())
				.withEncrypted(model.isEncrypted());
		for (Entry<String, String> entry : model.getParameters().entrySet()) {
			signer.withParameter(entry.getKey(), entry.getValue());
		}
		String signatureVersion = model.getSignatureVersion();
		switch (signatureVersion) {
		case GatewayPayloadSigner.PAYLOAD_SIGNATURE_VERSION_1: {
			String signature = signer.signV1();
			logInfo(method, "model signature: %s", model.getSignature());
			logInfo(method, "local signature: %s", signature);
			return StringUtils.equals(signature, model.getSignature());
		}
		default: {
			logWarn(method, "signature of version %s is not supported", signatureVersion);
			return false;
		}
		}
	}

	public static class CloudResponseWrapper {
		CloudResponseModel response;
		boolean ready = false;

		public synchronized void complete(CloudResponseModel response) {
			if (!ready) {
				this.response = response;
				this.ready = true;
			}
			this.notifyAll();
		}

		public synchronized CloudResponseModel waitForResponse(long timeoutSecs) {
			long timeout = System.currentTimeMillis() + timeoutSecs;
			while (!ready && System.currentTimeMillis() < timeout) {
				try {
					this.wait(timeoutSecs * 1000);
				} catch (InterruptedException e) {
				}
			}
			return response;
		}
	}
}
