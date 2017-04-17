package com.yj.ws.dao;

import java.util.List;
import java.util.Map;

import com.yj.ws.pojo.User;

/**
 * 用户管理
 * @author yj
 *
 */
@SuppressWarnings("all")
public interface UserMapper {
	public User selectUser(Map pmap);			//查询用户
	public List<User> selectUserBack(Map pmap);
	public Integer selectUserCount(Map pmap);	
	public Long insertUser(User user);				//增加用户
	public Integer getfriends(Long userId);        //得到资金粉丝数目
	public Integer getTwoFriendCount(Long userId);
	public Integer pdId(Map map);				//判断是否真实用户
	public void updateUserMoney(User user);        //修改金额
	public List<Map<String, Object>> getOnefriends(Map pmap);
	public List<Map<String, Object>> getTwofriends(Map pmap);
}

