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
package com.konexios.acn.client.cloud.aws.shadow;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;

import com.konexios.acn.client.model.DeviceStateRequestModel;
import com.konexios.acn.client.model.DeviceStateUpdateModel;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((metadata == null) ? 0 : metadata.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShadowRequest other = (ShadowRequest) obj;
		if (metadata == null) {
			if (other.metadata != null)
				return false;
		} else if (!metadata.equals(other.metadata))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
}
