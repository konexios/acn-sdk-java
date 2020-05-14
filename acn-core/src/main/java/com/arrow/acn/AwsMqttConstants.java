package com.arrow.acn;

public interface AwsMqttConstants {
    final static String TELEMETRY_TOPIC = "konexios/telemetries/<gatewayHid>/<deviceHid>";
    final static String TELEMETRY_TOPIC_WILDCARD = "konexios/telemetries/+/+";
    final static String TELEMETRY_TOPIC_REGEX = "konexios/telemetries/.+/\\w+";

    static String telemetryTopic(String gatewayHid, String deviceHid) {
        return TELEMETRY_TOPIC.replace("<gatewayHid>", gatewayHid).replace("<deviceHid>", deviceHid);
    }

    final static String COMMAND_TOPIC = "konexios/commands/<gatewayHid>";
    final static String COMMAND_TOPIC_WILDCARD = "konexios/commands/+";
    final static String COMMAND_TOPIC_REGEX = "konexios/commands/\\w+";

    static String commandTopic(String gatewayHid) {
        return COMMAND_TOPIC.replace("<gatewayHid>", gatewayHid);
    }

    final static String API_REQUEST_TOPIC = "konexios/requests/<gatewayHid>";
    final static String API_REQUEST_TOPIC_WILDCARD = "konexios/requests/+";
    final static String API_REQUEST_TOPIC_REGEX = "konexios/requests/\\w+";

    static String apiRequestTopic(String gatewayHid) {
        return API_REQUEST_TOPIC.replace("<gatewayHid>", gatewayHid);
    }

    final static String API_RESPONSE_TOPIC = "konexios/responses/<gatewayHid>";
    final static String API_RESPONSE_TOPIC_WILDCARD = "konexios/responses/+";
    final static String API_RESPONSE_TOPIC_REGEX = "konexios/responses/\\w+";

    static String apiResponseTopic(String gatewayHid) {
        return API_RESPONSE_TOPIC.replace("<gatewayHid>", gatewayHid);
    }

    final static String SHADOW_UPDATE_TOPIC = "$aws/things/<deviceHid>/shadow/update";
    final static String SHADOW_UPDATE_TOPIC_WILDCARD = "$aws/things/+/shadow/update";
    final static String SHADOW_UPDATE_TOPIC_REGEX = "\\$aws/things/\\w+/shadow/update";

    static String shadowUpdateTopic(String deviceHid) {
        return SHADOW_UPDATE_TOPIC.replace("<deviceHid>", deviceHid);
    }

    final static String SHADOW_UPDATE_ACCEPTED_TOPIC = "$aws/things/<deviceHid>/shadow/update/accepted";
    final static String SHADOW_UPDATE_ACCEPTED_TOPIC_WILDCARD = "$aws/things/+/shadow/update/accepted";
    final static String SHADOW_UPDATE_ACCEPTED_TOPIC_REGEX = "\\$aws/things/\\w+/shadow/update/accepted";

    static String shadowUpdateAcceptedTopic(String deviceHid) {
        return SHADOW_UPDATE_ACCEPTED_TOPIC.replace("<deviceHid>", deviceHid);
    }

    final static String SHADOW_UPDATE_DOCUMENTS_TOPIC = "$aws/things/<deviceHid>/shadow/update/documents";
    final static String SHADOW_UPDATE_DOCUMENTS_TOPIC_WILDCARD = "$aws/things/+/shadow/update/documents";
    final static String SHADOW_UPDATE_DOCUMENTS_TOPIC_REGEX = "\\$aws/things/\\w+/shadow/update/documents";

    static String shadowUpdateDocumentsTopic(String deviceHid) {
        return SHADOW_UPDATE_DOCUMENTS_TOPIC.replace("<deviceHid>", deviceHid);
    }

    final static String SHADOW_UPDATE_REJECTED_TOPIC = "$aws/things/<deviceHid>/shadow/update/rejected";
    final static String SHADOW_UPDATE_REJECTED_TOPIC_WILDCARD = "$aws/things/+/shadow/update/rejected";
    final static String SHADOW_UPDATE_REJECTED_TOPIC_REGEX = "\\$aws/things/\\w+/shadow/update/rejected";

