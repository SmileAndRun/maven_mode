package com.bdcom.server.controller;



import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.bdcom.server.utils.JDBCUtils;



@Controller
@RequestMapping(value="/server")
public class RootController {
	
	@Value("${spring.datasource.url}")
	String databaseUrl;
	@Value("${spring.datasource.username}")
	String userName;
	@Value("${spring.datasource.password}")
	String userPwd;
	
	@RequestMapping(value="/index")
	public String initRootPage(HttpServletRequest req){
		return "manage";
	}
	@RequestMapping(value="/database")
	public String getDatabasePage(String url,HttpServletRequest request){
		JSONObject obj = null;
		
		try {
			obj = JDBCUtils.getMysqlDataBase(databaseUrl,userName,userPwd);
		} catch (SQLException e) {
			obj = null;
			e.printStackTrace();
		}
		request.setAttribute("database", obj);
		return url;
	}
	@RequestMapping(value="/generator")
	public String getGeneratorPage(String url,HttpServletRequest request){
		
		return url;
	}
}
