package com.arrow.acn.client.model;

import java.util.List;

import com.arrow.acs.client.model.DefinitionModelAbstract;

public class GlobalActionTypeModel extends DefinitionModelAbstract<GlobalActionTypeModel> {

	private static final long serialVersionUID = -8974718291650615443L;

	private String applicationHid;
	private String systemName;
	private boolean editable;
	private List<GlobalActionTypeParameterModel> parameters;

	@Override
	protected GlobalActionTypeModel self() {
		return this;
	}

	public String getApplicationHid() {
		return applicationHid;
	}

	public void setApplicationHid(String applicationHid) {
		this.applicationHid = applicationHid;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public List<GlobalActionTypeParameterModel> getParameters() {
		return parameters;
	}

	public void setParameters(List<GlobalActionTypeParameterModel> parameters) {
		this.parameters = parameters;
	}
}
