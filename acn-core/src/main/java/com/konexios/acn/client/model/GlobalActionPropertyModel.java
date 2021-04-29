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

public class GlobalActionPropertyModel implements Serializable {
	private static final long serialVersionUID = -1881669763883357159L;

	private String parameterName;
	private String parameterType;
	private String parameterValue;

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getParameterType() {
		return parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
	
	public GlobalActionPropertyModel withParameterName(String parameterName) {
		setParameterName(parameterName);
		return this;
	}
	
	public GlobalActionPropertyModel withParameterValue(String parameterValue) {
		setParameterValue(parameterValue);
		return this;
	}
	
	public GlobalActionPropertyModel withParameterType(String parameterType) {
		setParameterType(parameterType);
		return this;
	}
}
