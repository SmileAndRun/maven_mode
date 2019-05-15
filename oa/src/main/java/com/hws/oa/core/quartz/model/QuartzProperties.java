package com.hws.oa.core.quartz.model;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QuartzProperties {
	@Value(value = "${org.quartz.dataSource.quartzDataSource.Url}")
	private String quartzUrl;
	@Value(value = "${org.quartz.dataSource.quartzDataSource.username}")
	private String quartzUserName;
	@Value(value = "${org.quartz.dataSource.quartzDataSource.password}")
	private String quartzUserPassword;
	@Value(value = "${org.quartz.dataSource.quartzDataSource.driverClassName}")
	private String quartzDriverName;
	@Value(value = "${org.quartz.dataSource.quartzDataSource.connectionProvider.class}")
	private String connectionProvider;
	@Value(value = "${org.quartz.dataSource.quartzDataSource.validationQuery}")
	private String validationQuery;
	
	@Value(value = "${org.quartz.scheduler.instanceName}")
	private String instanceName;
	@Value(value = "${org.quartz.jobStore.class}")
	private String jobStoreClass;
	@Value(value = "${org.quartz.jobStore.driverDelegateClass}")
	private String driverDelegateClass;
	@Value(value = "${org.quartz.jobStore.tablePrefix}")
	private String tablePrefix;
	@Value(value = "${org.quartz.jobStore.dataSource}")
	private String dataSource;
	@Value(value = "${org.quartz.jobStore.isClustered}")
	private String isClustered;
	@Value(value = "${org.quartz.threadPool.threadCount}")
	private String threadCount;
	
	
	
	
	public String getInstanceName() {
		return instanceName;
	}
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}
	public String getJobStoreClass() {
		return jobStoreClass;
	}
	public void setJobStoreClass(String jobStoreClass) {
		this.jobStoreClass = jobStoreClass;
	}
	public String getDriverDelegateClass() {
		return driverDelegateClass;
	}
	public void setDriverDelegateClass(String driverDelegateClass) {
		this.driverDelegateClass = driverDelegateClass;
	}
	public String getTablePrefix() {
		return tablePrefix;
	}
	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getIsClustered() {
		return isClustered;
	}
	public void setIsClustered(String isClustered) {
		this.isClustered = isClustered;
	}
	public String getThreadCount() {
		return threadCount;
	}
	public void setThreadCount(String threadCount) {
		this.threadCount = threadCount;
	}
	public String getQuartzUrl() {
		return quartzUrl;
	}
	public void setQuartzUrl(String quartzUrl) {
		this.quartzUrl = quartzUrl;
	}
	public String getQuartzUserName() {
		return quartzUserName;
	}
	public void setQuartzUserName(String quartzUserName) {
		this.quartzUserName = quartzUserName;
	}
	public String getQuartzUserPassword() {
		return quartzUserPassword;
	}
	public void setQuartzUserPassword(String quartzUserPassword) {
		this.quartzUserPassword = quartzUserPassword;
	}
	public String getQuartzDriverName() {
		return quartzDriverName;
	}
	public void setQuartzDriverName(String quartzDriverName) {
		this.quartzDriverName = quartzDriverName;
	}
	
	public Properties toProperties(){
		Properties properties = new Properties();
		properties.put("org.quartz.dataSource.quartzDataSource.Url", quartzUrl);
		properties.put("org.quartz.dataSource.quartzDataSource.username", quartzUserName);
		properties.put("org.quartz.dataSource.quartzDataSource.password", quartzUserPassword);
		properties.put("org.quartz.dataSource.quartzDataSource.driverClassName", quartzDriverName);
		properties.put("org.quartz.dataSource.quartzDataSource.connectionProvider.class", connectionProvider);
		properties.put("org.quartz.dataSource.quartzDataSource.validationQuery", validationQuery);
		properties.put("org.quartz.scheduler.instanceName", instanceName);
		properties.put("org.quartz.jobStore.class", jobStoreClass);
		properties.put("org.quartz.jobStore.driverDelegateClass", driverDelegateClass);
		properties.put("org.quartz.jobStore.tablePrefix", tablePrefix);
		properties.put("org.quartz.jobStore.dataSource", dataSource);
		properties.put("org.quartz.jobStore.isClustered", isClustered);
		properties.put("org.quartz.threadPool.threadCount", threadCount);
		return properties;
	}
}
