package com.yj.ws.service.imp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yj.ws.dao.ShortMessageLogsMapper;
import com.yj.ws.pojo.ShortMessageLogs;
import com.yj.ws.service.ShortMessageLogsService;

@Service("shortMessageLogsService")
public class ShortMessageLogsServiceImpl implements ShortMessageLogsService{
	@Autowired
	private ShortMessageLogsMapper shortMessageLogsMapper;
	public Integer messageCount(Map map){
		return shortMessageLogsMapper.messageCount(map);
	}
	public void insertMessage(ShortMessageLogs message){
		shortMessageLogsMapper.insertMessage(message);
	}
	public void updateMessage(Long messageId){
		shortMessageLogsMapper.updateMessage(messageId);
	}
}
