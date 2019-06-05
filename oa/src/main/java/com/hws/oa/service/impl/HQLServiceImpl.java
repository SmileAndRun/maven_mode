package com.hws.oa.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.hws.oa.service.HQLService;


@Service
public class HQLServiceImpl implements HQLService {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Session getHSession() {
		Session session = em.unwrap(Session.class);
		return session;
	}

	@Override
	public <T> List<T> getEntitiesBySql(Class<T> entitClazz, String sql,
			Object... args) {
		Query sqlQuery = em.createNativeQuery(sql);
		if( args != null ){
			for( int i=0; i<args.length ;i++ ){
				sqlQuery.setParameter(i+1, args[i]);
			}
		}
		sqlQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(entitClazz));
		@SuppressWarnings("unchecked")
		List<T> list = sqlQuery.getResultList();
		if(null == list || list.size()==0)return null;
		return list;
	}

	//Quartz job会产生代理来执行job  
	@SuppressWarnings("deprecation")
	public <T> List<T> getEntitiesBySqlByProx(Class<T> entitClazz, String sql,
			Object... args) {
		Query sqlQuery = em.createNativeQuery(sql);
		if( args != null ){
			for( int i=0; i<args.length ;i++ ){
				sqlQuery.setParameter(i+1, args[i]);
			}
		}
		sqlQuery.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(entitClazz));
		@SuppressWarnings("unchecked")
		List<T> list = sqlQuery.getResultList();
		if(null == list || list.size()==0)return null;
		return list;
	}
	
	@Transactional
	@Modifying
	public boolean updateBySql(String sql,
			Object... args){
		Query sqlQuery = em.createNativeQuery(sql);
		if( args != null ){
			int j = 0;
			for( int i=0; i<args.length ;i++ ){
				j++;
				if(null == args[i]){
					j--;
					continue;
				} 
				sqlQuery.setParameter(j, args[i]);
			}
		}
		int num = sqlQuery.executeUpdate();
		return num ==0 ?false :true;
	}
	@Override
	public <T> T getEntityBySql(Class<T> clazz, String sql, Object... params) {
		List<T> list =getEntitiesBySql(clazz, sql, params);
		if(null == list||list.size()==0)return null;
		return list.get(0);
	}
	
}
