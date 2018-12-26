package com.bdcom.hws.service;


import org.common.model.client.Images;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;


public interface ImagesService {
	public JSONObject getImages(int pageNum, int pageSize);
	public boolean addImage(Images images);
	public boolean delImage(Integer imageId);
	public  void  upload(MultipartFile[] files) throws Exception;
}
