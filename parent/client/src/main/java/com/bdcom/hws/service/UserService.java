package com.bdcom.hws.service;

import java.util.List;

import com.bdcom.hws.model.User;

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
	
}
