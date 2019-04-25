package com.bdcom.hws.core.shiro;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MySessionDao extends MemorySessionDAO {

	private static final Logger log = LoggerFactory.getLogger(MySessionDao.class);
	private Map<String,Session> beforeCache = new ConcurrentHashMap<String,Session>();
	private Map<String,Session> afterCache = new ConcurrentHashMap<String,Session>();
    private ConcurrentMap<Serializable, Session> sessions;

    public MySessionDao() {
        this.sessions = new ConcurrentHashMap<Serializable, Session>();
    }

    //重写方法
    protected Serializable doCreate(Session session) {
    	log.info("shiro开始创建session");
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        storeSession(sessionId, session);
        //获取当前用户登录名
    	String userName = String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
    	if(afterCache.containsKey(userName))
    		beforeCache.put(userName,afterCache.get(userName));
    	afterCache.put(userName,session);
    	log.info("shiro session创建完成");
        return sessionId;
    }
    public void delete(Session session) {
    	log.info("shiro开始删除session");
        if (session == null) {
            throw new NullPointerException("session argument cannot be null.");
        }
        Serializable id = session.getId();
        if (id != null) {
            sessions.remove(id);
            
            String userName = String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
            if(beforeCache.containsKey(userName))
            	beforeCache.remove(userName);
            log.info("shiro删除session完成");
        }
    }
	public Map<String, Session> getBeforeCache() {
		return beforeCache;
	}

}
