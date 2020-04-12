package com.arrow.acn;

public interface AwsMqttConstants {
	final static String TELEMETRY_TOPIC = "konexios/telemetries/<gatewayHid>/<deviceHid>";
	final static String TELEMETRY_TOPIC_PREFIX_MATCH = "konexios/telemetries/";

	final static String COMMAND_TOPIC = "konexios/commands/<gatewayHid>";
	final static String COMMAND_TOPIC_PREFIX_MATCH = "konexios/commands/";

	final static String API_REQUEST_TOPIC = "konexios/requests/<gatewayHid>";
	final static String API_REQUEST_TOPIC_PREFIX_MATCH = "konexios/requests/";

	final static String API_RESPONSE_TOPIC = "konexios/responses/<gatewayHid>";
	final static String API_RESPONSE_TOPIC_PREFIX_MATCH = "konexios/responses/";

	final static String SHADOW_UPDATE_TOPIC = "$aws/things/<deviceHid>/shadow/update";
	final static String SHADOW_UPDATE_TOPIC_SUFFIX_MATCH = "/shadow/update";

	final static String SHADOW_UPDATE_ACCEPTED_TOPIC = "$aws/things/<deviceHid>/shadow/update/accepted";
	final static String SHADOW_UPDATE_ACCEPTED_TOPIC_SUFFIX_MATCH = "/shadow/update/accepted";

	final static String SHADOW_UPDATE_DOCUMENTS_TOPIC = "$aws/things/<deviceHid>/shadow/update/documents";
	final static String SHADOW_UPDATE_DOCUMENTS_TOPIC_SUFFIX_MATCH = "/shadow/update/documents";

	final static String SHADOW_UPDATE_REJECTED_TOPIC = "$aws/things/<deviceHid>/shadow/update/rejected";
	final static String SHADOW_UPDATE_REJECTED_TOPIC_SUFFIX_MATCH = "/shadow/update/rejected";

	final static String SHADOW_UPDATE_DELTA_TOPIC = "$aws/things/<deviceHid>/shadow/update/delta";
	final static String SHADOW_UPDATE_DELTA_TOPIC_SUFFIX_MATCH = "/shadow/update/delta";

	final static String SHADOW_GET_TOPIC = "$aws/things/<deviceHid>/shadow/get";
	final static String SHADOW_GET_TOPIC_SUFFIX_MATCH = "/shadow/get";

	final static String SHADOW_GET_ACCEPTED_TOPIC = "$aws/things/<deviceHid>/shadow/get/accepted";
	final static String SHADOW_GET_ACCEPTED_TOPI_SUFFIX_MATCH = "/shadow/get/accepted";

	final static String SHADOW_GET_REJECTED_TOPIC = "$aws/things/<deviceHid>/shadow/get/rejected";
	final static String SHADOW_GET_REJECTED_TOPIC_SUFFIX_MATCH = "/shadow/get/rejected";

	final static String SHADOW_DELETE_TOPIC = "$aws/things/<deviceHid>/shadow/delete";
	final static String SHADOW_DELETE_TOPIC_SUFFIX_MATCH = "/shadow/delete";

	final static String SHADOW_DELETE_ACCEPTED_TOPIC = "$aws/things/<deviceHid>/shadow/delete/accepted";
	final static String SHADOW_DELETE_ACCEPTED_TOPIC_SUFFIX_MATCH = "/shadow/delete/accepted";

	final static String SHADOW_DELETE_REJECTED_TOPIC = "$aws/things/<deviceHid>/shadow/delete/rejected";
	final static String SHADOW_DELETE_REJECTED_TOPIC_SUFFIX_MATCH = "/shadow/delete/rejected";

	final static int QOS = 1;
}
