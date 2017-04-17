package com.yj.ws.dao;

import java.util.List;
import java.util.Map;

import com.yj.ws.pojo.WithDrawRecord;

/**
 * 资金记录
 * @author yj
 *
 */
public interface WithDrawRecordMapper {
	public Long insertRecord( WithDrawRecord record);
	public Map<String, Object> callAccount(Map<String, Object> map);			//提现
	//得到资金记录
	public List<WithDrawRecord> getRecord(Map<String, Object> map);
	//得到资金记录总数
	public Integer getRecordCount(Map<String, Object> map);
	public Double getlastMoney(Long userId);
	public List<WithDrawRecord> getRecordByClass(WithDrawRecord record);
	//修改记录
	public void updateRecord(WithDrawRecord record);
	//后台得到资金记录
	public List<WithDrawRecord> hgetRecord(Map<String, Object> map);
	//得到资金总额
	public Double hgetMoney(Map<String, Object> map);
	public Integer hgetCount(Map<String, Object> map);
	//修改自己状态
	public void updateState(Map<String, Object> map);
}
