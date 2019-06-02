package com.hws.oa.model;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Arrays;

public class QuartzModel {
	//QrtzCronTriggers
	private String TIME_ZONE_ID;
	//QrtzFiredTriggers
	private String ENTRY_ID;
	private String INSTANCE_NAME;
	private BigInteger FIRED_TIME;
	private BigInteger SCHED_TIME;
	private String STATE;
	//QrtzJobData
	private int DATAID;
	private String JOBNAME;
	private Timestamp EXCUTETIME;
	private String JOBDATA;
	private String JOBCLASS;
	//QrtzJobDetails
	private String IS_DURABLE;
	private String IS_NONCONCURRENT;
	private String IS_UPDATE_DATA;
	private String REQUESTS_RECOVERY;
	//QrtzTriggers
	private String SCHED_NAME;
	private String DESCRIPTION;
	private String NEXT_FIRE_TIME;
	private String PREV_FIRE_TIME;
	private int PRIORITY;
	private String TRIGGER_TYPE;
	private String CALENDAR_NAME;
	private int MISFIRE_INSTR;
	private byte[] JOB_DATA;
	//QuartzModel
	private String JOB_NAME;
	private String JOB_GROUP;
	private String TRIGGER_NAME;
	private String TRIGGER_GROUP;
	private String START_TIME;
	private String END_TIME;
	private String CRON_EXPRESSION;
	private String TRIGGER_STATE;
	private String JOB_CLASS_NAME;
	/*备用字段*/
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
	public String getJOB_CLASS_NAME() {
		return JOB_CLASS_NAME;
	}
	public void setJOB_CLASS_NAME(String jOB_CLASS_NAME) {
		JOB_CLASS_NAME = jOB_CLASS_NAME;
	}
	public QuartzModel() {
		super();
	}
	public String getTIME_ZONE_ID() {
		return TIME_ZONE_ID;
	}
	public void setTIME_ZONE_ID(String tIME_ZONE_ID) {
		TIME_ZONE_ID = tIME_ZONE_ID;
	}
	public String getENTRY_ID() {
		return ENTRY_ID;
	}
	public void setENTRY_ID(String eNTRY_ID) {
		ENTRY_ID = eNTRY_ID;
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
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	public int getDATAID() {
		return DATAID;
	}
	public void setDATAID(int dATAID) {
		DATAID = dATAID;
	}
	public String getJOBNAME() {
		return JOBNAME;
	}
	public void setJOBNAME(String jOBNAME) {
		JOBNAME = jOBNAME;
	}
	public Timestamp getEXCUTETIME() {
		return EXCUTETIME;
	}
	public void setEXCUTETIME(Timestamp eXCUTETIME) {
		EXCUTETIME = eXCUTETIME;
	}
	public String getJOBDATA() {
		return JOBDATA;
	}
	public void setJOBDATA(String jOBDATA) {
		JOBDATA = jOBDATA;
	}
	public String getJOBCLASS() {
		return JOBCLASS;
	}
	public void setJOBCLASS(String jOBCLASS) {
		JOBCLASS = jOBCLASS;
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
	public String getSCHED_NAME() {
		return SCHED_NAME;
	}
	public void setSCHED_NAME(String sCHED_NAME) {
		SCHED_NAME = sCHED_NAME;
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
	public void setNEXT_FIRE_TIME(String nEXT_FIRE_TIME) {
		NEXT_FIRE_TIME = nEXT_FIRE_TIME;
	}
	public String getPREV_FIRE_TIME() {
		return PREV_FIRE_TIME;
	}
	public void setPREV_FIRE_TIME(String pREV_FIRE_TIME) {
		PREV_FIRE_TIME = pREV_FIRE_TIME;
	}
	public int getPRIORITY() {
		return PRIORITY;
	}
	public void setPRIORITY(int pRIORITY) {
		PRIORITY = pRIORITY;
	}
	public String getTRIGGER_TYPE() {
		return TRIGGER_TYPE;
	}
	public void setTRIGGER_TYPE(String tRIGGER_TYPE) {
		TRIGGER_TYPE = tRIGGER_TYPE;
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
	@Override
	public String toString() {
		return "QuartzModel [TIME_ZONE_ID=" + TIME_ZONE_ID + ", ENTRY_ID=" + ENTRY_ID + ", INSTANCE_NAME="
				+ INSTANCE_NAME + ", FIRED_TIME=" + FIRED_TIME + ", SCHED_TIME=" + SCHED_TIME + ", STATE=" + STATE
				+ ", DATAID=" + DATAID + ", JOBNAME=" + JOBNAME + ", EXCUTETIME=" + EXCUTETIME + ", JOBDATA=" + JOBDATA
				+ ", JOBCLASS=" + JOBCLASS + ", IS_DURABLE=" + IS_DURABLE + ", IS_NONCONCURRENT=" + IS_NONCONCURRENT
				+ ", IS_UPDATE_DATA=" + IS_UPDATE_DATA + ", REQUESTS_RECOVERY=" + REQUESTS_RECOVERY + ", SCHED_NAME="
				+ SCHED_NAME + ", DESCRIPTION=" + DESCRIPTION + ", NEXT_FIRE_TIME=" + NEXT_FIRE_TIME
				+ ", PREV_FIRE_TIME=" + PREV_FIRE_TIME + ", PRIORITY=" + PRIORITY + ", TRIGGER_TYPE=" + TRIGGER_TYPE
				+ ", CALENDAR_NAME=" + CALENDAR_NAME + ", MISFIRE_INSTR=" + MISFIRE_INSTR + ", JOB_DATA="
				+ Arrays.toString(JOB_DATA) + ", JOB_NAME=" + JOB_NAME + ", JOB_GROUP=" + JOB_GROUP + ", TRIGGER_NAME="
				+ TRIGGER_NAME + ", TRIGGER_GROUP=" + TRIGGER_GROUP + ", START_TIME=" + START_TIME + ", END_TIME="
				+ END_TIME + ", CRON_EXPRESSION=" + CRON_EXPRESSION + ", TRIGGER_STATE=" + TRIGGER_STATE
				+ ", JOB_CLASS_NAME=" + JOB_CLASS_NAME + ", UNDETERMINED=" + UNDETERMINED + "]";
	}
}
