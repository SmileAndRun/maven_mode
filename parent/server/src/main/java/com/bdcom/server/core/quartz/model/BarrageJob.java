package com.bdcom.server.core.quartz.model;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class BarrageJob implements Job{

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("1111111");
		System.out.println(new Date());
		
	}
	
	

}
