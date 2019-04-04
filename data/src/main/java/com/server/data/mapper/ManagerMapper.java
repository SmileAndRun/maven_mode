package com.server.data.mapper;

import java.util.List;

import com.server.restful.api.pojo.Log;
import com.server.restful.api.pojo.Permission;
import com.server.restful.api.pojo.Role;
import com.server.restful.api.pojo.server.User;






public interface ManagerMapper {

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
	public List<User> getFuzzyUsersByUid(String searchValue);
	public List<User> getFuzzyUserByUname(String searchValue);
	public Integer getLastMaxId();
	public Integer insertLog(Log record);
	public Integer getLogLastMaxId();
	public List<Role> getAllRoleInfo();
	public List<Role> getFuzzyRoleByUid(String searchValue);
	public List<Role> getFuzzyRoleByUname(String searchValue);
	public Integer deletePermission(Permission permission);
	public Integer addPermission(Permission permission);
	public Permission getMaxPermissionId();
	public Integer addRole(Role role);
	public Role getMaxRoleId();
	public Integer deleteRole(Role role);
}
