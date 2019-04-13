package com.bdcom.server.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.common.core.annotation.TargetDataSource;
import org.common.core.datasource.DatabaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bdcom.server.mapper.BarrageMapper;
import com.bdcom.server.service.BarrageService;
import com.server.restful.api.pojo.Barrage;


@Service
public class BarrageServiceImpl implements BarrageService{

	@Autowired
	private BarrageMapper barrMapper;
	
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<Barrage> getAllBar() {
		
		return barrMapper.getAllBar();
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<Barrage> getListBarByTime(Date time) {
		// TODO Auto-generated method stub
		return null;
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public Barrage getBarById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public List<Barrage> getListBarByLike(String content) {
		// TODO Auto-generated method stub
		return null;
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public int getBarrageCount(Timestamp time) {
		return barrMapper.getBarrageCount(time);
	}
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public JSONObject getBarrageByImagesId(Integer imagesId){
		JSONObject obj = new JSONObject();
		List<Barrage> list = barrMapper.getBarByImagesId(imagesId);
		obj.put("imgBarList", list);
		obj.put("imgBarFlag", true);
		if(null == list) obj.put("imgBarFlag", false);
		return obj;
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public boolean addBarrage(Barrage barrage) {
		Barrage temp =  barrMapper.getMaxContentId();
		if(null == temp){
			barrage.setContentId(1);
		}else{
			barrage.setContentId(temp.getContentId() + 1);
		}
		if(null == barrage.getTime()){
			Date date = new Date();
			barrage.setTime(new Timestamp(date.getTime()));
		}
		
		return barrMapper.addBarrage(barrage)==0?false:true;
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public boolean delBarrageBycontentId(Integer contentId) {
		return barrMapper.delBarrageBycontentId(contentId)==0?false:true;
	} 
	

}
