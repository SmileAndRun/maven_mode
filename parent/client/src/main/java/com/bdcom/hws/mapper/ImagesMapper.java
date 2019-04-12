package com.bdcom.hws.mapper;

import java.util.List;

import com.server.restful.api.pojo.client.Images;



public interface ImagesMapper {
	public List<Images> getAllImages();
	public int addImage(Images images);
	public Images getMaxImageId();
	public int delImage(Integer imageId);
}
