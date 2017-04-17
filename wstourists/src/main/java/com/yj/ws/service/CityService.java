package com.yj.ws.service;

import java.util.List;
import java.util.Map;

import com.yj.ws.pojo.City;


public interface CityService {
	public List<Map> selectProve();
	public List<City> selectCityByProce(int proceId);
	public Integer getCityId(String city);
}
