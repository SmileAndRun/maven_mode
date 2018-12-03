package com.bdcom.server.core.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bdcom.server.core.listener.MyJobListener;

@Configuration
public class ScheduleConfig {
	@Autowired
	MyJobListener myJobListener;
	@Bean(name="myScheduler")
	public  Scheduler getScheduler() throws SchedulerException{
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		scheduler.getListenerManager().addJobListener(myJobListener);
		return scheduler;
	}
}
