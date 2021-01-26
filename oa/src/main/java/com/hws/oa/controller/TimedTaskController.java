package com.hws.oa.controller;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hws.oa.core.LoadConf;
import com.hws.oa.core.quartz.ScheduleMethod;
import com.hws.oa.model.QuartzModel;
import com.hws.oa.service.JGitService;
import com.hws.oa.service.QuartzService;
import com.hws.oa.util.MyCacheUtils;
import com.hws.oa.util.ReadResourceUtils;

@Controller
@RequestMapping(value="/timedtask")
public class TimedTaskController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TimedTaskController.class);


	@Value("${websocket.ip}")
	private String websocketIp;
	@Value("${nginx.port}")
	private String serverPort;
	
	@Autowired
	QuartzService quartzService;
	@RequestMapping(value="/initPage")
	public String initPage(HttpServletRequest request){
		LOGGER.info("初始化定时管理界面开始");
		//待定  未完成对象映射关系
		//获取已经存在的job
		List<QuartzModel> list = quartzService.getALlFromMyDefine();
		request.setAttribute("result", list);
		//获取job类型
		String path = System.getProperty("user.dir")+"/conf/JobType.xml";
		List<String> attribute = new ArrayList<String>();
		attribute.add("name");
		attribute.add("class");
		try {
			Element rootElement = ReadResourceUtils.getXmlRootElement(new File(path));
			List<Map<String, String>> typeList = ReadResourceUtils.getAttributeValues(attribute, rootElement);
			request.setAttribute("typeList", typeList);
			request.setAttribute("websocketIp", websocketIp);
			request.setAttribute("serverPort", serverPort);
			request.setAttribute("jsessionId", request.getSession().getId());
		} catch (DocumentException e) {
			LOGGER.error("初始化定时管理界面发生DocumentException异常");
			e.printStackTrace();
		}
		LOGGER.info("初始化定时管理界面结束");
		return "timedtask";
	}
	@Autowired
	ScheduleMethod scheduleMethod ;
	
	@Autowired
	JGitService js;
	
	@RequestMapping(value="/addNewTask")
	@ResponseBody
	public JSONObject addNewJob(HttpServletRequest request,QuartzModel model,Integer num,Integer[] numArr,String[] addressArr,String command){
		JSONObject obj = new JSONObject();
		obj.put("flag", false);
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("jsessionId", request.getSession().getId());
		 
		if(model.getJOB_CLASS_NAME().equals("com.hws.oa.core.quartz.model.UpdateCodeJob")){
			//检查是否存在不存在则不执行定时任务
			if(null == LoadConf.getSystems().get(num)) return obj;
			jobDataMap.put("num", num);
		}else if(model.getJOB_CLASS_NAME().equals("com.hws.oa.core.quartz.model.DeleteJob")){
				jobDataMap.put("startTime", model.getSTART_TIME().longValue());
				jobDataMap.put("endTime", model.getEND_TIME().longValue());
		}else if(model.getJOB_CLASS_NAME().equals("com.hws.oa.core.quartz.model.PackageJob")){
			jobDataMap.put("num", num);
			jobDataMap.put("nums", numArr);
			jobDataMap.put("poms", addressArr);
			jobDataMap.put("command", command);
		}
		if(MyCacheUtils.isContain(model.getJOB_NAME())){
			obj.put("message", "name_already_exists");
			return obj;
		}
		try {
			model.setJOB_GROUP(model.getJOB_NAME());
			model.setTRIGGER_NAME(model.getJOB_NAME());
			model.setTRIGGER_GROUP(model.getJOB_NAME());
			
			scheduleMethod.addJobDetails( Class.forName(model.getJOB_CLASS_NAME()), model,jobDataMap);
			//设置任务永久存储
			boolean isStroe = quartzService.setPermanentStorage(model.getJOB_NAME());
			LOGGER.info("stroe:{}",isStroe);
			QuartzModel quartzModel = quartzService.getJobDetailForJobName(model.getJOB_NAME());
			LOGGER.info("quartzModel:{}",quartzModel == null);
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
		LOGGER.info("定时管理菜单操作");
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
