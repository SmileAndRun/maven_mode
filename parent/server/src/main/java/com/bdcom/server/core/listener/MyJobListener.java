package com.bdcom.server.core.listener;

import org.common.model.QuartzModel;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdcom.server.service.QuartzService;
/**
 * 监听定时任务
 * @author hws
 *
 */
public class MyJobListener implements JobListener {

	@Override
	public String getName() {
		return "myJobListener";
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		String jobName = context.getJobDetail().getKey().getName();
		QuartzModel model = new QuartzModel();
		model.setJOB_NAME(jobName);
		model.setTRIGGER_STATE("SCHEDULING");
		quartzService.updateSelfDefined(model);
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {

	}
	@Autowired
	QuartzService quartzService;
	/**任务执行后更新任务状态*/
	@Override
	public void jobWasExecuted(JobExecutionContext context,
			JobExecutionException jobException) {
		String jobName = context.getJobDetail().getKey().getName();
		QuartzModel model = quartzService.getJobDetailForJobName(jobName);
		if(null == model){
			model = new QuartzModel();
			model.setJOB_NAME(jobName);
			model.setTRIGGER_STATE("COMPLETED");
			quartzService.updateSelfDefined(model);
		}else{
			quartzService.updateSelfDefined(model);
		}
	}

}
