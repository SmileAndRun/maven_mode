package com.server.data.mapper;

import java.util.List;

import com.server.restful.api.pojo.QrtzJobData;
import com.server.restful.api.pojo.QrtzJobDetails;
import com.server.restful.api.pojo.QuartzModel;



public interface QuartzMapper {
	
	public List<QrtzJobDetails> getJobDetails();
	public List<QrtzJobDetails> getJobDetailForJobName(String jobName);
	public Integer setPermanentStorage(String jobName);
	public QrtzJobDetails getJobData(String jobName);
	public Integer insertSelfDifined(QuartzModel model);
	public List<QuartzModel> getALlFromMyDefine();
	public Integer deleteJobDetails(String name);
	public Integer updateSelfDefined(QuartzModel model);
	public Integer insertJobData(QrtzJobData model);
	public QrtzJobData getMaxDataId();
	public Integer deleteDataByJobName(String name);
	public List<QrtzJobData> getJobDataByJobName(String jobName);
}
