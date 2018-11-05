package org.common.model;

public class QuartzNameModel {
	private String jobName;
	private String jobGroup;
	private String triggerName;
	private String triggerGroup;
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getTriggerName() {
		return triggerName;
	}
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	public String getTriggerGroup() {
		return triggerGroup;
	}
	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}
	public QuartzNameModel() {
		super();
	}
	public QuartzNameModel(String jobName, String jobGroup, String triggerName,
			String triggerGroup) {
		super();
		this.jobName = jobName;
		this.jobGroup = jobGroup;
		this.triggerName = triggerName;
		this.triggerGroup = triggerGroup;
	}
}
