package com.yj.ws.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yj.ws.dao.IpsMapper;
import com.yj.ws.pojo.Ips;
import com.yj.ws.service.IpsService;

@Service("ipsService")
public class IpsServiceImpl implements IpsService{
	@Autowired
	private IpsMapper ipsMapper;
	public List<Ips> selectBytimes(Ips ips){
		return ipsMapper.selectBytimes(ips);
	}
	public void insertIps(Ips ips){
		ipsMapper.insertIps(ips);
	}
}
