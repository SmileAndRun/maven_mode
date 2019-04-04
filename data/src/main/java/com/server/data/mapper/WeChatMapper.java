package com.server.data.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.server.restful.api.pojo.client.WeChat;


public interface WeChatMapper {
	public List<WeChat> getAllChat();
	public Integer addChat(WeChat model);
	public Integer delChatById(Integer w_id);
	public List<WeChat> selectChatByTime(@RequestParam("startTime")Timestamp startTime,@RequestParam("endTime")Timestamp endTime);
	public WeChat getMaxChatId();
}
