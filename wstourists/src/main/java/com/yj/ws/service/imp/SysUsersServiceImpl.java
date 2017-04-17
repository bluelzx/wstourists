package com.yj.ws.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.yj.ws.dao.SysUsersMapper;
import com.yj.ws.pojo.SysUsers;
import com.yj.ws.service.SysUsersService;


@Service("sysUsersService")
public class SysUsersServiceImpl implements SysUsersService{
	private Log log = LogFactory.getLog(SysUsersServiceImpl.class);
	@Autowired
	private SysUsersMapper mapper;
	public List<SysUsers> loginUsers(String userName, String passWord) {     //系统登陆
		Map<String , Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		map.put("passWord", passWord);
		List<SysUsers> loginUsers=mapper.loginUsers(map);
		return loginUsers;
	}
	public Integer getUserCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.getUserCount(map);
	}
	public List<SysUsers> getQueryUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.getQueryUser(map);
	}

}



