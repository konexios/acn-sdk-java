/*******************************************************************************
 * Copyright (c) 2018 Arrow Electronics, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License 2.0
 * which accompanies this distribution, and is available at
 * http://apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *     Arrow Electronics, Inc.
 *     Konexios, Inc.
 *******************************************************************************/
package com.arrow.acn.client.cloud.aws;

import java.io.StringReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

import com.arrow.acn.AwsMqttConstants;
import com.arrow.acn.client.IotParameters;
import com.arrow.acn.client.api.AcnClient;
import com.arrow.acn.client.cloud.CloudConnectorAbstract;
import com.arrow.acn.client.cloud.CustomMqttClient;
import com.arrow.acn.client.cloud.TransferMode;
import com.arrow.acn.client.cloud.aws.defender.DeviceDefenderReport;
import com.arrow.acn.client.cloud.aws.defender.DeviceDefenderReportResponse;
import com.arrow.acn.client.cloud.aws.job.JobExecutionPayload;
import com.arrow.acn.client.cloud.aws.job.JobExecutionUpdate;
import com.arrow.acn.client.cloud.aws.shadow.ShadowDelta;
import com.arrow.acn.client.cloud.aws.shadow.ShadowRequest;
import com.arrow.acn.client.model.DeviceStateRequestModel;
import com.arrow.acn.client.model.DeviceStateUpdateModel;
import com.arrow.acn.client.model.aws.ConfigModel;
import com.arrow.acn.client.utils.Utils;
import com.arrow.acs.AcsLogicalException;
import com.arrow.acs.AcsRuntimeException;
import com.arrow.acs.AcsUtils;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.client.api.MqttHttpChannel;
import com.arrow.acs.client.model.CloudRequestModel;
import com.arrow.acs.client.model.CloudResponseModel;

public class AwsConnector extends CloudConnectorAbstract implements MqttHttpChannel {

    private static final int DEFAULT_DEVICE_TOPICS_MONITOR_INTERVAL_SECS = 10;
    private static final int DEFAULT_CONNECTION_RETRY_INTERVAL_SECS = 10;
    private static final int DEFAULT_MESSAGE_RETRY_INTERVAL_SECS = 5;
    private static final int DEFAULT_PUBLISH_QOS = 1;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private final ConfigModel model;
    private CustomMqttClient client;
    private Set<String> deviceTopics = new HashSet<>();

    private SSLContext sslContext;
    private MqttConnectOptions mqttConnectOptions;

    private Timer deviceTopicsMonitorTimer;

    private int deviceTopicsMonitorIntervalSecs = DEFAULT_DEVICE_TOPICS_MONITOR_INTERVAL_SECS;
    private int connectionRetryIntervalSecs = DEFAULT_CONNECTION_RETRY_INTERVAL_SECS;
    private int messageRetryIntervalSecs = DEFAULT_MESSAGE_RETRY_INTERVAL_SECS;

    private AtomicBoolean connected = new AtomicBoolean(false);
    private AtomicBoolean connecting = new AtomicBoolean(false);

    private int publishQos = DEFAULT_PUBLISH_QOS;

    private Pattern commandTopicRegex = Pattern.compile(AwsMqttConstants.COMMAND_TOPIC_REGEX);
    private Pattern apiResponseTopicRegex = Pattern.compile(AwsMqttConstants.API_RESPONSE_TOPIC_REGEX);
    private Pattern shadowUpdateDeltaTopicRegex = Pattern.compile(AwsMqttConstants.SHADOW_UPDATE_DELTA_TOPIC_REGEX);
    private Pattern defenderResponseTopicRegex = Pattern
            .compile(AwsMqttConstants.DEVICE_DEFENDER_JSON_RESPONSE_TOPIC_REGEX);
    private Pattern jobNotifyNextTopicRegex = Pattern.compile(AwsMqttConstants.JOB_NOTIFY_NEXT_TOPIC_REGEX);
    private Pattern jobUpdateResponseTopicRegex = Pattern.compile(AwsMqttConstants.JOB_UPDATE_RESPONSE_TOPIC_REGEX);

    public AwsConnector(ConfigModel model, String gatewayHid, AcnClient acnClient) {
        super(acnClient);
        this.model = model;
        setGatewayHid(gatewayHid);
    }

    @Override
    public void start() {
        AcsUtils.notNull(model, "model is NULL");
        AcsUtils.notEmpty(getGatewayHid(), "gatewayHid is NULL");
        connectClient();
    }

