package com.arrow.acn.client;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.Before;
import org.junit.Test;

import com.arrow.acn.client.api.AcnClient;
import com.arrow.acn.client.api.DeviceTelemetryClient;
import com.arrow.acn.client.api.NodeTelemetryClient;
import com.arrow.acn.client.websocket.MessageListener;
import com.arrow.acn.client.websocket.WebSocketSubscription;
import com.arrow.acs.client.api.ApiConfig;

public class TestTelemetrySubscription {

    private AcnClient acnClient;

    private MessageListener messageListener = new MessageListener() {
        @Override
        public void onMessage(String message) {
            System.out.println(message);
        }
    };

    @Before
    public void init() {
        ApiConfig apiConfig = new ApiConfig().withBaseWebSocketUrl("ws://localhost:8080").withApiKey("key")
                .withSecretkey("key");
        acnClient = new AcnClient(apiConfig);
    }

    @Test
    public void testDeviceTelemetry() throws Exception {
        DeviceTelemetryClient client = acnClient.getDeviceTelemetryClient();
        client.setMessageListener(messageListener);
        try {
            client.start();
            WebSocketSubscription sub1 = client.subscribe("55fe32c8d6a35b313c4f260f2d2a8f8a3ccaf078", "*");
            WebSocketSubscription sub2 = client.subscribe("cffe2dad488d780c20e7033799a69c9f6dba8643", "*");
            new Thread(() -> { // wait 1 min and close sub1
                try {
                    Thread.sleep(60000);
                    sub1.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
            while (sub1.isOpen() || sub2.isOpen()) {
                Thread.sleep(5000);
            }
        } finally {
            client.stop();
        }
    }

    @Test
    public void testNodeTelemetry() throws Exception {
        NodeTelemetryClient client = acnClient.getNodeTelemetryClient();
        client.setMessageListener(messageListener);
        try {
            client.start();
            WebSocketSubscription subscription = client.subscribe("e17918f2141c7865b71985ac8ce88270cc30efd7", "*");
            Thread.sleep(60000);
            subscription.close();
        } finally {
            client.stop();
        }
    }

    @Test
    public void testPetnet() {
        try {
            MqttClient client = new MqttClient("ssl://mqtt-a01-test.arrowconnect.io",
                    "ac09f4c538456942cfb8b598e6641bdd0374a6b8");
            client.setCallback(new MqttCallback() {
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("messageArrived");
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("deliveryComplete");
                }

                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("connection lost: " + cause.getMessage());
                }
            });
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setUserName("/pegasus:ac09f4c538456942cfb8b598e6641bdd0374a6b8");
            options.setPassword("0dccd55653ecf1e1706edd80147b920071fe2bf7a50b6ee84483f1170c28a62a".toCharArray());
            options.setConnectionTimeout(15000);
            options.setAutomaticReconnect(true);
            client.connect(options);
            System.out.println("CONNECTED!");
            client.subscribe("krs/cmd/stg/ac09f4c538456942cfb8b598e6641bdd0374a6b8", 1);
            System.out.println("SUBSCRIBED");
            client.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
