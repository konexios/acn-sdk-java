package com.arrow.acn.client;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.Before;
import org.junit.Test;

import com.arrow.acn.MqttConstants;
import com.arrow.acn.client.api.AcnClient;
import com.arrow.acn.client.cloud.CustomMqttClient;
import com.arrow.acn.client.cloud.MessageListener;
import com.arrow.acn.client.model.CreateGatewayModel;
import com.arrow.acn.client.model.CreateGatewayModel.GatewayType;
import com.arrow.acn.client.model.DeviceCommandModel;
import com.arrow.acn.client.model.DeviceModel;
import com.arrow.acn.client.model.DeviceRegistrationModel;
import com.arrow.acn.client.model.GatewayConfigModel;
import com.arrow.acn.client.model.GatewayModel;
import com.arrow.acn.client.search.DeviceSearchCriteria;
import com.arrow.acn.client.search.GatewaySearchCriteria;
import com.arrow.acn.client.utils.Utils;
import com.arrow.acs.AcsUtils;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.Loggable;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.model.ExternalHidModel;
import com.arrow.acs.client.model.GatewayEventModel;
import com.arrow.acs.client.model.HidModel;
import com.arrow.acs.client.model.KeyModel;
import com.arrow.acs.client.model.PagingResultModel;

public class RabbitLoadTest extends Loggable {

	private static class Config {
		public String apiUrl;
		public String mqttUrl;
		public String appHid;
		public String appApiKey;
		public String appSecretKey;
		public long clientCount;
		public int startCounter;
		public long clientDelay;
		public long statusCheckInterval;
		public int mqttKeepAliveInterval;
		public int mqttConnectionTimeout;
		public boolean useCustomClient;
		public boolean printMessage;
		public boolean sendCommand;
		public int commandSize;
		public long sendCommandInterval;
		public boolean sendTelemetry;
		public int telemetrySize;
		public long sendTelemetryInterval;
		public long runLoopCheckInterval;
		public boolean updateEventStatus;
		public boolean clientTimer;
		public long clientTimerCheckInterval;
		public long delayWarning;
	}

	static {
		System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
	}

	Map<String, String> statuses = new HashMap<>();
	long commandCounter;
	long commandTime;
	long commandFailed;
	long telemetryCounter;
	long telemetryFailed;
	AcnClient client;
	Config config;

	@Before
	public void init() throws Exception {
		config = JsonUtils.fromJson(
				AcsUtils.streamToString(getClass().getResourceAsStream("/RabbitLoadTest.json"), StandardCharsets.UTF_8),
				Config.class);
	}

	public synchronized AcnClient globalAcnClient() {
		if (client == null) {
			client = new AcnClient(new ApiConfig().withBaseUrl(config.apiUrl).withApiKey(config.appApiKey)
					.withSecretkey(config.appSecretKey));
		}
		return client;
	}

	@Test
	public void createGateways() {
		String method = "createGateways";
		int start = 0;
		int end = 2000;
		List<CreateGatewayModel> models = new ArrayList<>();
		for (int i = start; i < end; i++) {
			CreateGatewayModel model = new CreateGatewayModel();
			model.setApplicationHid(config.appHid);
			model.setDeviceType("test-gateway");
			String id = StringUtils.leftPad("" + i, 5, '0');
			model.setName("gateway-" + id);
			model.setOsName("test-os");
			model.setSdkVersion("1.0");
			model.setSoftwareName("test-software");
			model.setSoftwareVersion("1.0");
			model.setType(GatewayType.Local);
			model.setUid("gateway-" + id);
			models.add(model);
		}
		ExecutorService service = Executors.newFixedThreadPool(100);
		AcnClient client = globalAcnClient();
		for (CreateGatewayModel model : models) {
			service.execute(new Runnable() {
				@Override
				public void run() {
					try {
						ExternalHidModel result = client.getGatewayApi().registerNewGateway(model);
						System.out.println(
								Thread.currentThread().getName() + ": " + model.getUid() + " ---> " + result.getHid());
					} catch (Exception e) {
					}
				}
			});
		}
		service.shutdown();
		try {
			service.awaitTermination(4, TimeUnit.HOURS);
		} catch (InterruptedException e) {
		}
		logInfo(method, "TERMINATED!");
	}

