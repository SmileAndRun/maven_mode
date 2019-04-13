package com.bdcom.hws.service;


import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.server.restful.api.pojo.client.Images;


public interface ImagesService {
	public JSONObject getImages(int pageNum, int pageSize);
	public boolean addImage(Images images);
	public boolean delImage(Integer imageId);
	public  void  upload(MultipartFile[] files) throws Exception;
}
