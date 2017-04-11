package com.arrow.acn.client.api;

import org.apache.commons.lang3.NotImplementedException;

import com.arrow.acn.client.model.DeviceStateModel;
import com.arrow.acn.client.model.DeviceStateRequestModel;
import com.arrow.acn.client.model.DeviceStateUpdateModel;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.model.HidModel;
import com.arrow.acs.client.model.StatusModel;

public class DeviceStateApi extends ApiAbstract {

	DeviceStateApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public DeviceStateModel find(String deviceHid) {
		// TODO - convenient method
		throw new NotImplementedException("not implemented yet!");
	}

	public HidModel createStateRequest(String deviceHid, DeviceStateRequestModel model) {
		// TODO - convenient method
		throw new NotImplementedException("not implemented yet!");
	}

	public HidModel createStateUpdate(String deviceHid, DeviceStateUpdateModel model) {
		// TODO - convenient method
		throw new NotImplementedException("not implemented yet!");
	}

	public StatusModel transReceived(String hid) {
		// TODO
		throw new NotImplementedException("not implemented yet!");
	}

	public StatusModel transComplete(String hid) {
		// TODO
		throw new NotImplementedException("not implemented yet!");
	}

	public StatusModel transError(String hid, String error) {
		// TODO
		throw new NotImplementedException("not implemented yet!");
	}
}
