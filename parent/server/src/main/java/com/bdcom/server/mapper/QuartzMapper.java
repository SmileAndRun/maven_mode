package com.bdcom.server.mapper;

import java.util.List;

import org.common.model.QrtzJobDetails;
import org.common.model.QuartzModel;

public interface QuartzMapper {
	
	public List<QrtzJobDetails> getJobDetails();
	public List<QrtzJobDetails> getJobDetailForJobName(String jobName);
	public int setPermanentStorage(String jobName);
	public QrtzJobDetails getJobData(String jobName);
	public int insertSelfDifined(QuartzModel model);
	public List<QuartzModel> getALlFromMyDefine();
	public int deleteJobDetails(String name);
}
