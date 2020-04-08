package com.arrow.acn;

public interface AwsMqttConstants {
	public final static String TELEMETRY_TOPIC = "konexios/telemetries/<gatewayHid>/<deviceHid>";
	public final static String COMMAND_TOPIC = "konexios/commands/<gatewayHid>";
	public final static String API_REQUEST_TOPIC = "konexios/requests/<gatewayHid>";
	public final static String API_RESPONSE_TOPIC = "konexios/responses/<gatewayHid>";

	public final static String SHADOW_UPDATE_TOPIC = "$aws/things/<deviceHid>/shadow/update";
	public final static String SHADOW_UPDATE_ACCEPTED_TOPIC = "$aws/things/<deviceHid>/shadow/update/accepted";
	public final static String SHADOW_UPDATE_DOCUMENTS_TOPIC = "$aws/things/<deviceHid>/shadow/update/documents";
	public final static String SHADOW_UPDATE_REJECTED_TOPIC = "$aws/things/<deviceHid>/shadow/update/rejected";
	public final static String SHADOW_UPDATE_DELTA_TOPIC = "$aws/things/<deviceHid>/shadow/update/delta";

	public final static String SHADOW_GET_TOPIC = "$aws/things/<deviceHid>/shadow/get";
	public final static String SHADOW_GET_ACCEPTED_TOPIC = "$aws/things/<deviceHid>/shadow/get/accepted";
	public final static String SHADOW_GET_REJECTED_TOPIC = "$aws/things/<deviceHid>/shadow/get/rejected";

	public final static String SHADOW_DELETE_TOPIC = "$aws/things/<deviceHid>/shadow/delete";
	public final static String SHADOW_DELETE_ACCEPTED_TOPIC = "$aws/things/<deviceHid>/shadow/delete/accepted";
	public final static String SHADOW_DELETE_REJECTED_TOPIC = "$aws/things/<deviceHid>/shadow/delete/rejected";

	public final static int QOS = 1;
}
