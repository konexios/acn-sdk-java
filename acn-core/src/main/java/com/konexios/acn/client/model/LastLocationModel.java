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
package com.konexios.acn.client.model;

import com.konexios.acs.client.model.ModelAbstract;

public class LastLocationModel extends ModelAbstract<LastLocationModel> {
	private static final long serialVersionUID = 1431304627786033124L;

	private String objectType;
	private String objectHid;
	private Double latitude;
	private Double longitude;
	private String locationType;
	private String timestamp;

	@Override
	protected LastLocationModel self() {
		return this;
	}

	public LastLocationModel withObjectType(String objectType) {
		setObjectType(objectType);
		return this;
	}

	public LastLocationModel withObjectHid(String objectHid) {
		setObjectHid(objectHid);
		return this;
	}

	public LastLocationModel withLatitude(Double latitude) {
		setLatitude(latitude);
		return this;
	}

	public LastLocationModel withLongitude(Double longitude) {
		setLongitude(longitude);
		return this;
	}

	public LastLocationModel withLocationType(String locationType) {
		setLocationType(locationType);
		return this;
	}

	public LastLocationModel withTimestamp(String timestamp) {
		setTimestamp(timestamp);
		return this;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getObjectHid() {
		return objectHid;
	}

	public void setObjectHid(String objectHid) {
		this.objectHid = objectHid;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
