package com.arrow.acn.client.cloud;

import com.arrow.acs.client.model.CloudResponseModel;

public interface CloudResponseListener {
	 void processCloudResponse(CloudResponseModel cloudResponse);
}
