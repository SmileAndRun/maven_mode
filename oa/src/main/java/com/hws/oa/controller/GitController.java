package com.hws.oa.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.hws.oa.exception.CommonException;
import com.hws.oa.model.SystemModel;
import com.hws.oa.service.JGitService;
import com.hws.oa.service.MavenService;
import com.hws.oa.service.SystemService;

@RestController
public class GitController {
	private static Logger logger = Logger.getLogger(GitController.class);
	@Autowired
	JGitService js;
	@Autowired
	MavenService ms;
	
	@RequestMapping("/pull")
	public void pull(HttpServletRequest request){
		try {
			js.update(1,request);
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/mvn")
	public void test(HttpServletRequest request,String pomPath){
		try {
			ms.mvn(pomPath, "mvn clean");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Autowired
	SystemService sm;
	
	@RequestMapping(value="/settings/search")
	@ResponseBody
	public JSONObject searchUsers(String content,Integer type){
		JSONObject obj = new JSONObject();
		obj.put("flag", true);
		try{
			List<SystemModel> list = sm.searchUsersAndColoring(content, type);
			obj.put("setttings", list);
		}catch(NumberFormatException e){
			obj.put("flag", false);
		}
		
		return obj;
	}
	@RequestMapping(value="/addSystemSet")
	@ResponseBody
	public JSONObject addSystemSet(SystemModel systemModel){
		JSONObject obj = new JSONObject();
		boolean flag = sm.addSystemSet(systemModel);
		obj.put("flag", flag);
		return obj;
	}
	@RequestMapping(value="/updateSystemSet")
	@ResponseBody
	public JSONObject updateSystemSet(SystemModel systemModel){
		JSONObject obj = new JSONObject();
		boolean flag = sm.updateSystemSet(systemModel);
		obj.put("flag", flag);
		return obj;
	}
	@RequestMapping(value="/test")
	@ResponseBody
	public JSONObject test(){
		JSONObject obj = new JSONObject();
		SystemModel systemModel = new SystemModel();
		systemModel.setNum(1);
		systemModel.setLocalRepo("test");
		systemModel.setRemoteRepo("test");
		boolean flag = sm.updateSystemSet(systemModel);
		obj.put("flag", flag);
		return obj;
	}
}
