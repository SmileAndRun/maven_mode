package com.hws.oa.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="t_log")
public class LogModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7922467487036993012L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int logId;
	//1:update,2:package,3:delete file  TODO
	private int logType;
	private String logMessage;
	private Timestamp logTime;
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public int getLogType() {
		return logType;
	}
	public void setLogType(int logType) {
		this.logType = logType;
	}
	public String getLogMessage() {
		return logMessage;
	}
	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}
	public Timestamp getLogTime() {
		return logTime;
	}
	public void setLogTime(Timestamp logTime) {
		this.logTime = logTime;
	}
}
