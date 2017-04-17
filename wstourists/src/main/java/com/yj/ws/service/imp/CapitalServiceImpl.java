package com.yj.ws.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yj.ws.dao.CapitalMapper;
import com.yj.ws.pojo.Capital;
import com.yj.ws.service.CapitalService;

@Service("capitalService")
public class CapitalServiceImpl implements CapitalService{
	@Autowired
	private CapitalMapper capitalMapper;
	public List<Capital> selectCapital(Capital capital){
		return capitalMapper.selectCapital(capital);
	}
}
