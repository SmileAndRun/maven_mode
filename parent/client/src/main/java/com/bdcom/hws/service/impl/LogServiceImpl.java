package com.bdcom.hws.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcom.hws.mapper.LogMapper;
import com.bdcom.hws.model.Log;
import com.bdcom.hws.service.LogService;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	LogMapper logMapper;
	
	@Override
	@Transactional(rollbackFor ={IllegalArgumentException.class})
	public int insertLog(Log log) {
		String temp = logMapper.getLastMaxId();
		if(temp == null) temp = "0";
		int id = Integer.parseInt(temp)+1;
		log.setLogid(String.valueOf(id));
		return logMapper.insertSelective(log);
	}
	@Override
	public String getLastMaxId() {
		
		return logMapper.getLastMaxId();
	}

}
