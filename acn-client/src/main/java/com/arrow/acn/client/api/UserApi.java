package com.arrow.acn.client.api;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import com.arrow.acn.client.model.AuthRequestModel;
import com.arrow.acn.client.model.AuthResponseModel;
import com.arrow.acn.client.model.ChangePasswordRequestModel;
import com.arrow.acn.client.model.ChangePasswordResponseModel;
import com.arrow.acn.client.model.CreateUserRequestModel;
import com.arrow.acn.client.model.CreateUserResponseModel;
import com.arrow.acn.client.model.FindUserRequestModel;
import com.arrow.acn.client.model.FindUserResponseModel;
import com.arrow.acn.client.model.ResetPasswordRequestModel;
import com.arrow.acn.client.model.ResetPasswordResponseModel;
import com.arrow.acn.client.model.UpdateUserRequestModel;
import com.arrow.acn.client.model.UpdateUserResponseModel;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.api.MqttHttpChannel;

public class UserApi extends ApiAbstract {
    private static final String USER_BASE_URL = API_BASE + "/users";
    private static final String CREATE_URL = USER_BASE_URL;
    private static final String UPDATE_URL = USER_BASE_URL + "/{hid}";
    private static final String RESET_PASSWORD_URL = USER_BASE_URL + "/{hid}/reset-password";
    private static final String CHANGE_PASSWORD_URL = USER_BASE_URL + "/{hid}/change-password";
    private static final String AUTH_URL = USER_BASE_URL + "/auth";
    private static final String FIND_URL = USER_BASE_URL + "/find";

    private static final Pattern PATTERN = Pattern.compile("{hid}", Pattern.LITERAL);

    UserApi(ApiConfig apiConfig, MqttHttpChannel mqttHttpChannel) {
        super(apiConfig, mqttHttpChannel);
    }

    public AuthResponseModel auth(AuthRequestModel model) {
        String method = "auth";
        try {
            URI uri = buildUri(AUTH_URL);
            AuthResponseModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), AuthResponseModel.class);
            log(method, result);
            return result;
        } catch (Throwable e) {
            throw handleException(e);
        }
    }

    public CreateUserResponseModel create(CreateUserRequestModel model) {
        String method = "create";
        try {
            URI uri = buildUri(CREATE_URL);
            CreateUserResponseModel result = execute(new HttpPost(uri), JsonUtils.toJson(model),
                    CreateUserResponseModel.class);
            log(method, result);
            return result;
        } catch (Throwable e) {
            throw handleException(e);
        }
    }

    public UpdateUserResponseModel update(String hid, UpdateUserRequestModel model) {
        String method = "update";
        try {
            URI uri = buildUri(PATTERN.matcher(UPDATE_URL).replaceAll(Matcher.quoteReplacement(hid)));
            UpdateUserResponseModel result = execute(new HttpPut(uri), JsonUtils.toJson(model),
                    UpdateUserResponseModel.class);
            log(method, result);
            return result;
        } catch (Throwable e) {
            throw handleException(e);
        }
    }

    public ResetPasswordResponseModel resetPassword(String hid, ResetPasswordRequestModel model) {
        String method = "resetPassword";
        try {
            URI uri = buildUri(PATTERN.matcher(RESET_PASSWORD_URL).replaceAll(Matcher.quoteReplacement(hid)));
            ResetPasswordResponseModel result = execute(new HttpPost(uri), JsonUtils.toJson(model),
                    ResetPasswordResponseModel.class);
            log(method, result);
            return result;
        } catch (Throwable e) {
            throw handleException(e);
        }
    }

    public ChangePasswordResponseModel changePassword(String hid, ChangePasswordRequestModel model) {
        String method = "changePassword";
        try {
            URI uri = buildUri(PATTERN.matcher(CHANGE_PASSWORD_URL).replaceAll(Matcher.quoteReplacement(hid)));
            ChangePasswordResponseModel result = execute(new HttpPost(uri), JsonUtils.toJson(model),
                    ChangePasswordResponseModel.class);
            log(method, result);
            return result;
        } catch (Throwable e) {
            throw handleException(e);
        }
    }

    public FindUserResponseModel find(FindUserRequestModel model) {
        String method = "find";
        try {
            URI uri = buildUri(FIND_URL);
            FindUserResponseModel result = execute(new HttpPost(uri), JsonUtils.toJson(model),
                    FindUserResponseModel.class);
            log(method, result);
            return result;
        } catch (Throwable e) {
            throw handleException(e);
        }
    }
}
