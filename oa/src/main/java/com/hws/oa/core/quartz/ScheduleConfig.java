package com.hws.oa.core.quartz;

import java.util.Properties;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.hws.oa.core.listener.MyJobListener;
import com.hws.oa.core.quartz.model.QuartzProperties;


@Configuration
public class ScheduleConfig {
	@Autowired
	MyJobListener myJobListener;
	@Autowired
	MyJobFactory myJobFactory;
	
	@Autowired
	QuartzProperties qp;
	@Bean
	public SchedulerFactoryBean getSchedulerFactoryBean(){
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setJobFactory(myJobFactory);
		Properties quartzProperties = qp.toProperties();
		if(null != quartzProperties)factory.setQuartzProperties(quartzProperties);
		return factory;
	}
	@Bean(name="myScheduler")
	public  Scheduler getScheduler() throws SchedulerException{
		Scheduler scheduler = getSchedulerFactoryBean().getScheduler();
		scheduler.getListenerManager().addJobListener(myJobListener);
		return scheduler;
	}
	
}
