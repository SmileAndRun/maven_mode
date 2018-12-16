package com.bdcom.server.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
@RequiresRoles(value={"Admin","SuperAdmin"},logical=Logical.OR)
@RequestMapping(value="/timedtask")
public class TimedTaskController {

	private static Logger logger = Logger.getLogger(TimedTaskController.class);
	@Autowired
	QuartzService quartzService;
	@RequestMapping(value="/initPage")
	public String initPage(HttpServletRequest request){
		logger.info("初始化定时管理界面开始");
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
			logger.error("初始化定时管理界面发生DocumentException异常");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("初始化定时管理界面发生IOException异常");
			e.printStackTrace();
		}
		logger.info("初始化定时管理界面结束");
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
			obj.put("flag", false);
			e.printStackTrace();
			
		} catch (ParseException e) {
			obj.put("flag", false);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			obj.put("flag", false);
			e.printStackTrace();
		}
		return obj;
	}
	@RequestMapping(value="/menuOperate")
	@ResponseBody
	public JSONObject menuOperate(@RequestParam String[] names,String type,String jobClass){
		logger.info("定时管理菜单操作");
		JSONObject obj = new JSONObject();
		try {
			obj = quartzService.menuOperate(names, type, jobClass);
		} catch (SchedulerException e) {
			obj.put("flag", false);
			e.printStackTrace();
		}
		obj.put("type", type);
		return obj;
	}
}