    static String shadowUpdateRejectedTopic(String deviceHid) {
        return SHADOW_UPDATE_REJECTED_TOPIC.replace("<deviceHid>", deviceHid);
    }

    final static String SHADOW_UPDATE_DELTA_TOPIC = "$aws/things/<deviceHid>/shadow/update/delta";
    final static String SHADOW_UPDATE_DELTA_TOPIC_WILDCARD = "$aws/things/+/shadow/update/delta";
    final static String SHADOW_UPDATE_DELTA_TOPIC_REGEX = "\\$aws/things/\\w+/shadow/update/delta";

    static String shadowUpdateDeltaTopic(String deviceHid) {
        return SHADOW_UPDATE_DELTA_TOPIC.replace("<deviceHid>", deviceHid);
    }

    final static String SHADOW_GET_TOPIC = "$aws/things/<deviceHid>/shadow/get";
    final static String SHADOW_GET_TOPIC_WILDCARD = "$aws/things/+/shadow/get";
    final static String SHADOW_GET_TOPIC_REGEX = "\\$aws/things/\\w+/shadow/get";

    static String shadowGetTopic(String deviceHid) {
        return SHADOW_GET_TOPIC.replace("<deviceHid>", deviceHid);
    }

    final static String SHADOW_GET_ACCEPTED_TOPIC = "$aws/things/<deviceHid>/shadow/get/accepted";
    final static String SHADOW_GET_ACCEPTED_TOPIC_WILDCARD = "$aws/things/+/shadow/get/accepted";
    final static String SHADOW_GET_ACCEPTED_TOPIC_REGEX = "\\$aws/things/\\w+/shadow/get/accepted";

    static String shadowGetAcceptedTopic(String deviceHid) {
        return SHADOW_GET_ACCEPTED_TOPIC.replace("<deviceHid>", deviceHid);
    }

    final static String SHADOW_GET_REJECTED_TOPIC = "$aws/things/<deviceHid>/shadow/get/rejected";
    final static String SHADOW_GET_REJECTED_TOPIC_WILDCARD = "$aws/things/+/shadow/get/rejected";
    final static String SHADOW_GET_REJECTED_TOPIC_REGEX = "\\$aws/things/\\w+/shadow/get/rejected";

    static String shadowGetRejectedTopic(String deviceHid) {
        return SHADOW_GET_REJECTED_TOPIC.replace("<deviceHid>", deviceHid);
    }

    final static String SHADOW_DELETE_TOPIC = "$aws/things/<deviceHid>/shadow/delete";
    final static String SHADOW_DELETE_TOPIC_WILDCARD = "$aws/things/+/shadow/delete";
    final static String SHADOW_DELETE_TOPIC_REGEX = "\\$aws/things/\\w+/shadow/delete";

    static String shadowDeleteTopic(String deviceHid) {
        return SHADOW_DELETE_TOPIC.replace("<deviceHid>", deviceHid);
    }

    final static String SHADOW_DELETE_ACCEPTED_TOPIC = "$aws/things/<deviceHid>/shadow/delete/accepted";
    final static String SHADOW_DELETE_ACCEPTED_TOPIC_WILDCARD = "$aws/things/+/shadow/delete/accepted";
    final static String SHADOW_DELETE_ACCEPTED_TOPIC_REGEX = "\\$aws/things/\\w+/shadow/delete/accepted";

    static String shadowDeleteAcceptedTopic(String deviceHid) {
        return SHADOW_DELETE_ACCEPTED_TOPIC.replace("<deviceHid>", deviceHid);
    }

    final static String SHADOW_DELETE_REJECTED_TOPIC = "$aws/things/<deviceHid>/shadow/delete/rejected";
    final static String SHADOW_DELETE_REJECTED_TOPIC_WILDCARD = "$aws/things/+/shadow/delete/rejected";
    final static String SHADOW_DELETE_REJECTED_TOPIC_REGEX = "\\$aws/things/\\w+/shadow/delete/rejected";

