package com.arrow.acn.client.cloud;

import com.arrow.acn.client.model.DeviceStateRequestModel;

public interface DeviceStateRequestListener {
	void receive(String deviceHid, DeviceStateRequestModel model);
}
