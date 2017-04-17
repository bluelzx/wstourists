package com.yj.ws.dao;

import java.util.List;
import java.util.Map;

import com.yj.ws.pojo.SysMenu;



/**
 * author yanjun
 * 创建日期 �
 * 主要作用：用户菜单管理
 */
public interface SysMenuMapper {
	//获取到菜单
	public List<SysMenu> selectMenuOwer(Map<String, Object> map);
}






