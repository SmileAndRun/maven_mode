package com.bdcom.hws.service;

import java.util.List;

import org.common.model.client.Images;


public interface ImagesService {
	public List<Images> getImages(int pageNum, int pageSize);
	public boolean addImage(Images images);
	public boolean delImage(Integer imageId);
}
