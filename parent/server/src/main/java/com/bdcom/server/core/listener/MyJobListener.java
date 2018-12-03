package com.bdcom.server.core.listener;


import java.io.IOException;
import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.common.core.websocket.WebSocketClient;
import org.common.core.websocket.WebSocketServer;
import org.common.model.QuartzModel;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bdcom.server.service.QuartzService;
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
	@Autowired
	WebSocketServer webSocketServer;
	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		System.out.println("start:jobToBeExecuted");
		String jobName = context.getJobDetail().getKey().getName();
		QuartzModel model = new QuartzModel();
		model.setJOB_NAME(jobName);
		model.setTRIGGER_STATE("SCHEDULING");
		quartzService.updateSelfDefined(model);
		String message = "{name:'"+jobName+"',state:'"+model.getTRIGGER_STATE()+"'}";
		try {
			for(WebSocketServer socketServer:webSocketServer.getWebSocketServers().values()){
				
			}
			webSocketServer.sendMessage(message);
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
		QuartzModel model = quartzService.getJobDetailForJobName(jobName);
		if(null == model){
			model = new QuartzModel();
			model.setJOB_NAME(jobName);
			model.setTRIGGER_STATE("COMPLETE");
			quartzService.updateSelfDefined(model);
		}else{
			quartzService.updateSelfDefined(model);
		}
		String message = "{name:'"+jobName+"',state:'"+model.getTRIGGER_STATE()+"'}";
		try {
			webSocketServer.sendMessage(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("end:jobWasExecuted");
	}

}
