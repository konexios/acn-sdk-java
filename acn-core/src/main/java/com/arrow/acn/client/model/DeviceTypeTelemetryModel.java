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

public class DeviceTypeTelemetryModel implements Serializable {

    private static final long serialVersionUID = 1351634662217682079L;

    private String description;
    private String name;
    private String type;
    private boolean controllable = false;

    public DeviceTypeTelemetryModel withDescription(String description) {
        setDescription(description);
        return this;
    }

    public DeviceTypeTelemetryModel withName(String name) {
        setName(name);
        return this;
    }

    public DeviceTypeTelemetryModel withType(String type) {
        setType(type);
        return this;
    }

    public DeviceTypeTelemetryModel withControllable(boolean controllable) {
        setControllable(controllable);
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setControllable(boolean controllable) {
        this.controllable = controllable;
    }

    public boolean isControllable() {
        return controllable;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (controllable ? 1231 : 1237);
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DeviceTypeTelemetryModel other = (DeviceTypeTelemetryModel) obj;
        if (controllable != other.controllable)
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }
}
