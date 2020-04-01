package com.arrow.acn;

public interface AwsMqttConstants {
	public final static String TELEMETRY_TOPIC = "konexios/telemetries/<gatewayHid>/<deviceHid>";
	public final static String COMMAND_TOPIC = "konexios/commands/<gatewayHid>";
	public final static String API_REQUEST_TOPIC = "konexios/requests/<gatewayHid>";
	public final static String API_RESPONSE_TOPIC = "konexios/responses/<gatewayHid>";

	public final static int QOS = 1;
}
