package com.bdcom.hws.service.impl;

import java.util.List;

import org.common.core.annotation.TargetDataSource;
import org.common.core.datasource.DatabaseType;
import org.common.model.client.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdcom.hws.mapper.UserMapper;
import com.bdcom.hws.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getUserByUid(int uId) {
		
		return userMapper.getUserByUid(uId);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<User> getUsers() {
		return userMapper.getUsers();
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getUserByUname(String uName) {
		return userMapper.getUserByUname(uName);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<User> getUser(User user) {
		return null;
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public boolean registerUser(User user) {
		if(userMapper.registerUser(user)==0)return false;
		return true;
		
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public boolean changeUser(User user) {
		if(userMapper.changeUser(user)==0)return false;
		return true;
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public boolean deleteUserByUid(int uId) {
		if(userMapper.deleteUserByUid(uId)==0)return false;
		return true;
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public boolean deleteUserByUname(String uName) {
		if(userMapper.deleteUserByUname(uName)==0)return false;
		return true;
	}


	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getUserInfoByUid(int userId) {
		return userMapper.getUserInfoByUid(userId);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getUserInfoByUname(String uName) {
		return userMapper.getUserInfoByUname(uName);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public User getUserSalt(String userName) {
		return userMapper.getSaltByUname(userName);
	}

}
