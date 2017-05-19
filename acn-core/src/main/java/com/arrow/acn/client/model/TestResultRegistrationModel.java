package com.arrow.acn.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestResultRegistrationModel implements Serializable  {
	private static final long serialVersionUID = 6175664500208558852L;
	//private DeviceCategory category; 
	private String objectHid;
	private String status;
	private String testProcedureHid;
	private List<TestResultStepModel> steps = new ArrayList<>();

	public String getTestProcedureHid() {
		return testProcedureHid;
	}

	public void setTestProcedureHid(String testProcedureId) {
		this.testProcedureHid = testProcedureId;
	}

	public TestResultRegistrationModel withTestProcedureHid(String testProcedureId) {
		this.testProcedureHid = testProcedureId;
		return this;
	}

	public String getObjectHid() {
		return objectHid;
	}

	public void setObjectHid(String objectId) {
		this.objectHid = objectId;
	}

	public TestResultRegistrationModel withObjectHid(String objectId) {
		this.objectHid = objectId;
		return this;
	}

	/*public DeviceCategory getCategory() {
        return category;
    }

    public void setCategory(DeviceCategory category) {
        this.category = category;
    }

    public TestResultCommonModel withCategory(DeviceCategory category) {
        this.category = category;
        return this;
    }*/

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TestResultRegistrationModel withStatus(String status) {
		this.status = status;
		return this;
	}

	public List<TestResultStepModel> getSteps() {
		return steps;
	}

	public void setSteps(List<TestResultStepModel> steps) {
		this.steps = steps;
	}
}
