package com.yj.ws.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yj.ws.dao.WithDrawRecordMapper;
import com.yj.ws.pojo.WithDrawRecord;
import com.yj.ws.service.WithDrawRecordService;

@Service("withDrawRecordService")
public class WithDrawRecordServiceImpl  implements WithDrawRecordService{
	@Autowired
	private WithDrawRecordMapper withDrawRecordMapper;
	
	public Long insertRecord( WithDrawRecord record){
		return withDrawRecordMapper.insertRecord(record);
	}
	public Map<String, Object>  callAccount(Map<String, Object> map){
		//return null;
		return withDrawRecordMapper.callAccount(map);
	}
	public List<WithDrawRecord> getRecord(Map<String, Object> map){
		return withDrawRecordMapper.getRecord(map);
	}
	public Integer getRecordCount(Map<String, Object> map){
		return withDrawRecordMapper.getRecordCount(map);
	}
	public Double getlastMoney(Long userId){
		return withDrawRecordMapper.getlastMoney(userId);
	}
	public List<WithDrawRecord> getRecordByClass(WithDrawRecord record){
		return withDrawRecordMapper.getRecordByClass(record);
	}
	public void updateRecord(WithDrawRecord record){
		withDrawRecordMapper.updateRecord(record);
	}
	public List<WithDrawRecord> hgetRecord(Map<String, Object> map){
		return withDrawRecordMapper.hgetRecord(map);
	}
	//得到资金总额
	public Double hgetMoney(Map<String, Object> map){
		return withDrawRecordMapper.hgetMoney(map);
	}
	public Integer hgetCount(Map<String, Object> map){
		return withDrawRecordMapper.hgetCount(map);
	}
	public void updateState(Map<String, Object> map){
		withDrawRecordMapper.updateState(map);
	}
}