	@Test
	public void createDevices() {
		String method = "createDevices";
		int page = 0;
		AcnClient client = globalAcnClient();
		List<DeviceRegistrationModel> models = new ArrayList<>();
		int start = 0;
		while (true) {
			PagingResultModel<GatewayModel> gateways = client.getGatewayApi()
					.findAllBy(new GatewaySearchCriteria().withPage(page).withSize(100));
			System.out.println(
					"page: " + page + ", size: " + gateways.getData().size() + " / " + gateways.getTotalSize());
			if (gateways.getSize() == 0)
				break;
			gateways.getData().forEach(g -> {
				if (g.getUid().startsWith("gateway-")) {
					try {
						int id = Integer.parseInt(g.getUid().split("-")[1]);
						if (id >= start) {
							DeviceRegistrationModel model = new DeviceRegistrationModel();
							model.setGatewayHid(g.getHid());
							model.setEnabled(true);
							model.setName(g.getName() + "-device");
							model.setType("test-device");
							model.setUid(g.getUid() + "-device");
							models.add(model);
						}
					} catch (Exception e) {
					}
				}
			});
			page++;
		}
		logInfo(method, "total size: %d", models.size());

		ExecutorService service = Executors.newFixedThreadPool(200);
		for (DeviceRegistrationModel model : models) {
			service.execute(new Runnable() {

				@Override
				public void run() {
					try {
						ExternalHidModel result = client.getDeviceApi().createOrUpdate(model);
						System.out.println(
								Thread.currentThread().getName() + ": " + model.getUid() + " ---> " + result.getHid());
					} catch (Exception e) {
					}
				}
			});
		}
		service.shutdown();
		try {
			service.awaitTermination(4, TimeUnit.HOURS);
		} catch (InterruptedException e) {
		}
		logInfo(method, "TERMINATED!");
	}

