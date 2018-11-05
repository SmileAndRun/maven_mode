package com.bdcom.hws.service.impl;

import org.common.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcom.hws.mapper.LogMapper;
import com.bdcom.hws.service.LogService;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	LogMapper logMapper;
	
	@Override
	@Transactional(rollbackFor ={IllegalArgumentException.class})
	public int insertLog(Log log) {
		int temp = logMapper.getLastMaxId();
		int id = temp+1;
		log.setLogid(id);
		return logMapper.insertSelective(log);
	}
	@Override
	public int getLastMaxId() {
		
		return logMapper.getLastMaxId();
	}

}
