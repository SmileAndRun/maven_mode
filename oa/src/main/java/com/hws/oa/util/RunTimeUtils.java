package com.hws.oa.util;

import java.io.IOException;

public class RunTimeUtils {

	private static final String WINDOWS="WINDOWS";
	/**
	 * 检查当前系统
	 */
	public static boolean isWindows(){
		String systemName = System.getProperty("os.name");
		if(systemName.toUpperCase().matches(WINDOWS)) return true;
		return false;
	}
	public static void excute(String pomPath,String command) throws IOException, InterruptedException{
		Runtime runtime=Runtime.getRuntime();
		Process process=null; 
		if(isWindows()){
			process= runtime.exec("cmd /c   cd "+pomPath+" && mvn "+command);
			
		}else{
			process= runtime.exec("cd "+pomPath+" && mvn "+command);
		}
		process.waitFor();
	    process.destroy();
		
	}
}
