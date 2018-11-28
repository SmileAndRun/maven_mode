package org.common.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
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

	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {

	}

	/**任务执行后更新任务状态*/
	@Override
	public void jobWasExecuted(JobExecutionContext context,
			JobExecutionException jobException) {

	}

}
