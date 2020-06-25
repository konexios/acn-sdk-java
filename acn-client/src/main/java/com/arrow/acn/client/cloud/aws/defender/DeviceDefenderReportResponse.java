package com.arrow.acn.client.cloud.aws.defender;

import java.util.Map;

public class DeviceDefenderReportResponse {
	private String thingName;
	private Long reportId;
	private String status;
	private Map<String, String> statusDetails;
	private Long timestamp;

	public String getThingName() {
		return thingName;
	}

	public void setThingName(String thingName) {
		this.thingName = thingName;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map<String, String> getStatusDetails() {
		return statusDetails;
	}

	public void setStatusDetails(Map<String, String> statusDetails) {
		this.statusDetails = statusDetails;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}
