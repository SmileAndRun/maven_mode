package com.server.data.mapper;

import com.server.restful.api.pojo.Log;




public interface LogMapper {
   public Integer deleteByPrimaryKey(String logid);
   public Integer insert(Log record);
   public Integer insertSelective(Log record);
   public Log selectByPrimaryKey(String logid);
   public Integer updateByPrimaryKeySelective(Log record);
   public Integer updateByPrimaryKey(Log record);
   public Log getLastMaxId();
}