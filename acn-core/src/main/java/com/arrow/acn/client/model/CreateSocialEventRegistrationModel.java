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

public class CreateSocialEventRegistrationModel implements Serializable {
	private static final long serialVersionUID = 7677396417422603524L;
	
	private String socialEventHid;
	private String origPassword;
	private String origEmail;
	private String email;
	private String userHid;

	public String getSocialEventHid() {
		return socialEventHid;
	}

	public void setSocialEventHid(String socialEventHid) {
		this.socialEventHid = socialEventHid;
	}

	public String getOrigPassword() {
		return origPassword;
	}

	public void setOrigPassword(String origPassword) {
		this.origPassword = origPassword;
	}

	public String getOrigEmail() {
		return origEmail;
	}

	public void setOrigEmail(String origEmail) {
		this.origEmail = origEmail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserHid() {
		return userHid;
	}

	public void setUserHid(String userHid) {
		this.userHid = userHid;
	}
}
