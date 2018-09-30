package com.bdcom.hws.mapper;

import com.bdcom.hws.model.Log;

public interface LogMapper {
    int deleteByPrimaryKey(String logid);
    int insert(Log record);
    int insertSelective(Log record);
    Log selectByPrimaryKey(String logid);
    int updateByPrimaryKeySelective(Log record);
    int updateByPrimaryKey(Log record);
}