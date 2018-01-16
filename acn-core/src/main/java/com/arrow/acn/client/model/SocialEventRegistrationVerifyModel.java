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

public class SocialEventRegistrationVerifyModel implements Serializable {

	private static final long serialVersionUID = 605752316012531011L;

	private String applicationHid;
	private String tenantHid;
	private String userHid;

	public SocialEventRegistrationVerifyModel withApplicationHid(String applicationHid) {
		setApplicationHid(applicationHid);
		return this;
	}

	public String getApplicationHid() {
		return applicationHid;
	}

	public void setApplicationHid(String applicationHid) {
		this.applicationHid = applicationHid;
	}

	public String getCompanyHid() {
		return tenantHid;
	}

	public void setCompanyHid(String tenantHid) {
		this.tenantHid = tenantHid;
	}

	public String getUserHid() {
		return userHid;
	}

	public void setUserHid(String userHid) {
		this.userHid = userHid;
	}	
}
