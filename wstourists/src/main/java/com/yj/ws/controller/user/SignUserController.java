package com.yj.ws.controller.user;

import java.math.BigDecimal;
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

import sun.awt.image.PixelConverter.Bgrx;

import com.yj.ws.controller.back.user.BaseController;
import com.yj.ws.pojo.Capital;
import com.yj.ws.pojo.SignUser;
import com.yj.ws.pojo.User;
import com.yj.ws.pojo.WithDrawRecord;
import com.yj.ws.service.SignUserService;
import com.yj.ws.service.UserService;
import com.yj.ws.service.WithDrawRecordService;
import com.yj.ws.util.code.PageUtil;
import com.yj.ws.util.common.DateUtil;
import com.yj.ws.util.common.ResultCode;
import com.yj.ws.util.common.Validator;
import com.yj.ws.util.json.ResultObj;

/**
 * 签到
 * @author Administrator
 *
 */
@SuppressWarnings("all")
@RequestMapping("app/sign")
@Controller
public class SignUserController extends BaseController{
	protected final Logger log = Logger.getLogger(SignUserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private SignUserService signUserService;
	@Autowired
	private WithDrawRecordService withDrawRecordService;
	
	/**
	 * 用户签到
	 * @param phone
	 * @param userId
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/signUser.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String signUser(String phone,Long userId,HttpServletResponse response,HttpServletRequest request){
		if(phone==null || phone.length()==0){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PARAM_NULL, "login.api###RequestParam is null");
			return robj.toString();
		}
		if(!Validator.isMobileNum(phone)){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PHONE_ERROR, "homepageindex.api###Phone number is incorrect");
			return robj.toString();
		}
		try {
			Map map=new HashMap();
			map.put("phone", phone);
			map.put("userId", userId);
			User user=userService.selectUser(map);                       //得到用户信息
			if(user==null){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NO_PHONEID, "no id and phone");
				return robj.toString();
			}
			SignUser signUser=new SignUser();
			signUser.setUserId(userId);
			String strNew=DateUtil.dateFormatToString(new Date(), "yyyy-MM-dd");
			signUser.setFback1(strNew);
			int count=signUserService.selectSignCount(signUser);
			Map pmap=new HashMap();
			if(count>0){
				SignUser signUsers=new SignUser();
				signUsers.setSignTotal(1);
				signUsers.setUserId(userId);
				//int signCount= signUserService.selectSignCount(signUser);
				Double money=0.0;
				int signCount= signUserService.selectSignCount(signUsers);
				Date startTime=DateUtil.stringToDate(strNew, "yyyy-MM-dd");
				/*map.put("startTime", startTime);
				List<WithDrawRecord> rlist=withDrawRecordService.getRecord(map);
				WithDrawRecord record=rlist.get(0);
				BigDecimal recordMoney=record.getRecordMomey();*/
				Double m=withDrawRecordService.getlastMoney(userId);
				if(m==null){
					m=0.0;
				}
				pmap.put("signCount", signCount);
				pmap.put("recordMoney",new BigDecimal(m));
				ResultObj robj = new ResultObj(ResultCode.RESULT_NO_SING, "no sing",pmap);
				return robj.toString();
			}
			signUser.setSignTime(DateUtil.timeToTime(new Date(), "yyyy-MM-dd HH:mm:ss"));
			signUser.setSignTotal(1);
			signUserService.inserSign(signUser,user);
			pmap.put("result", 1);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",pmap);
			return robj.toString();
		}catch(Exception e){
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "login.api###exception:",e.getMessage());
			return robj.toString();
		}
	}
	/**
	 * 得到签到
	 * @param phone
	 * @param userId
	 * @param limitTimes
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getsignUser.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String getsignUser(String phone,Long userId,Integer limitTimes,HttpServletResponse response,HttpServletRequest request){
		if(phone==null || phone.length()==0){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PARAM_NULL, "login.api###RequestParam is null");
			return robj.toString();
		}
		if(!Validator.isMobileNum(phone)){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PHONE_ERROR, "homepageindex.api###Phone number is incorrect");
			return robj.toString();
		}
		try {
			Map map=new HashMap();
			map.put("phone", phone);
			map.put("userId", userId);
			int user=userService.pdId(map);                       //得到用户信息
			if(user==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NO_PHONEID, "no id and phone");
				return robj.toString();
			}
			map.put("limitTimes", limitTimes);
			List<SignUser> listSign= signUserService.selectSign(map);
			Map pmap=new HashMap();
			pmap.put("listSign", listSign);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",pmap);
			return robj.toString();
		}catch(Exception e){
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "login.api###exception:",e.getMessage());
			return robj.toString();
		}
	}
	/**
	 * 得到签到
	 * @param phone
	 * @param userId
	 * @param limitTimes
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getsignNumber.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String getsignNumber(String phone,Long userId,HttpServletResponse response,HttpServletRequest request){
		if(phone==null || phone.length()==0){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PARAM_NULL, "login.api###RequestParam is null");
			return robj.toString();
		}
		if(!Validator.isMobileNum(phone)){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PHONE_ERROR, "homepageindex.api###Phone number is incorrect");
			return robj.toString();
		}
		try {
			Map map=new HashMap();
			map.put("phone", phone);
			map.put("userId", userId);
			int userCount=userService.pdId(map);                       //得到用户信息
			if(userCount==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NO_PHONEID, "no id and phone");
				return robj.toString();
			}
			SignUser signUser=new SignUser();
			signUser.setSignTotal(1);
			signUser.setUserId(userId);
			int signCount= signUserService.selectSignCount(signUser);
			Map pmap=new HashMap();
			pmap.put("signCount", signCount);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",pmap);
			return robj.toString();
		}catch(Exception e){
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "login.api###exception:",e.getMessage());
			return robj.toString();
		}
	}
}









