package com.hws.oa.core.listener;


import java.io.IOException;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hws.oa.core.websocket.WebSocketServer;
import com.hws.oa.model.QuartzModel;
import com.hws.oa.service.QuartzService;

/**
 * 监听定时任务
 * @author hws
 *
 */
@Component
public class MyJobListener implements JobListener {

	@Override
	public String getName() {
		return "myJobListener";
	}
	@Autowired
	QuartzService quartzService;
	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		System.out.println("start:jobToBeExecuted");
		String jobName = context.getJobDetail().getKey().getName();
		QuartzModel model = new QuartzModel();
		model.setJOB_NAME(jobName);
		model.setTRIGGER_STATE("SCHEDULING");
		quartzService.updateSelfDefined(model);
		String message = "{name:'"+jobName+"',state:'"+model.getTRIGGER_STATE()+"'}";
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		String jsessionId = jobDataMap.getString("jsessionId");
		try {
			WebSocketServer.getMapCache().get(jsessionId).getBasicRemote().sendText(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("end:jobToBeExecuted");
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {

	}
	/**任务执行后更新任务状态*/
	@Override
	public void jobWasExecuted(JobExecutionContext context,
			JobExecutionException jobException) {
		System.out.println("start:jobWasExecuted");
		String jobName = context.getJobDetail().getKey().getName();
		
		QuartzModel model  = new QuartzModel();
		model.setJOB_NAME(jobName);
		model.setTRIGGER_STATE("COMPLETE");
		quartzService.updateSelfDefined(model);
		
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		String jsessionId = jobDataMap.getString("jsessionId");
		String message = "{name:'"+jobName+"',state:'"+model.getTRIGGER_STATE()+"'}";
		try {
			WebSocketServer.getMapCache().get(jsessionId).getBasicRemote().sendText(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("end:jobWasExecuted");
	}

}
