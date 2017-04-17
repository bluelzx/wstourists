package com.yj.ws.dao;

import java.util.List;
import java.util.Map;

import com.yj.ws.pojo.SignUser;

/**
 * 用户签到
 * @author yj
 *
 */
public interface SignUserMapper {
	public void inserSign(SignUser signUser);           //签到
	public List<SignUser> selectSign(Map map);			//得到签到
	public void updateSign(Long userId);        //修改签到标示
	public Integer selectSignCount(SignUser signUser);	//得到签到总数
	public List<SignUser> selectSignTotal(SignUser signUser);
}
