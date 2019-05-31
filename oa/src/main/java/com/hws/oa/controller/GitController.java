package com.hws.oa.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.hws.oa.common.MyCommonConstants;
import com.hws.oa.core.LoadConf;
import com.hws.oa.exception.CommonException;
import com.hws.oa.model.SystemModel;
import com.hws.oa.model.VersionModel;
import com.hws.oa.service.JGitService;
import com.hws.oa.service.MavenService;
import com.hws.oa.service.MysqlService;
import com.hws.oa.service.SystemService;
import com.hws.oa.util.DownUtils;

@RestController
public class GitController {
	private static Logger logger = Logger.getLogger(GitController.class);
	@Autowired
	JGitService js;
	@Autowired
	MavenService ms;
	@Autowired
	MysqlService mysqlService;
	
	
	@RequestMapping("/getSettings")
	@ResponseBody
	public JSONObject getSettings(HttpServletRequest request){
		JSONObject obj = new JSONObject();
		obj.put("flag", true);
		List<SystemModel> list = LoadConf.getSystems();
		if(null == list || list.size() == 0){
			obj.put("flag", false);
			return obj;
		}
		obj.put("Settings", list);
		return obj;
	}
	@RequestMapping("/update")
	@ResponseBody
	public JSONObject updateCode(Integer num,String jessionId){
		logger.info("开始代码更新");
		JSONObject obj = new JSONObject();
		try {
			obj = js.update(num,jessionId);
		} catch (GitAPIException e) {
			e.printStackTrace();
			obj.put("updateFlag", false);
		} catch (CommonException e) {
			e.printStackTrace();
			obj.put("updateFlag", false);
		} catch (IOException e) {
			e.printStackTrace();
			obj.put("updateFlag", false);
		}
		obj.put("code_version", MyCommonConstants.codeVersion.incrementAndGet());
		logger.info("开始代码更新完成");
		return obj;
	}
	@RequestMapping("/package")
	@ResponseBody
	public JSONObject mvnPackage(HttpServletRequest request,Integer[] numArr,String[] addressArr,String command){
		Map<Integer, String> map = new HashMap<Integer, String>();
		for(int i = 0;i<numArr.length ;i++){
			map.put(numArr[i],addressArr[i]);
		}
		Arrays.sort(numArr);
		if(null == command || "".equals(command))command = "mvn clean package install";
		JSONObject obj = new JSONObject();
		for(Integer temp : numArr){
			try {
				 ms.mvn(map.get(temp).replace("pom.xml", ""), command,request.getSession().getId());
				if(!obj.getBooleanValue("flag"))return obj;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
	@RequestMapping("/getPoms")
	@ResponseBody
	public JSONObject getPoms(HttpServletRequest request,Integer num){
		String local = LoadConf.getSystems().get(num).getLocalRepo();
		String remote = LoadConf.getSystems().get(num).getRemoteRepo();
		String[] temp = remote.split("\\.");
		temp = temp[temp.length-2].split("/");
		String repo = temp[temp.length-1];
		JSONObject obj = ms.searchPom(local+File.separator+repo);
		return obj;
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
	@Value("${package.address}")
	private String zipAddress;
	@RequestMapping(value="/zipData")
	@ResponseBody
	public JSONObject zipData(String[] addressArr,String version,String updateInfo,String packageInfo){
		JSONObject obj = new JSONObject();
		obj.put("flag",false);
		if(version.equals("code version"))
			version = String.valueOf(MyCommonConstants.codeVersion.getAndIncrement());
		for(int i=0;i<addressArr.length;i++){
			addressArr[i] = addressArr[i].replace("pom.xml", "");
		}
		try {
			DownUtils.zipData(addressArr, version, zipAddress);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		obj.put("flag",true);
		//更新数据库
		
		VersionModel versionModel = new VersionModel();
		versionModel.setVersionId(Long.valueOf(version));
		Timestamp createTime = new Timestamp(System.currentTimeMillis());
		versionModel.setCreateTime(createTime);
		versionModel.setUpdateInfo(updateInfo);
		versionModel.setPackageInfo(packageInfo);
		mysqlService.addVersionModel(versionModel);
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
