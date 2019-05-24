package com.hws.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hws.oa.model.SystemModel;
import com.hws.oa.service.SystemService;

@Service
public class SystemServiceImpl implements SystemService {

	@Override
	public List<SystemModel> searchUsersAndColoring(String content, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addSystemSet(SystemModel systemModel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleSystemSet(Integer num) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateSystemSet(SystemModel systemModel) {
		// TODO Auto-generated method stub
		return false;
	}

}
