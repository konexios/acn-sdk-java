/*******************************************************************************
 * Copyright (c) 2018 Arrow Electronics, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License 2.0
 * which accompanies this distribution, and is available at
 * http://apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *     Arrow Electronics, Inc.
 *******************************************************************************/
package com.arrow.acn.client.model;

public class GlobalActionTypeModel extends GlobalActionTypeDetailsModel{
	private static final long serialVersionUID = 4288725444616929749L;
	
	private String hid;
	private String applicationHid;
	
	public String getHid() {
		return hid;
	}
	
	public void setHid(String hid) {
		this.hid = hid;
	}
	
	public String getApplicationHid() {
		return applicationHid;
	}
	
	public void setApplicationHid(String applicationHid) {
		this.applicationHid = applicationHid;
	}
	
}

