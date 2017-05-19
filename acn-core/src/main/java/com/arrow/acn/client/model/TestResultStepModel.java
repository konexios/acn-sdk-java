package com.arrow.acn.client.model;

import java.io.Serializable;
import java.time.Instant;

public class TestResultStepModel implements Serializable{
	private static final long serialVersionUID = 7560816543482740980L;

	private String testProcedureStepId;
	private String comment;
	private String error;
	private String status;
	private Instant started;
	private Instant ended;
	
	public String getTestProcedureStepId() {
		return testProcedureStepId;
	}

	public void setTestProcedureStepId(String testProcedureStepId) {
		this.testProcedureStepId = testProcedureStepId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Instant getStarted() {
		return started;
	}

	public void setStarted(Instant started) {
		this.started = started;
	}
	
	public Instant getEnded() {
		return ended;
	}

	public void setEnded(Instant ended) {
		this.ended = ended;
	}
}
