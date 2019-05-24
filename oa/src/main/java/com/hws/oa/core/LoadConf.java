package com.hws.oa.core;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;

import com.hws.oa.core.threadpool.MyThreadPoolExecutor;
import com.hws.oa.model.SystemModel;
import com.hws.oa.util.ReadResourceUtils;

public class LoadConf {

	private static final String USER_DIR= System.getProperty("user.dir");
	private static final String CONF = "conf";
	private static final String SYSTEM_CONFIG="system-config.xml";
	private static List<SystemModel> systemInfo = null;
	
	
	
	public static void load() throws DocumentException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		loadSystem();
		//初始化线程池
		new MyThreadPoolExecutor().initThreadPoolExcutor();
	}
	public static void loadSystem() throws DocumentException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		//加载系统配置文件
		String url = USER_DIR +File.separator+CONF+File.separator+SYSTEM_CONFIG;
		File file = new File(url);
		List<String> attribute = new ArrayList<String>();
		attribute.add("remote-repo");
		attribute.add("local-repo");
		systemInfo = ReadResourceUtils.getNodes(attribute, ReadResourceUtils.getXmlRootElement(file),SystemModel.class);
	}
	public static List<SystemModel> getSystems() {
		return systemInfo;
	}
}
