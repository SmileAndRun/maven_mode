package com.bdcom.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value="/grapha")
public class GraphaController {

	@RequestMapping(value="/graphAnalysis")
	public String initGraphAnalysis(){
		return "graphAnalysis";
	}
	@RequestMapping(value="/getPageView")
	@ResponseBody
	public JSONObject getPageView(HttpServletRequest request){
		JSONObject obj = new JSONObject();
		int count = (int)request.getServletContext().getAttribute("count");
		obj.put("count", count);
		return obj;
	}
	
}
