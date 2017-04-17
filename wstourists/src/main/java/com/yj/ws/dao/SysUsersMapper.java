package com.yj.ws.dao;

import java.util.List;
import java.util.Map;

import com.yj.ws.pojo.SysUsers;




/**
 * author yanjun
 * 创建日期 �
 * 主要作用：用户管理
 */
public interface SysUsersMapper {
	//用户登陆
	public List<SysUsers> loginUsers(Map<String, Object> map);
	//得到总数
	public Integer getUserCount(Map<String, Object> map);
	//得到用户数 按照分页
	public List<SysUsers> getQueryUser(Map<String, Object> map);
}






