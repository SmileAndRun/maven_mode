package com.bdcom.server.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.common.model.QuartzModel;
import org.common.utils.MyCacheUtils;
import org.common.utils.ReadResourceUtils;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bdcom.server.core.quartz.ScheduleMethod;
import com.bdcom.server.service.QuartzService;

@Controller
@RequestMapping(value="/timedtask")
public class TimedTaskController {

	@Autowired
	QuartzService quartzService;
	@RequestMapping(value="/initPage")
	public String initPage(HttpServletRequest request){
		//获取已经存在的job
		List<QuartzModel> list = quartzService.getALlFromMyDefine();
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
	@Autowired
	ScheduleMethod scheduleMethod ;
	
	@RequestMapping(value="/addNewTask")
	@ResponseBody
	public JSONObject addNewJob(QuartzModel model){
		JSONObject obj = new JSONObject();
		obj.put("flag", false);
		if(MyCacheUtils.isContain(model.getJOB_NAME())){
			obj.put("message", "name_already_exists");
			return obj;
		}
		try {
			model.setJOB_GROUP(model.getJOB_NAME());
			model.setTRIGGER_NAME(model.getJOB_NAME());
			model.setTRIGGER_GROUP(model.getJOB_NAME());
			scheduleMethod.addJobDetails( Class.forName(model.getJOB_CLASS_NAME()), model);
			//设置任务永久存储
			quartzService.setPermanentStorage(model.getJOB_NAME());
			QuartzModel quartzModel = quartzService.getJobDetailForJobName(model.getJOB_NAME());
			if(null == quartzModel){
				obj.put("message", "add_failed");
			}else{
				quartzModel.setCRON_EXPRESSION(model.getCRON_EXPRESSION());
				quartzService.insertSelfDifined(quartzModel);
				obj.put("flag", true);
				obj.put("result", quartzModel);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			obj.put("flag", false);
		} catch (ParseException e) {
			e.printStackTrace();
			obj.put("flag", false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			obj.put("flag", false);
		}
		return obj;
	}
	@RequestMapping(value="/menuOperate")
	@ResponseBody
	public JSONObject menuOperate(@RequestParam String[] names,String type,String jobClass){
		JSONObject obj = new JSONObject();
		boolean flag = false;
		try {
			switch (type) {
			case "1":
				JSONObject dataJson = quartzService.getJobDataByJobName(names[0], jobClass);
				obj.put("dataJson", dataJson);
				flag = true;
				break;
			case "2":
				flag = quartzService.deleteTasks(names);
				break;
			case "3":
				flag = true;
				quartzService.pausedTasks(names[0]);
				break;
			case "4":
				flag = true;
				quartzService.openTasks(names[0]);
				break;
			default:
				break;
			}
			
		} catch (SchedulerException e) {
			flag = false;
		}
		obj.put("flag", flag);
		obj.put("type", type);
		return obj;
	}
}
