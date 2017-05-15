package com.arrow.acn.client.model;

import java.util.ArrayList;
import java.util.List;

import com.arrow.acs.client.model.ModelAbstract;

public class SoftwareReleaseScheduleModel extends ModelAbstract<SoftwareReleaseScheduleModel> {

	private static final long serialVersionUID = 4446552374750442128L;

	private String applicationHid;
	private String scheduledDate;
	private String softwareReleaseHid;
	private String deviceCategoryHid;
	private String comments;
	private List<String> objectHids = new ArrayList<String>();
	private SoftwareReleaseScheduleStatus status;
	private boolean notifyOnStart;
	private boolean notifyOnEnd;
	private String notifyEmails;

	@Override
	protected SoftwareReleaseScheduleModel self() {
		return this;
	}

	public SoftwareReleaseScheduleModel withApplicationHid(String applicationHid) {
		setApplicationHid(applicationHid);
		return this;
	}

	public String getApplicationHid() {
		return applicationHid;
	}

	public void setApplicationHid(String applicationHid) {
		this.applicationHid = applicationHid;
	}

	public SoftwareReleaseScheduleModel withScheduledDate(String scheduledDate) {
		setScheduledDate(scheduledDate);
		return this;
	}

	public SoftwareReleaseScheduleModel withSoftwareReleaseHid(String softwareReleaseHid) {
		setSoftwareReleaseHid(softwareReleaseHid);
		return this;
	}

	public SoftwareReleaseScheduleModel withComments(String comments) {
		setComments(comments);
		return this;
	}

	public SoftwareReleaseScheduleModel withObjectHids(List<String> objectHids) {
		setObjectHids(objectHids);
		return this;
	}

	public SoftwareReleaseScheduleModel withObjectHid(String objectHid) {
		objectHids.add(objectHid);
		return this;
	}

	public SoftwareReleaseScheduleModel withStatus(SoftwareReleaseScheduleStatus status) {
		setStatus(status);
		return this;
	}

	public SoftwareReleaseScheduleModel withNotifyOnStart(boolean notifyOnStart) {
		setNotifyOnStart(notifyOnStart);
		return this;
	}

	public SoftwareReleaseScheduleModel withNotifyOnEnd(boolean notifyOnEnd) {
		setNotifyOnEnd(notifyOnEnd);
		return this;
	}

	public SoftwareReleaseScheduleModel withNotifyEmails(String notifyEmails) {
		setNotifyEmails(notifyEmails);
		return this;
	}

	public SoftwareReleaseScheduleModel withDeviceCategoryHid(String deviceCategoryHid) {
		setDeviceCategoryHid(deviceCategoryHid);
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

	public List<String> getObjectHids() {
		return objectHids;
	}

	public void setObjectHids(List<String> objectHids) {
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

	public String getDeviceCategoryHid() {
		return deviceCategoryHid;
	}

	public void setDeviceCategoryHid(String deviceCategoryHid) {
		this.deviceCategoryHid = deviceCategoryHid;
	}
}
