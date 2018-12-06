package com.bdcom.server.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequiresRoles(value={"Admin","SuperAdmin"},logical=Logical.OR)
@RequestMapping(value="/grapha")
public class GraphaController {

	@RequestMapping(value="/graphAnalysis")
	public String initGraphAnalysis(HttpServletRequest request){
		List<Integer> list = new ArrayList<Integer>();
		String initData = "";
	    boolean flag = true;
	    long time = System.currentTimeMillis();
	    int activeSessions = (int)request.getServletContext().getAttribute("activeSessions");
		list.add(activeSessions);
		initData += activeSessions+",";
	    while(flag){
	    	if(System.currentTimeMillis()-time == 1000){
	    		time = System.currentTimeMillis();
	    		activeSessions = (int)request.getServletContext().getAttribute("activeSessions");
	    		list.add(activeSessions);
	    		initData += activeSessions+",";
	    		if(list.size()>=20){
	    			System.out.println(list.size());
	    			flag = false;
	    		}
	    	}
	    }
	    request.setAttribute("initData", initData);
		return "graphAnalysis";
	}
	@RequestMapping(value="/getPageView")
	@ResponseBody
	public JSONObject getPageView(HttpServletRequest request){
		JSONObject obj = new JSONObject();
		int activeSessions = (int)request.getServletContext().getAttribute("activeSessions");
		obj.put("activeSessions", activeSessions);
		return obj;
	}
}
