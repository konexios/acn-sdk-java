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

public class AzureAccountModel extends AuditableDocumentModelAbstract<AzureAccountModel> {

    private static final long serialVersionUID = -8205580201727669257L;

    private String applicationHid;
    private String dpsHostname;
    private String dpsConnectionString;
    private String dpsIdScope;
    private String iotHubHostname;
    private String iotHubConnectionString;
    private String eventHubHostname;
    private String eventHubConnectionString;
    private int numPartitions;
    private String telemetrySync;
    private boolean enabled;
    private String consumerGroupName;

    @Override
    protected AzureAccountModel self() {
        return this;
    }

    public AzureAccountModel withApplicationHid(String applicationHid) {
        setApplicationHid(applicationHid);
        return this;
    }

    public AzureAccountModel withDpsConnectionString(String dpsConnectionString) {
        setDpsConnectionString(dpsConnectionString);
        return this;
    }

    public AzureAccountModel withDpsIdScope(String dpsIdScope) {
        setDpsIdScope(dpsIdScope);
        return this;
    }

    public AzureAccountModel withIotHubHostname(String iotHubHostname) {
        setIotHubHostname(iotHubHostname);
        return this;
    }

    public AzureAccountModel withIotHubConnectionString(String iotHubConnectionString) {
        setIotHubConnectionString(iotHubConnectionString);
        return this;
    }

    public AzureAccountModel withEventHubConnectionString(String eventHubConnectionString) {
        setEventHubConnectionString(eventHubConnectionString);
        return this;
    }

    public AzureAccountModel withNumPartitions(int numPartitions) {
        setNumPartitions(numPartitions);
        return this;
    }

    public AzureAccountModel withTelemetrySync(String telemetrySync) {
        setTelemetrySync(telemetrySync);
        return this;
    }

    public AzureAccountModel withEnabled(boolean enabled) {
        setEnabled(enabled);
        return this;
    }

    public AzureAccountModel withConsumerGroupName(String consumerGroupName) {
        setConsumerGroupName(consumerGroupName);
        return this;
    }

    public AzureAccountModel withDpsHostname(String dpsHostname) {
        setDpsHostname(dpsHostname);
        return this;
    }

    public AzureAccountModel withEventHubHostname(String eventHubHostname) {
        setEventHubHostname(eventHubHostname);
        return this;
    }

    public String getApplicationHid() {
        return applicationHid;
    }

    public void setApplicationHid(String applicationHid) {
        this.applicationHid = applicationHid;
    }

    public String getDpsHostname() {
        return dpsHostname;
    }

    public void setDpsHostname(String dpsHostname) {
        this.dpsHostname = dpsHostname;
    }

    public String getDpsConnectionString() {
        return dpsConnectionString;
    }

    public void setDpsConnectionString(String dpsConnectionString) {
        this.dpsConnectionString = dpsConnectionString;
    }

    public String getDpsIdScope() {
        return dpsIdScope;
    }

    public void setDpsIdScope(String dpsIdScope) {
        this.dpsIdScope = dpsIdScope;
    }

    public String getIotHubHostname() {
        return iotHubHostname;
    }

    public void setIotHubHostname(String iotHubHostname) {
        this.iotHubHostname = iotHubHostname;
    }

    public String getIotHubConnectionString() {
        return iotHubConnectionString;
    }

    public void setIotHubConnectionString(String iotHubConnectionString) {
        this.iotHubConnectionString = iotHubConnectionString;
    }

    public String getEventHubHostname() {
        return eventHubHostname;
    }

    public void setEventHubHostname(String eventHubHostname) {
        this.eventHubHostname = eventHubHostname;
    }

    public String getEventHubConnectionString() {
        return eventHubConnectionString;
    }

    public void setEventHubConnectionString(String eventHubConnectionString) {
        this.eventHubConnectionString = eventHubConnectionString;
    }

    public int getNumPartitions() {
        return numPartitions;
    }

    public void setNumPartitions(int numPartitions) {
        this.numPartitions = numPartitions;
    }

    public String getTelemetrySync() {
        return telemetrySync;
    }

    public void setTelemetrySync(String telemetrySync) {
        this.telemetrySync = telemetrySync;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getConsumerGroupName() {
        return consumerGroupName;
    }

    public void setConsumerGroupName(String consumerGroupName) {
        this.consumerGroupName = consumerGroupName;
    }
}
