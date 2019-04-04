package com.server.data.mapper;

import com.server.restful.api.pojo.client.SessionModel;



public interface SessionMapper {
	
	public Integer insert(SessionModel model);
	public Integer update(SessionModel model);
	public SessionModel findId(Integer sessioinId);
	public SessionModel fingIdBySessionName(String sessionName);

}
