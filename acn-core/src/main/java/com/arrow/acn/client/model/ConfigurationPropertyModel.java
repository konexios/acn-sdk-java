package com.arrow.acn.client.model;

import java.io.Serializable;

public class ConfigurationPropertyModel implements Serializable {

	public enum ConfigurationPropertyCategory {
		Api, Branding, Email, Gateway, MetaData, Settings
	}

	public enum ConfigurationPropertyDataType {
		String, Integer, Long, Boolean, Json
	}

	private static final long serialVersionUID = 686843365799143882L;

	private ConfigurationPropertyDataType dataType;
	private ConfigurationPropertyCategory category;
	private String name;
	private String value;
	private String jsonClass;

	public ConfigurationPropertyModel withDataType(ConfigurationPropertyDataType dataType) {
		setDataType(dataType);
		return this;
	}

	public ConfigurationPropertyModel withCategory(ConfigurationPropertyCategory category) {
		setCategory(category);
		return this;
	}

	public ConfigurationPropertyModel withName(String name) {
		setName(name);
		return this;
	}

	public ConfigurationPropertyModel withValue(String value) {
		setValue(value);
		return this;
	}

	public ConfigurationPropertyModel withJsonClass(String jsonClass) {
		setJsonClass(jsonClass);
		return this;
	}

	public ConfigurationPropertyDataType getDataType() {
		return dataType;
	}

	public void setDataType(ConfigurationPropertyDataType dataType) {
		this.dataType = dataType;
	}

	public ConfigurationPropertyCategory getCategory() {
		return category;
	}

	public void setCategory(ConfigurationPropertyCategory category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getJsonClass() {
		return jsonClass;
	}

	public void setJsonClass(String jsonClass) {
		this.jsonClass = jsonClass;
	}
}
