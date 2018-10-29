package com.bdcom.server.controller;



import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bdcom.server.utils.FileWriterUtils;
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
	public String getGeneratorPage(String url){
		return url;
	}
	@RequestMapping(value="/generator/code")
	@ResponseBody
	public JSONObject getGeneratorCode(HttpServletRequest request){
		JSONObject obj = new JSONObject();
		String tableName = request.getParameter("tableName");
		String[] colums = request.getParameterValues("columnArray");
		String[] types = request.getParameterValues("typeArray");
		/*try {
			FileWriterUtils.writerData(tableName, colums, types);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		Map<String,String[]> map = FileWriterUtils.getFileInfo();
		obj.put("result", FileWriterUtils.getFileInfo());
		return obj;
	}
}
