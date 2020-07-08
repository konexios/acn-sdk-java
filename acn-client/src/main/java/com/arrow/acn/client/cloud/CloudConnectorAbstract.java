/*******************************************************************************
 * Copyright (c) 2018 Arrow Electronics, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License 2.0
 * which accompanies this distribution, and is available at
 * http://apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *     Arrow Electronics, Inc.
 *******************************************************************************/
package com.arrow.acn.client.cloud;

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

import com.arrow.acn.client.IotParameters;
import com.arrow.acn.client.api.AcnClient;
import com.arrow.acn.client.api.CoreEventApi;
import com.arrow.acn.client.cloud.aws.job.JobExecutionPayload;
import com.arrow.acn.client.cloud.aws.job.JobExecutionStatus;
import com.arrow.acn.client.cloud.aws.job.JobExecutionUpdate;
import com.arrow.acn.client.model.DeviceStateRequestModel;
import com.arrow.acn.client.model.DeviceStateUpdateModel;
import com.arrow.acs.AcsLogicalException;
import com.arrow.acs.AcsUtils;
import com.arrow.acs.GatewayPayloadSigner;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.Loggable;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.model.CloudResponseModel;
import com.arrow.acs.client.model.GatewayEventModel;

public abstract class CloudConnectorAbstract extends Loggable {
    private String gatewayHid;
    protected MessageListener listener;
    protected AcnClient acnClient;
    protected DeviceStateRequestListener deviceStateRequestListener;
    protected AwsJobListener awsJobListener;
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

    public void setAwsJobListener(AwsJobListener awsJobListener) {
        this.awsJobListener = awsJobListener;
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

    protected void sendAwsJobUpdate(String deviceHid, String jobId, JobExecutionUpdate update) {
        throw new AcsLogicalException("this feature is not applicable for this cloud connector!");
    }

    protected void receiveDeviceStateRequest(String deviceHid, DeviceStateRequestModel model) {
        if (deviceStateRequestListener != null) {
            deviceStateRequestListener.receive(deviceHid, model);
        }
    }

    protected void awsJobNotifyNext(String deviceHid, JobExecutionPayload payload) {
        String method = "awsJobNotifyNext";
        if (payload.getExecution() != null) {
            if (awsJobListener != null) {
                awsJobListener.notifyNext(deviceHid, payload);
            } else {
                logError(method, "ERROR; no listener defined to process aws jobs!");
                JobExecutionUpdate update = new JobExecutionUpdate();
                update.setExpectedVersion(payload.getExecution().getVersionNumber());
                update.setClientToken(AcsUtils.randomString(16));
                update.setStatus(JobExecutionStatus.REJECTED.name());
                sendAwsJobUpdate(deviceHid, payload.getExecution().getJobId(), update);
            }
        } else {
            logWarn(method, "ignored payload with empty execution!");
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

        CoreEventApi eventApi = acnClient.getCoreEventApi();
        CompletableFuture.supplyAsync(() -> eventApi.putReceived(model.getHid()));
        if (isSignatureValid(model)) {
            logInfo(method, "signature is valid");
            if (listener == null) {
                logError(method, "listener is not defined");
                CompletableFuture.supplyAsync(() -> eventApi.putFailed(model.getHid(), "Listener is not defined"));
            } else {
                try {
                    listener.processMessage(topic, payload);
                    CompletableFuture.supplyAsync(() -> eventApi.putSucceeded(model.getHid()));
                } catch (Exception e) {
                    CompletableFuture.supplyAsync(() -> eventApi.putFailed(model.getHid(), e.getMessage()));
                }
            }
        } else {
            logWarn(method, "signature is invalid, even with UTF-8 charset");
            CompletableFuture.supplyAsync(() -> eventApi.putFailed(model.getHid(), "Signature is invalid"));
        }
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
