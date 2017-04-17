package com.yj.ws.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.yj.ws.pojo.Capital;
import com.yj.ws.pojo.SignUser;
import com.yj.ws.pojo.User;

public interface SignUserService {
	public void inserSign(SignUser signUser,User user);
	public List<SignUser> selectSign(Map map);
	public Integer selectSignCount(SignUser signUser);
	public List<SignUser> selectSignTotal(SignUser signUser);
	public BigDecimal getMoney(Capital capital);
}
