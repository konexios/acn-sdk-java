/*******************************************************************************
 * Copyright 2021 Konexios, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.konexios.acn.client.api;

import java.net.URI;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import com.fasterxml.jackson.core.type.TypeReference;

import com.konexios.acn.client.AcnClientException;
import com.konexios.acn.client.model.NodeTypeModel;
import com.konexios.acs.JsonUtils;
import com.konexios.acs.client.api.ApiConfig;
import com.konexios.acs.client.api.MqttHttpChannel;
import com.konexios.acs.client.model.HidModel;
import com.konexios.acs.client.model.ListResultModel;

public final class NodeTypeApi extends ApiAbstract {
	private final String NODE_TYPES_BASE_URL = API_BASE + "/nodes/types";
	private final String SPECIFIC_NODE_TYPE_URL = NODE_TYPES_BASE_URL + "/{hid}";

	private TypeReference<ListResultModel<NodeTypeModel>> nodeTypeModelTypeRef;

	NodeTypeApi(ApiConfig apiConfig, MqttHttpChannel mqttHttpChannel) {
		super(apiConfig, mqttHttpChannel);
	}

	/**
	 * Sends GET request to obtain parameters of all available node types
	 *
	 * @return list of {@link NodeTypeModel} containing type parameters
	 *
	 * @throws AcnClientException if request failed
	 */
	public ListResultModel<NodeTypeModel> listExistingNodeTypes() {
		String method = "listExistingNodeTypes";
		try {
			URI uri = buildUri(NODE_TYPES_BASE_URL);
			ListResultModel<NodeTypeModel> result = execute(new HttpGet(uri), getNodeTypeModelTypeRef());
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	/**
	 * Sends POST request to create new node type according to {@code model} passed
	 * 
	 * @param model {@link NodeTypeModel} representing parameters of node type to be
	 *              created
	 *
	 * @return {@link HidModel} containing {@code hid} of node type created
	 *
	 * @throws AcnClientException if request failed
	 */
	public HidModel createNewNodeType(NodeTypeModel model) {
		String method = "createNewNodeType";
		try {
			URI uri = buildUri(NODE_TYPES_BASE_URL);
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	/**
	 * Sends PUT request to update existing node type according to {@code model}
	 * passed
	 *
	 * @param hid   {@link String} representing {@code hid} of node type to be
	 *              updated
	 * @param model {@link NodeTypeModel} representing node type parameters to be
	 *              updated
	 *
	 * @return {@link HidModel} containing {@code hid} of node type updated
	 *
	 * @throws AcnClientException if request failed
	 */
	public HidModel updateExistingNodeType(String hid, NodeTypeModel model) {
		String method = "updateExistingNodeType";
		try {
			URI uri = buildUri(SPECIFIC_NODE_TYPE_URL.replace("{hid}", hid));
			HidModel result = execute(new HttpPut(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	private synchronized TypeReference<ListResultModel<NodeTypeModel>> getNodeTypeModelTypeRef() {
		return nodeTypeModelTypeRef != null ? nodeTypeModelTypeRef
				: (nodeTypeModelTypeRef = new TypeReference<ListResultModel<NodeTypeModel>>() {
				});
	}
}
