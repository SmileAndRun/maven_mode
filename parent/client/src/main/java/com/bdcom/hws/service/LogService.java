package com.bdcom.hws.service;

import org.common.model.Log;



public interface LogService {
	public int getLastMaxId();
	public int insertLog(Log log);
}
