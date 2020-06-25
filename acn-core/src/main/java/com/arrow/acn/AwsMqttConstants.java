package com.arrow.acn;

public interface AwsMqttConstants {
	final static String TELEMETRY_TOPIC = "konexios/telemetries/<gatewayHid>/<deviceHid>";

	static String telemetryTopic(String gatewayHid, String deviceHid) {
		return TELEMETRY_TOPIC.replace("<gatewayHid>", gatewayHid).replace("<deviceHid>", deviceHid);
	}

	final static String COMMAND_TOPIC = "konexios/commands/<gatewayHid>";
	final static String COMMAND_TOPIC_REGEX = "konexios/commands/.+";

	static String commandTopic(String gatewayHid) {
		return COMMAND_TOPIC.replace("<gatewayHid>", gatewayHid);
	}

	final static String API_REQUEST_TOPIC = "konexios/requests/<gatewayHid>";

	static String apiRequestTopic(String gatewayHid) {
		return API_REQUEST_TOPIC.replace("<gatewayHid>", gatewayHid);
	}

	final static String API_RESPONSE_TOPIC = "konexios/responses/<gatewayHid>";
	final static String API_RESPONSE_TOPIC_REGEX = "konexios/responses/.+";

	static String apiResponseTopic(String gatewayHid) {
		return API_RESPONSE_TOPIC.replace("<gatewayHid>", gatewayHid);
	}

	final static String SHADOW_UPDATE_TOPIC = "$aws/things/<deviceHid>/shadow/update";

	static String shadowUpdateTopic(String deviceHid) {
		return SHADOW_UPDATE_TOPIC.replace("<deviceHid>", deviceHid);
	}

	final static String SHADOW_UPDATE_DELTA_TOPIC = "$aws/things/<deviceHid>/shadow/update/delta";
	final static String SHADOW_UPDATE_DELTA_TOPIC_REGEX = "\\$aws/things/.+/shadow/update/delta";

	static String shadowUpdateDeltaTopic(String deviceHid) {
		return SHADOW_UPDATE_DELTA_TOPIC.replace("<deviceHid>", deviceHid);
	}

	final static String DEVICE_DEFENDER_JSON_TOPIC = "$aws/things/<gatewayHid>/defender/metrics/json";
	final static String DEVICE_DEFENDER_JSON_RESPONSE_TOPIC = "$aws/things/<gatewayHid>/defender/metrics/json/+";
	final static String DEVICE_DEFENDER_JSON_RESPONSE_TOPIC_REGEX = "\\$aws/things/.+/defender/metrics/json/.+";

	static String deviceDefenderJsonTopic(String gatewayHid) {
		return DEVICE_DEFENDER_JSON_TOPIC.replace("<gatewayHid>", gatewayHid);
	}

	static String deviceDefenderJsonResponseTopic(String gatewayHid) {
		return DEVICE_DEFENDER_JSON_RESPONSE_TOPIC.replace("<gatewayHid>", gatewayHid);
	}

	final static String JOB_UPDATE_TOPIC = "$aws/things/<deviceHid>/jobs/<jobId>/update";
	final static String JOB_UPDATE_RESPONSE_TOPIC = "$aws/things/<deviceHid>/jobs/+/update/+";
	final static String JOB_UPDATE_RESPONSE_TOPIC_REGEX = "\\$aws/things/.+/jobs/.+/update/.+";

	static String jobUpdateTopic(String deviceHid, String jobId) {
		return JOB_UPDATE_TOPIC.replace("<deviceHid>", deviceHid).replace("<jobId>", jobId);
	}

	static String jobUpdateResponseTopic(String deviceHid) {
		return JOB_UPDATE_RESPONSE_TOPIC.replace("<deviceHid>", deviceHid);
	}

	final static String JOB_NOTIFY_NEXT_TOPIC = "$aws/things/<deviceHid>/jobs/notify-next";
	final static String JOB_NOTIFY_NEXT_TOPIC_REGEX = "\\$aws/things/.+/jobs/notify-next";

	static String jobNotifyNextTopic(String deviceHid) {
		return JOB_NOTIFY_NEXT_TOPIC.replace("<deviceHid>", deviceHid);
	}

	final static int QOS = 1;
}
