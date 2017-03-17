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
package com.arrow.acn.client.cloud;

import java.util.List;

import com.arrow.acn.client.IotParameters;
import com.arrow.acs.Loggable;

public abstract class CloudConnectorAbstract extends Loggable {
	private String gatewayHid;

	protected String getGatewayHid() {
		return gatewayHid;
	}

	public void setGatewayHid(String gatewayHid) {
		this.gatewayHid = gatewayHid;
	}

	public abstract void setListener(MessageListener listener);

	public abstract void start();

	public abstract void stop();

	public abstract void send(IotParameters payload);

	public abstract void sendBatch(List<IotParameters> batch, TransferMode transferMode);
}
