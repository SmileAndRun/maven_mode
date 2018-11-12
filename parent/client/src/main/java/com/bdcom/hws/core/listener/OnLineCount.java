package com.bdcom.hws.core.listener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


@WebListener
public class OnLineCount implements HttpSessionListener,ServletRequestListener {
	private static int activeSessions = 0;// 当前活动人数
	private HttpServletRequest request;
	private static List<String> list = new ArrayList<String>();// 用于存放不同的ip地址
 
	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
	}
 
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		request=(HttpServletRequest)sre.getServletRequest();
	}
 
	@Override
	public synchronized void sessionCreated(HttpSessionEvent httpSessionEvent) {
		System.out.println("SessionCounter sessionCreater!");
        String sessionId = httpSessionEvent.getSession().getId();
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        String loginIp = request.getRemoteAddr();
        boolean rs = true;
        if(list.size() > 0){
            for(int i = 0;i < list.size(); i ++){
                if(loginIp.equals(list.get(i))){
                    rs = false;
                }
            }
        }
        if(rs){                      //如果队列中存在相同的IP 则SESSION不增加
           list.add(loginIp);
           System.out.println("ipList队列新增ip: "+loginIp);
           activeSessions++;
           httpSessionEvent.getSession().getServletContext().setAttribute("activeSessions", activeSessions);
           System.out.println("新增SESSION,sessionId = " + sessionId +"; createTime = " + createTime
                             + "; loginIp = " + loginIp +"; 当前总SESSION值为 "+activeSessions);
        }
 
	}
 
	@Override
	public synchronized void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		System.out.println("SessionCounter sessionDestroyed!");
        String sessionId = httpSessionEvent.getSession().getId();
        Timestamp overTime = new Timestamp(System.currentTimeMillis());
        String loginIp = request.getRemoteAddr();
        if(activeSessions>0){
            if(list.size() > 0){
                for(int i = 0;i < list.size(); i ++){
                    if(loginIp.equals(list.get(i))){
                        list.remove(i);  
                        System.out.println("ipList队列移除ip: "+loginIp);
                    }
                }
            }
            activeSessions--;                   //在用户销毁的时候,从队列中踢出这个IP
            httpSessionEvent.getSession().getServletContext().setAttribute("activeSessions", activeSessions);
            System.out.println("销毁SESSION,sessionId = " + sessionId +"; overTime = " + overTime
                             + "; loginIp = " + loginIp +"; 当前总SESSION值为 "+activeSessions);
        }
 
 
	}
 
	public synchronized static int getActiveSessions() {
		return activeSessions;
	}
 
	public static void setActiveSessions(int activeSessions) {
		OnLineCount.activeSessions = activeSessions;
	}


}
