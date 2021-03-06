package com.hws.oa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hws.oa.core.LoadConf;
import com.hws.oa.model.SystemModel;
import com.hws.oa.service.MysqlService;

@Controller
@RequestMapping("/html")
public class HtmlController {

	@Autowired
	MysqlService mysqlService;
	
	@RequestMapping("/homepage")
	public ModelAndView homepage(){
		return new ModelAndView("index");
	}
	@RequestMapping("/settings")
	public ModelAndView settings(HttpServletRequest request){
		List<SystemModel> list = LoadConf.getSystems();
		request.setAttribute("sets", list);
		return new ModelAndView("settings");
	}
	@Value("${websocket.ip}")
	private String websocketIp;
	@Value("${nginx.port}")
	private String nginxPort;
	
	
	@RequestMapping("/package")
	public ModelAndView packageView(HttpServletRequest request){
		request.setAttribute("jsessionId", request.getSession().getId());
		request.setAttribute("websocketIp", websocketIp);
		request.setAttribute("serverPort", nginxPort);
		return new ModelAndView("package");
	}
	
	@RequestMapping("/versionInfo")
	public ModelAndView versionInfo(HttpServletRequest request){
		request.setAttribute("versions", mysqlService.getAllVersionModel());
		return new ModelAndView("versionInfo");
	}
	
	@RequestMapping("/404")
	public ModelAndView errorPage(){
		return new ModelAndView("404");
	}
	
}
