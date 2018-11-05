package org.common.model;

import java.sql.Timestamp;
import java.util.Date;

public class Log {
    private int logid;
    private Integer userid;
    private String logtype;
    private String logmessage;
    private String logiserror;
    private Timestamp	 logtime;
    
    public int getLogid() {
        return logid;
    }
    public void setLogid(int logid) {
        this.logid = logid;
    }
    public Integer getUserid() {
        return userid;
    }
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    public String getLogtype() {
        return logtype;
    }
    public void setLogtype(String logtype) {
        this.logtype = logtype;
    }
    public String getLogmessage() {
        return logmessage;
    }
    public void setLogmessage(String logmessage) {
        this.logmessage = logmessage;
    }
    public String getLogiserror() {
        return logiserror;
    }
    public void setLogiserror(String logiserror) {
        this.logiserror = logiserror;
    }
    public Date getLogtime() {
        return logtime;
    }
    public void setLogtime(Timestamp logtime) {
        this.logtime = logtime;
    }
}