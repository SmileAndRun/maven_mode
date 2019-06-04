package com.hws.oa.model;

import java.math.BigInteger;



public class QuartzModel {
	//QuartzModel
	private String JOB_NAME;
	private String JOB_GROUP;
	private String TRIGGER_NAME;
	private String TRIGGER_GROUP;
	private BigInteger START_TIME;
	private BigInteger END_TIME;
	private String CRON_EXPRESSION;
	private String TRIGGER_STATE;
	private String JOB_CLASS_NAME;
	
	public String getJOB_NAME() {
		return JOB_NAME;
	}
	public String getTRIGGER_STATE() {
		return TRIGGER_STATE;
	}

	public void setTRIGGER_STATE(String tRIGGER_STATE) {
		TRIGGER_STATE = tRIGGER_STATE;
	}

	public void setJOB_NAME(String jOB_NAME) {
		JOB_NAME = jOB_NAME;
	}
	public String getJOB_GROUP() {
		return JOB_GROUP;
	}
	public void setJOB_GROUP(String jOB_GROUP) {
		JOB_GROUP = jOB_GROUP;
	}
	public String getTRIGGER_NAME() {
		return TRIGGER_NAME;
	}
	public void setTRIGGER_NAME(String tRIGGER_NAME) {
		TRIGGER_NAME = tRIGGER_NAME;
	}
	public String getTRIGGER_GROUP() {
		return TRIGGER_GROUP;
	}
	public void setTRIGGER_GROUP(String tRIGGER_GROUP) {
		TRIGGER_GROUP = tRIGGER_GROUP;
	}
	public BigInteger getSTART_TIME() {
		return START_TIME;
	}
	public void setSTART_TIME(BigInteger sTART_TIME) {
		
		START_TIME = sTART_TIME;
	}
	public BigInteger getEND_TIME() {
		return END_TIME;
	}
	public void setEND_TIME(BigInteger eND_TIME) {
		//TimeUtils.get(null).format(new Date(eND_TIME.longValue()))
		END_TIME = eND_TIME;
	}
	public String getCRON_EXPRESSION() {
		return CRON_EXPRESSION;
	}
	public void setCRON_EXPRESSION(String cRON_EXPRESSION) {
		CRON_EXPRESSION = cRON_EXPRESSION;
	}
	public QuartzModel(String jobName,String jobGroup) {
		this.JOB_NAME = jobName;
		this.JOB_GROUP = jobGroup;
	}
	public String getJOB_CLASS_NAME() {
		return JOB_CLASS_NAME;
	}
	public void setJOB_CLASS_NAME(String jOB_CLASS_NAME) {
		JOB_CLASS_NAME = jOB_CLASS_NAME;
	}
	public QuartzModel() {
		super();
	}
}
