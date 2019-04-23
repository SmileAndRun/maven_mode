package com.bdcom.server.service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.common.model.Log;
import org.common.model.Role;
import org.common.model.server.User;
import org.dom4j.DocumentException;

import com.alibaba.fastjson.JSONObject;


public interface ManagerService {
	public User getUserByUid(int userId);
	public User getUserInfoByUid(int userId);
	public List<User> getUsers();
	public User getUserByUname(String uName);
	public User getUserInfoByUname(String uName);
	public List<User> getUser(User user);
	public int registerUser(User user,String role)throws NoSuchAlgorithmException, InvalidKeyException;
	public JSONObject changeUser(User user,String[] roleList,String[] roleListO);
	public int deleteUserByUid(int userId);
	public int deleteUserByUname(String uName);
	public User getSaltByUname(String uName);
	public List<User> getFuzzyUsersByUid(String searchValue);
	public List<User> getFuzzyUserByUname(String searchValue);
	public int getLastMaxId();
	public int insertLog(Log record);
	public int getLogLastMaxId();
	public JSONObject getAllRoleInfo();
	public List<Role> getFuzzyRoleByUid(String searchValue);
	public List<Role> getFuzzyRoleByUname(String searchValue);
	public JSONObject changeRole(Integer roleId,String[] preList,String[] preListO);
	public List<String> getElement(List<String> attribute, String path,String type) throws DocumentException,IOException;
	public void initGraphAnalysis(HttpServletRequest request);
	public List<String> getSuggestion(String context)throws IOException;
	public List<User> searchUsersAndColoring(String content,String type);
	public List<Role> searchsRoleAndColoring(String content,String type);
}
