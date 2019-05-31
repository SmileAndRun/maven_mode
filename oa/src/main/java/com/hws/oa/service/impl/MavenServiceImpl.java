package com.hws.oa.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hws.oa.core.threadpool.MyThreadPoolExecutor;
import com.hws.oa.core.threadpool.PackageTask;
import com.hws.oa.core.websocket.WebSocketServer;
import com.hws.oa.service.MavenService;

@Service
public class MavenServiceImpl implements MavenService {

	
	@Override
	public void mvn(String pomPath,String command,String jsessionId) throws IOException, InterruptedException{
		
		MyThreadPoolExecutor.myThreadPoolExecutor.getThreadPoolExecutor().submit(new PackageTask(WebSocketServer.getMapCache().get(jsessionId),pomPath,command));	
	}
	
	@Override
	public JSONObject searchPom(String path) {
		JSONObject obj = new JSONObject();
		obj.put("flag", true);
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
		if(list.size()==0){
			obj.put("flag", false);
		}else{
			obj.put("pomList", list);
		}
		return obj;
	}

}
