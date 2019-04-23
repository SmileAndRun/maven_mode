package org.common.model;

import java.sql.Timestamp;

public class QrtzJobData {
	private int DATAID;
	private String JOBNAME;
	private Timestamp EXCUTETIME;
	private String JOBDATA;
	private String JOBCLASS;
	
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
	public QrtzJobData() {
		super();
	}
	public QrtzJobData(int dATAId,String jOBNAME, Timestamp eXCUTETIME, String jOBDATA, String jOBCLASS) {
		super();
		DATAID = dATAId;
		JOBNAME = jOBNAME;
		EXCUTETIME = eXCUTETIME;
		JOBDATA = jOBDATA;
		JOBCLASS = jOBCLASS;
	}
}
