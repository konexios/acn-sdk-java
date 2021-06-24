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

import java.util.HashMap;
import java.util.Map;

import com.konexios.acs.client.model.TsModelAbstract;

public class AuditLogModel extends TsModelAbstract<AuditLogModel> {

	private static final long serialVersionUID = 4738822771495190726L;

	private String productName;
	private String type;
	private String objectHid;
	private Map<String, String> parameters = new HashMap<>();

	@Override
	protected AuditLogModel self() {
		return this;
	}

	public AuditLogModel withProductName(String productName) {
		setProductName(productName);
		return this;
	}

	public AuditLogModel withType(String type) {
		setType(type);
		return this;
	}

	public AuditLogModel withObjectHid(String objectHid) {
		setObjectHid(objectHid);
		return this;
	}

	public AuditLogModel withCreatedDate(String createdDate) {
		setCreatedBy(createdDate);
		return this;
	}

	public AuditLogModel withCreatedBy(String createdBy) {
		setCreatedBy(createdBy);
		return this;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getObjectHid() {
		return objectHid;
	}

	public void setObjectHid(String objectHid) {
		this.objectHid = objectHid;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}
}
