package com.yj.ws.service;

import java.util.List;
import java.util.Map;

import com.yj.ws.pojo.WithDrawRecord;

public interface WithDrawRecordService {
	public Long insertRecord( WithDrawRecord record);
	public Map<String, Object>  callAccount(Map<String, Object> map);
	public List<WithDrawRecord> getRecord(Map<String, Object> map);
	public Integer getRecordCount(Map<String, Object> map);
	public Double getlastMoney(Long userId);
	public List<WithDrawRecord> getRecordByClass(WithDrawRecord record);
	public void updateRecord(WithDrawRecord record);
	public List<WithDrawRecord> hgetRecord(Map<String, Object> map);
	//得到资金总额
	public Double hgetMoney(Map<String, Object> map);
	public Integer hgetCount(Map<String, Object> map);
	public void updateState(Map<String, Object> map);
}
