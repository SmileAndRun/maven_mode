package com.bdcom.hws.service.impl;

import java.util.List;

import org.common.core.annotation.TargetDataSource;
import org.common.core.datasource.DatabaseType;
import org.common.model.client.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdcom.hws.mapper.ImagesMapper;
import com.bdcom.hws.service.ImagesService;
import com.github.pagehelper.PageHelper;

@Service
public class ImagesServiceImpl implements ImagesService {

	@Autowired
	ImagesMapper im;
	
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<Images> getImages(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Images> list = im.getAllImages();
		return list;
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public boolean addImage(Images images) {
		
		return im.addImage(images)==0?false:true;
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public boolean delImage(Integer imageId) {
		return im.delImage(imageId)==0?false:true;
	}

}
