package com.arrow.acn.client.api;

import java.net.URI;

import org.apache.http.client.methods.HttpGet;

import com.arrow.acn.client.AcnClientException;
import com.arrow.acn.client.model.ConfigBackupModel;
import com.arrow.acs.client.api.ApiConfig;

public class ConfigBackupApi extends ApiAbstract {

	private static final String CONFIG_BACKUPS_BASE_URL = API_BASE + "/config-backups";
	private static final String SPECIFIC_CONFIG_BACKUP_URL = CONFIG_BACKUPS_BASE_URL + "/{hid}";

	ConfigBackupApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public ConfigBackupModel findByHid(String hid) {
		String method = "findByHid";
		try {
			URI uri = buildUri(SPECIFIC_CONFIG_BACKUP_URL.replace("{hid}", hid));
			ConfigBackupModel result = execute(new HttpGet(uri), ConfigBackupModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}
}
