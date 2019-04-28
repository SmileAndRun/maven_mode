package com.bdcom.hws.core.shiro;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.common.model.Log;
import org.common.model.OnlineCountModel;
import org.common.model.client.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdcom.hws.service.LogService;
import com.bdcom.hws.service.UserService;


public class MySessionDao extends MemorySessionDAO {

	private static final Logger log = LoggerFactory.getLogger(MySessionDao.class);
	private Map<String,Session> beforeCache = new ConcurrentHashMap<String,Session>();
	private Map<String,Session> afterCache = new ConcurrentHashMap<String,Session>();
    private ConcurrentMap<Serializable, Session> sessions;

    public MySessionDao() {
        this.sessions = new ConcurrentHashMap<Serializable, Session>();
    }

    @Autowired
    LogService ls;
    @Autowired
    UserService us;
    
    //重写方法
    protected Serializable doCreate(Session session) {
    	log.info("shiro开始创建session");
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        storeSession(sessionId, session);
        synchronized(session){
       	 OnlineCountModel.activeSessions++;
		}
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
            log.info("shiro删除session完成");
        }
        String userName = String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
        beforeCache.remove(userName);
        synchronized(session){
        	 OnlineCountModel.activeSessions--;
		}
        Date date = new Date();
		Log log = new Log();
		if(userName != null){
			User user = us.getUserByUname(userName);
			log.setUserid(user.getUserId());
			log.setLogtype("2");
			log.setLogmessage("loginout");
			log.setLogtime(new Timestamp(date.getTime()));
			log.setLogiserror("0");
			ls.insertLog(log);
		}
    }
	public Map<String, Session> getBeforeCache() {
		return beforeCache;
	}
	public Map<String, Session> getAfterCache() {
		return afterCache;
	}

}
