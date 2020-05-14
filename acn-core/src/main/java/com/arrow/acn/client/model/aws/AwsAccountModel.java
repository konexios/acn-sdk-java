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
package com.arrow.acn.client.model.aws;

import com.arrow.acs.client.model.AuditableDocumentModelAbstract;

public class AwsAccountModel extends AuditableDocumentModelAbstract<AwsAccountModel> {
	private static final long serialVersionUID = 4379771072996285501L;

	private String applicationHid;
	private PolicyModel policy;
	private CertModel cert;
	private String region;
	private String accountId;
	private String dataEndpoint;
	private String login;
	private String accessKey;
	private String secretKey;
	private boolean enabled;

	@Override
	protected AwsAccountModel self() {
		return this;
	}

	public AwsAccountModel withApplicationHid(String applicationHid) {
		setApplicationHid(applicationHid);
		return this;
	}

	public AwsAccountModel withPolicy(PolicyModel policy) {
		setPolicy(policy);
		return this;
	}

	public AwsAccountModel withCert(CertModel cert) {
		setCert(cert);
		return this;
	}

	public AwsAccountModel withRegion(String region) {
		setRegion(region);
		return this;
	}

	public AwsAccountModel withDataEndpoint(String dataEndpoint) {
		setDataEndpoint(dataEndpoint);
		return this;
	}

	public AwsAccountModel withLogin(String login) {
		setLogin(login);
		return this;
	}

	public AwsAccountModel withAccessKey(String accessKey) {
		setAccessKey(accessKey);
		return this;
	}

	public AwsAccountModel withSecretKey(String secretKey) {
		setSecretKey(secretKey);
		return this;
	}

	public AwsAccountModel withEnabled(boolean enabled) {
		setEnabled(enabled);
		return this;
	}

	public AwsAccountModel withAccountId(String accountId) {
		setAccountId(accountId);
		return this;
	}

	public String getApplicationHid() {
		return applicationHid;
	}

	public void setApplicationHid(String applicationHid) {
		this.applicationHid = applicationHid;
	}

	public PolicyModel getPolicy() {
		return policy;
	}

	public void setPolicy(PolicyModel policy) {
		this.policy = policy;
	}

	public CertModel getCert() {
		return cert;
	}

	public void setCert(CertModel cert) {
		this.cert = cert;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDataEndpoint() {
		return dataEndpoint;
	}

	public void setDataEndpoint(String dataEndpoint) {
		this.dataEndpoint = dataEndpoint;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((accessKey == null) ? 0 : accessKey.hashCode());
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + ((applicationHid == null) ? 0 : applicationHid.hashCode());
		result = prime * result + ((cert == null) ? 0 : cert.hashCode());
		result = prime * result + ((dataEndpoint == null) ? 0 : dataEndpoint.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((policy == null) ? 0 : policy.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + ((secretKey == null) ? 0 : secretKey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AwsAccountModel other = (AwsAccountModel) obj;
		if (accessKey == null) {
			if (other.accessKey != null)
				return false;
		} else if (!accessKey.equals(other.accessKey))
			return false;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (applicationHid == null) {
			if (other.applicationHid != null)
				return false;
		} else if (!applicationHid.equals(other.applicationHid))
			return false;
		if (cert == null) {
			if (other.cert != null)
				return false;
		} else if (!cert.equals(other.cert))
			return false;
		if (dataEndpoint == null) {
			if (other.dataEndpoint != null)
				return false;
		} else if (!dataEndpoint.equals(other.dataEndpoint))
			return false;
		if (enabled != other.enabled)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (policy == null) {
			if (other.policy != null)
				return false;
		} else if (!policy.equals(other.policy))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (secretKey == null) {
			if (other.secretKey != null)
				return false;
		} else if (!secretKey.equals(other.secretKey))
			return false;
		return true;
	}
}
