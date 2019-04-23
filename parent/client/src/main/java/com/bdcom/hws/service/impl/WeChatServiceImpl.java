package com.bdcom.hws.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.common.core.annotation.TargetDataSource;
import org.common.core.datasource.DatabaseType;
import org.common.model.client.WeChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bdcom.hws.mapper.WeChatMapper;
import com.bdcom.hws.service.WeChatService;

@Service
public class WeChatServiceImpl implements WeChatService {

	@Autowired
	WeChatMapper wm;
	
	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public JSONObject getAllChat() {
		JSONObject obj= new JSONObject();
		List<WeChat> list = wm.getAllChat();
		obj.put("WeChatList", list);
		return obj;
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public boolean addChat(WeChat model) {
		WeChat temp = wm.getMaxChatId();
		if(null == temp){
			model.setW_id(1);
		}else{
			model.setW_id(temp.getW_id()+1);
		}
		if(null == model.getW_time())model.setW_time(new Timestamp(new Date().getTime()));
		return wm.addChat(model)==0?false:true;
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public boolean delChatById(Integer w_id) {
		return wm.delChatById(w_id)==0?false:true;
	}

	@TargetDataSource(dataBaseType = DatabaseType.xlt)
	@Override
	public JSONObject selectChatByTime(Timestamp startTime, Timestamp endTime) {
		JSONObject obj= new JSONObject();
		List<WeChat> list = wm.selectChatByTime(startTime, endTime);
		obj.put("WeChatList", list);
		return obj;
	}

}
