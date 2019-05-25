package com.hws.oa.service;

import java.util.List;

import com.hws.oa.model.SystemModel;

public interface SystemService {

	public List<SystemModel> searchUsersAndColoring(String content,Integer type)throws NumberFormatException;
	public boolean addSystemSet(SystemModel systemModel);
	public boolean deleSystemSet(Integer num);
	public boolean updateSystemSet(SystemModel systemModel);
} 
