package com.yj.ws.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yj.ws.dao.CityMapper;
import com.yj.ws.pojo.City;
import com.yj.ws.service.CityService;

@Service("cityService")
public class CityServiceImpl implements CityService{
	@Autowired 
	private CityMapper cityMapper;
	public List<Map> selectProve(){
		return cityMapper.selectProve();
	}

	public List<City> selectCityByProce(int proceId) {
		return cityMapper.selectCityByProce(proceId);
	}
	public Integer getCityId(String city){
		return cityMapper.getCityId(city);
	}
}
