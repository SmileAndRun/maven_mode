package org.common.model.server;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.common.model.Role;


public class User {
	
	private String userName;
	private String userPwd;
	private char uIsLock;
	private int userId;
	private byte[] uSalt;
	private Timestamp logTime;
	private String time;
	//主要为了前台显示
	private boolean flag;
	//为了前台字符上色
	private String uId;
	
	
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public Timestamp getLogTime() {
		return logTime;
	}
	public void setLogTime(Timestamp logTime) {
		this.logTime = logTime;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		time = sdf.format(new Date(logTime.getTime()));
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	private List<Role> roleList;
	
	
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public byte[] getuSalt() {
		return uSalt;
	}
	public void setuSalt(byte[] uSalt) {
		this.uSalt = uSalt;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public char getuIsLock() {
		return uIsLock;
	}
	public void setuIsLock(char uIsLock) {
		this.uIsLock = uIsLock;
		flag = true;
		if(uIsLock == '0')flag = false;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public User() {
		super();
	}
}
