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

import com.arrow.acs.client.model.ModelAbstract;

public class SocialEventRegistrationModel extends ModelAbstract<SocialEventRegistrationModel> {

	private static final long serialVersionUID = 605752316012531011L;

	private String applicationHid;
	private String socialEventHid;
	private String name;
	private String email;
	private String hashedPassword;
	private String salt;
	private SocialEventRegistrationStatuses status;
	private String createdDate;
	private String createdBy;
	private String lastModifiedDate;
	private String lastModifiedBy;
	private String origEmail;
	private String origEncryptedPassword;
	private String userHid;

	@Override
	protected SocialEventRegistrationModel self() {
		return this;
	}

	public SocialEventRegistrationModel withApplicationHid(String applicationHid) {
		setApplicationHid(applicationHid);
		return this;
	}

	public String getApplicationHid() {
		return applicationHid;
	}

	public void setApplicationHid(String applicationHid) {
		this.applicationHid = applicationHid;
	}
	
	public SocialEventRegistrationModel withStatus(SocialEventRegistrationStatuses status) {
		setStatus(status);
		return this;
	}


	public SocialEventRegistrationModel withName(String name) {
		setName(name);
		return this;
	}

	public SocialEventRegistrationStatuses getStatus() {
		return status;
	}

	public void setStatus(SocialEventRegistrationStatuses status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSocialEventHid() {
		return socialEventHid;
	}

	public void setSocialEventHid(String socialEventHid) {
		this.socialEventHid = socialEventHid;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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

	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public String getOrigEmail() {
		return origEmail;
	}

	public void setOrigEmail(String origEmail) {
		this.origEmail = origEmail;
	}

	public String getOrigEncryptedPassword() {
		return origEncryptedPassword;
	}

	public void setOrigEncryptedPassword(String origEncryptedPassword) {
		this.origEncryptedPassword = origEncryptedPassword;
	}

	public String getUserHid() {
		return userHid;
	}

	public void setUserHid(String userHid) {
		this.userHid = userHid;
	}	
}
