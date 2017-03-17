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
package com.arrow.acn;

public interface AcnEventNames {
    public interface ServerToGateway {
        public final static String GATEWAY_SOFTWARE_UPDATE = "ServerToGateway_GatewaySoftwareUpdate";

        public final static String DEVICE_START = "ServerToGateway_DeviceStart";
        public final static String DEVICE_STOP = "ServerToGateway_DeviceStop";

        public final static String DEVICE_PROPERTY_CHANGE = "ServerToGateway_DevicePropertyChange";
        public final static String DEVICE_COMMAND = "ServerToGateway_DeviceCommand";

        public final static String SENSOR_PROPERTY_CHANGE = "ServerToGateway_SensorPropertyChange";
        public final static String SENSOR_TELEMETRY_CHANGE = "ServerToGateway_SensorTelemetryChange";
        public final static String SENSOR_COMMAND = "ServerToGateway_SensorCommand";
    }
}
