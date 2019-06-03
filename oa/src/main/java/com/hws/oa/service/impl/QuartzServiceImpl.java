package com.hws.oa.service.impl;

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
import com.hws.oa.service.HQLService;
import com.hws.oa.service.MysqlService;
import com.hws.oa.service.QuartzService;
import com.hws.oa.util.MyCacheUtils;

@Service
public class QuartzServiceImpl implements QuartzService {
	@Autowired
	MysqlService mysqlService;
	@Autowired
	HQLService hqlService;
	
	@Override
	public List<QrtzJobDetails> getJobDetails() {
		String sql = "select d.JOB_NAME,START_TIME,END_TIME,TRIGGER_STATE,JOB_CLASS_NAME"
				+ " from qrtz_job_details d,qrtz_triggers t where d.JOB_NAME = t.JOB_NAME";
		List<QrtzJobDetails> list = hqlService.getEntitiesBySql(QrtzJobDetails.class, sql);
		return list;
	}
	@Override
	public QuartzModel getJobDetailForJobName(String jobName){
		String sql = "select d.JOB_NAME,START_TIME,END_TIME,TRIGGER_STATE,JOB_CLASS_NAME"
				+ " from qrtz_job_details d,qrtz_triggers t where d.JOB_NAME = t.JOB_NAME and d.JOB_NAME = ?";
		List<QrtzJobDetails> list = hqlService.getEntitiesBySql(QrtzJobDetails.class, sql, jobName);
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
		String sql = "update qrtz_job_details set IS_DURABLE = 1 where JOB_NAME = ?";
		return hqlService.updateBySql(sql, jobName);

	}
	
	@Override
	public QrtzJobDetails getJobData(String jobName) {
		String sql = "select JOB_DATA from qrtz_job_details where JOB_NAME = ?";
		return hqlService.getEntityBySql(QrtzJobDetails.class, sql, jobName);
	}
	
	@Override
	public boolean insertSelfDifined(QuartzModel model) {
		String sql = "insert into qrtz_self_defined(JOB_NAME,START_TIME,END_TIME,CRON_EXPRESSION,TRIGGER_STATE,JOB_CLASS_NAME) values(?,?,?,?,?,?)";
		return hqlService.updateBySql(sql, model.getJOB_NAME(),model.getSTART_TIME(),model.getEND_TIME(),model.getCRON_EXPRESSION(),model.getTRIGGER_STATE(),model.getJOB_CLASS_NAME());
	}
	
	@Override
	public List<QuartzModel> getALlFromMyDefine() {
		String sql = "select JOB_NAME,START_TIME,END_TIME,CRON_EXPRESSION,TRIGGER_STATE,JOB_CLASS_NAME"
				+ " from qrtz_self_defined";
		List<QuartzModel> list = hqlService.getEntitiesBySql(QuartzModel.class, sql);
		if(null == list ||list.size()<=0)return null;
		for(QuartzModel model:list){
			MyCacheUtils.addItem(model.getJOB_NAME());
		}
		return list;
	}
	@Autowired
	ScheduleMethod scheduleMethod;
	
	@Override
	public boolean deleteTasks(String[] names) throws SchedulerException {
		for(String name:names){
			MyCacheUtils.removeItem(name);
			if(scheduleMethod.deleJobDetails(new QuartzModel(name,name))){
				String sql = "delete from qrtz_self_defined where JOB_NAME = ?";
				if(hqlService.updateBySql(sql, name))
					return false;
				sql = "delete from qrtz_self_data where JOBNAME = ?";
				hqlService.updateBySql(sql,name);
			}
				
		}
		return true;
	}
	
	@Override
	public boolean updateSelfDefined(QuartzModel model) {
		StringBuffer sql = new StringBuffer("update qrtz_self_defined set 1=1 ");
		Object[] args = new Object[6];
		if(null != model.getJOB_NAME()&&!"".equals(model.getJOB_NAME()))
			{	sql.append(",JOB_NAME=?");
				args[0] = model.getJOB_NAME();
			}
		if(null != model.getSTART_TIME()&&!"".equals(model.getSTART_TIME()))
			{
				sql.append(",START_TIME=?");
				args[1] = model.getSTART_TIME();
			}
		if(null != model.getEND_TIME()&&!"".equals(model.getEND_TIME()))
			{
				sql.append(",END_TIME=?");
				args[2] = model.getEND_TIME();
			}
		if(null != model.getCRON_EXPRESSION()&&!"".equals(model.getCRON_EXPRESSION()))
			{
				sql.append(",CRON_EXPRESSION=?");
				args[3] = model.getCRON_EXPRESSION();
			}
		if(null != model.getTRIGGER_STATE()&&!"".equals(model.getTRIGGER_STATE()))
			{
				sql.append(",TRIGGER_STATE=?");
				args[4] = model.getTRIGGER_STATE();
			}
		if(null != model.getJOB_CLASS_NAME()&&!"".equals(model.getJOB_CLASS_NAME()))
			{
				sql.append(",JOB_CLASS_NAME=?");
				args[5] = model.getJOB_CLASS_NAME();
			}
		return hqlService.updateBySql(sql.toString(), args);
	}
	
	@Override
	public void pausedTasks(String name) throws SchedulerException {
		scheduleMethod.stopJobDetails(new JobKey(name,name));
		QuartzModel model = new QuartzModel();
		model.setJOB_NAME(name);
		model.setSTART_TIME("PAUSED");
		updateSelfDefined(model);
	}
	
	@Override
	public void openTasks(String name) throws SchedulerException {
		scheduleMethod.recoverJobDetails(new JobKey(name,name));
	}
	
	@Override
	public QrtzJobDetails seeTasksDetais(String name) {
		return getJobData(name);
	}
	
	@Override
	public boolean insertJobData(QrtzJobData model) {
		String sql = "insert into qrtz_self_data(JOBNAME,EXCUTETIME,JOBDATA,JOBCLASS)"
				+ "values(?,?,?,?)";
		return hqlService.updateBySql(sql, model.getJOBNAME(),model.getEXCUTETIME(),model.getJOBDATA(),model.getJOBCLASS());
	}
	
	@Override
	public JSONObject getJobDataByJobName(String jobName,String jobClass) {
		String sql = "select DATAID,JOBNAME,EXCUTETIME,JOBDATA,JOBCLASS from qrtz_self_data where JOBNAME = ? GROUP BY EXCUTETIME";
		List<QrtzJobData> list = hqlService.getEntitiesBySql(QrtzJobData.class, sql, jobName);
		if(list == null||list.size()==0) return null;
		JSONObject obj = new JSONObject();
		obj.put("result", list);
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