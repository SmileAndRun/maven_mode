package com.bdcom.hws.mapper;

import com.bdcom.hws.model.Log;

public interface LogMapper {
   public int deleteByPrimaryKey(String logid);
   public int insert(Log record);
   public int insertSelective(Log record);
   public Log selectByPrimaryKey(String logid);
   public int updateByPrimaryKeySelective(Log record);
   public int updateByPrimaryKey(Log record);
   public String getLastMaxId();
}