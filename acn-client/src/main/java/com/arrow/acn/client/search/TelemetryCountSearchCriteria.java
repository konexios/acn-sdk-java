package com.arrow.acn.client.search;

public class TelemetryCountSearchCriteria extends TimestampSearchCriteria {
	private static final String TELEMETRY_NAME = "telemetryName";

	public TelemetryCountSearchCriteria withTelemetryName(String telemetryName) {
		simpleCriteria.put(TELEMETRY_NAME, telemetryName);
		return this;
	}
}
