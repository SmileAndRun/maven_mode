package com.bdcom.hws.core.listener;



import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.bdcom.hws.service.LogService;
import com.bdcom.hws.service.SessionService;


@WebListener
public class OnLineCount implements HttpSessionListener{
	
 
	@Autowired
	private LogService logService;
	@Autowired
	private SessionService sessionService;
 
	private static int activeSessions = 0;// 当前活动人数
	
	@Override
	public synchronized void sessionCreated(HttpSessionEvent httpSessionEvent) {
		System.out.println("SessionCounter sessionCreater!");
		activeSessions++;
		httpSessionEvent.getSession().getServletContext().setAttribute("activeSessions", activeSessions);
 
	}
 
	@Override
	public synchronized void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		System.out.println("SessionCounter sessionDestroyed!");
		activeSessions--;
		//待定 
		//bug：出现主键重复
		/*Date date = new Date();
		Log log = new Log();
		SessionModel model = sessionService.fingIdBySessionName(httpSessionEvent.getSession().getId());
		if(model != null){
			log.setUserid(model.getUserId());
			log.setLogtype("2");
			log.setLogmessage("loginout");
			log.setLogtime(new Timestamp(date.getTime()));
			log.setLogiserror("0");
			logService.insertLog(log);
		}*/
		httpSessionEvent.getSession().getServletContext().setAttribute("activeSessions", activeSessions);
 
 
	}
	public synchronized static int getActiveSessions() {
		return activeSessions;
	}
 
	public static void setActiveSessions(int activeSessions) {
		OnLineCount.activeSessions = activeSessions;
	}


}
