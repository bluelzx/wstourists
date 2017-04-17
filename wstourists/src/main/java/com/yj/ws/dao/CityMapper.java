package com.yj.ws.dao;

import java.util.List;
import java.util.Map;

import com.yj.ws.pojo.City;

/**
 * 城市相關信息
 * @author Administrator
 *
 */
public interface CityMapper {
	public List<Map> selectProve();
	public List<City> selectCityByProce(int proceId);
	public Integer getCityId(String city);
}
