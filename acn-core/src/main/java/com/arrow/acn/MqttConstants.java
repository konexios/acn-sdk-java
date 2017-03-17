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

public interface MqttConstants {
    // topic exchange
    public final static String DEFAULT_RABBITMQ_EXCHANGE = "amq.topic";

    // durable incoming queue
    public final static String DEFAULT_RABBITMQ_TELEMETRY_QUEUE = "kronos.telemetry";
    public final static String DEFAULT_RABBITMQ_TELEMETRY_BATCH_QUEUE = "kronos.telemetry.batch";
    public final static String DEFAULT_RABBITMQ_TELEMTRY_GZIP_BATCH_QUEUE = "kronos.telemetry.gzip.batch";
    public final static String DEFAULT_RABBITMQ_COMMAND_QUEUE = "kronos.command";

    public static String gatewayToServerTelemetryRouting(String gatewayHid) {
        return String.format("krs.tel.gts.%s", gatewayHid);
    }

    public static boolean isGatewayToServerTelemetryRouting(String queueName) {
        return queueName.startsWith("krs.tel.gts.");
    }

    public static String gatewayToServerTelemetryBatchRouting(String gatewayHid) {
        return String.format("krs.tel.bat.gts.%s", gatewayHid);
    }

    public static boolean isGatewayToServerTelemetryBatchRouting(String queueName) {
        return queueName.startsWith("krs.tel.bat.gts.");
    }

    public static String gatewayToServerTelemetryGzipBatchRouting(String gatewayHid) {
        return String.format("krs.tel.gzb.gts.%s", gatewayHid);
    }

    public static boolean isGatewayToServerTelemetryGzipBatchRouting(String queueName) {
        return queueName.startsWith("krs.tel.gzb.gts.");
    }

    public static String gatewayToServerCommandRouting(String gatewayHid) {
        return String.format("krs.cmd.gts.%s", gatewayHid);
    }

    public static String serverToGatewayTelemetryRouting(String gatewayHid) {
        return String.format("krs.tel.stg.%s", gatewayHid);
    }

    public static String serverToGatewayCommandRouting(String gatewayHid) {
        return String.format("krs.cmd.stg.%s", gatewayHid);
    }
}
