package com.yj.ws.dao;

import java.util.List;
import java.util.Map;

import com.yj.ws.pojo.UserAndRole;



/**
 * author yanjun
 * 创建日期 
 * 主要作用：角色管理
 */
public interface UserAndRoleMapper {
	//根据用户得到角色ID
	public List<Map<String, Object>> getRoleByUser(int userId);
	//得到有用的角色
	public List<UserAndRole> getRoleIdUse(int userId);
}