    static String shadowDeleteRejectedTopic(String deviceHid) {
        return SHADOW_DELETE_REJECTED_TOPIC.replace("<deviceHid>", deviceHid);
    }

    final static String JOB_GET_JOB_ID_TOPIC = "$aws/things/<deviceHid>/jobs/<jobId>/get";
    final static String JOB_GET_JOB_ID_TOPIC_WILDCARD = "$aws/things/+/jobs/+/get";
    final static String JOB_GET_JOB_ID_TOPIC_REGEX = "\\$aws/things/\\w+/jobs/\\w+/get";

    final static String JOB_GET_JOB_ID_ACCEPTED_TOPIC = "$aws/things/<deviceHid>/jobs/<jobId>/get/accepted";
    final static String JOB_GET_JOB_ID_ACCEPTED_TOPIC_WILDCARD = "$aws/things/+/jobs/+/get/accepted";
    final static String JOB_GET_JOB_ID_ACCEPTED_TOPIC_REGEX = "\\$aws/things/\\w+/jobs/\\w+/get/accepted";

    final static String JOB_GET_JOB_ID_REJECTED_TOPIC = "$aws/things/<deviceHid>/jobs/<jobId>/get/rejected";
    final static String JOB_GET_JOB_ID_REJECTTED_TOPIC_WILDCARD = "$aws/things/+/jobs/+/get/rejected";
    final static String JOB_GET_JOB_ID_REJECTTED_TOPIC_REGEX = "\\$aws/things/\\w+/jobs/\\w+/get/rejected";

    final static String JOB_UPDATE_JOB_ID_TOPIC = "$aws/things/<deviceHid>/jobs/<jobId>/update";
    final static String JOB_UPDATE_JOB_ID_TOPIC_WILDCARD = "$aws/things/+/jobs/+/update";
    final static String JOB_UPDATE_JOB_ID_TOPIC_REGEX = "\\$aws/things/\\w+/jobs/\\w+/update";

    final static String JOB_UPDATE_JOB_ID_ACCEPTED_TOPIC = "$aws/things/<deviceHid>/jobs/<jobId>/update/accepted";
    final static String JOB_UPDATE_JOB_ID_ACCEPTED_TOPIC_WILDCARD = "$aws/things/+/jobs/+/update/accepted";
    final static String JOB_UPDATE_JOB_ID_ACCEPTED_TOPIC_REGEX = "\\$aws/things/\\w+/jobs/\\w+/update/accepted";

    final static String JOB_UPDATE_JOB_ID_REJECTED_TOPIC = "$aws/things/<deviceHid>/jobs/<jobId>/update/rejected";
    final static String JOB_UPDATE_JOB_ID_REJECTTED_TOPIC_WILDCARD = "$aws/things/+/jobs/+/update/rejected";
    final static String JOB_UPDATE_JOB_ID_REJECTTED_TOPIC_REGEX = "\\$aws/things/\\w+/jobs/\\w+/update/rejected";

    final static String JOB_NOTIFY_TOPIC = "$aws/things/<deviceHid>/jobs/notify";
    final static String JOB_NOTIFY_TOPIC_WILDCARD = "$aws/things/+/jobs/notify";
    final static String JOB_NOTIFY_TOPIC_REGEX = "\\$aws/things/\\w+/jobs/notify";

    final static String JOB_NOTIFY_NEXT_TOPIC = "$aws/things/<deviceHid>/jobs/notify-next";
    final static String JOB_NOTIFY_NEXT_TOPIC_WILDCARD = "$aws/things/+/jobs/notify-next";
    final static String JOB_NOTIFY_NEXT_TOPIC_REGEX = "\\$aws/things/\\w+/jobs/notify-next";

    final static String JOB_START_NEXT_TOPIC = "$aws/things/<deviceHid>/jobs/start-next";
    final static String JOB_START_NEXT_TOPIC_WILDCARD = "$aws/things/+/jobs/start-next";
    final static String JOB_START_NEXT_TOPIC_REGEX = "\\$aws/things/\\w+/jobs/start-next";

    final static int QOS = 1;
}
