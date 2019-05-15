/*package com.hws.oa.service.impl;

import java.util.List;

import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hws.oa.core.quartz.ScheduleMethod;
import com.hws.oa.model.QrtzJobData;
import com.hws.oa.model.QrtzJobDetails;
import com.hws.oa.model.QuartzModel;
import com.hws.oa.service.QuartzService;

@Service
public class QuartzServiceImpl implements QuartzService {

	@Autowired
	QuartzMapper quartzMapper;
	@Override
	public List<QrtzJobDetails> getJobDetails() {
		List<QrtzJobDetails> list = quartzMapper.getJobDetails();
		if(list.size()<=0)return null;
		return list;
	}
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

	
	@Override
	public boolean setPermanentStorage(String jobName) {
		return quartzMapper.setPermanentStorage(jobName)==0?false:true;
	}
	
	@Override
	public QrtzJobDetails getJobData(String jobName) {
		return quartzMapper.getJobData(jobName);
	}
	
	@Override
	public boolean insertSelfDifined(QuartzModel model) {
		return quartzMapper.insertSelfDifined(model)==0?false:true;
	}
	
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
	
	@Override
	public boolean updateSelfDefined(QuartzModel model) {
		return quartzMapper.updateSelfDefined(model)==0?false:true;
	}
	
	@Override
	public void pausedTasks(String name) throws SchedulerException {
		scheduleMethod.stopJobDetails(new JobKey(name,name));
		QuartzModel model = new QuartzModel();
		model.setJOB_NAME(name);
		model.setSTART_TIME("PAUSED");
		quartzMapper.updateSelfDefined(model);
	}
	
	@Override
	public void openTasks(String name) throws SchedulerException {
		scheduleMethod.recoverJobDetails(new JobKey(name,name));
	}
	
	@Override
	public QrtzJobDetails seeTasksDetais(String name) {
		return quartzMapper.getJobData(name);
	}
	
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
	
	@Override
	public JSONObject getJobDataByJobName(String jobName,String jobClass) {
		List<QrtzJobData> list = quartzMapper.getJobDataByJobName(jobName);
		if(list == null||list.size()==0)
		return null;
		JSONObject obj = (JSONObject)JobDataUtils.translate(jobClass, list);
		return obj;
	}
	
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
*/