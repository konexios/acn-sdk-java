/*******************************************************************************
 * Copyright 2021 Konexios, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.konexios.acn.client.model;

import com.konexios.acs.client.model.AuditableDocumentModelAbstract;

public class SoftwareReleaseTransModel extends AuditableDocumentModelAbstract<SoftwareReleaseTransModel> {

	private static final long serialVersionUID = -5156352330326271900L;

	private String applicationHid;
	private String objectHid;
	private AcnDeviceCategory deviceCategory;
	private String softwareReleaseScheduleHid;
	private String fromSoftwareReleaseHid;
	private String toSoftwareReleaseHid;
	private SoftwareReleaseTransStatus status;
	private String error;
	private String relatedSoftwareReleaseTransHid;
	private String started;
	private String ended;
	private Long timeToExpireSeconds;

	@Override
	protected SoftwareReleaseTransModel self() {
		return this;
	}

	public SoftwareReleaseTransModel withApplicationHid(String applicationHid) {
		setApplicationHid(applicationHid);
		return this;
	}

	public SoftwareReleaseTransModel withObjectHid(String objectHid) {
		setObjectHid(objectHid);
		return this;
	}

	public SoftwareReleaseTransModel withSoftwareReleaseScheduleHid(String softwareReleaseScheduleHid) {
		setSoftwareReleaseScheduleHid(softwareReleaseScheduleHid);
		return this;
	}

	public SoftwareReleaseTransModel withFromSoftwareReleaseHid(String fromSoftwareReleaseHid) {
		setFromSoftwareReleaseHid(fromSoftwareReleaseHid);
		return this;
	}

	public SoftwareReleaseTransModel withToSoftwareReleaseHid(String toSoftwareReleaseHid) {
		setToSoftwareReleaseHid(toSoftwareReleaseHid);
		return this;
	}

	public SoftwareReleaseTransModel withStatus(SoftwareReleaseTransStatus status) {
		setStatus(status);
		return this;
	}

	public SoftwareReleaseTransModel withRelatedSoftwareReleaseTransHid(String relatedSoftwareReleaseTransHid) {
		setRelatedSoftwareReleaseTransHid(relatedSoftwareReleaseTransHid);
		return this;
	}

	public SoftwareReleaseTransModel withError(String error) {
		setError(error);
		return this;
	}

	public SoftwareReleaseTransModel withStared(String started) {
		setStarted(started);
		return this;
	}

	public SoftwareReleaseTransModel withEnded(String ended) {
		setEnded(ended);
		return this;
	}

	public String getApplicationHid() {
		return applicationHid;
	}

	public void setApplicationHid(String applicationHid) {
		this.applicationHid = applicationHid;
	}

	public String getObjectHid() {
		return objectHid;
	}

	public void setObjectHid(String objectHid) {
		this.objectHid = objectHid;
	}

	public AcnDeviceCategory getDeviceCategory() {
		return deviceCategory;
	}

	public void setDeviceCategory(AcnDeviceCategory deviceCategory) {
		this.deviceCategory = deviceCategory;
	}

	public String getSoftwareReleaseScheduleHid() {
		return softwareReleaseScheduleHid;
	}

	public void setSoftwareReleaseScheduleHid(String softwareReleaseScheduleHid) {
		this.softwareReleaseScheduleHid = softwareReleaseScheduleHid;
	}

	public String getFromSoftwareReleaseHid() {
		return fromSoftwareReleaseHid;
	}

	public void setFromSoftwareReleaseHid(String fromSoftwareReleaseHid) {
		this.fromSoftwareReleaseHid = fromSoftwareReleaseHid;
	}

	public String getToSoftwareReleaseHid() {
		return toSoftwareReleaseHid;
	}

	public void setToSoftwareReleaseHid(String toSoftwareReleaseHid) {
		this.toSoftwareReleaseHid = toSoftwareReleaseHid;
	}

	public SoftwareReleaseTransStatus getStatus() {
		return status;
	}

	public void setStatus(SoftwareReleaseTransStatus status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getRelatedSoftwareReleaseTransHid() {
		return relatedSoftwareReleaseTransHid;
	}

	public void setRelatedSoftwareReleaseTransHid(String relatedSoftwareReleaseTransHid) {
		this.relatedSoftwareReleaseTransHid = relatedSoftwareReleaseTransHid;
	}

	public String getStarted() {
		return started;
	}

	public void setStarted(String started) {
		this.started = started;
	}

	public String getEnded() {
		return ended;
	}

	public void setEnded(String ended) {
		this.ended = ended;
	}

	public Long getTimeToExpireSeconds() {
		return timeToExpireSeconds;
	}

	public void setTimeToExpireSeconds(Long timeToExpireSeconds) {
		this.timeToExpireSeconds = timeToExpireSeconds;
	}
}
