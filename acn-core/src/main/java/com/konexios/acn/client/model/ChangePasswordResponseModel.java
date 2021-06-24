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

import java.util.List;

import com.konexios.acs.client.model.StatusModelAbstract;

public class ChangePasswordResponseModel extends StatusModelAbstract<ChangePasswordResponseModel> {
	private static final long serialVersionUID = 3763025496512207366L;

	private List<String> errorMessages;

	@Override
	protected ChangePasswordResponseModel self() {
		return this;
	}

	public ChangePasswordResponseModel withErrorMessages(List<String> errorMessages) {
		setErrorMessages(errorMessages);
		return this;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((errorMessages == null) ? 0 : errorMessages.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChangePasswordResponseModel other = (ChangePasswordResponseModel) obj;
		if (errorMessages == null) {
			if (other.errorMessages != null)
				return false;
		} else if (!errorMessages.equals(other.errorMessages))
			return false;
		return true;
	}
}
