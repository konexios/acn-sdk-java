/*******************************************************************************
 * Copyright (c) 2017 Arrow Electronics, Inc.
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

public class DeviceEventModel implements Serializable {

    private static final long serialVersionUID = -4535923297899013459L;

    private String deviceActionTypeName;
    private String criteria;
    private String createdDate;
    private String status;

    public DeviceEventModel withDeviceActionTypeName(String deviceActionTypeName) {
        setDeviceActionTypeName(deviceActionTypeName);
        return this;
    }

    public DeviceEventModel withCriteria(String criteria) {
        setCriteria(criteria);
        return this;
    }

    public DeviceEventModel withCreatedDate(String createdDate) {
        setCreatedDate(createdDate);
        return this;
    }

    public DeviceEventModel withStatus(String status) {
        setStatus(status);
        return this;
    }

    public String getDeviceActionTypeName() {
        return deviceActionTypeName;
    }

    public void setDeviceActionTypeName(String deviceActionTypeName) {
        this.deviceActionTypeName = deviceActionTypeName;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
