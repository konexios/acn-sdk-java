package com.arrow.acn.client.model;

import java.io.Serializable;

public class SoftwareReleaseUpgradeModel implements Serializable {

	private static final long serialVersionUID = 7506106949220160871L;

	private String objectHid;
	private String toSoftwareReleaseHid;
	private String softwareReleaseScheduleHid;

	public SoftwareReleaseUpgradeModel withObjectHid(String hid) {
		setObjectHid(hid);
		return this;
	}

	public String getObjectHid() {
		return objectHid;
	}

	public void setObjectHid(String hid) {
		this.objectHid = hid;
	}

	public SoftwareReleaseUpgradeModel withToSoftwareReleaseHid(String toSoftwareReleaseHid) {
		setToSoftwareReleaseHid(toSoftwareReleaseHid);
		return this;
	}

	public String getToSoftwareReleaseHid() {
		return toSoftwareReleaseHid;
	}

	public void setToSoftwareReleaseHid(String toSoftwareReleaseHid) {
		this.toSoftwareReleaseHid = toSoftwareReleaseHid;
	}

	public SoftwareReleaseUpgradeModel withSoftwareReleaseScheduleHid(String softwareReleaseScheduleHid) {
		setSoftwareReleaseScheduleHid(softwareReleaseScheduleHid);
		return this;
	}

	public String getSoftwareReleaseScheduleHid() {
		return softwareReleaseScheduleHid;
	}

	public void setSoftwareReleaseScheduleHid(String softwareReleaseScheduleHid) {
		this.softwareReleaseScheduleHid = softwareReleaseScheduleHid;
	}
}
