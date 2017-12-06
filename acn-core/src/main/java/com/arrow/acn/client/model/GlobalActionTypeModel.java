package com.arrow.acn.client.model;

public class GlobalActionTypeModel extends GlobalActionTypeDetailsModel{
	private static final long serialVersionUID = 4288725444616929749L;
	
	private String hid;
	private String applicationHid;
	
	public String getHid() {
		return hid;
	}
	
	public void setHid(String hid) {
		this.hid = hid;
	}
	
	public String getApplicationHid() {
		return applicationHid;
	}
	
	public void setApplicationHid(String applicationHid) {
		this.applicationHid = applicationHid;
	}
	
}

