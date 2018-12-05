package com.bdcom.server.service;

import java.util.List;

import org.common.model.QrtzJobData;
import org.common.model.QrtzJobDetails;
import org.common.model.QuartzModel;
import org.quartz.SchedulerException;

import com.alibaba.fastjson.JSONObject;

public interface QuartzService {
	public List<QrtzJobDetails> getJobDetails();
	public QuartzModel getJobDetailForJobName(String jobName);
	public boolean setPermanentStorage(String jobName);
	public QrtzJobDetails getJobData(String jobName);
	public boolean insertSelfDifined(QuartzModel model);
	public List<QuartzModel> getALlFromMyDefine();
	public boolean deleteTasks(String[] names)throws SchedulerException;
	public void pausedTasks(String name) throws SchedulerException;
	public void openTasks(String name) throws SchedulerException;
	public QrtzJobDetails seeTasksDetais(String name) throws SchedulerException;
	public boolean updateSelfDefined(QuartzModel model);
	public boolean insertJobData(QrtzJobData model);
	public JSONObject getJobDataByJobName(String jobName,String jobClass);
}
