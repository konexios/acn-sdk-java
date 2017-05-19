package com.arrow.acn.client.model;

import java.util.ArrayList;
import java.util.List;

import com.arrow.acs.client.model.AuditableDocumentModelAbstract;

public class TestResultModel extends AuditableDocumentModelAbstract<TestResultModel> {
	private static final long serialVersionUID = 5708579470146042662L;
	//private DeviceCategory category; 
	private String objectHid;
	private String status;
	private String testProcedureHid;
	private List<TestResultStepModel> steps = new ArrayList<>();
	private String hid;

	@Override
	protected TestResultModel self() {
		return this;
	}

	public String getTestProcedureHid() {
		return testProcedureHid;
	}

	public void setTestProcedureHid(String testProcedureId) {
		this.testProcedureHid = testProcedureId;
	}

	public TestResultModel withTestProcedureHid(String testProcedureId) {
		this.testProcedureHid = testProcedureId;
		return this;
	}

	public String getObjectHid() {
		return objectHid;
	}

	public void setObjectHid(String objectId) {
		this.objectHid = objectId;
	}

	public TestResultModel withObjectHid(String objectId) {
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

	public TestResultModel withStatus(String status) {
		this.status = status;
		return this;
	}

	public List<TestResultStepModel> getSteps() {
		return steps;
	}

	public void setSteps(List<TestResultStepModel> steps) {
		this.steps = steps;
	}
	
	public String getHid() {
		return hid;
	}
	
	public void setHid(String hid) {
		this.hid = hid;
	}

	public TestResultModel withHid(String hid) {
		setHid(hid);
		return this;
	}
}
