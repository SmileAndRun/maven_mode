package com.hws.oa.core.quartz.model;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.hws.oa.model.VersionModel;
import com.hws.oa.service.MysqlService;



public class DeleteJob implements Job{

	private static Logger logger = Logger.getLogger(DeleteJob.class);
	
	@Value("${package.address}")
	private String zipAddress;
	@Autowired
	private MysqlService mysqlService;
	
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		logger.info("定时任务：开始删除文件");
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		Long startTime = jobDataMap.getLongValue("startTime");;
		Long endTime = jobDataMap.getLongValue("endTime");
		
		startTime = (startTime == null)?01 :startTime;
		endTime = (endTime == null)?System.currentTimeMillis() :endTime;
		
		List<VersionModel> list = mysqlService.getListVersionModelByTime(new Timestamp(startTime), new Timestamp(endTime));
		if(null != list && list.size() != 0){
			for(VersionModel model : list){
				new File(zipAddress + File.separator + model.getVersionId()+".zip").delete();
			}
		}
		logger.info("定时任务：删除文件完成");
	}
	
	

}
