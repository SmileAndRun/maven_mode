package org.common.model;

import java.math.BigInteger;

public class QrtzFiredTriggers {
	
	private String SCHED_NAME;
	private String ENTRY_ID;
	private String TRIGGER_NAME;
	private String TRIGGER_GROUP;
	private String INSTANCE_NAME;
	private BigInteger FIRED_TIME;
	private BigInteger SCHED_TIME;
	private int PRIORITY;
	private String STATE;
	private String JOB_NAME;
	private String JOB_GROUP;
	private String IS_NONCONCURRENT;
	private String REQUESTS_RECOVERY;
	public String getSCHED_NAME() {
		return SCHED_NAME;
	}
	public void setSCHED_NAME(String sCHED_NAME) {
		SCHED_NAME = sCHED_NAME;
	}
	public String getENTRY_ID() {
		return ENTRY_ID;
	}
	public void setENTRY_ID(String eNTRY_ID) {
		ENTRY_ID = eNTRY_ID;
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
	public String getINSTANCE_NAME() {
		return INSTANCE_NAME;
	}
	public void setINSTANCE_NAME(String iNSTANCE_NAME) {
		INSTANCE_NAME = iNSTANCE_NAME;
	}
	public BigInteger getFIRED_TIME() {
		return FIRED_TIME;
	}
	public void setFIRED_TIME(BigInteger fIRED_TIME) {
		FIRED_TIME = fIRED_TIME;
	}
	public BigInteger getSCHED_TIME() {
		return SCHED_TIME;
	}
	public void setSCHED_TIME(BigInteger sCHED_TIME) {
		SCHED_TIME = sCHED_TIME;
	}
	public int getPRIORITY() {
		return PRIORITY;
	}
	public void setPRIORITY(int pRIORITY) {
		PRIORITY = pRIORITY;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
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
	public String getIS_NONCONCURRENT() {
		return IS_NONCONCURRENT;
	}
	public void setIS_NONCONCURRENT(String iS_NONCONCURRENT) {
		IS_NONCONCURRENT = iS_NONCONCURRENT;
	}
	public String getREQUESTS_RECOVERY() {
		return REQUESTS_RECOVERY;
	}
	public void setREQUESTS_RECOVERY(String rEQUESTS_RECOVERY) {
		REQUESTS_RECOVERY = rEQUESTS_RECOVERY;
	}
}
