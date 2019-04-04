package com.server.restful.api.pojo;

import java.sql.Timestamp;

public class Barrage {
	private Integer contentId;//防止超出长度
	private String content;
	private Timestamp time;
	private int imageId;
	
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Barrage() {
		super();
	}
	
	public Integer getContentId() {
		return contentId;
	}
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	@Override
	public String toString() {
		return "Barrage [contentId=" + contentId + ", content=" + content
				+ ", time=" + time + "]";
	}
	

}
