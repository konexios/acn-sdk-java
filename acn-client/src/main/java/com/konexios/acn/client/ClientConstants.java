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
package com.konexios.acn.client;

public interface ClientConstants {
    long DEFAULT_CLOUD_CONNECTION_RETRY_TIMEOUT_SECS = 180L;
    long DEFAULT_CLOUD_CONNECTION_RETRY_INTERVAL_MS = 10000L;
    long DEFAULT_CLOUD_SENDING_RETRY_MS = 5000L;

    long DEFAULT_SHUTDOWN_WAITING_MS = 10000L;

    interface Mqtt {
        int DEFAULT_CONNECTION_TIMEOUT_SECS = 60;
        int DEFAULT_KEEP_ALIVE_INTERVAL_SECS = 60;
        long DEFAULT_CHECK_CONNECTION_RETRY_INTERVAL_MS = 5000L;
        long DEFAULT_PAUSE_BEFORE_RECONNECT_MS = 2000L;
    }
}
