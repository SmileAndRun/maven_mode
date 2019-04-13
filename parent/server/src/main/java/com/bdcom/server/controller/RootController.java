package com.bdcom.server.controller;


import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.common.utils.CookieUtils;
import org.common.utils.DownUtils;
import org.common.utils.FileWriterUtils;
import org.common.utils.JDBCUtils;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.bdcom.server.service.ManagerService;
import com.server.restful.api.pojo.Role;
import com.server.restful.api.pojo.server.User;

@Controller
@RequiresRoles(value={"Admin","SuperAdmin"},logical=Logical.OR)
@RequestMapping(value="/server")
public class RootController {
	
	private static Logger logger = Logger.getLogger(RootController.class);
	
	@Value("${spring.datasource.url}")
	String databaseUrl;
	@Value("${spring.datasource.username}")
	String userName;
	@Value("${spring.datasource.password}")
	String userPwd;
	
	
	@RequestMapping(value="/index")
	public String initRootPage(HttpServletRequest req){
		logger.info("初始化管理界面");
		return "manage";
	}
	@RequestMapping(value="/database")
	public String getDatabasePage(String url,HttpServletRequest request){
		logger.info("初始化数据库界面");
		JSONObject obj = null;
		try {
			obj = JDBCUtils.getMysqlDataBase(databaseUrl,userName,userPwd);
		} catch (SQLException e) {
			obj = null;
			logger.error("初始化管理界面发生SQLException异常");
			e.printStackTrace();
		}
		request.setAttribute("database", obj);
		logger.info("初始化管理界面结束");
		return url;
	}
	@RequestMapping(value="/generator")
	public String getGeneratorPage(String url,HttpServletRequest request){
		logger.info("初始化代码生成器界面");
		String path = "model/model1/src/main/java/com/example/demo/model/";
		boolean flag = FileWriterUtils.isExist(path);
		request.setAttribute("flag", flag);
		logger.info("初始化代码生成器结束");
		return url;
	}
	@RequestMapping(value="/generator/code")
	@ResponseBody
	public JSONObject getGeneratorCode(HttpServletRequest request){
		logger.info("自动构建代码开始");
		JSONObject obj = new JSONObject();
		String tableName = request.getParameter("tableName");
		String[] colums = request.getParameterValues("columnArray");
		String[] types = request.getParameterValues("typeArray");
		try {
			FileWriterUtils.writerData(tableName, colums, types);
		} catch (IOException e) {
			logger.error("自动构建代码发生IOException异常");
			e.printStackTrace();
		}
		obj.put("result", FileWriterUtils.getFileInfo());
		logger.info("自动构建代码结束");
		return obj;
	}
	@RequestMapping(value="/generator/init")
	@ResponseBody
	public JSONObject generatorInit(){
		logger.info("初始化代码生成器数据开始");
		JSONObject obj = new JSONObject();
		obj.put("result", FileWriterUtils.getFileInfo());
		logger.info("初始化代码生成器数据开始");
		return obj;
	}
	@RequestMapping(value="/downloadModel")
	public void downloadModel(HttpServletRequest request,HttpServletResponse response){
		logger.info("代码下载开始");
		response.setContentType("application/zip");
		response.setHeader("Content-Disposition", "attachment; filename=model.zip");  
		
		String filePath = "model/model1";
		String name = "model";
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			ZipOutputStream zos = new ZipOutputStream(out);
			DownUtils.download(filePath,zos,name);
		} catch (IOException e1) {
			logger.error("代码下载发生IOException异常");
			e1.printStackTrace();
		}
		logger.info("代码下载结束");
	}
	@Autowired
	ManagerService ms;
	
	@RequestMapping(value="/initUserManager")
	public ModelAndView initUserManager(){
		
		logger.info("初始化用户管理界面开始");
		JSONObject obj = new JSONObject();
		List<User> users = ms.getUsers();
		obj.put("users", users);
		//获取所有permission
		String path = "UserRole.xml";
		List<String> attribute = new ArrayList<String>();
		attribute.add("name");
		attribute.add("type");
		List<String> roleList = null;
		try {
			roleList = ms.getElement(attribute, path, "role");
		} catch (DocumentException e) {
			logger.error("初始化用户管理界面出现DocumentException异常");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("初始化用户管理界面出现IOException异常");
			e.printStackTrace();
		}
		if(null != roleList && roleList.size() != 0)obj.put("roleList", roleList);
		ModelAndView model = new ModelAndView("usermanage",obj);
		logger.info("初始化用户管理界面结束");
		return model;
	}
	
	
	@RequestMapping(value="/user/search")
	@ResponseBody
	public JSONObject searchUsers(String content,String type){
		logger.info("查询用户并着色开始");
		JSONObject obj = new JSONObject();
		List<User> list = ms.searchUsersAndColoring(content, type);
		
		obj.put("users", list);
		logger.info("查询用户并着色结束");
		return obj;
	}
	@RequestMapping(value="/addUser")
	@ResponseBody
	public JSONObject addUser(User user,String roleUl){
		logger.info("添加新用户开始");
		JSONObject obj = new JSONObject();
		int num = 0;
		try {
			num = ms.registerUser(user,roleUl);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		obj.put("addFlag", false);
		if(num>0)obj.put("addFlag", true);
		logger.info("添加新用户结束");
		return obj;
	}
	@RequestMapping(value="/changeUser")
	@ResponseBody
	public JSONObject changeUser(User user,String[]roleList,String[]roleListO){
		logger.info("修改用户信息开始");
		JSONObject obj = ms.changeUser(user,roleList,roleListO);
		logger.info("修改用户信息结束");
		return obj;
	}
	@RequestMapping(value="/initRoleManager")
	public ModelAndView initRolePage(){
		logger.info("初始化角色管理界面开始");
		JSONObject obj = ms.getAllRoleInfo();
		//获取所有permission
		String path = "UserRole.xml";
		List<String> attribute = new ArrayList<String>();
		attribute.add("name");
		attribute.add("type");
		List<String> permissionList = null;
		try {
			permissionList = ms.getElement(attribute, path, "permission");
		} catch (DocumentException e) {
			logger.error("初始化角色管理界面发生DocumentException异常");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("初始化角色管理界面发生IOException异常");
			e.printStackTrace();
		}
		if(null != permissionList && permissionList.size() != 0)obj.put("permissionList", permissionList);
		ModelAndView modelAndView = new ModelAndView("rolemanage",obj);
		logger.info("初始化角色管理界面结束");
		return modelAndView;
	}
	@RequestMapping(value="/searchRoleInfo")
	@ResponseBody
	public JSONObject searchRoleInfo(String content,String type){
		logger.info("搜索角色权限信息开始");
		JSONObject obj = new JSONObject();
		List<Role> list = ms.searchsRoleAndColoring(content, type);
		obj.put("roles", list);
		logger.info("搜索角色权限信息结束");
		return obj;
	}
	@RequestMapping(value="/changeRole")
	@ResponseBody
	public JSONObject changeRole(String roleId,String[] preList,String[] preListO){
		logger.info("修改角色权限信息开始");
		JSONObject obj = ms.changeRole(Integer.parseInt(roleId), preList, preListO);
		logger.info("修改角色权限信息开始");
		return obj;
	}
	@RequestMapping(value="/logout")
	@ResponseBody
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response){
		Subject subject = SecurityUtils.getSubject();
		CookieUtils.deleteCookies(request,response,null,null);
		subject.logout();
		return new ModelAndView("login");
	}
}