    private void connectClient() {
        String method = "connectClient";

        if (!connecting.compareAndSet(false, true)) {
            logInfo(method, "connecting is already in progress ...");
        }

        while (true) {
            try {
                connected.set(false);
                closeClient();

                logInfo(method, "instantiating AWS client, host: %s, clientId: %s", model.getHost(), getGatewayHid());
                client = new CustomMqttClient("ssl://" + model.getHost() + ":8883", getGatewayHid());
                client.setOptions(getMqttConnectOptions());
                client.setListener(this::processMessage);

                logInfo(method, "connecting to endpoint: %s", model.getHost());
                client.connect(true);
                logInfo(method, "connected successfully!");

                String commandTopic = AwsMqttConstants.commandTopic(getGatewayHid());
                logInfo(method, "subscribing to commandTopic: %s", commandTopic);
                client.subscribe(commandTopic);

                String apiResponseTopic = AwsMqttConstants.apiResponseTopic(getGatewayHid());
                logInfo(method, "subscribing to apiResponseTopic: %s", apiResponseTopic);
                client.subscribe(apiResponseTopic);

                String deviceDefenderTopicResponse = AwsMqttConstants.deviceDefenderJsonResponseTopic(getGatewayHid());
                logInfo(method, "subscribing to deviceDefenderTopicResponse: %s", deviceDefenderTopicResponse);
                client.subscribe(deviceDefenderTopicResponse);

                // subscribe once
                subscribeDeviceTopics();

                // start timer to monitor new device
                startDeviceTopicsMonitorTimer();

                // route API calls to MQTT
                acnClient.setMqttHttpChannel(this);

                logInfo(method, "client is ready!");
                connected.set(true);

                break;
            } catch (Exception e) {
                logError(method, "Error connecting to AWS, retrying in " + connectionRetryIntervalSecs, e);
                Utils.sleep(connectionRetryIntervalSecs * 1000);
            }
        }
        connecting.set(false);
    }

    @Override
    public void stop() {
        super.stop();
        clearDeviceTopicsMonitorTimer();
        closeClient();
    }

