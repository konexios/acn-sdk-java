package com.arrow.acn.client.model;

import java.io.Serializable;

public class CreateConfigBackupModel implements Serializable {

	private static final long serialVersionUID = -8493723677663189931L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
