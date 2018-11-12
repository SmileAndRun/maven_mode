package com.bdcom.hws.service.impl;

import org.common.model.client.SessionModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdcom.hws.mapper.SessionMapper;
import com.bdcom.hws.service.SessionService;

public class SessionServiceImpl implements SessionService{

	@Autowired
	SessionMapper sessionMapper;
	
	@Override
	public int insert(SessionModel model) {
		return sessionMapper.insert(model);
	}

	@Override
	public int update(SessionModel model) {
		return sessionMapper.update(model);
	}

	@Override
	public SessionModel findId(int sessioinId) {
		return sessionMapper.findId(sessioinId);
	}

}
