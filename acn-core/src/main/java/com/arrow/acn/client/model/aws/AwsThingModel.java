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

public class AwsThingModel extends AuditableDocumentModelAbstract<AwsThingModel> {
	private static final long serialVersionUID = -3865374064160170604L;

	private ThingModel thing;
	private CertModel cert;
	private String awsAccountHid;
	private String gatewayHid;
	private boolean enabled;

	@Override
	protected AwsThingModel self() {
		return this;
	}

	public AwsThingModel withThing(ThingModel thing) {
		setThing(thing);
		return this;
	}

	public AwsThingModel withCert(CertModel cert) {
		setCert(cert);
		return this;
	}

	public AwsThingModel withAwsAccountHid(String awsAccountHid) {
		setAwsAccountHid(awsAccountHid);
		return this;
	}

	public AwsThingModel withGatewayHid(String gatewayHid) {
		setGatewayHid(gatewayHid);
		return this;
	}

	public AwsThingModel withEnabled(boolean enabled) {
		setEnabled(enabled);
		return this;
	}

	public ThingModel getThing() {
		return thing;
	}

	public void setThing(ThingModel thing) {
		this.thing = thing;
	}

	public CertModel getCert() {
		return cert;
	}

	public void setCert(CertModel cert) {
		this.cert = cert;
	}

	public String getAwsAccountHid() {
		return awsAccountHid;
	}

	public void setAwsAccountHid(String awsAccountHid) {
		this.awsAccountHid = awsAccountHid;
	}

	public String getGatewayHid() {
		return gatewayHid;
	}

	public void setGatewayHid(String gatewayHid) {
		this.gatewayHid = gatewayHid;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((awsAccountHid == null) ? 0 : awsAccountHid.hashCode());
		result = prime * result + ((cert == null) ? 0 : cert.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((gatewayHid == null) ? 0 : gatewayHid.hashCode());
		result = prime * result + ((thing == null) ? 0 : thing.hashCode());
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
		AwsThingModel other = (AwsThingModel) obj;
		if (awsAccountHid == null) {
			if (other.awsAccountHid != null)
				return false;
		} else if (!awsAccountHid.equals(other.awsAccountHid))
			return false;
		if (cert == null) {
			if (other.cert != null)
				return false;
		} else if (!cert.equals(other.cert))
			return false;
		if (enabled != other.enabled)
			return false;
		if (gatewayHid == null) {
			if (other.gatewayHid != null)
				return false;
		} else if (!gatewayHid.equals(other.gatewayHid))
			return false;
		if (thing == null) {
			if (other.thing != null)
				return false;
		} else if (!thing.equals(other.thing))
			return false;
		return true;
	}
}