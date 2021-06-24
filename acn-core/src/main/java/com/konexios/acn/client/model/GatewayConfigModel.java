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
package com.konexios.acn.client.model;

import java.io.Serializable;

import com.konexios.acn.client.model.aws.ConfigModel;
import com.konexios.acs.client.model.KeyModel;

public class GatewayConfigModel implements Serializable {
	private static final long serialVersionUID = -4444841987164491688L;

	private CloudPlatform cloudPlatform = CloudPlatform.IotConnect;
	private KeyModel key;
	private ConfigModel aws;
	private IbmConfigModel ibm;
	private AzureConfigModel azure;

	public GatewayConfigModel withCloudPlatform(CloudPlatform cloudPlatform) {
		setCloudPlatform(cloudPlatform);
		return this;
	}

	public GatewayConfigModel withKey(KeyModel key) {
		setKey(key);
		return this;
	}

	public GatewayConfigModel withAws(ConfigModel aws) {
		setAws(aws);
		return this;
	}

	public GatewayConfigModel withIbm(IbmConfigModel ibm) {
		setIbm(ibm);
		return this;
	}

	public GatewayConfigModel withAzure(AzureConfigModel azure) {
		setAzure(azure);
		return this;
	}

	public IbmConfigModel getIbm() {
		return ibm;
	}

	public void setIbm(IbmConfigModel ibm) {
		this.ibm = ibm;
	}

	public KeyModel getKey() {
		return key;
	}

	public void setKey(KeyModel key) {
		this.key = key;
	}

	public ConfigModel getAws() {
		return aws;
	}

	public void setAws(ConfigModel aws) {
		this.aws = aws;
	}

	public AzureConfigModel getAzure() {
		return azure;
	}

	public void setAzure(AzureConfigModel azure) {
		this.azure = azure;
	}

	public CloudPlatform getCloudPlatform() {
		return cloudPlatform;
	}

	public void setCloudPlatform(CloudPlatform cloudPlatform) {
		this.cloudPlatform = cloudPlatform;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aws == null) ? 0 : aws.hashCode());
		result = prime * result + ((azure == null) ? 0 : azure.hashCode());
		result = prime * result + ((cloudPlatform == null) ? 0 : cloudPlatform.hashCode());
		result = prime * result + ((ibm == null) ? 0 : ibm.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GatewayConfigModel other = (GatewayConfigModel) obj;
		if (aws == null) {
			if (other.aws != null)
				return false;
		} else if (!aws.equals(other.aws))
			return false;
		if (azure == null) {
			if (other.azure != null)
				return false;
		} else if (!azure.equals(other.azure))
			return false;
		if (cloudPlatform != other.cloudPlatform)
			return false;
		if (ibm == null) {
			if (other.ibm != null)
				return false;
		} else if (!ibm.equals(other.ibm))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
}
