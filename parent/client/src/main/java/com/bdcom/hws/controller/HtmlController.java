package com.bdcom.hws.controller;


import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bdcom.hws.core.quartz.ScheduleConfig;
import com.bdcom.hws.core.quartz.model.BarrageJob;
import com.bdcom.hws.model.Barrage;
import com.bdcom.hws.model.CronScheduleModel;
import com.bdcom.hws.model.QuartzNameModel;
import com.bdcom.hws.service.BarrageService;

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
		ScheduleConfig config = new ScheduleConfig();
		QuartzNameModel qModel = new QuartzNameModel("test","test","testT","testT");
		CronScheduleModel model = new CronScheduleModel();
		model.setSecond("0/5");
		model.setMinute("0/1");
		model.setHour("all");
		model.setDate("all");
		model.setMonth("all");
		model.setWeek("?");
		model.setYear("all");
		model.setStartDate("2018-9-21 09:05:00");
		model.setEndDate("2018-09-21 09:08:00");
		try {
			config.addJobDetails(BarrageJob.class, model, qModel);
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "index";
	}

}
