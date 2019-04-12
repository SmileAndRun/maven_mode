package com.bdcom.hws.mapper;

import com.server.restful.api.pojo.client.SessionModel;


public interface SessionMapper {
	
	public int insert(SessionModel model);
	public int update(SessionModel model);
	public SessionModel findId(int sessioinId);
	public SessionModel fingIdBySessionName(String sessionName);

}
