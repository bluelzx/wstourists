package com.yj.ws.dao;

import java.util.List;

import com.yj.ws.pojo.Capital;

public interface CapitalMapper {
	public List<Capital> selectCapital(Capital capital);
}
