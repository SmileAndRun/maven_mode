package com.bdcom.hws.service;

import com.bdcom.hws.model.Log;


public interface LogService {
	public String getLastMaxId();
	public int insertLog(Log log);
}
