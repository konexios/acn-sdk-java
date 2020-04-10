package com.arrow.acn.client.cloud.aws;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;

import com.arrow.acn.client.model.DeviceStateRequestModel;
import com.arrow.acn.client.model.DeviceStateUpdateModel;

public class ShadowRequest implements Serializable {
	private static final long serialVersionUID = 1626960821194814264L;

	private ShadowState state;
	private ShadowMetadata metadata;
	private Integer version;
	private Long timestamp;

	public static ShadowRequest fromUpdateModel(DeviceStateUpdateModel model) {
		ShadowRequest result = new ShadowRequest();

		result.setState(new ShadowState().withReported(model.getStates()));

		ShadowMetadata metadata = new ShadowMetadata().withReported(new HashMap<>());
		long timestamp = Instant.parse(model.getTimestamp()).toEpochMilli();
		model.getStates().forEach((name, value) -> {
			metadata.getReported().put(name, new ShadowTimestamp().withTimestamp(timestamp));
		});

		result.setMetadata(metadata);

		return result;
	}

	public static ShadowRequest fromRequestModel(DeviceStateRequestModel model) {
		ShadowRequest result = new ShadowRequest();

		result.setState(new ShadowState().withDesired(model.getStates()));

		ShadowMetadata metadata = new ShadowMetadata().withDesired(new HashMap<>());
		long timestamp = Instant.parse(model.getTimestamp()).toEpochMilli();
		model.getStates().forEach((name, value) -> {
			metadata.getDesired().put(name, new ShadowTimestamp().withTimestamp(timestamp));
		});

		result.setMetadata(metadata);

		return result;
	}

	public DeviceStateRequestModel toRequestModel() {
		DeviceStateRequestModel result = new DeviceStateRequestModel();
		if (state.getDelta() != null) {
			result.setStates(state.getDelta());
		}

		if (timestamp != null) {
			result.setTimestamp(Instant.ofEpochSecond(timestamp).toString());
		} else {
			result.setTimestamp(Instant.now().toString());
		}
		return result;
	}

	public DeviceStateUpdateModel toUpdateModel() {
		DeviceStateUpdateModel result = new DeviceStateUpdateModel();
		if (state.getReported() != null) {
			result.setStates(state.getReported());
		}

		if (timestamp != null) {
			result.setTimestamp(Instant.ofEpochSecond(timestamp).toString());
		} else {
			result.setTimestamp(Instant.now().toString());
		}
		return result;
	}

	public ShadowState getState() {
		return state;
	}

	public void setState(ShadowState state) {
		this.state = state;
	}

	public ShadowMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(ShadowMetadata metadata) {
		this.metadata = metadata;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
