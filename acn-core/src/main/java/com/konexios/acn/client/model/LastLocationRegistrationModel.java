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

import java.io.Serializable;

public class LastLocationRegistrationModel implements Serializable {
	private static final long serialVersionUID = 1912437041926487256L;

	private Double latitude;
	private Double longitude;
	private String locationType;

	public LastLocationRegistrationModel withLatitude(Double latitude) {
		setLatitude(latitude);
		return this;
	}

	public LastLocationRegistrationModel withLongitude(Double longitude) {
		setLongitude(longitude);
		return this;
	}

	public LastLocationRegistrationModel withLocationType(String locationType) {
		setLocationType(locationType);
		return this;
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
}
