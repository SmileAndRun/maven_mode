package org.common.model.client;

import java.util.List;

import org.common.model.Role;





public class User {
	
	private String userName;
	private String userPwd;
	private char uIsLock;
	private int userId;
	private byte[] uSalt;
	private char uIsManage;
	private List<Role> roleList;
	
	
	public char getuIsManage() {
		return uIsManage;
	}
	public void setuIsManage(char uIsManage) {
		this.uIsManage = uIsManage;
	}
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
