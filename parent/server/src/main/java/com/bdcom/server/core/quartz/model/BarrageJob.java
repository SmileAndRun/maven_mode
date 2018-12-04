package com.bdcom.server.core.quartz.model;

import java.sql.Timestamp;
import java.util.Date;

import org.common.model.QrtzJobData;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdcom.server.service.BarrageService;
import com.bdcom.server.service.QuartzService;


public class BarrageJob implements Job{

	@Autowired
	BarrageService barrageService;
	@Autowired
	QuartzService quartzService;
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		Integer count = barrageService.getBarrageCount(time);
		System.out.println("count:"+count);
		quartzService.insertJobData(new QrtzJobData(context.getJobDetail().getKey().getName(),time,String.valueOf(count),BarrageJob.class.getName()));
		
	}
	
	

}
