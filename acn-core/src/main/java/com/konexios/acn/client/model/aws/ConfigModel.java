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
package com.konexios.acn.client.model.aws;

import java.io.Serializable;

public class ConfigModel implements Serializable {
	private static final long serialVersionUID = -3541308072074902349L;

	private String host;
	private int port = 8883;
	private String caCert;
	private String clientCert;
	private String privateKey;

	public ConfigModel withHost(String host) {
		setHost(host);
		return this;
	}

	public ConfigModel withPort(int port) {
		setPort(port);
		return this;
	}

	public ConfigModel withClientCert(String clientCert) {
		setClientCert(clientCert);
		return this;
	}

	public ConfigModel withPrivateKey(String privateKey) {
		setPrivateKey(privateKey);
		return this;
	}

	public ConfigModel withCaCert(String caCert) {
		setCaCert(caCert);
		return this;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getClientCert() {
		return clientCert;
	}

	public void setClientCert(String clientCert) {
		this.clientCert = clientCert;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getCaCert() {
		return caCert;
	}

	public void setCaCert(String caCert) {
		this.caCert = caCert;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caCert == null) ? 0 : caCert.hashCode());
		result = prime * result + ((clientCert == null) ? 0 : clientCert.hashCode());
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime * result + port;
		result = prime * result + ((privateKey == null) ? 0 : privateKey.hashCode());
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
		ConfigModel other = (ConfigModel) obj;
		if (caCert == null) {
			if (other.caCert != null)
				return false;
		} else if (!caCert.equals(other.caCert))
			return false;
		if (clientCert == null) {
			if (other.clientCert != null)
				return false;
		} else if (!clientCert.equals(other.clientCert))
			return false;
		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		if (port != other.port)
			return false;
		if (privateKey == null) {
			if (other.privateKey != null)
				return false;
		} else if (!privateKey.equals(other.privateKey))
			return false;
		return true;
	}
}
