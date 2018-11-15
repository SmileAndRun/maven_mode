package com.bdcom.hws.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:quartz.properties")
public class QuartzProperties {
	@Value(value = "${org.quartz.dataSource.quartzDataSource.Url}")
	private String quartzUrl;
	@Value(value = "${org.quartz.dataSource.quartzDataSource.username}")
	private String quartzUserName;
	@Value(value = "${org.quartz.dataSource.quartzDataSource.password}")
	private String quartzUserPassword;
	@Value(value = "${org.quartz.dataSource.quartzDataSource.driverClassName}")
	private String quartzDriverName;
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
	
}
