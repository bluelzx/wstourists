package com.yj.ws.dao;

import java.util.Map;

import com.yj.ws.pojo.ShortMessageLogs;

public interface ShortMessageLogsMapper {
	public Integer messageCount(Map map);                        //得到当天短信数目
	public void insertMessage(ShortMessageLogs message);		//插入短信信息
	public void updateMessage(Long messageId);
}
