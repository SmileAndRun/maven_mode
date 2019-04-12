package com.bdcom.hws.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.server.restful.api.pojo.client.WeChat;

public interface WeChatMapper {
	public List<WeChat> getAllChat();
	public int addChat(WeChat model);
	public int delChatById(Integer w_id);
	public List<WeChat> selectChatByTime(@Param("startTime")Timestamp startTime,@Param("endTime")Timestamp endTime);
	public WeChat getMaxChatId();
}
