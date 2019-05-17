package com.hws.oa.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hws.oa.service.MavenService;
import com.hws.oa.util.RunTimeUtils;

@Service
public class MavenServiceImpl implements MavenService {

	
	@Override
	public JSONObject mvn(String pomPath,String command) throws IOException, InterruptedException{
		RunTimeUtils.excute(pomPath, command);
		return null;
	}
	
	@Override
	public JSONObject searchPom(String path) {
		JSONObject obj = new JSONObject();
		List<String> list = new ArrayList<String>();
		File rootPath = new File(path);
		File[] files = rootPath.listFiles();
		for(File file :files){
			if(file.isDirectory()){
				String pomPath = file.getAbsolutePath()+File.separator+"pom.xml";
				File temp = new File(pomPath);
				if(temp.exists())list.add(pomPath);
			}
		}
		obj.put("pomList", list);
		return obj;
	}

}
