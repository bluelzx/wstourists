package com.yj.ws.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yj.ws.dao.SysMenuMapper;
import com.yj.ws.dao.SysUsersMapper;
import com.yj.ws.dao.UserAndRoleMapper;
import com.yj.ws.pojo.SysMenu;
import com.yj.ws.pojo.SysUsers;
import com.yj.ws.pojo.UserAndRole;
import com.yj.ws.service.SysMenuService;


@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService{
	@Autowired
	private SysUsersMapper usersMapper;
	@Autowired
	private SysMenuMapper sysMenuMapper;
	@Autowired
	private UserAndRoleMapper userAndRoleMapper;
	public List<SysMenu> showAllModel(int menuFlag,SysUsers user,String menuId) {
		int userId=user.getUserId();
		//得到角色
		List<UserAndRole> rolelist=userAndRoleMapper.getRoleIdUse(userId);
		String roles="";
		for (UserAndRole rlist : rolelist) {
			if(roles.length()==0){
				roles=rlist.getRoleId()+"";
			}else{
				roles=roles+","+rlist.getRoleId();
			}
		}
		//得到模块
		Map<String , Object> map = new HashMap<String,Object>();
		map.put("menuId", menuId);
		map.put("menuFlag", menuFlag);
		map.put("roles", roles);
		List<SysMenu> list=sysMenuMapper.selectMenuOwer(map);
		return list;
	}
	
	
}






