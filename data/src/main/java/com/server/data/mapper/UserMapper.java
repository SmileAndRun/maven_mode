package com.server.data.mapper;

import java.util.List;

import com.server.restful.api.pojo.server.User;





public interface UserMapper {

	public User getUserByUid(Integer userId);
	public User getUserInfoByUid(Integer userId);
	public List<User> getUsers();
	public User getUserByUname(String uName);
	public User getUserInfoByUname(String uName);
	public List<User> getUser(User user);
	public Integer registerUser(User user);
	public Integer changeUser(User user);
	public Integer deleteUserByUid(Integer userId);
	public Integer deleteUserByUname(String uName);
	public User getSaltByUname(String uName);
}
