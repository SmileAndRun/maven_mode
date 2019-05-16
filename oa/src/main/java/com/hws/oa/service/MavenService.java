package com.hws.oa.service;

import com.alibaba.fastjson.JSONObject;

public interface MavenService {

	public JSONObject mvnPackage();
	public JSONObject mvnPackage(String parm);
	public JSONObject mvnInstall();
	public JSONObject mvnInstall(String parm);
	public JSONObject mvnClean();
	public JSONObject searchPom(String path);
}
