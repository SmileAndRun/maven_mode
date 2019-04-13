package com.bdcom.server.mapper;

import java.util.List;

import com.server.restful.api.pojo.Log;
import com.server.restful.api.pojo.Permission;
import com.server.restful.api.pojo.Role;
import com.server.restful.api.pojo.server.User;




public interface ManagerMapper {

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
	public List<User> getFuzzyUsersByUid(String searchValue);
	public List<User> getFuzzyUserByUname(String searchValue);
	public int getLastMaxId();
	public int insertLog(Log record);
	public int getLogLastMaxId();
	public List<Role> getAllRoleInfo();
	public List<Role> getFuzzyRoleByUid(String searchValue);
	public List<Role> getFuzzyRoleByUname(String searchValue);
	public int deletePermission(Permission permission);
	public int addPermission(Permission permission);
	public Permission getMaxPermissionId();
	public int addRole(Role role);
	public Role getMaxRoleId();
	public int deleteRole(Role role);
}
