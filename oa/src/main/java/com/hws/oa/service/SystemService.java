package com.hws.oa.service;

import java.util.List;

import com.hws.oa.model.SystemModel;

public interface SystemService {

	public List<SystemModel> searchUsersAndColoring(String content,String type);
	public boolean addSystemSet(SystemModel systemModel);
	public boolean deleSystemSet(Integer num);
	public boolean updateSystemSet(SystemModel systemModel);
} 
