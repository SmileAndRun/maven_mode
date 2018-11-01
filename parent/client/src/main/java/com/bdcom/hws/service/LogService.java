package com.bdcom.hws.service;

import com.bdcom.hws.model.Log;


public interface LogService {
	public int getLastMaxId();
	public int insertLog(Log log);
}
