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
package com.arrow.acn.client.cloud.azure;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.StringUtils;

import com.arrow.acn.client.IotParameters;
import com.arrow.acn.client.api.AcnClient;
import com.arrow.acn.client.cloud.CloudConnectorAbstract;
import com.arrow.acn.client.cloud.TransferMode;
import com.arrow.acn.client.model.AzureConfigModel;
import com.arrow.acs.AcsLogicalException;
import com.arrow.acs.AcsRuntimeException;
import com.arrow.acs.AcsSystemException;
import com.arrow.acs.AcsUtils;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.client.api.MqttHttpChannel;
import com.arrow.acs.client.model.CloudRequestModel;
import com.arrow.acs.client.model.CloudResponseModel;
import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.IotHubClientProtocol;
import com.microsoft.azure.sdk.iot.device.IotHubEventCallback;
import com.microsoft.azure.sdk.iot.device.IotHubMessageResult;
import com.microsoft.azure.sdk.iot.device.IotHubStatusCode;
import com.microsoft.azure.sdk.iot.device.Message;
import com.microsoft.azure.sdk.iot.device.MessageCallback;

public class AzureConnector extends CloudConnectorAbstract implements MqttHttpChannel {
    public static final String PROPERTY_MESSAGE_TYPE = "message_type";
    public static final String PROPERTY_HID = "hid";

    public enum MessageType {
        TELEMETRY, COMMAND, API_REQUEST, API_RESPONSE
    }

    private final AzureConfigModel model;
    private DeviceClient client;

    private AtomicLong eventCounter = new AtomicLong();
    private LocalEventCallback eventCallback = new LocalEventCallback();
    private LocalMessageCallback messageCallback = new LocalMessageCallback();

    public AzureConnector(AcnClient acnClient, String gatewayHid, AzureConfigModel model) {
        super(acnClient, gatewayHid);
        this.model = model;
    }

    @Override
    public void start() {
        String method = "start";
        AcsUtils.notNull(model, "model is NULL");
        try {
            if (client == null) {
                logInfo(method, "creating azure client ...");
                client = new DeviceClient(model.getConnectionString(), IotHubClientProtocol.MQTT);
                client.setMessageCallback(messageCallback, null);
                logInfo(method, "connecting azure client ...");
                client.open();

                // route API calls to MQTT
                logInfo(method, "routing REST calls to MQTT ...");
                acnClient.setMqttHttpChannel(this);
            } else {
                logWarn(method, "client is already initialized");
            }
        } catch (AcsRuntimeException e) {
            throw e;
        } catch (Throwable t) {
            throw new AcsSystemException("Unable to start azure connector", t);
        }
    }

    @Override
    public void stop() {
        String method = "stop";
        if (client != null) {
            try {
                logInfo(method, "stopping client ...");
                client.closeNow();
            } catch (Throwable e) {
            }
            client = null;
        }
        super.stop();
    }

    @Override
    public void send(IotParameters payload) {
        String method = "send";
        if (client != null) {
            String json = JsonUtils.toJson(payload);
            Message message = new Message(json);
            message.setProperty(PROPERTY_MESSAGE_TYPE, MessageType.TELEMETRY.name());
            message.setProperty(PROPERTY_HID, payload.getDeviceHid());
            long counter = eventCounter.getAndIncrement();
            logDebug(method, "counter: %d, json size: %d", counter, json.length());
            client.sendEventAsync(message, eventCallback, counter);
        } else {
            logError(method, "client is NULL");
        }
    }

    @Override
    public void sendBatch(List<IotParameters> batch, TransferMode transferMode) {
        if (transferMode == TransferMode.GZIP_BATCH) {
            throw new AcsLogicalException(
                    "TransferMode not supported for Azure integration: " + TransferMode.GZIP_BATCH.name());
        }
        if (batch != null) {
            batch.forEach(this::send);
        }
    }

    @Override
    public CloudResponseModel sendRequest(CloudRequestModel request, long timeoutSecs) {
        String method = "sendRequest";
        if (!terminating && client != null) {
            try {
                String json = JsonUtils.toJson(request);
                Message message = new Message(json);
                message.setProperty(PROPERTY_MESSAGE_TYPE, MessageType.API_REQUEST.name());
                message.setProperty(PROPERTY_HID, getGatewayHid());
                long counter = eventCounter.getAndIncrement();
                logDebug(method, "counter: %d, json size: %d", counter, json.length());

                CloudResponseWrapper wrapper = new CloudResponseWrapper();
                responseMap.put(request.getRequestId(), wrapper);
                client.sendEventAsync(message, eventCallback, counter);

                CloudResponseModel response = wrapper.waitForResponse(timeoutSecs);
                if (response == null) {
                    throw new AcsLogicalException("Timeout waiting for response from MQTT channel");
                }
                return response;
            } catch (AcsRuntimeException e) {
                logError(method, e);
                throw e;
            } catch (Exception e) {
                logError(method, e);
                throw new AcsLogicalException("sendRequest failed", e);
            } finally {
                responseMap.remove(request.getRequestId());
            }
        } else {
            throw new AcsLogicalException("connector is terminating!");
        }
    }

    class LocalMessageCallback implements MessageCallback {
        public IotHubMessageResult execute(Message msg, Object context) {
            String method = "messageCallback";
            String messageType = msg.getProperty(PROPERTY_MESSAGE_TYPE);
            byte[] payload = msg.getBytes();
            logInfo(method, "messageType: %s, payload size: %d", messageType, payload.length);
            if (StringUtils.equalsIgnoreCase(messageType, AzureConnector.MessageType.COMMAND.name())) {
                try {
                    service.submit(() -> {
                        logDebug(method, "submitting command payload ...");
                        validateAndProcessEvent(getGatewayHid(), payload);
                    });
                } catch (Exception e) {
                    logError(method, e);
                }
            } else if (StringUtils.equalsIgnoreCase(messageType, AzureConnector.MessageType.API_RESPONSE.name())) {
                service.submit(() -> {
                    try {
                        CloudResponseModel responseModel = JsonUtils.fromJsonBytes(payload, CloudResponseModel.class);
                        logDebug(method, "responseModel: %s", JsonUtils.toJson(responseModel));

                        CloudResponseWrapper wrapper = responseMap.get(responseModel.getRequestId());
                        if (wrapper != null) {
                            logDebug(method, "marking request complete: %s", responseModel.getRequestId());
                            wrapper.complete(responseModel);
                        }
                    } catch (Exception e) {
                        logError(method, e);
                    }
                });
            } else {
                logError(method, "unsupported message type: %s", messageType);
            }
            return IotHubMessageResult.COMPLETE;
        }
    }

    class LocalEventCallback implements IotHubEventCallback {
        public void execute(IotHubStatusCode status, Object context) {
            String method = "eventCallback";
            Long counter = (Long) context;
            logDebug(method, "counter: %d, status: %s", counter, status.name());
        }
    }
}
