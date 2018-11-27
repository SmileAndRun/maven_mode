package org.common.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.common.model.QuartzModel;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;



public class MyQuartzUtils {
	
	private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	public static Scheduler getScheduler() throws SchedulerException{
		Scheduler scheduler = schedulerFactory.getScheduler();
		return scheduler;
	}
	
	public static CronTrigger getCronTrigger(QuartzModel model) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime= sdf.parse(model.getStartDate());
		Date endTime = sdf.parse(model.getEndDate());
				
		CronTrigger trigger = TriggerBuilder.newTrigger().
                withSchedule(CronScheduleBuilder.cronSchedule(model.getExpression())).withIdentity(model.getTriggerName(), model.getTriggerGroup()).startAt(startTime).endAt(endTime).build();
		
		return trigger;
	}
}
