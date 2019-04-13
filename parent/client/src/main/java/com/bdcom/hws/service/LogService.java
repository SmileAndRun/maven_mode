package com.bdcom.hws.service;

import com.server.restful.api.pojo.Log;




public interface LogService {
	public Log getLastMaxId();
	public int insertLog(Log log);
}
