package com.bdcom.server.controller;


import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.bdcom.server.model.User;
import com.bdcom.server.service.ManagerService;
import com.bdcom.server.utils.DownUtils;
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
	public String getGeneratorPage(String url,HttpServletRequest request){
		String path = "model/model1/src/main/java/com/example/demo/model/";
		boolean flag = FileWriterUtils.isExist(path);
		request.setAttribute("flag", flag);
		return url;
	}
	@RequestMapping(value="/generator/code")
	@ResponseBody
	public JSONObject getGeneratorCode(HttpServletRequest request){
		JSONObject obj = new JSONObject();
		String tableName = request.getParameter("tableName");
		String[] colums = request.getParameterValues("columnArray");
		String[] types = request.getParameterValues("typeArray");
		try {
			FileWriterUtils.writerData(tableName, colums, types);
		} catch (IOException e) {
			e.printStackTrace();
		}
		obj.put("result", FileWriterUtils.getFileInfo());
		return obj;
	}
	@RequestMapping(value="/generator/init")
	@ResponseBody
	public JSONObject generatorInit(){
		JSONObject obj = new JSONObject();
		obj.put("result", FileWriterUtils.getFileInfo());
		return obj;
	}
	@RequestMapping(value="/downloadModel")
	public void downloadModel(HttpServletRequest request,HttpServletResponse response){
		
		response.setContentType("application/zip");
		response.setHeader("Content-Disposition", "attachment; filename=model.zip");  
		
		String filePath = "model/model1";
		String temp = "model/temp";
		String name = "model";
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			ZipOutputStream zos = new ZipOutputStream(out);
			DownUtils.download(filePath,zos,name, temp);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	@Autowired
	ManagerService ms;
	
	@RequestMapping(value="initUserManager")
	public ModelAndView initUserManager(){
		HashMap<String, List<User>> map = new HashMap<String,List<User>>();
		List<User> users = ms.getUsers();
		map.put("users", users);
		ModelAndView model = new ModelAndView("usermanage",map);
		return model;
	}
}
