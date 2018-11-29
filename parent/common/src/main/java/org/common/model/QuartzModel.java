package org.common.model;

public class QuartzModel {
	private String JOB_NAME;
	private String JOB_GROUP;
	private String TRIGGER_NAME;
	private String TRIGGER_GROUP;
	private String START_TIME;
	private String END_TIME;
	private String CRON_EXPRESSION;
	private String TRIGGER_STATE;
	/*待定字段*/
	private Object UNDETERMINED;
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
	public String getSTART_TIME() {
		return START_TIME;
	}
	public void setSTART_TIME(String sTART_TIME) {
		START_TIME = sTART_TIME;
	}
	public String getEND_TIME() {
		return END_TIME;
	}
	public void setEND_TIME(String eND_TIME) {
		END_TIME = eND_TIME;
	}
	public String getCRON_EXPRESSION() {
		return CRON_EXPRESSION;
	}
	public void setCRON_EXPRESSION(String cRON_EXPRESSION) {
		CRON_EXPRESSION = cRON_EXPRESSION;
	}
	public Object getUNDETERMINED() {
		return UNDETERMINED;
	}
	public void setUNDETERMINED(Object uNDETERMINED) {
		UNDETERMINED = uNDETERMINED;
	}
	public QuartzModel(String jobName,String jobGroup) {
		this.JOB_NAME = jobName;
		this.JOB_GROUP = jobGroup;
	}
	public QuartzModel() {
		super();
	}
}
