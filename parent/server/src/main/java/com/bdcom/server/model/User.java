package com.bdcom.server.model;

import java.sql.Timestamp;
import java.util.List;


public class User {
	
	private String userName;
	private String userPwd;
	private char uIsLock;
	private int userId;
	private byte[] uSalt;
	private Timestamp logTime;
	public Timestamp getLoginTime() {
		return logTime;
	}
	public void setLoginTime(Timestamp loginTime) {
		this.logTime = loginTime;
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
