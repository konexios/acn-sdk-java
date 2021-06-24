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
package com.konexios.acn;

public interface AwsMqttConstants {
	final static String TELEMETRY_TOPIC = "konexios/telemetries/<gatewayHid>/<deviceHid>";
	final static String TELEMETRY_TOPIC_WILDCARD = "konexios/telemetries/+/+";
	final static String TELEMETRY_TOPIC_REGEX = "konexios/telemetries/.+/.+";

	static String telemetryTopic(String gatewayHid, String deviceHid) {
		return TELEMETRY_TOPIC.replace("<gatewayHid>", gatewayHid).replace("<deviceHid>", deviceHid);
	}

	final static String COMMAND_TOPIC = "konexios/commands/<gatewayHid>";
	final static String COMMAND_TOPIC_REGEX = "konexios/commands/.+";

	static String commandTopic(String gatewayHid) {
		return COMMAND_TOPIC.replace("<gatewayHid>", gatewayHid);
	}

	final static String API_REQUEST_TOPIC = "konexios/requests/<gatewayHid>";
	final static String API_REQUEST_TOPIC_WILDCARD = "konexios/requests/+";
	final static String API_REQUEST_TOPIC_REGEX = "konexios/requests/.+";

	static String apiRequestTopic(String gatewayHid) {
		return API_REQUEST_TOPIC.replace("<gatewayHid>", gatewayHid);
	}

	final static String API_RESPONSE_TOPIC = "konexios/responses/<gatewayHid>";
	final static String API_RESPONSE_TOPIC_REGEX = "konexios/responses/.+";

	static String apiResponseTopic(String gatewayHid) {
		return API_RESPONSE_TOPIC.replace("<gatewayHid>", gatewayHid);
	}

	final static String SHADOW_UPDATE_TOPIC = "$aws/things/<deviceHid>/shadow/update";
	final static String SHADOW_UPDATE_ACCEPTED_TOPIC_WILDCARD = "$aws/things/+/shadow/update/accepted";
	final static String SHADOW_UPDATE_ACCEPTED_TOPIC_REGEX = "\\$aws/things/.+/shadow/update/accepted";

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
