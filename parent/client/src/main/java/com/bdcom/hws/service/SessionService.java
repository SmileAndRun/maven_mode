package com.bdcom.hws.service;

import org.common.model.client.SessionModel;


public interface SessionService {
	public int insert(SessionModel model);
	public int update(SessionModel model);
	public SessionModel findId(int sessioinId);
	public SessionModel fingIdBySessionName(String sessionName);

}
