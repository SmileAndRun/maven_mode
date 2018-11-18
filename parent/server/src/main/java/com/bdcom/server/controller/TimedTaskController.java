package com.bdcom.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.common.model.QrtzJobDetails;
import org.common.utils.DataBaseSourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bdcom.server.service.QuartzService;

@Controller
@RequestMapping(value="/timedtask")
public class TimedTaskController {

	@Autowired
	QuartzService quartzService;
	@RequestMapping(value="/initPage")
	public ModelAndView initPage(){
		DataBaseSourceUtils.switchDataSource();
		Map<String,List<QrtzJobDetails>> map = new HashMap<String,List<QrtzJobDetails>>();
		List<QrtzJobDetails> list = quartzService.getJobDetails();
		map.put("result", list);
		ModelAndView model = new ModelAndView("timedtask",map);
		return model;
	}
}
