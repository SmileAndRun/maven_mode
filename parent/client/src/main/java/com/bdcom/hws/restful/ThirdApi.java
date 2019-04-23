package com.bdcom.hws.restful;

import java.util.List;

import org.common.model.CommonConstant;
import org.common.model.client.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

public class ThirdApi {

	@Autowired
	private RestTemplate restTemplate;
	
	public JSONObject test(){
		JSONObject obj = new JSONObject();
		StringBuffer url = new StringBuffer("url");
		User user = CommonConstant.getDataForMap(restTemplate, url, User.class, null);
		List<User> List = CommonConstant.getDataForMap(restTemplate, url, List.class, null);
		return obj;
	}
}
