package com.bdcom.server.service.impl;

import java.util.List;


import org.common.core.annotation.TargetDataSource;
import org.common.core.datasource.DatabaseType;
import org.common.model.Log;
import org.common.model.Permission;
import org.common.model.Role;
import org.common.model.server.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.bdcom.server.mapper.ManagerMapper;
import com.bdcom.server.service.ManagerService;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	ManagerMapper mp;
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getUserByUid(int userId) {
		return mp.getUserByUid(userId);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getUserInfoByUid(int userId) {
		return mp.getUserInfoByUid(userId);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<User> getUsers() {
		return mp.getUsers();
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getUserByUname(String uName) {
		return mp.getUserByUname(uName);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getUserInfoByUname(String uName) {
		return mp.getUserInfoByUname(uName);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<User> getUser(User user) {
		return mp.getUser(user);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public int registerUser(User user,Log log) {
		int id = getLastMaxId()+1;
		user.setUserId(id);
		log.setLogid(mp.getLogLastMaxId()+1);
		log.setUserid(id);
		//t_log表中有user外键故需先插t_user表
		int row = mp.registerUser(user);
		mp.insertLog(log);
		return row;
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public int changeUser(User user) {
		return mp.changeUser(user);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public int deleteUserByUid(int userId) {
		return mp.deleteUserByUid(userId);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public int deleteUserByUname(String uName) {
		return mp.deleteUserByUname(uName);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getSaltByUname(String uName) {
		return mp.getSaltByUname(uName);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<User> getFuzzyUsersByUid(String searchValue) {
		return mp.getFuzzyUsersByUid(searchValue);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<User> getFuzzyUserByUname(String searchValue){
		return mp.getFuzzyUserByUname(searchValue);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<User> getFuzzyRoleByUid(String searchValue) {
		return mp.getFuzzyRoleByUid(searchValue);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<User> getFuzzyRoleByUname(String searchValue){
		return mp.getFuzzyRoleByUname(searchValue);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public int getLastMaxId() {
		return mp.getLastMaxId();
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public int insertLog(Log record) {
		return mp.insertLog(record);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public int getLogLastMaxId() {
		return mp.getLogLastMaxId();
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public JSONObject getAllRoleInfo() {
		JSONObject obj = new JSONObject();
		List<Role> roleList = mp.getAllRoleInfo();
		if(null == roleList||roleList.size()==0)return null;
		obj.put("roleList", roleList);
		return obj;
	}
	@Override
	public JSONObject changeRole(Integer roleId,  String[] preList,
			String[] preListO) {
		JSONObject obj = new JSONObject();
		boolean flag = true;
		for(String temp:preList){
			//新增
			Permission permission = mp.getMaxPermission();
			if(null == permission){
				permission = new Permission();
				permission.setpId(1);
			}else{
				permission.setpId(permission.getpId() + 1);
			}
			permission.setRoleId(roleId);
			permission.setPermission(temp);
			if(mp.addPermission(permission)==0)flag = false;
		}
		for(String temp:preListO){
			//删除
			Permission permission = new Permission();
			permission.setRoleId(roleId);
			permission.setPermission(temp);
			if(mp.deletePermission(permission)== 0)flag = false;
		}
		obj.put("flag", flag);
		return obj;
	}

}
