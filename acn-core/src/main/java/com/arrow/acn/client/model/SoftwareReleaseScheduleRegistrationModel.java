package com.arrow.acn.client.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class SoftwareReleaseScheduleRegistrationModel implements Serializable {

	private static final long serialVersionUID = 6384264075263411626L;

	private String scheduledDate;
	private String softwareReleaseHid;
	private DeviceCategory category;
	private String comments;
	private Set<String> objectHids = new HashSet<String>();
	private SoftwareReleaseScheduleStatus status;
	private boolean notifyOnStart;
	private boolean notifyOnEnd;
	private String notifyEmails;

	public SoftwareReleaseScheduleRegistrationModel withScheduledDate(String scheduledDate) {
		setScheduledDate(scheduledDate);
		return this;
	}

	public SoftwareReleaseScheduleRegistrationModel withSoftwareReleaseHid(String softwareReleaseHid) {
		setSoftwareReleaseHid(softwareReleaseHid);
		return this;
	}

	public SoftwareReleaseScheduleRegistrationModel withCategory(DeviceCategory category) {
		setCategory(category);
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

	public SoftwareReleaseScheduleRegistrationModel withNotifyEmails(String notifyEmails) {
		setNotifyEmails(notifyEmails);
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

	public DeviceCategory getCategory() {
		return category;
	}

	public void setCategory(DeviceCategory category) {
		this.category = category;
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

	public String getNotifyEmails() {
		return notifyEmails;
	}

	public void setNotifyEmails(String notifyEmails) {
		this.notifyEmails = notifyEmails;
	}
}
