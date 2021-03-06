package com.bdcom.hws.controller;



import java.sql.Timestamp;




import javax.servlet.http.HttpServletRequest;

import org.common.model.client.WeChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.bdcom.hws.service.WeChatService;


@Controller
public class HtmlController {
	
	@Value("${websocket.ip}")
	private String websocketIp;
	@Value("${nginx.port}")
	private String serverPort;
	
	@RequestMapping(value="/")
	public ModelAndView initLogin(){
		ModelAndView model = new ModelAndView("login");
		return model;
	}
	@RequestMapping(value="/html/chatroom")
	public ModelAndView homePage(HttpServletRequest request){
		ModelAndView model = new ModelAndView("chatroom");
		request.setAttribute("websocketIp", websocketIp);
		request.setAttribute("serverPort", serverPort);
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
	
}
