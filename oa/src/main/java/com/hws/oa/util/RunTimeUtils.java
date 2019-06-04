package com.hws.oa.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.websocket.Session;

import org.apache.log4j.Logger;


public class RunTimeUtils {

	private static Logger logger = Logger.getLogger(RunTimeUtils.class);
	private static final String WINDOWS="WINDOWS";
	/**
	 * 检查当前系统
	 */
	public static boolean isWindows(){
		String systemName = System.getProperty("os.name");
		if(systemName.toUpperCase().contains(WINDOWS)) return true;
		return false;
	}
	public static void excute(String pomPath,String command,Session session) throws IOException, InterruptedException{
		Runtime runtime=Runtime.getRuntime();
		Process process=null; 
		if(isWindows()){
			//修复window无法切换路径的问题
			if(pomPath.indexOf(":")!=-1){
				String temp = pomPath.split(":")[0];
				process= runtime.exec("cmd /c   cd "+pomPath+" && "+temp+":"+" &&"+command);
			}else{
				process= runtime.exec("cmd /c   cd "+pomPath+" &&"+command);
			}
			
		}else{
			process= runtime.exec("cd "+pomPath+" &&"+command);
		}
		if(null != session){
			String line = null;
			InputStream input = process.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(input,"GB2312"));
			boolean packageFlag = true;
			boolean isFinish = false;
			String message = "";
			while((line= read.readLine())!=null){
				if(line.indexOf("ERROR")!= -1)packageFlag = false;
				logger.info("打包完成状态："+isFinish);
				//解决前台json格式数据解析失败问题
				line = line.replace("'", "");
				message = "{type:'package',isFinish:"+isFinish+",value:'"+line+"',flag:'"+packageFlag+"'}";
				session.getBasicRemote().sendText(message);
			}
			isFinish = true;
			message = "{type:'package',isFinish:"+isFinish+",value:'',flag:'"+packageFlag+"'}";
			session.getBasicRemote().sendText(message);
		}
		process.waitFor();
	    process.destroy();
	}
	
	
	public static String excute(String pomPath,String command) throws IOException, InterruptedException{
		Runtime runtime=Runtime.getRuntime();
		Process process=null; 
		if(isWindows()){
			//修复window无法切换路径的问题
			if(pomPath.indexOf(":")!=-1){
				String temp = pomPath.split(":")[0];
				process= runtime.exec("cmd /c   cd "+pomPath+" && "+temp+":"+" &&"+command);
			}else{
				process= runtime.exec("cmd /c   cd "+pomPath+" &&"+command);
			}
			
		}else{
			process= runtime.exec("cd "+pomPath+" &&"+command);
		}
		String line = null;
		InputStream input = process.getInputStream();
		BufferedReader read = new BufferedReader(new InputStreamReader(input,"GB2312"));
		String message = "";
		while((line= read.readLine())!=null){
			//解决前台json格式数据解析失败问题
			line = line.replace("'", "");
			message += line + "<br/>";
		}
		process.waitFor();
	    process.destroy();
	    return message;
	}
}
