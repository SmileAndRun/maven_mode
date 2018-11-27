package com.bdcom.server.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.common.core.quartz.ScheduleConfig;
import org.common.model.QrtzJobDetails;
import org.common.model.QuartzModel;
import org.common.utils.ReadResourceUtils;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bdcom.server.service.QuartzService;

@Controller
@RequestMapping(value="/timedtask")
public class TimedTaskController {

	@Autowired
	QuartzService quartzService;
	@RequestMapping(value="/initPage")
	public String initPage(HttpServletRequest request){
		
		//获取已经存在的job
		List<QrtzJobDetails> list = quartzService.getJobDetails();
		request.setAttribute("result", list);
		//获取job类型
		String path = "JobType.xml";
		List<String> attribute = new ArrayList<String>();
		attribute.add("name");
		attribute.add("class");
		try {
			Element rootElement = ReadResourceUtils.getXmlRootElement(ReadResourceUtils.getClassPathResource(path));
			List<Map<String, String>> typeList = ReadResourceUtils.getAttributeValues(attribute, rootElement);
			request.setAttribute("typeList", typeList);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "timedtask";
	}
	@RequestMapping(value="/addNewTask")
	@ResponseBody
	public JSONObject addNewJob(QuartzModel model){
		JSONObject obj = new JSONObject();
		ScheduleConfig config = new ScheduleConfig();
		try {
			model.setJobGroup(model.getJobName());
			model.setTriggerGroup(model.getJobName());
			model.setTriggerName(model.getJobName());
			config.addJobDetails( Class.forName((String)model.getUndetermined()), model);
		} catch (SchedulerException e) {
			obj.put("flag", false);
		} catch (ParseException e) {
			obj.put("flag", false);
		} catch (ClassNotFoundException e) {
			obj.put("flag", false);
		}
		List<QrtzJobDetails> list = quartzService.getJobDetailForJobName(model.getJobName());
		if(null != list)obj.put("result", list.get(0));
		obj.put("flag", true);
		return obj;
	}
}
