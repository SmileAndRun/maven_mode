package com.bdcom.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcom.server.mapper.ManagerMapper;
import com.bdcom.server.model.User;
import com.bdcom.server.service.ManagerService;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	ManagerMapper mp;
	
	@Override
	public User getUserByUid(int userId) {
		return mp.getUserByUid(userId);
	}
	@Override
	public User getUserInfoByUid(int userId) {
		return mp.getUserInfoByUid(userId);
	}
	@Override
	public List<User> getUsers() {
		return mp.getUsers();
	}
	@Override
	public User getUserByUname(String uName) {
		return mp.getUserByUname(uName);
	}
	@Override
	public User getUserInfoByUname(String uName) {
		return mp.getUserInfoByUname(uName);
	}
	@Override
	public List<User> getUser(User user) {
		return mp.getUser(user);
	}
	@Override
	public int registerUser(User user) {
		return mp.registerUser(user);
	}
	@Override
	public int changeUser(User user) {
		return mp.changeUser(user);
	}
	@Override
	public int deleteUserByUid(int userId) {
		return mp.deleteUserByUid(userId);
	}
	@Override
	public int deleteUserByUname(String uName) {
		return mp.deleteUserByUname(uName);
	}
	@Override
	public User getSaltByUname(String uName) {
		return mp.getSaltByUname(uName);
	}

}
