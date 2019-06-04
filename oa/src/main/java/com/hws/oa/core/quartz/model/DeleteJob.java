package com.hws.oa.core.quartz.model;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.hws.oa.model.QrtzJobData;
import com.hws.oa.model.VersionModel;
import com.hws.oa.service.MysqlService;
import com.hws.oa.service.QuartzService;



public class DeleteJob implements Job{

	private static Logger logger = Logger.getLogger(DeleteJob.class);
	
	@Value("${package.address}")
	private String zipAddress;
	@Autowired
	private MysqlService mysqlService;
	
	@Autowired
	QuartzService quartzService;
	
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		logger.info("定时任务：开始删除文件");
		Long startTime = 0l;
		Long endTime = System.currentTimeMillis() - 30*24*60*60*1000;
		
		List<VersionModel> list = mysqlService.getListVersionModelByTime(new Timestamp(startTime), new Timestamp(endTime));
		String deleteInfo = "";
		if(null != list && list.size() != 0){
			for(VersionModel model : list){
				String fileName = zipAddress + File.separator + model.getVersionId()+".zip";
				boolean flag = new File(fileName).delete();
				if(flag) deleteInfo += fileName +"<br/>";
			}
		}
		
		QrtzJobData jobData = new QrtzJobData();
		jobData.setJOBNAME(context.getJobDetail().getKey().getName());
		jobData.setJOBCLASS(PackageJob.class.getName());
		jobData.setEXCUTETIME(new Timestamp(System.currentTimeMillis()));
		String data="{deleteInfo:'"+ deleteInfo +"',type:'delete'}";
		jobData.setJOBDATA(data);
		quartzService.insertJobData(jobData);
		logger.info("定时任务：删除文件完成");
	}
	
	

}
