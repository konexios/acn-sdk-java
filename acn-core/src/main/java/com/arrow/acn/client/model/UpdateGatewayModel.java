package com.arrow.acn.client.model;

public class UpdateGatewayModel extends CreateGatewayModel {
	private static final long serialVersionUID = -7414844558794457946L;

	private String hid;

	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}
}
