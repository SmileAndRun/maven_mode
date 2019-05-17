package com.hws.oa.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class RunTimeUtils {

	private static final String WINDOWS="WINDOWS";
	/**
	 * 检查当前系统
	 */
	public static boolean isWindows(){
		String systemName = System.getProperty("os.name");
		if(systemName.toUpperCase().contains(WINDOWS)) return true;
		return false;
	}
	public static void excute(String pomPath,String command) throws IOException, InterruptedException{
		Runtime runtime=Runtime.getRuntime();
		Process process=null; 
		if(isWindows()){
			process= runtime.exec("cmd /c   cd "+pomPath+" &&"+command);
			
		}else{
			process= runtime.exec("cd "+pomPath+" &&"+command);
		}
		String line;
		InputStream input = process.getInputStream();
		BufferedReader read = new BufferedReader(new InputStreamReader(input,"GB2312"));
		while((line= read.readLine())!=null){
			System.out.println(line);
		}
		process.waitFor();
	    process.destroy();
		
	}
}
