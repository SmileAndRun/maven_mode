package com.bdcom.server.core.quartz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.common.model.QuartzModel;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMethod {

	@Autowired
	Scheduler myScheduler;
	public  CronTrigger getCronTrigger(QuartzModel model) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime= sdf.parse(model.getSTART_TIME());
		Date endTime = sdf.parse(model.getEND_TIME());
				
		CronTrigger trigger = TriggerBuilder.newTrigger().
                withSchedule(CronScheduleBuilder.cronSchedule(model.getCRON_EXPRESSION())).withIdentity(model.getTRIGGER_NAME(), model.getTRIGGER_GROUP()).startAt(startTime).endAt(endTime).build();
		
		return trigger;
	}
	/**
	 * 添加一个任务
	 * @param name
	 * @param model
	 * @param nameModel
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public void addJobDetails(@SuppressWarnings("rawtypes") Class name,QuartzModel model) throws SchedulerException, ParseException{
		Scheduler scheduler = myScheduler;
		@SuppressWarnings("unchecked")
		JobDetail myjob = JobBuilder.newJob(name).withIdentity(model.getJOB_NAME(), model.getJOB_GROUP()).build();
		scheduler.scheduleJob(myjob, getCronTrigger(model));
		
		scheduler.start();
		
	}
	/**
	 * 删除一个任务
	 * @param model
	 * @param nameModel
	 * @return
	 * @throws SchedulerException
	 */
	public boolean deleJobDetails(QuartzModel model) throws SchedulerException{
		Scheduler scheduler = myScheduler;
		boolean flag = scheduler.deleteJob(JobKey.jobKey(model.getJOB_NAME(), model.getJOB_GROUP()));
		return flag;
	}
	/**
	 * 删除多个任务
	 * @param jobKeys
	 * @return
	 * @throws SchedulerException
	 */
	public boolean deleAllJobDetails(List<JobKey> jobKeys) throws SchedulerException{
		Scheduler scheduler = myScheduler;
		boolean flag = scheduler.deleteJobs(jobKeys);
		return flag;
	}
	/**
	 * 暂停所有任务
	 * @throws SchedulerException
	 */
	public void stopAllJobDetails() throws SchedulerException{
		Scheduler scheduler = myScheduler;
		scheduler.pauseAll();
	}
	/**
	 * 暂停一个任务
	 * @param jobKey
	 * @throws SchedulerException
	 */
	public void stopJobDetails(JobKey jobKey) throws SchedulerException{
		Scheduler scheduler = myScheduler;
		scheduler.pauseJob(jobKey);
	}
	/**
	 * 暂停一组任务
	 * @param jobGroup
	 * @throws SchedulerException
	 */
	public void stopJobGroup(GroupMatcher<JobKey> jobGroup) throws SchedulerException{
		Scheduler scheduler = myScheduler;
		scheduler.pauseJobs(jobGroup);
	}
	/**
	 * 恢复所有暂停任务
	 * @throws SchedulerException
	 */
	public void recoverAllJobDetails() throws SchedulerException{
		Scheduler scheduler = myScheduler;
		scheduler.resumeAll();
	}
	/**
	 * 恢复一个暂停任务
	 * @param jobKey
	 * @throws SchedulerException
	 */
	public void recoverJobDetails(JobKey jobKey) throws SchedulerException{
		Scheduler scheduler = myScheduler;
		scheduler.resumeJob(jobKey);
	}
	/**
	 * 恢复一个组任务
	 * @param jobGroup
	 * @throws SchedulerException
	 */
	public void recoverJobGroup(GroupMatcher<JobKey> jobGroup) throws SchedulerException{
		Scheduler scheduler = myScheduler;
		scheduler.resumeJobs(jobGroup);
	}
	/**
	 * 停止调度器
	 * @throws SchedulerException
	 */
	public void stopScheduler() throws SchedulerException{
		Scheduler scheduler = myScheduler;
		scheduler.shutdown();
	}
}
