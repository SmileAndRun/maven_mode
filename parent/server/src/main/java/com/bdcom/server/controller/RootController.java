package com.bdcom.server.controller;


import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.common.model.Log;
import org.common.model.server.User;
import org.common.utils.DownUtils;
import org.common.utils.EncryptionUtils;
import org.common.utils.FileWriterUtils;
import org.common.utils.FontColourUtils;
import org.common.utils.JDBCUtils;
import org.common.utils.ReadResourceUtils;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.bdcom.server.service.ManagerService;

@Controller
@RequiresRoles(value={"Admin","SuperAdmin"},logical=Logical.OR)
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
		//String temp = "model/temp";
		String name = "model";
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			ZipOutputStream zos = new ZipOutputStream(out);
			DownUtils.download(filePath,zos,name);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	@Autowired
	ManagerService ms;
	
	@RequestMapping(value="/initUserManager")
	public ModelAndView initUserManager(){
		HashMap<String, List<User>> map = new HashMap<String,List<User>>();
		List<User> users = ms.getUsers();
		map.put("users", users);
		ModelAndView model = new ModelAndView("usermanage",map);
		return model;
	}
	@RequestMapping(value="/user/search")
	@ResponseBody
	public JSONObject searchUsers(String content,String type){
		JSONObject obj = new JSONObject();
		String color = "red";
		List<User> list = null;
		if(type.equals("1")){
			list = ms.getFuzzyUsersByUid(content);
			for(User user : list){
				user.setuId(FontColourUtils.colour(String.valueOf(user.getUserId()), color, content));
			}
		}else{
			list = ms.getFuzzyUserByUname(content);
			for(User user : list){
				user.setUserName(FontColourUtils.colour(user.getUserName(), color, content));
			}
		}
		obj.put("users", list);
		return obj;
	}
	@RequestMapping(value="/addUser")
	@ResponseBody
	public JSONObject addUser(User user){
		JSONObject obj = new JSONObject();
		User temp = new User();
		temp.setUserName(user.getUserName());
		temp.setuIsLock('0');
		byte[] key;
		try {
			byte[] salt = EncryptionUtils.getRandomNum(16);
			key = EncryptionUtils.combineByteArray(user.getUserPwd().getBytes(),salt);
			temp.setUserPwd(EncryptionUtils.transformByteToString(EncryptionUtils.encryptHMAC(key, null)));
			temp.setuSalt(salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		Log log = new Log();
		Date date = new Date();
		log.setLogtype("3");
		log.setLogmessage("register");
		log.setLogiserror("0");
		log.setLogtime(new Timestamp(date.getTime()));
		int num = ms.registerUser(temp,log);
		obj.put("addFlag", false);
		if(num>0)obj.put("addFlag", true);
		return obj;
	}
	@RequestMapping(value="/changeUser")
	@ResponseBody
	public JSONObject changeUser(User user){
		JSONObject obj = new JSONObject();
		int flag = ms.changeUser(user);
		obj.put("changeFlag", false);
		if(flag > 0)obj.put("changeFlag", true);
		return obj;
	}
	@RequestMapping(value="/initRoleManager")
	public ModelAndView initRolePage(){
		JSONObject obj = ms.getAllUserInfo();
		//获取所有role和permission
		String path = "UserRole.xml";
		List<String> attribute = new ArrayList<String>();
		attribute.add("name");
		attribute.add("type");
		List<String> roleList = new ArrayList<String>();
		List<String> permissionList = new ArrayList<String>();
		try {
			Element rootElement = ReadResourceUtils.getXmlRootElement(ReadResourceUtils.getClassPathResource(path));
			List<Map<String, String>> typeList = ReadResourceUtils.getAttributeValues(attribute, rootElement);
			for(Map<String, String> map :typeList){
				if(map.get("type").equals("role"))
					roleList.add(map.get("name"));
				if(map.get("type").equals("permission"))
					permissionList.add(map.get("name"));
				
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(roleList.size() != 0)obj.put("roleList", roleList);
		if(permissionList.size() != 0)obj.put("permissionList", permissionList);
		ModelAndView modelAndView = new ModelAndView("rolemanage",obj);
		return modelAndView;
	}
	@RequestMapping(value="/user/searchRoleInfo")
	@ResponseBody
	public JSONObject searchRoleInfo(String content,String type){
		JSONObject obj = new JSONObject();
		String color = "red";
		List<User> list = null;
		if(type.equals("1")){
			list = ms.getFuzzyRoleByUid(content);
			for(User user : list){
				user.setuId(FontColourUtils.colour(String.valueOf(user.getUserId()), color, content));
			}
		}else{
			list = ms.getFuzzyRoleByUname(content);
			for(User user : list){
				user.setUserName(FontColourUtils.colour(user.getUserName(), color, content));
			}
		}
		
		obj.put("users", list);
		return obj;
	}
	
	
}
