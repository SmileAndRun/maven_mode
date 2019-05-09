package com.bdcom.hws.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.common.core.annotation.TargetDataSource;
import org.common.core.datasource.DatabaseType;
import org.common.model.client.Images;
import org.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.bdcom.hws.mapper.ImagesMapper;
import com.bdcom.hws.service.ImagesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ImagesServiceImpl implements ImagesService {

	@Autowired
	ImagesMapper im;
	
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public JSONObject getImages(int pageNum, int pageSize) {
		JSONObject obj = new JSONObject();
		PageHelper.startPage(pageNum, pageSize);
		List<Images> list = im.getAllImages();
		PageInfo<Images> pageInfo = new PageInfo<Images>(list);
		obj.put("initPageSize", PageUtils.getPaginationNum(pageInfo.getPages(),10));
		obj.put("pageInfo", pageInfo);
		return obj;
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

	/**
	 * 处理图片上传方法
	 * @param files
	 * @throws Exception
	 */
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public  void  upload(MultipartFile[] files) throws Exception{
		InputStream inputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		for(MultipartFile file:files){
			//当打成jar包时此路径为jar包的父级文件夹路径
			File  project= new File(System.getProperty("user.dir"));
			String path = project.getAbsolutePath()+"/upload/img/";
			if(!new File(path).exists()){
				new File(path).mkdirs();
			}
			String fileName = file.getOriginalFilename();
			
			Images images = im.getMaxImageId();
			if(null == images){
				images = new Images();
				images.setImageId(1);
			}else{
				images.setImageId(images.getImageId()+1);
			}
			
			inputStream = file.getInputStream();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
			File tempFile = new File(path,fileName);
			String[] temp = fileName.split("\\.");
			images.setImageAlias(temp[0]);
			tempFile.renameTo(new File(path,images.getImageId()+"." + temp[1]));
			images.setImageAddress(path+images.getImageId()+"."+temp[1]);
			
			OutputStream out = new FileOutputStream(tempFile); 
			bufferedOutputStream = new BufferedOutputStream(out);
			byte[] buff = new byte[1024];
			int length = 0;
			while( (length = bufferedInputStream.read(buff)) != -1){
				bufferedOutputStream.write(buff,0,length);
			}
			bufferedOutputStream.flush();
			
			addImage(images);
		}
		if(inputStream != null){
			inputStream.close();
		}
		if(bufferedOutputStream != null){
			bufferedOutputStream.close();
		}
		
		
	}
}
