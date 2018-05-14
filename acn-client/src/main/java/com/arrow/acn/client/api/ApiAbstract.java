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
package com.arrow.acn.client.api;

import java.time.Instant;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;

import com.arrow.acn.AcnEventNames;
import com.arrow.acs.AcsUtils;
import com.arrow.acs.ApiHeaders;
import com.arrow.acs.ApiRequestSigner;
import com.arrow.acs.GatewayPayloadSigner;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.model.CloudMqttRequestParams;
import com.arrow.acs.client.model.CloudRequestMethodName;
import com.arrow.acs.client.model.CloudRequestModel;
import com.arrow.acs.client.model.CloudRequestParameters;
import com.arrow.acs.client.search.SearchCriteria;

public abstract class ApiAbstract extends com.arrow.acs.client.api.ApiAbstract {
	protected static final String API_BASE = "/api/v1/kronos";

	ApiAbstract(ApiConfig apiConfig) {
		super();
		setApiConfig(apiConfig);
	}

}
