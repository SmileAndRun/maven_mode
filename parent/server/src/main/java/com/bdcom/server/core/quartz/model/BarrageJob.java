package com.bdcom.server.core.quartz.model;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdcom.server.service.BarrageService;
import com.bdcom.server.service.QuartzService;
import com.server.restful.api.pojo.QrtzJobData;


public class BarrageJob implements Job{

	private static Logger logger = Logger.getLogger(BarrageJob.class);
	
	@Autowired
	BarrageService barrageService;
	@Autowired
	QuartzService quartzService;
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		logger.info("BarrageJob开始执行");
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		Integer count = barrageService.getBarrageCount(time);
		quartzService.insertJobData(new QrtzJobData(-1,context.getJobDetail().getKey().getName(),time,String.valueOf(count),BarrageJob.class.getName()));
		logger.info("BarrageJob执行完毕");
	}
	
	

}
