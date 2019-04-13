package com.bdcom.server.controller;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.common.utils.JDBCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.bdcom.server.service.ManagerService;
import com.server.restful.api.pojo.server.MysqlModel;

@RestController
@RequiresRoles(value={"Admin","SuperAdmin"},logical=Logical.OR)
@RequiresPermissions("database")
@RequestMapping(value="/mysql")
public class MysqlController {
	private static Logger logger = Logger.getLogger(MysqlController.class);
	
	@Value("${spring.datasource.url}")
	String databaseUrl;
	@Value("${spring.datasource.username}")
	String userName;
	@Value("${spring.datasource.password}")
	String userPwd;
	
	@Autowired
	ManagerService ms;
	
	@RequestMapping(value="/suggestion")
	@ResponseBody
	public List<String> getSuggestion(String context){
		logger.info("获取sql提示");
		List<String> strList = null;
		try {
			strList = ms.getSuggestion(context);
		} catch (IOException e) {
			logger.error("获取sql提示，发生IOException异常");
			e.printStackTrace();
		}
		return strList;
	}
	@RequestMapping(value="/getMysqlReturnData")
	@ResponseBody
	public JSONObject getMysqlReturnData(String sql){
		logger.info("获取sql结果开始");
		//清除特殊符号
		sql = sql.replaceAll("\t", "");
		sql = sql.replaceAll("\n", "");
		JSONObject obj = new JSONObject();
		MysqlModel model = null;
		try{
			model = JDBCUtils.excuteSql(databaseUrl, userName, userPwd, sql);
			
		}catch(NullPointerException e){
			obj.put("message", "已经停止");
			logger.error("获取sql结果发生NullPointerException异常");
			e.printStackTrace();
		}catch(Exception e){
			model = null;
			logger.error("获取sql结果发生Exception异常");
			e.printStackTrace();
		}
		obj.put("model", model);
		logger.info("获取sql结果结束");
		return obj;
	}
	@RequestMapping(value="/stopMysql")
	@ResponseBody
	public boolean stopMysql(){
		logger.info("终止此次sql");
		boolean flag = JDBCUtils.close();
		return flag;
	}
	
	@RequestMapping(value="/getTableStructureData")
	@ResponseBody
	public JSONObject getTableStructureData(String tableName){
		logger.info("获取表结构数据开始");
		//清除特殊符号
		JSONObject obj = new JSONObject();
		try{
			obj = JDBCUtils.getTableStructureData(databaseUrl, userName, userPwd, tableName);
			
		}catch(Exception e){
			obj.put("error", "The server is error!");
			logger.error("获取表结构数据出现Exception异常");
			e.printStackTrace();
		}
		logger.info("获取表结构数据结束");
		return obj;
	}
}
