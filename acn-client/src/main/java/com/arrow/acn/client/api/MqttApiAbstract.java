package com.arrow.acn.client.api;

import com.arrow.acs.client.api.ApiConfig;

public class MqttApiAbstract extends com.arrow.acs.client.api.MqttApiAbstract {

	MqttApiAbstract(ApiConfig apiConfig) {
		super();
		setApiConfig(apiConfig);
	}
}