    public void processMessage(String topic, byte[] payload) {
        String method = "processMessage";
        logInfo(method, "topic: %s, payload size: %d", topic, payload.length);

        if (commandTopicRegex.matcher(topic).matches()) {
            try {
                service.submit(() -> {
                    logInfo(method, "topic: %s", topic);
                    validateAndProcessEvent(topic, payload);
                });
            } catch (Exception e) {
                logError(method, e);
            }
        } else if (apiResponseTopicRegex.matcher(topic).matches()) {
            service.submit(() -> {
                try {
                    logInfo(method, "topic: %s, data size: %d", topic, payload.length);

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
        } else if (shadowUpdateDeltaTopicRegex.matcher(topic).matches()) {
            try {
                DeviceStateRequestModel request = JsonUtils.fromJsonBytes(payload, ShadowDelta.class).toRequestModel();
                logInfo(method, "topic: %s, request: %s", topic, JsonUtils.toJson(request));
                service.submit(() -> {
                    String deviceHid = StringUtils.split(topic, "/")[2];
                    receiveDeviceStateRequest(deviceHid, request);
                });
            } catch (Exception e) {
                logError(method, e);
            }
        } else if (defenderResponseTopicRegex.matcher(topic).matches()) {
            DeviceDefenderReportResponse response = JsonUtils.fromJsonBytes(payload,
                    DeviceDefenderReportResponse.class);
            if (response.getStatus().equalsIgnoreCase("REJECTED")) {
                String reason = response.getStatusDetails() == null ? "UNKNOWN"
                        : JsonUtils.toJson(response.getStatusDetails());
                logError(method, "device defender report rejected, reason: %s", reason);
            } else {
                logInfo(method, "device defender report accepted, id: %s", response.getReportId());
            }
        } else if (jobNotifyNextTopicRegex.matcher(topic).matches()) {
            try {
                JobExecutionPayload job = JsonUtils.fromJsonBytes(payload, JobExecutionPayload.class);
                logInfo(method, "received new job: %s", JsonUtils.toJson(job));
                service.submit(() -> {
                    String deviceHid = StringUtils.split(topic, "/")[2];
                    awsJobNotifyNext(deviceHid, job);
                });
            } catch (Exception e) {
                logError(method, e);
            }
        } else if (jobUpdateResponseTopicRegex.matcher(topic).matches()) {
            logInfo(method, "jobUpdateResponse: %s", new String(payload, StandardCharsets.UTF_8));
        } else {
            logError(method, "UNSUPPORTED topic: %s", topic);
        }
    }

    @Override
    public void send(IotParameters payload) {
        String method = "send";
        if (!terminating && checkConnection()) {
            try {
                byte[] data = JsonUtils.toJsonBytes(payload);
                String topic = AwsMqttConstants.telemetryTopic(getGatewayHid(), payload.getDeviceHid());
                logDebug(method, "sending %d bytes to topic: %s", data.length, topic);
                client.publish(topic, data, publishQos);
            } catch (AcsRuntimeException e) {
                throw e;
            } catch (Throwable t) {
                throw new AcsRuntimeException("unable to send data to AWS", t);
            }
        }
    }

    @Override
    public void sendBatch(List<IotParameters> batch, TransferMode transferMode) {
        if (!terminating && checkConnection()) {
            if (transferMode == TransferMode.GZIP_BATCH) {
                throw new AcsLogicalException(
                        "TransferMode not supported for AWS integration: " + TransferMode.GZIP_BATCH.name());
            }
            if (batch != null && batch.size() > 0) {
                batch.forEach(this::send);
            }
        }
    }

    @Override
    public CloudResponseModel sendRequest(CloudRequestModel request, long timeoutSecs) {
        String method = "sendRequest";
        if (!terminating && checkConnection()) {
            try {
                byte[] data = JsonUtils.toJsonBytes(request);
                String topic = AwsMqttConstants.apiRequestTopic(getGatewayHid());

                CloudResponseWrapper wrapper = new CloudResponseWrapper();
                responseMap.put(request.getRequestId(), wrapper);

                logDebug(method, "sending %d bytes to topic: %s", data.length, topic);
                client.publish(topic, data, publishQos);

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

    public void sendDeviceDefenderReport(DeviceDefenderReport report) {
        String method = "sendDeviceDefenderReport";
        if (!terminating && checkConnection()) {
            try {
                byte[] data = JsonUtils.toJsonBytes(report);
                String topic = AwsMqttConstants.deviceDefenderJsonTopic(getGatewayHid());
                logDebug(method, "sending %d bytes to topic: %s", data.length, topic);
                client.publish(topic, data, publishQos);
            } catch (AcsRuntimeException e) {
                throw e;
            } catch (Throwable t) {
                throw new AcsRuntimeException("unable to send data to AWS", t);
            }
        }
    }

    @Override
    protected void sendDeviceStateUpdate(String deviceHid, DeviceStateUpdateModel model) {
        String method = "sendDeviceStateUpdate";
        if (!terminating) {
            checkConnection();
            try {
                String topic = AwsMqttConstants.shadowUpdateTopic(deviceHid);
                ShadowRequest update = ShadowRequest.fromUpdateModel(model);
                logDebug(method, "sending shadow update to topic: %s, data: %s", topic, JsonUtils.toJson(update));
                client.publish(topic, JsonUtils.toJsonBytes(update), publishQos);
            } catch (AcsRuntimeException e) {
                logError(method, e);
                throw e;
            } catch (Exception e) {
                logError(method, e);
                throw new AcsLogicalException("sendDeviceStateUpdate failed", e);
            }
        }
    }

    @Override
    protected void sendAwsJobUpdate(String deviceHid, String jobId, JobExecutionUpdate update) {
        String method = "sendAwsJobUpdate";
        if (!terminating) {
            checkConnection();
            try {
                String topic = AwsMqttConstants.jobUpdateTopic(deviceHid, jobId);
                logDebug(method, "sending job update to topic: %s, data: %s", topic, JsonUtils.toJson(update));
                client.publish(topic, JsonUtils.toJsonBytes(update), publishQos);
            } catch (AcsRuntimeException e) {
                logError(method, e);
                throw e;
            } catch (Exception e) {
                logError(method, e);
                throw new AcsLogicalException("sendDeviceStateUpdate failed", e);
            }
        }
    }

    private boolean checkConnection() {
        String method = "checkConnection";
        while (!connected.get() && !terminating) {
            logWarn(method, "not connected, will check back later ...");
            Utils.sleep(messageRetryIntervalSecs * 1000);
        }
        return connected.get();
    }

    private synchronized MqttConnectOptions getMqttConnectOptions() {
        if (mqttConnectOptions == null) {
            mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setAutomaticReconnect(false);
            mqttConnectOptions.setCleanSession(false);
            mqttConnectOptions.setSocketFactory(getSslContext().getSocketFactory());
        }
        return mqttConnectOptions;
    }

    private synchronized SSLContext getSslContext() {
        String method = "getSslContext";
        if (sslContext == null) {
            try {
                String keystorePassword = new BigInteger(128,
                        new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes())).toString(32);

                KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
                keystore.load(null, null);

                // load CA cert
                logInfo(method, "loading CA cert ...");
                X509Certificate caCert = new JcaX509CertificateConverter()
                        .setProvider(BouncyCastleProvider.PROVIDER_NAME)
                        .getCertificate((X509CertificateHolder) new PEMParser(new StringReader(model.getCaCert()))
                                .readObject());
                keystore.setCertificateEntry("ca-cert", caCert);

                // load client cert
                logInfo(method, "loading client cert ...");
                X509Certificate clientCert = new JcaX509CertificateConverter()
                        .setProvider(BouncyCastleProvider.PROVIDER_NAME)
                        .getCertificate((X509CertificateHolder) new PEMParser(new StringReader(model.getClientCert()))
                                .readObject());
                keystore.setCertificateEntry("client-cert", clientCert);

                logInfo(method, "loading private key ...");
                PrivateKey privateKey = new JcaPEMKeyConverter().setProvider(BouncyCastleProvider.PROVIDER_NAME)
                        .getPrivateKey(
                                ((PEMKeyPair) new PEMParser(new StringReader(model.getPrivateKey())).readObject())
                                        .getPrivateKeyInfo());
                keystore.setKeyEntry("private-key", privateKey, keystorePassword.toCharArray(),
                        new Certificate[] { clientCert });

                // dummy trust manager
                TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                } };

                KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                kmf.init(keystore, keystorePassword.toCharArray());

                sslContext = SSLContext.getInstance("TLSv1.2");
                sslContext.init(kmf.getKeyManagers(), trustAllCerts, new SecureRandom());
            } catch (Throwable e) {
                logError(method, e);
                throw new AcsLogicalException("unable to prepare keystore", e);
            }
        }
        return sslContext;
    }

    private void startDeviceTopicsMonitorTimer() {
        String method = "startDeviceTopicsMonitorTimer";

        clearDeviceTopicsMonitorTimer();

        deviceTopicsMonitorTimer = new Timer(true);
        deviceTopicsMonitorTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                subscribeDeviceTopics();
            }
        }, 0L, deviceTopicsMonitorIntervalSecs * 1000);
        logDebug(method, "started!");
    }

