package com.arrow.acn.client.api;

import java.nio.charset.Charset;

import com.arrow.acn.client.cloud.CloudConnectorAbstract;
import com.arrow.acn.client.cloud.CloudResponseListener;
import com.arrow.acn.client.cloud.MessageListener;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.Loggable;
import com.arrow.acs.client.model.CloudResponseModel;

public class CouldResponseProcessorApi extends Loggable implements MessageListener {

	private CloudConnectorAbstract cloudConnector;
	private CloudResponseListener cloudResponseListener;

	public CloudConnectorAbstract getCloudConnector() {
		return cloudConnector;
	}

	public void setCloudConnector(CloudConnectorAbstract cloudConnector) {
		this.cloudConnector = cloudConnector;
		this.cloudConnector.setCloudResponseListener(this);
	}

	public void setCloudResponseListener(CloudResponseListener cloudResponseListener) {
		this.cloudResponseListener = cloudResponseListener;
	}

	@Override
	public void processMessage(String topic, byte[] payload) {
		String method = "processMessage";
		// here, we received validated CloudResponse model
		CloudResponseModel model = JsonUtils.fromJson(new String(payload, Charset.defaultCharset()),
				CloudResponseModel.class);
		logDebug(method, "received response on message with requestId: " + model.getRequestId() + "; parameters: "
				+ model.getParameters());

		if (cloudResponseListener != null) {
			try {
				cloudResponseListener.processCloudResponse(model);
			} catch (Throwable e) {
				logError(method, "cloudResponseListener failed to process response");
			}
		} else {
			logWarn(method, "cloudResponseListener is not set to handle response");
		}
	}

}
