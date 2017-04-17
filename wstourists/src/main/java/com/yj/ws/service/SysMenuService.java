package com.yj.ws.service;

import java.util.List;

import com.yj.ws.pojo.SysMenu;
import com.yj.ws.pojo.SysUsers;



public interface SysMenuService {
	public List<SysMenu> showAllModel(int menuFlag,SysUsers user,String menuId);
}
