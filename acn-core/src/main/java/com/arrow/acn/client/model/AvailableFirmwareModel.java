package com.arrow.acn.client.model;

import java.io.Serializable;

public class AvailableFirmwareModel implements Serializable {
	private static final long serialVersionUID = 8318152431132367706L;

	private String softwareReleaseName;
	private String softwareReleaseHID;

	public AvailableFirmwareModel withSoftwareReleaseHid(String softwareReleaseHid) {
		setSoftwareReleaseHID(softwareReleaseHid);
		return this;
	}
	
	public AvailableFirmwareModel withSoftwareReleaseName(String softwareReleaseName) {
		setSoftwareReleaseName(softwareReleaseName);
		return this;
	}

	public String getSoftwareReleaseName() {
		return softwareReleaseName;
	}

	public void setSoftwareReleaseName(String softwareReleaseName) {
		this.softwareReleaseName = softwareReleaseName;
	}

	public String getSoftwareReleaseHID() {
		return softwareReleaseHID;
	}

	public void setSoftwareReleaseHID(String softwareReleaseHID) {
		this.softwareReleaseHID = softwareReleaseHID;
	}

}
