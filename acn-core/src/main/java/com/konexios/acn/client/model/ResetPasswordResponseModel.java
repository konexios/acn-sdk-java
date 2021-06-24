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

import com.konexios.acs.client.model.StatusModelAbstract;

public class ResetPasswordResponseModel extends StatusModelAbstract<ResetPasswordResponseModel> {
	private static final long serialVersionUID = 7682145252516691981L;

	private String temporaryPassword;

	public ResetPasswordResponseModel withTemporaryPassword(String temporaryPassword) {
		setTemporaryPassword(temporaryPassword);
		return this;
	}

	public String getTemporaryPassword() {
		return temporaryPassword;
	}

	public void setTemporaryPassword(String temporaryPassword) {
		this.temporaryPassword = temporaryPassword;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((temporaryPassword == null) ? 0 : temporaryPassword.hashCode());
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
		ResetPasswordResponseModel other = (ResetPasswordResponseModel) obj;
		if (temporaryPassword == null) {
			if (other.temporaryPassword != null)
				return false;
		} else if (!temporaryPassword.equals(other.temporaryPassword))
			return false;
		return true;
	}

	@Override
	protected ResetPasswordResponseModel self() {
		return this;
	}
}
