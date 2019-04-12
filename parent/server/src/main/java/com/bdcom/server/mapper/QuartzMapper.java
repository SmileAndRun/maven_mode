package com.bdcom.server.mapper;

import java.util.List;

import com.server.restful.api.pojo.QrtzJobData;
import com.server.restful.api.pojo.QrtzJobDetails;
import com.server.restful.api.pojo.QuartzModel;


public interface QuartzMapper {
	
	public List<QrtzJobDetails> getJobDetails();
	public List<QrtzJobDetails> getJobDetailForJobName(String jobName);
	public int setPermanentStorage(String jobName);
	public QrtzJobDetails getJobData(String jobName);
	public int insertSelfDifined(QuartzModel model);
	public List<QuartzModel> getALlFromMyDefine();
	public int deleteJobDetails(String name);
	public int updateSelfDefined(QuartzModel model);
	public int insertJobData(QrtzJobData model);
	public QrtzJobData getMaxDataId();
	public int deleteDataByJobName(String name);
	public List<QrtzJobData> getJobDataByJobName(String jobName);
}
