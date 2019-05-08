package com.hws.file.util;

import java.io.File;
import java.io.FilenameFilter;

import org.apache.log4j.Logger;


public class MyFileUtils {

	private static Logger logger = Logger.getLogger(MyFileUtils.class);
	public static String findFileName(String path,String id){
		File directory = new File(path);
		if(!directory.exists()||!directory.isDirectory()){
			logger.error("文件夹不存在或者该路径不是文件夹");
			return null;
		}
		FilenameFilter filter = new MyFileNameFilter(id);
		File[] files = directory.listFiles(filter);
		if(files.length==0)return null;
		//id唯一，所以files的长度默认为1
		
		return files[0].getName();
	}
}
