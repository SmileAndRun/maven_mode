package com.server.data.mapper;

import com.server.restful.api.pojo.Role;


public interface RoleMapper {
   public Integer deleteByPrimaryKey(Integer roleid);
   public Integer insert(Role record);
   public Integer insertSelective(Role record);
   public  Role selectByPrimaryKey(Integer roleid);
   public Integer updateByPrimaryKeySelective(Role record);
   public Integer updateByPrimaryKey(Role record);
}