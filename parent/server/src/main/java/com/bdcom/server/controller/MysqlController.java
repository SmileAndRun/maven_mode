package com.bdcom.server.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.common.model.server.MysqlModel;
import org.common.utils.JDBCUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.bdcom.server.lucene.SearchMethod;

@RestController
@RequestMapping(value="/mysql")
public class MysqlController extends SearchMethod{
	Map<List<Document>, Query> map = null;
	List<String> strList = null;
	List<Document> docList = null;
	@Value("${spring.datasource.url}")
	String databaseUrl;
	@Value("${spring.datasource.username}")
	String userName;
	@Value("${spring.datasource.password}")
	String userPwd;
	
	
	@RequestMapping(value="/suggestion")
	@ResponseBody
	public List<String> getSuggestion(String context){
		
		try {
			map = searchByPrefixQuery(new Term("keyword", context));
			if(null != map){
				for(List<Document> list:map.keySet()){
					docList = list;
					break;
				}
				strList = new ArrayList<String>();
				for(Document doc:docList){
					if(null == doc) return null;
					String[] temp = doc.get("keyword").split(context);
					String text = "";
					if(doc.get("keyword").equals(context.trim())){
						text = "<font style='font-weight:bold;'>" + context + "</font>";
					}else{
						text = "<font style='font-weight:bold;'>" + context + "</font>" +temp[1];
					}
					
					strList.add(text);
				}
			}
		} catch (IOException e) {
			strList = null;
			e.printStackTrace();
		}
		return strList;
	}
	@RequestMapping(value="/getMysqlReturnData")
	@ResponseBody
	public JSONObject getMysqlReturnData(String sql){
		//清除特殊符号
		sql = sql.replaceAll("\t", "");
		sql = sql.replaceAll("\n", "");
		JSONObject obj = new JSONObject();
		MysqlModel model = null;
		try{
			model = JDBCUtils.excuteSql(databaseUrl, userName, userPwd, sql);
			
		}catch(NullPointerException e){
			obj.put("message", "已经停止");
			e.printStackTrace();
		}catch(Exception e){
			model = null;
			e.printStackTrace();
		}
		obj.put("model", model);
		return obj;
	}
	@RequestMapping(value="/stopMysql")
	@ResponseBody
	public boolean stopMysql(){
		boolean flag = JDBCUtils.close();
		return flag;
	}
	
	@RequestMapping(value="/getTableStructureData")
	@ResponseBody
	public JSONObject getTableStructureData(String tableName){
		//清除特殊符号
		JSONObject obj = new JSONObject();
		try{
			obj = JDBCUtils.getTableStructureData(databaseUrl, userName, userPwd, tableName);
			
		}catch(Exception e){
			obj.put("error", "The server is error!");
			e.printStackTrace();
		}
		return obj;
	}
}
