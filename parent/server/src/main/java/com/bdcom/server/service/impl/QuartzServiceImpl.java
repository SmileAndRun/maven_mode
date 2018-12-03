package com.bdcom.server.service.impl;

import java.util.List;

import org.common.core.annotation.TargetDataSource;
import org.common.core.datasource.DatabaseType;
import org.common.model.QrtzJobDetails;
import org.common.model.QuartzModel;
import org.common.utils.MyCacheUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdcom.server.core.quartz.ScheduleMethod;
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
	public QuartzModel getJobDetailForJobName(String jobName){
		List<QrtzJobDetails> list = quartzMapper.getJobDetailForJobName(jobName);
		if(list.size()<=0)return null;
		QuartzModel quartzModel = new QuartzModel();
		quartzModel.setJOB_NAME(list.get(0).getJOB_NAME());
		quartzModel.setSTART_TIME(list.get(0).getQrtzTriggers().getSTART_TIME());
		quartzModel.setEND_TIME(list.get(0).getQrtzTriggers().getEND_TIME());
		quartzModel.setTRIGGER_STATE(list.get(0).getQrtzTriggers().getTRIGGER_STATE());
		return quartzModel;
	}
	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public boolean setPermanentStorage(String jobName) {
		return quartzMapper.setPermanentStorage(jobName)==0?false:true;
	}
	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public QrtzJobDetails getJobData(String jobName) {
		return quartzMapper.getJobData(jobName);
	}
	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public boolean insertSelfDifined(QuartzModel model) {
		return quartzMapper.insertSelfDifined(model)==0?false:true;
	}
	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public List<QuartzModel> getALlFromMyDefine() {
		List<QuartzModel> list = quartzMapper.getALlFromMyDefine();
		for(QuartzModel model:list){
			MyCacheUtils.addItem(model.getJOB_NAME());
		}
		return list;
	}
	@Autowired
	ScheduleMethod scheduleMethod;
	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public boolean deleteTasks(String[] names) throws SchedulerException {
		for(String name:names){
			MyCacheUtils.removeItem(name);
			if(scheduleMethod.deleJobDetails(new QuartzModel(name,name))){
				if(quartzMapper.deleteJobDetails(name)==0)
					return false;
			}
				
		}
		return true;
	}
	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public boolean updateSelfDefined(QuartzModel model) {
		return quartzMapper.updateSelfDefined(model)==0?false:true;
	}
}
