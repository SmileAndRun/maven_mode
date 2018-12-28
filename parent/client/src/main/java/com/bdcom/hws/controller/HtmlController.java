package com.bdcom.hws.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.common.model.Barrage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bdcom.server.service.BarrageService;


@Controller
public class HtmlController {
	
	@RequestMapping(value="/")
	public ModelAndView initLogin(){
		ModelAndView model = new ModelAndView("login");
		return model;
	}
	@RequestMapping(value="/html/chatroom")
	public ModelAndView initHomePage(HttpServletRequest req){
		ModelAndView model = new ModelAndView("chatroom");
		return model;
	}

}
