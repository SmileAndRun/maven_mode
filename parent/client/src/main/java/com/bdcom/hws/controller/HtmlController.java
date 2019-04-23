package com.bdcom.hws.controller;



import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.common.model.CommonConstant;
import org.common.model.client.WeChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.bdcom.hws.service.WeChatService;
import com.server.restful.api.pojo.server.User;


@Controller
public class HtmlController {
	
	@RequestMapping(value="/")
	public ModelAndView initLogin(){
		ModelAndView model = new ModelAndView("login");
		return model;
	}
	
	@RequestMapping(value="/html/chatroom")
	public ModelAndView homePage(){
		ModelAndView model = new ModelAndView("chatroom");
		return model;
	}
	@RequestMapping(value="/html/share")
	public ModelAndView shaerPage(){
		ModelAndView model = new ModelAndView("share");
		return model;
	}
	@RequestMapping(value="/html/initChatroom")
	@ResponseBody
	public JSONObject initHomePage(){
		JSONObject obj = ws.getAllChat();
		return obj;
	}
	@Autowired
	WeChatService ws;
	@RequestMapping(value="/html/addMessage")
	@ResponseBody
	public JSONObject addMessage(WeChat model,long time){
		JSONObject obj = new JSONObject();
		model.setW_time(new Timestamp(time));
		boolean addChatFlag = ws.addChat(model);
		obj.put("addChatFlag", addChatFlag);
		return obj;
	}
	
	//test
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/html/test")
	@ResponseBody
	public JSONObject test(){
		
		JSONObject obj = new JSONObject();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userId", 3);
		//User temp = restTemplate.getForEntity("http://gateway-api/api-feign/feign/getUserByUid?identification=hws", User.class).getBody();
		
		StringBuffer url = new StringBuffer("/getUserByUid");
	
		User temp = CommonConstant.getDataForMap(restTemplate, url,User.class, map);
		/*for(User te:temp){
			System.out.println(te.getUserName());
		}*/
		obj.put("flag", temp);
		return obj;
	}
	
}
