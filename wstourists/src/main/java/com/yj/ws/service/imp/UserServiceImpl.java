package com.yj.ws.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yj.ws.dao.UserMapper;
import com.yj.ws.pojo.User;
import com.yj.ws.service.UserService;


@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	public User selectUser(Map pmap){
		return userMapper.selectUser(pmap);
	}
	public Long insertUser(User user){
		return userMapper.insertUser(user);
	}
	public Integer getfriends(Long userId){
		return userMapper.getfriends(userId);
	}
	public Integer pdId(Map map){
		return userMapper.pdId(map);
	}
	public void updateUserMoney(User user){
		userMapper.updateUserMoney(user);
	}
	public List<Map<String, Object>> getOnefriends(Map map){
		return userMapper.getOnefriends(map);
	}
	public List<Map<String, Object>> getTwofriends(Map map){
		return userMapper.getTwofriends(map);
	}
	public Integer getTwoFriendCount(Long userId){
		return userMapper.getTwoFriendCount(userId);
	}
	public Integer selectUserCount(Map pmap){
		return userMapper.selectUserCount(pmap);
	}
	public List<User> selectUserBack(Map pmap){
		return userMapper.selectUserBack(pmap);
	}
}






