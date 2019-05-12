package com.hws.oa.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;

import com.hws.oa.util.ReadResourceUtils;

public class LoadConf {

	private static final String USER_DIR= System.getProperty("user.dir");
	private static final String CONF = "conf";
	private static final String SYSTEM_CONFIG="system-config.xml";
	private static Map<Integer,Map<String,String>> systemMap = null;
	
	
	
	public static void load(boolean flag) throws DocumentException{
		loadSystem(flag);
	}
	public static void loadSystem(boolean flag) throws DocumentException{
		if(!flag)return ;
		//加载系统配置文件
		String url = USER_DIR +File.separator+CONF+File.separator+SYSTEM_CONFIG;
		File file = new File(url);
		List<String> attribute = new ArrayList<String>();
		attribute.add("is-select");
		attribute.add("num");
		attribute.add("remote-repo");
		attribute.add("local-repo");
		attribute.add("package-address");
		systemMap = ReadResourceUtils.getNodes(attribute, ReadResourceUtils.getXmlRootElement(file));
	}
	public static Map<Integer, Map<String, String>> getSystemMap() {
		return systemMap;
	}
	public static void setSystemMap(Map<Integer, Map<String, String>> systemMap) {
		LoadConf.systemMap = systemMap;
	}
}
