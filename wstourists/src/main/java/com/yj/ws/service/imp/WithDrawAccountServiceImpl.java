package com.yj.ws.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yj.ws.dao.WithDrawAccountMapper;
import com.yj.ws.service.WithDrawAccountService;

@Service("withDrawAccountService")
public class WithDrawAccountServiceImpl implements WithDrawAccountService{
	@Autowired
	private WithDrawAccountMapper withDrawAccountMapper;
	public List<Map<String, Object>> selectLast(Long userId){
		return withDrawAccountMapper.selectLast(userId);
	}
}
