package com.hws.oa.service;

import java.util.List;

import org.hibernate.Session;

public interface HQLService {

	public Session getHSession();
	public <T> List<T> getEntitiesBySql(Class<T> entitClazz, String sql,Object... args) ;
	public <T> List<T> getEntitiesBySqlByProx(Class<T> entitClazz, String sql,Object... args) ;
	public <T> T getEntityBySql(Class<T> clazz, String sql, Object... params) ;
	public boolean updateBySql(String sql,Object... args);
}
