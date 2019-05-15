package com.hws.oa.core.quartz.model;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;



public class BarrageJob implements Job{

	private static Logger logger = Logger.getLogger(BarrageJob.class);
	
	
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
	}
	
	

}
