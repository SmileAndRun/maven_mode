package com.bdcom.server.core.quartz;

import java.util.Properties;

import org.common.utils.ReadResourceUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.bdcom.server.core.listener.MyJobListener;

@Configuration
public class ScheduleConfig {
	@Autowired
	MyJobListener myJobListener;
	@Autowired
	MyJobFactory myJobFactory;
	
	@Bean
	public SchedulerFactoryBean getSchedulerFactoryBean(){
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setJobFactory(myJobFactory);
		Properties quartzProperties = ReadResourceUtils.getProperties(this.getClass().getResource("/quartz.properties").getPath());
		if(null != quartzProperties)factory.setQuartzProperties(quartzProperties);
		return factory;
	}
	@Bean(name="myScheduler")
	public  Scheduler getScheduler() throws SchedulerException{
		//SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = getSchedulerFactoryBean().getScheduler();
		scheduler.getListenerManager().addJobListener(myJobListener);
		return scheduler;
	}
	
}
