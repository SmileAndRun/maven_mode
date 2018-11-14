package org.common.model;

import java.sql.Timestamp;

public class Barrage {
	private String contentId;//防止超出长度
	private String content;
	private Timestamp time;
	private int imageId;
	
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
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
	public Barrage(String contentId, String content, Timestamp time) {
		super();
		this.contentId = contentId;
		this.content = content;
		this.time = time;
	}
	@Override
	public String toString() {
		return "Barrage [contentId=" + contentId + ", content=" + content
				+ ", time=" + time + "]";
	}
	

}
