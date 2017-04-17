package com.yj.ws.service;

import java.util.List;
import java.util.Map;

import com.yj.ws.pojo.SysUsers;



public interface SysUsersService {
	public List<SysUsers> loginUsers(String userName,String password);
	//得到总数
	public Integer getUserCount(Map<String, Object> map);
	//得到用户数 按照分页
	public List<SysUsers> getQueryUser(Map<String, Object> map);
}
