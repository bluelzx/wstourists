package com.yj.ws.service;

import java.util.List;
import java.util.Map;

import com.yj.ws.pojo.User;

public interface UserService {
	public User selectUser(Map pmap);
	public Long insertUser(User user);
	public Integer getfriends(Long userId);  
	public Integer pdId(Map map);
	public void updateUserMoney(User user); 
	public List<Map<String, Object>> getOnefriends(Map pmap);
	public List<Map<String, Object>> getTwofriends(Map pmap);
	public Integer getTwoFriendCount(Long userId);
	public Integer selectUserCount(Map pmap);
	public List<User> selectUserBack(Map pmap);
}
