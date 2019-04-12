package com.bdcom.hws.service;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.server.restful.api.pojo.client.WeChat;

public interface WeChatService {
	public JSONObject getAllChat();
	public boolean addChat(WeChat model);
	public boolean delChatById(Integer w_id);
	public JSONObject selectChatByTime(@Param("startTime")Timestamp startTime,@Param("endTime")Timestamp endTime);
}
