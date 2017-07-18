package com.arrow.acn.client.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.arrow.acs.client.model.YesNoInherit;

public class BaseDeviceConfigBackupModel implements Serializable {

	private static final long serialVersionUID = -693863482464850712L;

	private String deviceTypeHid;
	private String uid;
	private String name;
	private String userHid;
	private String nodeHid;
	private String externalId;
	private String softwareReleaseHid;
	private boolean enabled;
	private Map<String, String> info = new HashMap<>();
	private Map<String, String> properties = new HashMap<>();
	private Set<String> tags = new HashSet<>();
	private YesNoInherit persistTelemetry;
	private YesNoInherit indexTelemetry;

	public BaseDeviceConfigBackupModel withDeviceTypeHid(String deviceTypeHid) {
		setDeviceTypeHid(deviceTypeHid);
		return this;
	}

	public BaseDeviceConfigBackupModel withUid(String uid) {
		setUid(uid);
		return this;
	}

	public BaseDeviceConfigBackupModel withName(String name) {
		setName(name);
		return this;
	}

	public BaseDeviceConfigBackupModel withUserHid(String userHid) {
		setUserHid(userHid);
		return this;
	}

	public BaseDeviceConfigBackupModel withNodeHid(String nodeHid) {
		setNodeHid(nodeHid);
		return this;
	}

	public BaseDeviceConfigBackupModel withExternalId(String externalId) {
		setExternalId(externalId);
		return this;
	}

	public BaseDeviceConfigBackupModel withSoftwareRelaseHid(String softwareReleaseHid) {
		setSoftwareReleaseHid(softwareReleaseHid);
		return this;
	}

	public BaseDeviceConfigBackupModel withEnabled(boolean enabled) {
		setEnabled(enabled);
		return this;
	}

	public BaseDeviceConfigBackupModel withInfo(Map<String, String> info) {
		setInfo(info);
		return this;
	}

	public BaseDeviceConfigBackupModel withProperties(Map<String, String> properties) {
		setProperties(properties);
		return this;
	}

	public BaseDeviceConfigBackupModel withTags(Set<String> tags) {
		setTags(tags);
		return this;
	}

	public BaseDeviceConfigBackupModel withPersistTelemetry(YesNoInherit persistTelemetry) {
		setPersistTelemetry(persistTelemetry);
		return this;
	}

	public BaseDeviceConfigBackupModel withIndexTelemetry(YesNoInherit indexTelemetry) {
		setIndexTelemetry(indexTelemetry);
		return this;
	}

	public String getDeviceTypeHid() {
		return deviceTypeHid;
	}

	public void setDeviceTypeHid(String deviceTypeHid) {
		this.deviceTypeHid = deviceTypeHid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserHid() {
		return userHid;
	}

	public void setUserHid(String userHid) {
		this.userHid = userHid;
	}

	public String getNodeHid() {
		return nodeHid;
	}

	public void setNodeHid(String nodeHid) {
		this.nodeHid = nodeHid;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getSoftwareReleaseHid() {
		return softwareReleaseHid;
	}

	public void setSoftwareReleaseHid(String softwareReleaseHid) {
		this.softwareReleaseHid = softwareReleaseHid;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Map<String, String> getInfo() {
		return info;
	}

	public void setInfo(Map<String, String> info) {
		this.info = info;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public YesNoInherit getPersistTelemetry() {
		return persistTelemetry;
	}

	public void setPersistTelemetry(YesNoInherit persistTelemetry) {
		this.persistTelemetry = persistTelemetry;
	}

	public YesNoInherit getIndexTelemetry() {
		return indexTelemetry;
	}

	public void setIndexTelemetry(YesNoInherit indexTelemetry) {
		this.indexTelemetry = indexTelemetry;
	}
}
