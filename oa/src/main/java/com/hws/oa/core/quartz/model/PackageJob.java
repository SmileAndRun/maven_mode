package com.hws.oa.core.quartz.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;

import com.hws.oa.common.MyCommonConstants;
import com.hws.oa.util.DownUtils;
import com.hws.oa.util.RunTimeUtils;



public class PackageJob implements Job{

	private static Logger logger = Logger.getLogger(PackageJob.class);
	
	@Value("${package.address}")
	private String zipAddress;
	
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		logger.info("定时任务：开始打包");
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		String command = jobDataMap.getString("command");;
		String[] poms = (String[]) jobDataMap.get("poms");
		Integer[] nums = (Integer[]) jobDataMap.get("nums");
		
		Map<Integer, String> map = new HashMap<Integer, String>();
		for(int i = 0;i<nums.length ;i++){
			map.put(nums[i],poms[i]);
		}
		Arrays.sort(nums);
		if(null == command || "".equals(command))command = "mvn clean package install";
		for(Integer num:nums){
			try {
				RunTimeUtils.excute(map.get(num), command, null);
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
			DownUtils.zipData(poms,String.valueOf(MyCommonConstants.codeVersion.getAndIncrement()), zipAddress);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
