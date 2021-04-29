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

public class ParameterValidationModel implements Serializable {
	private static final long serialVersionUID = -5853957566950403385L;
	
	public enum ValidationType {
		STRING("String"), MULTILINE_STRING("Multiline string"), SELECT("Select"), JSON("JSON"), XML("XML"), HTML(
		        "HTML"), KEY_VALUE_PAIRS("Key-value pairs"), EMAIL("E-mail");

		private String value;

		private ValidationType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public String getName() {
			return name();
		}

		public static ValidationType fromValue(String value) {
			if (value != null) {
				for (ValidationType item : ValidationType.values()) {
					if (item.getValue().equals(value)) {
						return item;
					}
				}
			}
			throw new IllegalArgumentException("Unsupported AttributeType");
		}
	}

	private ValidationType type = ValidationType.STRING;
	private String defaultValue;
	private String data;
	
	public ParameterValidationModel() {}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public ValidationType getType() {
		return type;
	}

	public void setType(ValidationType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((defaultValue == null) ? 0 : defaultValue.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ParameterValidationModel other = (ParameterValidationModel) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (defaultValue == null) {
			if (other.defaultValue != null)
				return false;
		} else if (!defaultValue.equals(other.defaultValue))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}
