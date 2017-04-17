package com.yj.ws.controller.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yj.ws.pojo.City;
import com.yj.ws.pojo.User;
import com.yj.ws.service.CityService;
import com.yj.ws.service.UserService;
import com.yj.ws.util.common.DateUtil;
import com.yj.ws.util.common.ResultCode;
import com.yj.ws.util.common.Validator;
import com.yj.ws.util.json.ResultObj;

@SuppressWarnings("all")
@Controller
@RequestMapping("app/city")
public class CityController {
	private static final Logger log = Logger.getLogger(CityController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private CityService cityService;
	/**
	 * 得到省
	 * @param phone
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getprovice.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String getprovice(String phone,Long userId,HttpServletResponse response,HttpServletRequest request){
		if(phone==null || phone.length()==0){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PARAM_NULL, "homepageindex.api###RequestParam is null");
			return robj.toString();
		}
		if(!Validator.isMobileNum(phone)){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PHONE_ERROR, "homepageindex.api###Phone number is incorrect");
			return robj.toString();
		}
		try {
			/*
			 * 得到用户信息,断是否为vip用户
			 */
			Map map=new HashMap();
			int flag=0;                                 //vip是否到期标记
			map.put("phone", phone);
			map.put("userId", userId);
			User user=userService.selectUser(map);
			if(user!=null){
				Date startTime=user.getVipStart();
				Date endTime=user.getVipEnd();
				if(startTime!=null & endTime!=null){
					Date dt=new Date();
					Date newdt=DateUtil.stringToDate(DateUtil.dateToString(dt, "yyyy-MM-dd"),"yyyy-MM-dd");
					if(startTime.getTime()<=newdt.getTime() && endTime.getTime()>=newdt.getTime()){
						flag=1;
					}
				}
			}else{
				ResultObj robj = new ResultObj(ResultCode.RESULT_PHONE_NOT_REGISTER, "not find user");
				return robj.toString();
			}
			if(flag==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NO_VIP, "no vip");
				return robj.toString();
			}
			List<Map> priceList=cityService.selectProve();
			map.put("priceList", priceList);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",map);
			return robj.toString();
		}catch (Exception e) {
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:",e.getMessage());
			return robj.toString();
		}
	}
	/**
	 * 得到城市
	 * @param phone
	 * @param cityId
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getcity.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String getcity(String phone,Integer provinceId,HttpServletResponse response,HttpServletRequest request){
		if(phone==null || phone.length()==0){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PARAM_NULL, "homepageindex.api###RequestParam is null");
			return robj.toString();
		}
		if(!Validator.isMobileNum(phone)){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PHONE_ERROR, "homepageindex.api###Phone number is incorrect");
			return robj.toString();
		}
		if(provinceId==null){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PARAM_NULL, "homepageindex.api###city is null");
			return robj.toString();
		}
		try {
			Map<String, List<City>> map=new HashMap<String, List<City>>();
			List<City> cityList=cityService.selectCityByProce(provinceId);
			map.put("cityList", cityList);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",map);
			return robj.toString();
		}catch (Exception e) {
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:",e.getMessage());
			return robj.toString();
		}
	}
}










