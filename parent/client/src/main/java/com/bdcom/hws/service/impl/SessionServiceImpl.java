package com.bdcom.hws.service.impl;

import org.common.core.annotation.TargetDataSource;
import org.common.core.datasource.DatabaseType;
import org.common.model.client.SessionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdcom.hws.mapper.SessionMapper;
import com.bdcom.hws.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService{

	@Autowired
	SessionMapper sessionMapper;
	
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public int insert(SessionModel model) {
		return sessionMapper.insert(model);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public int update(SessionModel model) {
		return sessionMapper.update(model);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public SessionModel findId(int sessioinId) {
		return sessionMapper.findId(sessioinId);
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public SessionModel fingIdBySessionName(String sessionName) {
		return sessionMapper.fingIdBySessionName(sessionName);
	}

}
