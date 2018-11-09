package org.common.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	
	public static void setCookies(HttpServletRequest request,HttpServletResponse response,String name,String pwd){
		
		//设置账号
		Cookie u_Cookie = new Cookie("SESSION_USERNAME",name);
		u_Cookie.setPath("/");
		u_Cookie.setMaxAge(60*60*24*7);
		u_Cookie.setDomain(request.getServerName());
		u_Cookie.setComment("USER");
		//设置密码
		Cookie p_Cookie = new Cookie("SESSION_PASSWORD", pwd);
		p_Cookie.setPath("/");
		p_Cookie.setMaxAge(60*60*24*7);
		p_Cookie.setDomain(request.getServerName());
		p_Cookie.setComment("USER");
		response.addCookie(p_Cookie);
		response.addCookie(u_Cookie);
		
	}
	public static boolean isExist(Cookie[] cookies){
		boolean isExist = false; 
		int i = 0;
		for(Cookie cookie:cookies){
			if(cookie.getName().equals("SESSION_USERNAME")){
				i++;
				continue;
			}else if(cookie.getName().equals("SESSION_PASSWORD")){
				i++;
				continue;
			}
			if(i==2)isExist = true;
		}
		return isExist;
	}

}
