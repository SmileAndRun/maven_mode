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
		Date startTime= sdf.parse(model.getSTART_TIME());
		Date endTime = sdf.parse(model.getEND_TIME());
				
		CronTrigger trigger = TriggerBuilder.newTrigger().
                withSchedule(CronScheduleBuilder.cronSchedule(model.getCRON_EXPRESSION())).withIdentity(model.getTRIGGER_NAME(), model.getTRIGGER_GROUP()).startAt(startTime).endAt(endTime).build();
		
		return trigger;
	}
}
