package com.arrow.acn.client.model;

public class GlobalActionModel extends GlobalActionDetailsModel{
	private static final long serialVersionUID = -4499887658386290288L;

	private String applicationHid;
	private String hid;

	public String getApplicationHid() {
		return applicationHid;
	}
	public void setApplicationHid(String applicationHid) {
		this.applicationHid = applicationHid;
	}

	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}
}
