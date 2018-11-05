package com.bdcom.hws.mapper;

import java.util.List;

import org.common.model.client.User;


public interface UserMapper {

	public User getUserByUid(int userId);
	public User getUserInfoByUid(int userId);
	public List<User> getUsers();
	public User getUserByUname(String uName);
	public User getUserInfoByUname(String uName);
	public List<User> getUser(User user);
	public int registerUser(User user);
	public int changeUser(User user);
	public int deleteUserByUid(int userId);
	public int deleteUserByUname(String uName);
	public User getSaltByUname(String uName);
}
