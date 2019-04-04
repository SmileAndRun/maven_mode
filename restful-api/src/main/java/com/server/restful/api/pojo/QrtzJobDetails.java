package com.server.restful.api.pojo;

public class QrtzJobDetails {
	private String SCHED_NAME;
	private String JOB_NAME;
	private String JOB_GROUP;
	private String DESCRIPTION;
	private String JOB_CLASS_NAME;
	private String IS_DURABLE;
	private String IS_NONCONCURRENT;
	private String IS_UPDATE_DATA;
	private String REQUESTS_RECOVERY;
	private byte[] JOB_DATA;
	private QrtzTriggers qrtzTriggers;
	
	public QrtzTriggers getQrtzTriggers() {
		return qrtzTriggers;
	}
	public void setQrtzTriggers(QrtzTriggers qrtzTriggers) {
		this.qrtzTriggers = qrtzTriggers;
	}
	public String getSCHED_NAME() {
		return SCHED_NAME;
	}
	public void setSCHED_NAME(String sCHED_NAME) {
		SCHED_NAME = sCHED_NAME;
	}
	public String getJOB_NAME() {
		return JOB_NAME;
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
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	public String getJOB_CLASS_NAME() {
		return JOB_CLASS_NAME;
	}
	public void setJOB_CLASS_NAME(String jOB_CLASS_NAME) {
		JOB_CLASS_NAME = jOB_CLASS_NAME;
	}
	public String getIS_DURABLE() {
		return IS_DURABLE;
	}
	public void setIS_DURABLE(String iS_DURABLE) {
		IS_DURABLE = iS_DURABLE;
	}
	public String getIS_NONCONCURRENT() {
		return IS_NONCONCURRENT;
	}
	public void setIS_NONCONCURRENT(String iS_NONCONCURRENT) {
		IS_NONCONCURRENT = iS_NONCONCURRENT;
	}
	public String getIS_UPDATE_DATA() {
		return IS_UPDATE_DATA;
	}
	public void setIS_UPDATE_DATA(String iS_UPDATE_DATA) {
		IS_UPDATE_DATA = iS_UPDATE_DATA;
	}
	public String getREQUESTS_RECOVERY() {
		return REQUESTS_RECOVERY;
	}
	public void setREQUESTS_RECOVERY(String rEQUESTS_RECOVERY) {
		REQUESTS_RECOVERY = rEQUESTS_RECOVERY;
	}
	public byte[] getJOB_DATA() {
		return JOB_DATA;
	}
	public void setJOB_DATA(byte[] jOB_DATA) {
		JOB_DATA = jOB_DATA;
	}
}
