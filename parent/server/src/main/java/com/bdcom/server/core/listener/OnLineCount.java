package com.bdcom.server.core.listener;



import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.annotation.WebListener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.common.model.Log;
import org.common.model.server.User;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdcom.server.service.ManagerService;



@WebListener
public class OnLineCount implements SessionListener{
	
 
	@Autowired
	private ManagerService ms;
 
	private static int activeSessions = 0;// 当前活动人数
	
	public void insertLog(Session session){
		Date date = new Date();
		Log log = new Log();
		String userName = String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
		if(userName != null){
			User user = ms.getUserByUname(userName);
			log.setUserid(user.getUserId());
			log.setLogtype("2");
			log.setLogmessage("loginout");
			log.setLogtime(new Timestamp(date.getTime()));
			log.setLogiserror("0");
			ms.insertLog(log);
		}
	}

	
	public static int getActiveSessions() {
		return activeSessions;
	}

	@Override
	public  void onExpiration(Session session) {
		synchronized(session){
			activeSessions--;
		}
		
		insertLog(session);
		
	}

	@Override
	public  void onStart(Session session) {
		synchronized(session){
			activeSessions++;
		}
	}

	@Override
	public  void onStop(Session session) {
		synchronized(session){
			activeSessions--;
		}
		insertLog(session);
		
	}


}
