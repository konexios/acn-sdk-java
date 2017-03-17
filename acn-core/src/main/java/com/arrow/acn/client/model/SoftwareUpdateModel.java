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

import org.apache.commons.lang3.StringUtils;

public class SoftwareUpdateModel implements Serializable {
	private static final long serialVersionUID = -8219969834574648854L;

	private String url;

	public void trim() {
		url = StringUtils.trimToNull(url);
	}

	public SoftwareUpdateModel withUrl(String url) {
		setUrl(url);
		return this;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
