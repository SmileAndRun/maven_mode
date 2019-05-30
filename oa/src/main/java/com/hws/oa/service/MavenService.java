package com.hws.oa.service;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;

public interface MavenService {

	public JSONObject mvn(String pomPath,String command,String jsessionId)throws IOException, InterruptedException;
	public JSONObject searchPom(String path);
}
