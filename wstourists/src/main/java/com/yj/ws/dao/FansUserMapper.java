package com.yj.ws.dao;

import java.util.List;
import java.util.Map;

import com.yj.ws.pojo.FansUser;

public interface FansUserMapper {
	public void insertFuns(FansUser fansUser);
	public List<FansUser> getFans(Map map);
	public void updateFans(Map map);
}
