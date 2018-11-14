package com.bdcom.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/timedtask")
public class TimedTaskController {

	@RequestMapping(value="/initPage")
	public ModelAndView initPage(){
		ModelAndView model = new ModelAndView("timedtask");
		return model;
	}
}
