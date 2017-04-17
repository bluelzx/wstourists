package com.yj.ws.service;

import java.util.Map;

import com.yj.ws.pojo.ShortMessageLogs;


public interface ShortMessageLogsService {
	public Integer messageCount(Map map);
	public void insertMessage(ShortMessageLogs message);
	public void updateMessage(Long messageId);
}
