package com.bdcom.hws.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.bdcom.hws.model.CronScheduleModel;
import com.bdcom.hws.model.QuartzNameModel;


public class MyQuartzUtils {
	
	private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	public static Scheduler getScheduler() throws SchedulerException{
		Scheduler scheduler = schedulerFactory.getScheduler();
		return scheduler;
	}
	
	public static CronTrigger getCronTrigger(CronScheduleModel model,QuartzNameModel nameModel) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime= sdf.parse(model.getStartDate());
		Date endTime = sdf.parse(model.getEndDate());
				
		CronTrigger trigger = TriggerBuilder.newTrigger().
                withSchedule(CronScheduleBuilder.cronSchedule(getCron(model))).withIdentity(nameModel.getTriggerName(), nameModel.getTriggerGroup()).startAt(startTime).endAt(endTime).build();
		
		return trigger;
	}
	
	public static String getCron(CronScheduleModel model){
		String cron= "";
		String second = model.getSecond();
		String minute = model.getMinute();
		String hour = model.getHour();
		String date = model.getDate();
		String month = model.getMonth();
		String week = model.getWeek();
		String year = model.getYear();
		
		cron += transforTime(second);
		cron += transforTime(minute);
		cron += transforTime(hour);
		cron += transforTime(date);
		cron += transforTime(month);
		cron += transforTime(week);
		cron += transforTime(year);
		//清除最后的空格
		cron = cron.substring(0, cron.length()-1);
		return cron;
	}
	
	
	public static String transforTime(String time){
	
		if(time.equals("all"))return "* ";
		return time + " ";
	}
}
