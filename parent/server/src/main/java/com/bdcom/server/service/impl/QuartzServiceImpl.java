package com.bdcom.server.service.impl;

import java.util.List;

import org.common.core.annotation.TargetDataSource;
import org.common.core.datasource.DatabaseType;
import org.common.model.QrtzJobData;
import org.common.model.QrtzJobDetails;
import org.common.model.QuartzModel;
import org.common.utils.JobDataUtils;
import org.common.utils.MyCacheUtils;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
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
		quartzModel.setJOB_CLASS_NAME(list.get(0).getJOB_CLASS_NAME());
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
		if(list.size()<=0)list = null;
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
				quartzMapper.deleteDataByJobName(name);
			}
				
		}
		return true;
	}
	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public boolean updateSelfDefined(QuartzModel model) {
		return quartzMapper.updateSelfDefined(model)==0?false:true;
	}
	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public void pausedTasks(String name) throws SchedulerException {
		scheduleMethod.stopJobDetails(new JobKey(name,name));
		QuartzModel model = new QuartzModel();
		model.setJOB_NAME(name);
		model.setSTART_TIME("PAUSED");
		quartzMapper.updateSelfDefined(model);
	}
	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public void openTasks(String name) throws SchedulerException {
		scheduleMethod.recoverJobDetails(new JobKey(name,name));
	}
	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public QrtzJobDetails seeTasksDetais(String name) {
		return quartzMapper.getJobData(name);
	}
	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public boolean insertJobData(QrtzJobData model) {
		if(model.getDATAID()==-1){
			QrtzJobData maxId = quartzMapper.getMaxDataId();
			if(null == maxId){
				System.out.println("NULL");
				model.setDATAID(1);
			}else{
				model.setDATAID(maxId.getDATAID()+1);
			}
		}
		return quartzMapper.insertJobData(model)==0?false:true;
	}
	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public JSONObject getJobDataByJobName(String jobName,String jobClass) {
		List<QrtzJobData> list = quartzMapper.getJobDataByJobName(jobName);
		if(list == null||list.size()==0)
		return null;
		JSONObject obj = (JSONObject)JobDataUtils.translate(jobClass, list);
		return obj;
	}
	@TargetDataSource(dataBaseType = DatabaseType.quartz)
	@Override
	public JSONObject menuOperate(String[] names, String type, String jobClass) throws SchedulerException {
			
		JSONObject obj = new JSONObject();
		boolean flag = false;
		switch (type) {
			case "1":
				//此处调用方法不会触发getJobDataByJobName的aop
				JSONObject dataJson = getJobDataByJobName(names[0], jobClass);
				obj.put("dataJson", dataJson);
				flag = true;
				break;
			case "2":
				flag = deleteTasks(names);
				break;
			case "3":
				flag = true;
				pausedTasks(names[0]);
				break;
			case "4":
				flag = true;
				openTasks(names[0]);
				break;
			default:
				break;
			}
		obj.put("flag", flag);
		return obj;	
	}
}
