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

public class TestResultObjectModel implements Serializable {

	private static final long serialVersionUID = 9166997825674356939L;

	private String hid;
	private String testProcedureHid;

	public String getHid() {
		return hid;
	}

	public void setHid(String objectHid) {
		this.hid = objectHid;
	}

	public String getTestProcedureHid() {
		return testProcedureHid;
	}

	public void setTestProcedureHid(String testProcedureHid) {
		this.testProcedureHid = testProcedureHid;
	}
}
