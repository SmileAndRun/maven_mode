package com.hws.oa.core.quartz.model;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;

import com.hws.oa.util.RunTimeUtils;



public class PackageJob implements Job{

	private static Logger logger = Logger.getLogger(PackageJob.class);
	
	@Value("${package.address}")
	private String zipAddress;
	
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		logger.info("定时任务：开始打包");
		
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		String command = jobDataMap.getString("command");;
		String[] poms = (String[]) jobDataMap.get("poms");
		for(String temp:poms){
			try {
				RunTimeUtils.excute(temp, command, null);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		logger.info("定时任务：打包完成");
	}
	
	

}