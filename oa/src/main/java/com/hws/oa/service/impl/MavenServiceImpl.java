package com.hws.oa.service.impl;

import java.io.File;
import java.nio.file.Files;

import com.alibaba.fastjson.JSONObject;
import com.hws.oa.service.MavenService;

public class MavenServiceImpl implements MavenService {

	@Override
	public JSONObject mvnPackage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject mvnPackage(String parm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject mvnInstall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject mvnInstall(String parm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject mvnClean() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject searchPom(String path) {
		File rootPath = new File(path);
		File[] files = rootPath.listFiles();
		
		return null;
	}

}
