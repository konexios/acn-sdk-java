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
package com.arrow.acn.client.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.arrow.acn.AcnDeviceCategory;

public class CreateSoftwareReleaseScheduleModel implements Serializable {

	private static final long serialVersionUID = -8598243732118576300L;

	private String scheduledDate;
	private String softwareReleaseHid;
	private AcnDeviceCategory deviceCategory;
	private String comments;
	private Set<String> objectHids = new HashSet<String>();
	private boolean notifyOnStart;
	private boolean notifyOnEnd;
	private boolean notifyOnSubmit;
	private String notifyEmails;
	private String name;
	private boolean onDemand;
	private String deviceTypeHid;
	private String hardwareVersionHid;
	private Long timeToExpireSeconds;

	public CreateSoftwareReleaseScheduleModel withScheduledDate(String scheduledDate) {
		setScheduledDate(scheduledDate);
		return this;
	}

	public CreateSoftwareReleaseScheduleModel withSoftwareReleaseHid(String softwareReleaseHid) {
		setSoftwareReleaseHid(softwareReleaseHid);
		return this;
	}

	public CreateSoftwareReleaseScheduleModel withDeviceCategory(AcnDeviceCategory deviceCategory) {
		setDeviceCategory(deviceCategory);
		return this;
	}

	public CreateSoftwareReleaseScheduleModel withComments(String comments) {
		setComments(comments);
		return this;
	}

	public CreateSoftwareReleaseScheduleModel withObjectHids(Set<String> objectHids) {
		setObjectHids(objectHids);
		return this;
	}

	public CreateSoftwareReleaseScheduleModel withObjectHid(String objectHid) {
		objectHids.add(objectHid);
		return this;
	}

	public CreateSoftwareReleaseScheduleModel withNotifyOnStart(boolean notifyOnStart) {
		setNotifyOnStart(notifyOnStart);
		return this;
	}

	public CreateSoftwareReleaseScheduleModel withNotifyOnEnd(boolean notifyOnEnd) {
		setNotifyOnEnd(notifyOnEnd);
		return this;
	}

	public CreateSoftwareReleaseScheduleModel withNotifyOnSubmit(boolean notifyOnSubmit) {
		setNotifyOnSubmit(notifyOnSubmit);
		return this;
	}

	public CreateSoftwareReleaseScheduleModel withNotifyEmails(String notifyEmails) {
		setNotifyEmails(notifyEmails);
		return this;
	}

	public CreateSoftwareReleaseScheduleModel withName(String name) {
		setName(name);
		return this;
	}

	public CreateSoftwareReleaseScheduleModel withDeviceTypeHid(String deviceTypeHid) {
		setDeviceTypeHid(deviceTypeHid);
		return this;
	}

	public CreateSoftwareReleaseScheduleModel withOnDemand(boolean onDemand) {
		setOnDemand(onDemand);
		return this;
	}

	public CreateSoftwareReleaseScheduleModel withHardwareVersionHid(String hardwareVersionHid) {
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

	public Long getTimeToExpireSeconds() {
		return timeToExpireSeconds;
	}

	public void setTimeToExpireSeconds(Long timeToExpireSeconds) {
		this.timeToExpireSeconds = timeToExpireSeconds;
	}
}
