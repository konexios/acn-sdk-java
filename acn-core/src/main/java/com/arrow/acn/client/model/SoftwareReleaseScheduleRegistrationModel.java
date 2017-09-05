package com.arrow.acn.client.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.arrow.acn.AcnDeviceCategory;

public class SoftwareReleaseScheduleRegistrationModel implements Serializable {

	private static final long serialVersionUID = -1151936563899531439L;

	private String scheduledDate;
	private String softwareReleaseHid;
	// private String deviceCategoryHid;
	private AcnDeviceCategory deviceCategory;
	private String comments;
	private Set<String> objectHids = new HashSet<String>();
	private SoftwareReleaseScheduleStatus status;
	private boolean notifyOnStart;
	private boolean notifyOnEnd;
	private boolean notifyOnSubmit;
	private String notifyEmails;
	private String name;
	private boolean onDemand;
	private String deviceTypeHid;
	private String hardwareVersionHid;

	public SoftwareReleaseScheduleRegistrationModel withScheduledDate(String scheduledDate) {
		setScheduledDate(scheduledDate);
		return this;
	}

	public SoftwareReleaseScheduleRegistrationModel withSoftwareReleaseHid(String softwareReleaseHid) {
		setSoftwareReleaseHid(softwareReleaseHid);
		return this;
	}

	// public SoftwareReleaseScheduleRegistrationModel
	// withDeviceCategoryHid(String deviceCategoryHid) {
	// setDeviceCategoryHid(deviceCategoryHid);
	// return this;
	// }

	public SoftwareReleaseScheduleRegistrationModel withDeviceCategory(AcnDeviceCategory deviceCategory) {
		setDeviceCategory(deviceCategory);
		return this;
	}

	public SoftwareReleaseScheduleRegistrationModel withComments(String comments) {
		setComments(comments);
		return this;
	}

	public SoftwareReleaseScheduleRegistrationModel withObjectHids(Set<String> objectHids) {
		setObjectHids(objectHids);
		return this;
	}

	public SoftwareReleaseScheduleRegistrationModel withObjectHid(String objectHid) {
		objectHids.add(objectHid);
		return this;
	}

	public SoftwareReleaseScheduleRegistrationModel withStatus(SoftwareReleaseScheduleStatus status) {
		setStatus(status);
		return this;
	}

	public SoftwareReleaseScheduleRegistrationModel withNotifyOnStart(boolean notifyOnStart) {
		setNotifyOnStart(notifyOnStart);
		return this;
	}

	public SoftwareReleaseScheduleRegistrationModel withNotifyOnEnd(boolean notifyOnEnd) {
		setNotifyOnEnd(notifyOnEnd);
		return this;
	}

	public SoftwareReleaseScheduleRegistrationModel withNotifyOnSubmit(boolean notifyOnSubmit) {
		setNotifyOnSubmit(notifyOnSubmit);
		return this;
	}

	public SoftwareReleaseScheduleRegistrationModel withNotifyEmails(String notifyEmails) {
		setNotifyEmails(notifyEmails);
		return this;
	}

	public SoftwareReleaseScheduleRegistrationModel withName(String name) {
		setName(name);
		return this;
	}

	public SoftwareReleaseScheduleRegistrationModel withDeviceTypeHid(String deviceTypeHid) {
		setDeviceTypeHid(deviceTypeHid);
		return this;
	}

	public SoftwareReleaseScheduleRegistrationModel withOnDemand(boolean onDemand) {
		setOnDemand(onDemand);
		return this;
	}

	public SoftwareReleaseScheduleRegistrationModel withHardwareVersionHid(String hardwareVersionHid) {
		setHardwareVersionHid(hardwareVersionHid);
		return this;
	}

	public String getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(String scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public String getSoftwareReleaseHid() {
		return softwareReleaseHid;
	}

	public void setSoftwareReleaseHid(String softwareReleaseHid) {
		this.softwareReleaseHid = softwareReleaseHid;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Set<String> getObjectHids() {
		return objectHids;
	}

	public void setObjectHids(Set<String> objectHids) {
		this.objectHids.addAll(objectHids);
	}

	public SoftwareReleaseScheduleStatus getStatus() {
		return status;
	}

	public void setStatus(SoftwareReleaseScheduleStatus status) {
		this.status = status;
	}

	public boolean isNotifyOnStart() {
		return notifyOnStart;
	}

	public void setNotifyOnStart(boolean notifyOnStart) {
		this.notifyOnStart = notifyOnStart;
	}

	public boolean isNotifyOnEnd() {
		return notifyOnEnd;
	}

	public void setNotifyOnEnd(boolean notifyOnEnd) {
		this.notifyOnEnd = notifyOnEnd;
	}

	public boolean isNotifyOnSubmit() {
		return notifyOnSubmit;
	}

	public void setNotifyOnSubmit(boolean notifyOnSubmit) {
		this.notifyOnSubmit = notifyOnSubmit;
	}

	public String getNotifyEmails() {
		return notifyEmails;
	}

	public void setNotifyEmails(String notifyEmails) {
		this.notifyEmails = notifyEmails;
	}

	// public String getDeviceCategoryHid() {
	// return deviceCategoryHid;
	// }
	//
	// public void setDeviceCategoryHid(String deviceCategoryHid) {
	// this.deviceCategoryHid = deviceCategoryHid;
	// }

	public AcnDeviceCategory getDeviceCategory() {
		return deviceCategory;
	}

	public void setDeviceCategory(AcnDeviceCategory deviceCategory) {
		this.deviceCategory = deviceCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOnDemand() {
		return onDemand;
	}

	public void setOnDemand(boolean onDemand) {
		this.onDemand = onDemand;
	}

	public String getDeviceTypeHid() {
		return deviceTypeHid;
	}

	public void setDeviceTypeHid(String deviceTypeHid) {
		this.deviceTypeHid = deviceTypeHid;
	}

	public String getHardwareVersionHid() {
		return hardwareVersionHid;
	}

	public void setHardwareVersionHid(String hardwareVersionHid) {
		this.hardwareVersionHid = hardwareVersionHid;
	}
}
