package com.bdcom.server.mapper;

import java.util.List;

import org.common.model.QrtzJobDetails;

public interface QuartzMapper {
	
	public List<QrtzJobDetails> getJobDetails();
}