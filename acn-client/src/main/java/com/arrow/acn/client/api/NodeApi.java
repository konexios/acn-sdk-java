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
package com.arrow.acn.client.api;

import java.net.URI;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import com.arrow.acn.client.AcnClientException;
import com.arrow.acn.client.model.NodeModel;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.model.HidModel;
import com.arrow.acs.client.model.ListResultModel;
import com.fasterxml.jackson.core.type.TypeReference;

public final class NodeApi extends ApiAbstract {
	private final String NODES_BASE_URL = API_BASE + "/nodes";
	private final String SPECIFIC_NODE_URL = NODES_BASE_URL + "/{hid}";
	private final TypeReference<ListResultModel<NodeModel>> NODE_MODEL_TYPE_REF = new TypeReference<ListResultModel<NodeModel>>() {
	};

	NodeApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	/**
	 * Sends GET request to obtain parameters of all available nodes
	 *
	 * @return list of {@link NodeModel} containing node parameters
	 *
	 * @throws AcnClientException
	 *             if request failed
	 */
	public ListResultModel<NodeModel> listExistingNodes() {
		String method = "listExistingNodes";
		try {
			URI uri = buildUri(NODES_BASE_URL);
			ListResultModel<NodeModel> result = execute(new HttpGet(uri), NODE_MODEL_TYPE_REF);
			logDebug(method, "size: %s", result.getSize());
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	/**
	 * Sends POST request to create new node according to {@code model} passed
	 * 
	 * @param model
	 *            {@link NodeModel} representing parameters of node to be
	 *            created
	 *
	 * @return {@link HidModel} containing {@code hid} of node created
	 *
	 * @throws AcnClientException
	 *             if request failed
	 */
	public HidModel createNewNode(NodeModel model) {
		String method = "createNewNode";
		try {
			URI uri = buildUri(NODES_BASE_URL);
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	/**
	 * Sends PUT request to update existing node according to {@code model}
	 * passed
	 *
	 * @param hid
	 *            {@link String} representing {@code hid} of node to be updated
	 * @param model
	 *            {@link NodeModel} representing node parameters to be updated
	 *
	 * @return {@link HidModel} containing {@code hid} of node updated
	 *
	 * @throws AcnClientException
	 *             if request failed
	 */
	public HidModel updateExistingNode(String hid, NodeModel model) {
		String method = "updateExistingNode";
		try {
			URI uri = buildUri(SPECIFIC_NODE_URL.replace("{hid}", hid));
			HidModel result = execute(new HttpPut(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}
}
