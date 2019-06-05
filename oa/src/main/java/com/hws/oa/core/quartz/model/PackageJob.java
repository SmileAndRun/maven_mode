package com.hws.oa.core.quartz.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.hws.oa.common.MyCommonConstants;
import com.hws.oa.exception.CommonException;
import com.hws.oa.model.QrtzJobData;
import com.hws.oa.model.VersionModel;
import com.hws.oa.service.JGitService;
import com.hws.oa.service.MysqlService;
import com.hws.oa.service.QuartzService;
import com.hws.oa.util.DownUtils;
import com.hws.oa.util.RunTimeUtils;



public class PackageJob implements Job{

	private static Logger logger = Logger.getLogger(PackageJob.class);
	
	@Value("${package.address}")
	private String zipAddress;
	
	@Autowired
	MysqlService mysqlService;
	@Autowired
	QuartzService quartzService;
	
	@Autowired
	JGitService js;
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		logger.info("定时任务：开始打包");
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		String command = jobDataMap.getString("command");;
		String[] poms = (String[]) jobDataMap.get("poms");
		Integer[] nums = (Integer[]) jobDataMap.get("nums");
		Integer confNum = jobDataMap.getInt("num");
		
		//先更新代码
		String updateInfo = "";
		try {
			updateInfo = js.update(confNum);
			if(null == updateInfo)updateInfo ="";
		} catch (InvalidRemoteException e1) {
			e1.printStackTrace();
		} catch (TransportException e1) {
			e1.printStackTrace();
		} catch (CommonException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (GitAPIException e1) {
			e1.printStackTrace();
		}
		
		//打包
		Map<Integer, String> map = new HashMap<Integer, String>();
		for(int i = 0;i<nums.length ;i++){
			map.put(nums[i],poms[i]);
		}
		Arrays.sort(nums);
		if(null == command || "".equals(command))command = " mvn clean package install";
		String totalMessage = "";
		for(Integer num:nums){
			try {
				String message = RunTimeUtils.excute(map.get(num).replace("pom.xml", ""), command);
				totalMessage += message;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		logger.info("定时任务：打包完成");
		for(int i=0;i<poms.length;i++){
			poms[i] = poms[i].replace("pom.xml", "");
		}
		try {
			//压缩文件
			String versionId = DownUtils.zipData(poms,String.valueOf(MyCommonConstants.codeVersion.getAndIncrement()), zipAddress);
			//插入数据库
			VersionModel versionModel = new VersionModel();
			versionModel.setVersionId(Integer.parseInt(versionId));
			Timestamp createTime = new Timestamp(System.currentTimeMillis());
			versionModel.setCreateTime(createTime);
			versionModel.setUpdateInfo(updateInfo);
			versionModel.setPackageInfo(totalMessage);
			mysqlService.addVersionModel(versionModel);
			QrtzJobData jobData = new QrtzJobData();
			jobData.setJOBNAME(context.getJobDetail().getKey().getName());
			jobData.setJOBCLASS(PackageJob.class.getName());
			jobData.setEXCUTETIME(new Timestamp(System.currentTimeMillis()));
			String data="{updateInfo:'"+ updateInfo+"',packageInfo:'"+totalMessage+"',type:'package'}";
			jobData.setJOBDATA(data);
			quartzService.insertJobData(jobData);
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
