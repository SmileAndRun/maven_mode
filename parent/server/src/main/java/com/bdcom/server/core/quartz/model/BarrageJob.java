package com.bdcom.server.core.quartz.model;

import java.sql.Timestamp;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdcom.server.service.BarrageService;


public class BarrageJob implements Job{

	@Autowired
	BarrageService barrageService;
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		Date date = new Date();
		Integer count = barrageService.getBarrageCount(new Timestamp(date.getTime()));
		System.out.println("count:"+count);
		
		
	}
	
	

}
