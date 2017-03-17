/*******************************************************************************
 * Copyright (c) 2017 Arrow Electronics, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License 2.0
 * which accompanies this distribution, and is available at
 * http://apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *     Arrow Electronics, Inc.
 *******************************************************************************/
package com.arrow.acn.client.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AuditLogModel implements Serializable {
	private static final long serialVersionUID = -7499085226611672171L;

	private String productName;
	private String type;
	private String objectHid;
	private String createdDate;
	private String createdBy;
	private Map<String, String> parameters = new HashMap<>();

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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}
}
