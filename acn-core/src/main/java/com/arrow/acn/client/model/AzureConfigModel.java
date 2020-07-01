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

public class AzureConfigModel implements Serializable {
    private static final long serialVersionUID = 522892464697437578L;

    private String connectionString;

    public AzureConfigModel withConnectionString(String connectionString) {
        setConnectionString(connectionString);
        return this;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((connectionString == null) ? 0 : connectionString.hashCode());
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
        AzureConfigModel other = (AzureConfigModel) obj;
        if (connectionString == null) {
            if (other.connectionString != null)
                return false;
        } else if (!connectionString.equals(other.connectionString))
            return false;
        return true;
    }
}
