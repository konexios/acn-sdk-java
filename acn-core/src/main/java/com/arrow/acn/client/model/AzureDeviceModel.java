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

import com.arrow.acs.client.model.AuditableDocumentModelAbstract;

public class AzureDeviceModel extends AuditableDocumentModelAbstract<AzureDeviceModel> {

    private static final long serialVersionUID = 8960101654526157966L;

    private String azureAccountHid;
    private String gatewayHid;
    private String deviceHid;
    private String iotHubConnectionString;
    private boolean enabled;

    @Override
    protected AzureDeviceModel self() {
        return this;
    }

    public AzureDeviceModel withAzureAccountHid(String azureAccountHid) {
        setAzureAccountHid(azureAccountHid);
        return this;
    }

    public AzureDeviceModel withGatewayHid(String gatewayHid) {
        setGatewayHid(gatewayHid);
        return this;
    }

    public AzureDeviceModel withIotHubConnectionString(String iotHubConnectionString) {
        setIotHubConnectionString(iotHubConnectionString);
        return this;
    }

    public AzureDeviceModel withEnabled(boolean enabled) {
        setEnabled(enabled);
        return this;
    }

    public String getAzureAccountHid() {
        return azureAccountHid;
    }

    public void setAzureAccountHid(String azureAccountHid) {
        this.azureAccountHid = azureAccountHid;
    }

    public String getGatewayHid() {
        return gatewayHid;
    }

    public void setGatewayHid(String gatewayHid) {
        this.gatewayHid = gatewayHid;
    }

    public String getDeviceHid() {
        return deviceHid;
    }

    public void setDeviceHid(String deviceHid) {
        this.deviceHid = deviceHid;
    }

    public String getIotHubConnectionString() {
        return iotHubConnectionString;
    }

    public void setIotHubConnectionString(String iotHubConnectionString) {
        this.iotHubConnectionString = iotHubConnectionString;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
