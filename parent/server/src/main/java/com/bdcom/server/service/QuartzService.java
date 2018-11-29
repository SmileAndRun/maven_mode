package com.bdcom.server.service;

import java.util.List;

import org.common.model.QrtzJobDetails;
import org.common.model.QuartzModel;
import org.quartz.SchedulerException;

public interface QuartzService {
	public List<QrtzJobDetails> getJobDetails();
	public QuartzModel getJobDetailForJobName(String jobName);
	public boolean setPermanentStorage(String jobName);
	public QrtzJobDetails getJobData(String jobName);
	public boolean insertSelfDifined(QuartzModel model);
	public List<QuartzModel> getALlFromMyDefine();
	public boolean deleteTasks(String[] names)throws SchedulerException;
	public boolean updateSelfDefined(QuartzModel model);
}
