package com.bdcom.hws.core.quartz;

import java.text.ParseException;
import java.util.List;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;

import com.bdcom.hws.model.CronScheduleModel;
import com.bdcom.hws.model.QuartzNameModel;
import com.bdcom.hws.utils.MyQuartzUtils;

public class ScheduleConfig {
	
	/**
	 * 添加一个任务
	 * @param name
	 * @param model
	 * @param nameModel
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public void addJobDetails(@SuppressWarnings("rawtypes") Class name,CronScheduleModel model,QuartzNameModel nameModel) throws SchedulerException, ParseException{
		Scheduler scheduler = MyQuartzUtils.getScheduler();
		@SuppressWarnings("unchecked")
		JobDetail myjob = JobBuilder.newJob(name).withIdentity(nameModel.getJobName(), nameModel.getJobGroup()).build();
		scheduler.scheduleJob(myjob, MyQuartzUtils.getCronTrigger(model,nameModel));
		
		scheduler.start();
		
	}
	/**
	 * 删除一个任务
	 * @param model
	 * @param nameModel
	 * @return
	 * @throws SchedulerException
	 */
	public boolean deleJobDetails(CronScheduleModel model,QuartzNameModel nameModel) throws SchedulerException{
		Scheduler scheduler = MyQuartzUtils.getScheduler();
		boolean flag = scheduler.deleteJob(JobKey.jobKey(nameModel.getJobName(), nameModel.getJobGroup()));
		return flag;
	}
	/**
	 * 删除多个任务
	 * @param jobKeys
	 * @return
	 * @throws SchedulerException
	 */
	public boolean deleAllJobDetails(List<JobKey> jobKeys) throws SchedulerException{
		Scheduler scheduler = MyQuartzUtils.getScheduler();
		boolean flag = scheduler.deleteJobs(jobKeys);
		return flag;
	}
	/**
	 * 暂停所有任务
	 * @throws SchedulerException
	 */
	public void stopAllJobDetails() throws SchedulerException{
		Scheduler scheduler = MyQuartzUtils.getScheduler();
		scheduler.pauseAll();
	}
	/**
	 * 暂停一个任务
	 * @param jobKey
	 * @throws SchedulerException
	 */
	public void stopJobDetails(JobKey jobKey) throws SchedulerException{
		Scheduler scheduler = MyQuartzUtils.getScheduler();
		scheduler.pauseJob(jobKey);
	}
	/**
	 * 暂停一组任务
	 * @param jobGroup
	 * @throws SchedulerException
	 */
	public void stopJobGroup(GroupMatcher<JobKey> jobGroup) throws SchedulerException{
		Scheduler scheduler = MyQuartzUtils.getScheduler();
		scheduler.pauseJobs(jobGroup);
	}
	/**
	 * 恢复所有暂停任务
	 * @throws SchedulerException
	 */
	public void recoverAllJobDetails() throws SchedulerException{
		Scheduler scheduler = MyQuartzUtils.getScheduler();
		scheduler.resumeAll();
	}
	/**
	 * 恢复一个暂停任务
	 * @param jobKey
	 * @throws SchedulerException
	 */
	public void recoverJobDetails(JobKey jobKey) throws SchedulerException{
		Scheduler scheduler = MyQuartzUtils.getScheduler();
		scheduler.resumeJob(jobKey);
	}
	/**
	 * 恢复一个组任务
	 * @param jobGroup
	 * @throws SchedulerException
	 */
	public void recoverJobGroup(GroupMatcher<JobKey> jobGroup) throws SchedulerException{
		Scheduler scheduler = MyQuartzUtils.getScheduler();
		scheduler.resumeJobs(jobGroup);
	}
	/**
	 * 停止调度器
	 * @throws SchedulerException
	 */
	public void stopScheduler() throws SchedulerException{
		Scheduler scheduler = MyQuartzUtils.getScheduler();
		scheduler.shutdown();
	}
}
