package com.server.data.mapper;

import com.server.restful.api.pojo.Permission;




public interface PermissionMapper {
	Integer deleteByPrimaryKey(Integer pid);
	Integer insert(Permission record);
	Integer insertSelective(Permission record);
    Permission selectByPrimaryKey(Integer pid);
    Integer updateByPrimaryKeySelective(Permission record);
    Integer updateByPrimaryKey(Permission record);
}