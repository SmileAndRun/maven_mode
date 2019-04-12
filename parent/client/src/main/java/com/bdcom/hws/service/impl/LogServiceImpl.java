package com.bdcom.hws.service.impl;

import org.common.core.annotation.TargetDataSource;
import org.common.core.datasource.DatabaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcom.hws.mapper.LogMapper;
import com.bdcom.hws.service.LogService;
import com.server.restful.api.pojo.Log;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	LogMapper logMapper;
	
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	@Transactional(rollbackFor ={IllegalArgumentException.class})
	public int insertLog(Log log) {
		Log temp = logMapper.getLastMaxId();
		int id = 0;
		if(null == temp){
			id = 1;
		}else{
			id = temp.getLogid() + 1;
		}
		log.setLogid(id);
		return logMapper.insertSelective(log);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Log getLastMaxId() {
		
		return logMapper.getLastMaxId();
	}

}
