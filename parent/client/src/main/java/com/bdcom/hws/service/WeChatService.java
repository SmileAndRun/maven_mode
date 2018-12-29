package com.bdcom.hws.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.common.model.client.WeChat;

import com.alibaba.fastjson.JSONObject;

public interface WeChatService {
	public JSONObject getAllChat();
	public boolean addChat(WeChat model);
	public boolean delChatById(Integer w_id);
	public JSONObject selectChatByTime(@Param("startTime")Timestamp startTime,@Param("endTime")Timestamp endTime);
}
