package com.bdcom.server.mapper;

import java.util.List;

import org.common.model.QrtzJobDetails;

public interface QuartzMapper {
	
	public List<QrtzJobDetails> getJobDetails();
	public List<QrtzJobDetails> getJobDetailForJobName(String jobName);
	public int setPermanentStorage(String jobName);
}
