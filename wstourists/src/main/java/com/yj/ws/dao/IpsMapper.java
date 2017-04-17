package com.yj.ws.dao;

import java.util.List;

import com.yj.ws.pojo.Ips;

public interface IpsMapper {
	public List<Ips> selectBytimes(Ips ips);
	public void insertIps(Ips ips);
}
