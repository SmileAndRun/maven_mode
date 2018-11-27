package com.bdcom.server.service.impl;

import java.util.List;

import org.common.core.annotation.TargetDataSource;
import org.common.core.datasource.DatabaseType;
import org.common.model.QrtzJobDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdcom.server.mapper.QuartzMapper;
import com.bdcom.server.service.QuartzService;

@Service
public class QuartzServiceImpl implements QuartzService {

	@Autowired
	QuartzMapper quartzMapper;
	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public List<QrtzJobDetails> getJobDetails() {
		List<QrtzJobDetails> list = quartzMapper.getJobDetails();
		if(list.size()<=0)return null;
		return list;
	}
	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public List<QrtzJobDetails> getJobDetailForJobName(String jobName){
		List<QrtzJobDetails> list = quartzMapper.getJobDetailForJobName(jobName);
		if(list.size()<=0)return null;
		return list;
	}
	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public int setPermanentStorage(String jobName) {
		return quartzMapper.setPermanentStorage(jobName);
	}
}
