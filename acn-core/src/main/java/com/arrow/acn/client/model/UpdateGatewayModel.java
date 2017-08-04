package com.arrow.acn.client.model;

public class UpdateGatewayModel extends CreateGatewayModel {
	private static final long serialVersionUID = -1587112251938386153L;

	private String hid;

	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}
}
