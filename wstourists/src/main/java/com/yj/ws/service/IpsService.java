package com.yj.ws.service;

import java.util.List;

import com.yj.ws.pojo.Ips;

public interface IpsService {
	public List<Ips> selectBytimes(Ips ips);
	public void insertIps(Ips ips);
}
