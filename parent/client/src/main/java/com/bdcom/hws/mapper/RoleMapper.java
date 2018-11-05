package com.bdcom.hws.mapper;

import org.common.model.Role;


public interface RoleMapper {
   public int deleteByPrimaryKey(Integer roleid);
   public int insert(Role record);
   public int insertSelective(Role record);
   public  Role selectByPrimaryKey(Integer roleid);
   public int updateByPrimaryKeySelective(Role record);
   public int updateByPrimaryKey(Role record);
}