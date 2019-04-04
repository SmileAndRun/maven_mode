package com.server.restful.api.pojo.client;

import java.sql.Timestamp;

public class WeChat {
	
	private Integer w_id;
	private String w_content;
	private Timestamp w_time;
	private String w_nickName; 
	/*备用字段*/
	private String w_temp1;
	public Timestamp getW_time() {
		return w_time;
	}
	public void setW_time(Timestamp w_time) {
		this.w_time = w_time;
	}
	public Integer getW_id() {
		return w_id;
	}
	public void setW_id(Integer w_id) {
		this.w_id = w_id;
	}
	public String getW_content() {
		return w_content;
	}
	public void setW_content(String w_content) {
		this.w_content = w_content;
	}
	public String getW_temp1() {
		return w_temp1;
	}
	public void setW_temp1(String w_temp1) {
		this.w_temp1 = w_temp1;
	}
	public String getW_nickName() {
		return w_nickName;
	}
	public void setW_nickName(String w_nickName) {
		this.w_nickName = w_nickName;
	}
}