	@Test
	public void startSimulation() {
		String method = "startSimulation";
		AcnClient client = globalAcnClient();
		List<Thread> clients = new ArrayList<>();
		for (long i = 0; i < config.clientCount; i++) {
			String uid = "gateway-" + StringUtils.leftPad("" + (i + config.startCounter), 5, '0') + "-device";
			logInfo(method, "looking up gateway: %s", uid);
			List<DeviceModel> data = client.getDeviceApi().findAllBy(new DeviceSearchCriteria().withUid(uid)).getData();
			if (data.size() > 0) {
				try {
					DeviceModel device = data.get(0);
					clients.add(config.useCustomClient ? new CustomClient(device) : new StandardClient(device));
				} catch (Exception e) {
					logError(method, "unable to create client", e);
				}
			} else {
				logInfo(method, "device not found: %s", uid);
			}
		}

		logInfo(method, "clients size: %d", clients.size());
		clients.forEach(c -> {
			try {
				c.start();
				Utils.sleep(config.clientDelay);
			} catch (Exception e) {
				logError("unable to start client", e);
			}
		});

		Timer timer = new Timer(false);
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				printStatuses();
			}
		}, 0, config.statusCheckInterval);

		clients.forEach(c -> {
			try {
				c.join();
			} catch (InterruptedException e) {
			}
		});
	}

	synchronized void updateStatus(String uid, String status) {
		statuses.put(uid, status);
	}

	synchronized void updateCommandTime(long time) {
		commandCounter++;
		commandTime += time;
	}

	synchronized void updateCommandFailed() {
		commandFailed++;
	}

	synchronized void updateTelemetryCounter() {
		telemetryCounter++;
	}

	synchronized void updateTelemetryFailed() {
		telemetryFailed++;
	}

	synchronized void printStatuses() {
		String method = "printStatuses";
		Map<String, int[]> result = new HashMap<>();
		for (String value : statuses.values()) {
			int[] counter = result.get(value);
			if (counter == null) {
				result.put(value, new int[] { 1 });
			} else {
				counter[0]++;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (String key : result.keySet()) {
			if (sb.length() > 0)
				sb.append(", ");
			sb.append(key + "=" + result.get(key)[0]);
		}
		long average = commandCounter == 0 ? 0 : commandTime / commandCounter;
		logInfo(method,
				"command: %d, avgTime(ms): %d, commandFailed: %d, telemetry: %d, telemetryFailed: %d, statuses: %s",
				commandCounter, average, commandFailed, telemetryCounter, telemetryFailed, sb.toString());
		statuses.clear();
	}

	class CustomClient extends ClientAbstract {
		CustomMqttClient mqtt;

		CustomClient(DeviceModel device) {
			super(device);
		}

		@Override
		public void run() {
			String method = "run";
			info(method, "starting ...");
			try {
				mqtt = new CustomMqttClient(config.mqttUrl, gatewayHid);
				MqttConnectOptions options = createMqttOptions();
				options.setAutomaticReconnect(false);
				mqtt.setOptions(options);
				mqtt.setTopics(MqttConstants.serverToGatewayCommandRouting(gatewayHid));
				mqtt.setListener(new MessageListener() {
					@Override
					public void processMessage(String topic, byte[] payload) {
						doProcessMessage(topic, payload);
					}
				});
				mqtt.connect(false);
				mqtt.checkConnection();
				runLoop();
			} catch (Throwable t) {
				error(method, "client system error", t);
			}
			info(method, "terminated!");
		}

		@Override
		protected void sendTelemetry(String topic, String data) throws Exception {
			mqtt.publish(topic, data.getBytes(StandardCharsets.UTF_8), 1);
		}
	}

	class StandardClient extends ClientAbstract {
		MqttClient mqtt;

		StandardClient(DeviceModel device) {
			super(device);
		}

		@Override
		public void run() {
			String method = "run";
			info(method, "starting ...");
			try {
				mqtt = new MqttClient(config.mqttUrl, gatewayHid, new MemoryPersistence());
				MqttConnectOptions options = createMqttOptions();
				options.setAutomaticReconnect(false);
				mqtt.setCallback(new MqttCallbackExtended() {
					@Override
					public void messageArrived(String topic, MqttMessage message) throws Exception {
						doProcessMessage(topic, message.getPayload());
					}

					@Override
					public void deliveryComplete(IMqttDeliveryToken token) {
					}

					@Override
					public void connectionLost(Throwable cause) {
						String method = "connectionLost";
						info(method, "connection is lost!");
					}

					@Override
					public void connectComplete(boolean reconnect, String serverURI) {
						String method = "connectComplete";
						info(method, "reconnect: %s, serverURI: %s", reconnect, serverURI);
						try {
							mqtt.subscribe(MqttConstants.serverToGatewayCommandRouting(gatewayHid));
						} catch (MqttException e) {
						}
					}
				});
				mqtt.connect(options);

				if (config.clientTimer) {
					runTimer();
				} else {
					runLoop();
				}
				runLoop();
			} catch (Throwable t) {
				error(method, "client system error", t);
			}
			info(method, "terminated!");
		}

		@Override
		protected void sendTelemetry(String topic, String data) throws Exception {
			mqtt.publish(topic, data.getBytes(StandardCharsets.UTF_8), 1, false);
		}
	}

	abstract class ClientAbstract extends Thread {
		Loggable logger = new Loggable(RabbitLoadTest.class.getName()) {
		};
		DeviceModel device;
		AcnClient client;
		Map<Integer, Long> commandMap = new ConcurrentHashMap<Integer, Long>();
		AtomicInteger commandCounter = new AtomicInteger(0);
		Timer timer;
		String deviceUid = "";
		String gatewayHid = "";
		String randomTelemetry;
		String randomCommand;

		ClientAbstract(DeviceModel device) {
			String method = "ClientAbstract";
			this.device = device;
			this.deviceUid = device.getUid();
			this.gatewayHid = device.getGatewayHid();
			this.randomTelemetry = RandomStringUtils.randomAlphanumeric(config.telemetrySize);
			this.randomCommand = RandomStringUtils.randomAlphanumeric(config.commandSize);
			info(method, "downloading gateway info ...");
			GatewayConfigModel cfg = globalAcnClient().getGatewayApi()
					.downloadGatewayConfiguration(device.getGatewayHid());
			KeyModel key = cfg.getKey();
			client = new AcnClient(new ApiConfig().withBaseUrl(config.apiUrl).withApiKey(key.getApiKey())
					.withSecretkey(key.getSecretKey()));
			info(method, "created acnClient with new ApiKey: %s", key.getApiKey());
		}

		protected void doProcessMessage(String topic, byte[] payload) {
			String method = "processMessage";
			GatewayEventModel model = JsonUtils.fromJson(new String(payload, StandardCharsets.UTF_8),
					GatewayEventModel.class);
			Integer command = new Integer(model.getParameters().get("command"));
			if (config.printMessage) {
				info(method, "topic: %s, command: %s", topic, command);
			}

			if (config.updateEventStatus) {
				client.getCoreEventApi().putReceived(model.getHid());
				client.getCoreEventApi().putSucceeded(model.getHid());
			}

			Long time = commandMap.remove(command);
			if (time != null) {
				updateCommandTime(System.currentTimeMillis() - time);
			} else {
				error(method, "command not found, possible left-over or re-delivery: %d,", command);
			}
			int size = commandMap.size();
			if (size < 0) {
				error(method, "size: %d, command: %d", size, command);
			} else if (size == 0) {
				updateStatus(deviceUid, "OK");
			} else {
				updateStatus(deviceUid, "WARNING");
				String remaining = commandMap.keySet().stream().map(c -> c.toString()).collect(Collectors.joining(","));
				info(method, "remaining: %s", remaining, gatewayHid);
			}
		}

		protected void runLoop() {
			String method = "runLoop";
			info(method, "ready!");
			updateStatus(deviceUid, "OK");
			long nextSendCommand = 0;
			long nextSendTelemetry = 0;
			while (true) {
				long now = System.currentTimeMillis();
				try {
					updateStatuses();
					if (config.sendCommand && now >= nextSendCommand) {
						sendNextCommand();
						nextSendCommand = now + config.sendCommandInterval;
					}
					if (config.sendTelemetry && now >= nextSendTelemetry) {
						sendNextTelemetry();
						nextSendTelemetry = now + config.sendTelemetryInterval;
					}
					Utils.sleep(config.runLoopCheckInterval);
				} catch (Exception e) {
					error(method, "client system error", e);
				}
			}
		}

		protected void runTimer() {
			String method = "runTimer";
			info(method, "ready!");
			updateStatus(deviceUid, "OK");

			timer = new Timer(true);

			if (config.sendCommand) {
				timer.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
						sendNextCommand();
					}
				}, config.sendCommandInterval, config.sendCommandInterval);
			}

			if (config.sendTelemetry) {
				timer.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
						sendNextTelemetry();
					}
				}, config.sendTelemetryInterval, config.sendTelemetryInterval);
			}

			while (true) {
				updateStatuses();
				Utils.sleep(config.clientTimerCheckInterval);
			}
		}

		protected void sendNextCommand() {
			String method = "sendNextCommand";

			Integer command = commandCounter.getAndIncrement();
			Long ts = System.currentTimeMillis();
			commandMap.put(command, ts);

			boolean hasError = false;
			try {
				if (config.printMessage)
					info(method, "sending command %d", command);

				Map<String, String> payload = new HashMap<>();
				payload.put("ts", ts.toString());
				payload.put("data", randomCommand);
				HidModel response = client.getGatewayApi().sendCommandToGatewayAndDevice(device.getGatewayHid(),
						new DeviceCommandModel().withDeviceHid(device.getHid()).withCommand("" + command)
								.withPayload(JsonUtils.toJson(payload)).withMessageExpiration(300000L));
				hasError = AcsUtils.isEmpty(response.getHid());
				if (config.printMessage)
					info(method, "response eventHid: %s", response.getHid());
			} catch (Exception e) {
				error(method, "error sending command", e);
				hasError = true;
			} finally {
				if (hasError) {
					commandMap.remove(command);
					updateCommandFailed();
				}
			}
		}

		protected void updateStatuses() {
			String method = "updateStatuses";

			StringBuilder warning = new StringBuilder();
			long now = System.currentTimeMillis();
			for (Integer command : commandMap.keySet()) {
				Long ts = commandMap.get(command);
				if (ts != null) {
					long delay = now - ts;
					if (delay > config.delayWarning) {
						if (warning.length() > 0) {
							warning.append(", ");
						}
						warning.append(command);
						warning.append("/");
						warning.append(delay);
					}
				}
			}

			if (warning.length() > 0) {
				updateStatus(deviceUid, "WARNING");
				info(method, "warning: %s", warning.toString());
			} else {
				updateStatus(deviceUid, "OK");
			}
		}

		protected void sendNextTelemetry() {
			String method = "sendNextTelemetry";
			try {
				IotParameters params = new IotParameters();
				params.setDeviceHid(device.getHid());
				params.setString("data", randomTelemetry);
				sendTelemetry(MqttConstants.gatewayToServerTelemetryRouting(gatewayHid), JsonUtils.toJson(params));
				if (config.printMessage)
					info(method, "telemetry sent");
				updateTelemetryCounter();
			} catch (Exception e) {
				error(method, "error sending telemetry", e);
				updateTelemetryFailed();
			}
		}

		protected abstract void sendTelemetry(String topic, String data) throws Exception;

		protected MqttConnectOptions createMqttOptions() {
			MqttConnectOptions options = new MqttConnectOptions();

			options.setUserName(String.format("/pegasus:%s", gatewayHid));
			options.setPassword(client.getApiConfig().getApiKey().toCharArray());
			options.setCleanSession(false);
			options.setKeepAliveInterval(config.mqttKeepAliveInterval);
			options.setConnectionTimeout(config.mqttConnectionTimeout);
			return options;
		}

		public void info(String method, String message) {
			logger.logInfo(method, String.format("[%s] %s", deviceUid, message));
		}

		public void info(String method, String format, Object... args) {
			logger.logInfo(method, String.format("[%s] %s", deviceUid, format), args);
		}

		public void error(String method, String format, Object... args) {
			logger.logInfo(method, String.format("[%s] %s", deviceUid, format), args);
		}

		public void error(String method, String message, Throwable throwable) {
			logger.logError(method, String.format("[%s] %s", deviceUid, message), throwable);
		}
	}
}