package com.hws.oa.util;

import java.io.File;
import java.io.FilenameFilter;

public class MyFileNameFilter implements FilenameFilter{

	private String fileName;
	public  MyFileNameFilter(String fileName) {
		this.fileName = fileName;
	}
	
	
	@Override
	public boolean accept(File dir, String name) {
		if(null != name){
			if(fileName.equals(name.split(".")[0]))return true;
		}
		return false;
	}

}
