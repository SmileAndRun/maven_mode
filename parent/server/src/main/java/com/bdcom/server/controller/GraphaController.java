package com.bdcom.server.controller;



import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.common.model.OnlineCountModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bdcom.server.service.ManagerService;

@Controller
@RequiresRoles(value={"Admin","SuperAdmin"},logical=Logical.OR)
@RequestMapping(value="/grapha")
public class GraphaController {
	private static Logger logger = Logger.getLogger(GraphaController.class);
	@Autowired
	ManagerService ms;
	@RequestMapping(value="/graphAnalysis")
	public String initGraphAnalysis(HttpServletRequest request){
		logger.info("图表动态分析初始化开始");
		ms.initGraphAnalysis(request);
		logger.info("图表动态分析初始化结束");
		return "graphAnalysis";
	}
	@RequestMapping(value="/getPageView")
	@ResponseBody
	public JSONObject getPageView(HttpServletRequest request){
		logger.info("实时获取");
		JSONObject obj = new JSONObject();
		int activeSessions = OnlineCountModel.activeSessions;
		obj.put("activeSessions", activeSessions);
		return obj;
	}
}
