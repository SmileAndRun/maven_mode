package org.common.model.client;

import java.util.List;

import org.common.model.Barrage;

public class Images {
	private Integer imageId;
	private String imageAddress;
	private String imageAlias;
	private List<Barrage> barList;
	
	
	public List<Barrage> getBarList() {
		return barList;
	}
	public void setBarList(List<Barrage> barList) {
		this.barList = barList;
	}
	public Integer getImageId() {
		return imageId;
	}
	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}
	public String getImageAddress() {
		return imageAddress;
	}
	public void setImageAddress(String imageAddress) {
		this.imageAddress = imageAddress;
	}
	public String getImageAlias() {
		return imageAlias;
	}
	public void setImageAlias(String imageAlias) {
		this.imageAlias = imageAlias;
	}
	public Images() {
		super();
	}
}
