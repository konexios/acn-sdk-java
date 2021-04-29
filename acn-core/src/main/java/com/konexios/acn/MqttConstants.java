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
package com.konexios.acn;

public interface MqttConstants {
	// topic exchange
	final static String DEFAULT_RABBITMQ_EXCHANGE = "amq.topic";

	// durable incoming queue
	final static String DEFAULT_RABBITMQ_TELEMETRY_QUEUE = "kronos.telemetry";
	final static String DEFAULT_RABBITMQ_TELEMETRY_BATCH_QUEUE = "kronos.telemetry.batch";
	final static String DEFAULT_RABBITMQ_TELEMTRY_GZIP_BATCH_QUEUE = "kronos.telemetry.gzip.batch";
	final static String DEFAULT_RABBITMQ_COMMAND_QUEUE = "kronos.command";
	final static String DEFAULT_RABBITMQ_MQTT_API_QUEUE = "kronos.api";

	// application telemetry direct exchange
	final static String APPLICATION_TELEMETRY_EXCHANGE = "kronos.application.telemetry";

	static String applicationTelemetryRouting(String applicationHid) {
		return String.format("app.%s", applicationHid);
	}

	static String gatewayToServerTelemetryRouting(String gatewayHid) {
		return String.format("krs.tel.gts.%s", gatewayHid);
	}

	static boolean isGatewayToServerTelemetryRouting(String queueName) {
		return queueName.startsWith("krs.tel.gts.");
	}

	static String gatewayToServerTelemetryBatchRouting(String gatewayHid) {
		return String.format("krs.tel.bat.gts.%s", gatewayHid);
	}

	static boolean isGatewayToServerTelemetryBatchRouting(String queueName) {
		return queueName.startsWith("krs.tel.bat.gts.");
	}

	static String gatewayToServerTelemetryGzipBatchRouting(String gatewayHid) {
		return String.format("krs.tel.gzb.gts.%s", gatewayHid);
	}

	static boolean isGatewayToServerTelemetryGzipBatchRouting(String queueName) {
		return queueName.startsWith("krs.tel.gzb.gts.");
	}

	static String gatewayToServerCommandRouting(String gatewayHid) {
		return String.format("krs.cmd.gts.%s", gatewayHid);
	}

	static String serverToGatewayTelemetryRouting(String gatewayHid) {
		return String.format("krs.tel.stg.%s", gatewayHid);
	}

	static String serverToGatewayCommandRouting(String gatewayHid) {
		return String.format("krs.cmd.stg.%s", gatewayHid);
	}

	static String gatewayToServerMqttApiRouting(String gatewayHid) {
		return String.format("krs.api.gts.%s", gatewayHid);
	}

	static String serverToGatewayMqttApiRouting(String gatewayHid) {
		return String.format("krs.api.stg.%s", gatewayHid);
	}
}
