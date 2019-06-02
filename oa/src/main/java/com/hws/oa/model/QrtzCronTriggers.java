package com.hws.oa.model;


public class QrtzCronTriggers {
	private String SCHED_NAME;
	private String TRIGGER_NAME;
	private String TRIGGER_GROUP;
	private String CRON_EXPRESSION;
	private String TIME_ZONE_ID;
	public String getSCHED_NAME() {
		return SCHED_NAME;
	}
	public void setSCHED_NAME(String sCHED_NAME) {
		SCHED_NAME = sCHED_NAME;
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
	public String getCRON_EXPRESSION() {
		return CRON_EXPRESSION;
	}
	public void setCRON_EXPRESSION(String cRON_EXPRESSION) {
		CRON_EXPRESSION = cRON_EXPRESSION;
	}
	public String getTIME_ZONE_ID() {
		return TIME_ZONE_ID;
	}
	public void setTIME_ZONE_ID(String tIME_ZONE_ID) {
		TIME_ZONE_ID = tIME_ZONE_ID;
	}
}