    private void clearDeviceTopicsMonitorTimer() {
        if (deviceTopicsMonitorTimer != null) {
            deviceTopicsMonitorTimer.cancel();
            deviceTopicsMonitorTimer = null;
        }
        deviceTopics.clear();
    }

    private void closeClient() {
        if (client != null) {
            try {
                client.disconnect();
            } catch (Exception e) {
            } finally {
                client = null;
            }
        }
    }

    private void subscribeDeviceTopics() {
        String method = "subscribeDeviceTopics";
        getDeviceHids().forEach(deviceHid -> {
            try {
                subscribeDeviceTopic(AwsMqttConstants.shadowUpdateDeltaTopic(deviceHid));
                subscribeDeviceTopic(AwsMqttConstants.jobNotifyNextTopic(deviceHid));
                subscribeDeviceTopic(AwsMqttConstants.jobUpdateResponseTopic(deviceHid));
            } catch (Exception e) {
                logError(method, e);
            }
        });
    }

    private void subscribeDeviceTopic(String topic) {
        String method = "subscribeDeviceTopic";
        if (deviceTopics.add(topic)) {
            logInfo(method, "subscribing to device topic: %s", topic);
            client.subscribe(topic);
        }
    }

    public int getShadowRequestMonitorIntervalSecs() {
        return deviceTopicsMonitorIntervalSecs;
    }

    public void setShadowRequestMonitorIntervalSecs(int shadowRequestMonitorIntervalSecs) {
        this.deviceTopicsMonitorIntervalSecs = shadowRequestMonitorIntervalSecs;
    }

    public int getConnectionRetryIntervalSecs() {
        return connectionRetryIntervalSecs;
    }

    public void setConnectionRetryIntervalSecs(int connectionRetryIntervalSecs) {
        this.connectionRetryIntervalSecs = connectionRetryIntervalSecs;
    }

    public int getMessageRetryIntervalSecs() {
        return messageRetryIntervalSecs;
    }

    public void setMessageRetryIntervalSecs(int messageRetryIntervalSecs) {
        this.messageRetryIntervalSecs = messageRetryIntervalSecs;
    }

    public int getPublishQos() {
        return publishQos;
    }

    public void setPublishQos(int publishQos) {
        this.publishQos = publishQos;
    }
}
