package com.server.data.mapper;

import java.util.List;

import com.server.restful.api.pojo.client.Images;



public interface ImagesMapper {
	public List<Images> getAllImages();
	public Integer addImage(Images images);
	public Images getMaxImageId();
	public Integer delImage(Integer imageId);
}
