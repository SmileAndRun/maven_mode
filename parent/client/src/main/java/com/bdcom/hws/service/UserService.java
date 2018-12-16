package com.bdcom.hws.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.common.model.client.User;

import com.alibaba.fastjson.JSONObject;


public interface UserService {
	
	public User getUserByUid(int userId);
	public User getUserInfoByUid(int userId);
	public List<User> getUsers();
	public User getUserByUname(String uName);
	public User getUserInfoByUname(String uName);
	public List<User> getUser(User user);
	public boolean registerUser(User user);
	public boolean changeUser(User user);
	public boolean deleteUserByUid(int userId);
	public boolean deleteUserByUname(String uName);
	public User getUserSalt(String userName);
	public JSONObject validateAccount(HttpServletRequest request,HttpServletResponse response,boolean isRememberMe,boolean isCookie,User user) 
			throws InvalidKeyException, NoSuchAlgorithmException;
}
