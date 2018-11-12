package com.bdcom.hws.mapper;

import org.common.model.client.SessionModel;

public interface SessionMapper {
	
	public int insert(SessionModel model);
	public int update(SessionModel model);
	public SessionModel findId(int sessioinId);

}
