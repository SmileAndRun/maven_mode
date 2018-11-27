package com.bdcom.server.service;

import java.util.List;

import org.common.model.QrtzJobDetails;

public interface QuartzService {
	public List<QrtzJobDetails> getJobDetails();
	public List<QrtzJobDetails> getJobDetailForJobName(String jobName);
}
