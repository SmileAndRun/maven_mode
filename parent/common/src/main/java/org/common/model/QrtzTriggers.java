package org.common.model;

import java.math.BigInteger;

import org.common.utils.TimeUtils;





public class QrtzTriggers {
	private String SCHED_NAME;
	private String TRIGGER_NAME;
	private String TRIGGER_GROUP;
	private String JOB_NAME;
	private String JOB_GROUP;
	private String DESCRIPTION;
	private String NEXT_FIRE_TIME;
	private String PREV_FIRE_TIME;
	private int PRIORITY;
	private String TRIGGER_STATE;
	private String TRIGGER_TYPE;
	private String START_TIME;
	private String END_TIME;
	private String CALENDAR_NAME;
	private int MISFIRE_INSTR;
	private byte[] JOB_DATA;
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
	public String getNEXT_FIRE_TIME() {
		return NEXT_FIRE_TIME;
	}
	public void setNEXT_FIRE_TIME(BigInteger nEXT_FIRE_TIME) {
		NEXT_FIRE_TIME = TimeUtils.get(null).format(nEXT_FIRE_TIME.longValue());
	}
	public String getPREV_FIRE_TIME() {
		return PREV_FIRE_TIME;
	}
	public void setPREV_FIRE_TIME(BigInteger pREV_FIRE_TIME) {
		PREV_FIRE_TIME = TimeUtils.get(null).format(pREV_FIRE_TIME.longValue());
	}
	public int getPRIORITY() {
		return PRIORITY;
	}
	public void setPRIORITY(int pRIORITY) {
		PRIORITY = pRIORITY;
	}
	public String getTRIGGER_STATE() {
		return TRIGGER_STATE;
	}
	public void setTRIGGER_STATE(String tRIGGER_STATE) {
		TRIGGER_STATE = tRIGGER_STATE;
	}
	public String getTRIGGER_TYPE() {
		return TRIGGER_TYPE;
	}
	public void setTRIGGER_TYPE(String tRIGGER_TYPE) {
		TRIGGER_TYPE = tRIGGER_TYPE;
	}
	public String getSTART_TIME() {
		return START_TIME;
	}
	public void setSTART_TIME(BigInteger sTART_TIME) {
		START_TIME = TimeUtils.get(null).format(sTART_TIME.longValue());
	}
	public String getEND_TIME() {
		return END_TIME;
	}
	public void setEND_TIME(BigInteger eND_TIME) {
		END_TIME = TimeUtils.get(null).format(eND_TIME.longValue());
	}
	public String getCALENDAR_NAME() {
		return CALENDAR_NAME;
	}
	public void setCALENDAR_NAME(String cALENDAR_NAME) {
		CALENDAR_NAME = cALENDAR_NAME;
	}
	public int getMISFIRE_INSTR() {
		return MISFIRE_INSTR;
	}
	public void setMISFIRE_INSTR(int mISFIRE_INSTR) {
		MISFIRE_INSTR = mISFIRE_INSTR;
	}
	public byte[] getJOB_DATA() {
		return JOB_DATA;
	}
	public void setJOB_DATA(byte[] jOB_DATA) {
		JOB_DATA = jOB_DATA;
	}
}
