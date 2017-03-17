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

public class NodeTypeRegistrationModel implements Serializable {
	private static final long serialVersionUID = -4297835378597209705L;

	private String name;
	private String description;
	private boolean enabled = true;

	public void trim() {
		name = StringUtils.trimToNull(name);
		description = StringUtils.trimToNull(description);
	}

	public NodeTypeRegistrationModel withName(String name) {
		setName(name);
		return this;
	}

	public NodeTypeRegistrationModel withDescription(String description) {
		setDescription(description);
		return this;
	}

	public NodeTypeRegistrationModel withEnabled(boolean enabled) {
		setEnabled(enabled);
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
