package com.hws.oa.core.quartz.model;

import java.io.IOException;
import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.hws.oa.exception.CommonException;
import com.hws.oa.model.QrtzJobData;
import com.hws.oa.service.JGitService;
import com.hws.oa.service.QuartzService;



public class UpdateCodeJob implements Job{

	private static Logger logger = Logger.getLogger(UpdateCodeJob.class);
	
	@Autowired
	JGitService js;
	@Autowired
	QuartzService quartzService;
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		logger.info("定时任务：开始更新代码");
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		Integer num = jobDataMap.getInt("num");
		
		try {
			String updateInfo = js.update(num);
			
			QrtzJobData jobData = new QrtzJobData();
			jobData.setJOBNAME(context.getJobDetail().getKey().getName());
			jobData.setJOBCLASS(PackageJob.class.getName());
			jobData.setEXCUTETIME(new Timestamp(System.currentTimeMillis()));
			String data="{updateInfo:'"+ updateInfo+"',type:'update'}";
			jobData.setJOBDATA(data);
			quartzService.insertJobData(jobData);
		} catch (InvalidRemoteException e) {
			e.printStackTrace();
		} catch (TransportException e) {
			e.printStackTrace();
		} catch (CommonException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
		logger.info("定时任务：更新代码完成");
	}
	
	

}
