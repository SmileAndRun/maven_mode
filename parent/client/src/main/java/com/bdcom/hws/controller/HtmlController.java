package com.bdcom.hws.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.common.model.Barrage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bdcom.server.service.BarrageService;


@Controller
public class HtmlController {
	
	@Autowired
	private BarrageService barService;
	
	@RequestMapping(value="/")
	public String initLogin(){
		
		return "login";
	}
	
	@RequestMapping(value="/index")
	public String initHomePage(HttpServletRequest req){
		/*            适合不跳转页面
		 * JSONObject jsonObj = new JSONObject();
		List<Barrage> barList = barService.getAllBar();
		jsonObj.put("result", barList);
		response.setCharacterEncoding("UTF-8");
		JsonUtils.writeJson(response, jsonObj);*/
		
		List<Barrage> barList = barService.getAllBar();
		req.setAttribute("barList", barList);
		
		return "index";
	}
	@RequestMapping(value="/home")
	public String initIndexPage(HttpServletRequest req){
		return "index1";
	}
	@RequestMapping(value="/test")
	public String test(){
		
		return "index";
	}

}
