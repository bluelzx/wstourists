package com.yj.ws.service;

import java.util.List;
import java.util.Map;

import com.yj.ws.pojo.FansUser;
import com.yj.ws.pojo.User;

public interface FansUserService {
	public void insertFuns(FansUser fansUser);
	public List<FansUser> getFans(Map map);
	public void updateFans(Map map);
	public List<FansUser> insertFansAndGet(FansUser fansUser,User user,int vip);
}
