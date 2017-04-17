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

import com.yj.ws.controller.back.user.BaseController;
import com.yj.ws.pojo.FansUser;
import com.yj.ws.pojo.User;
import com.yj.ws.service.FansUserService;
import com.yj.ws.service.UserService;
import com.yj.ws.util.code.PageUtil;
import com.yj.ws.util.common.DateUtil;
import com.yj.ws.util.common.ResultCode;
import com.yj.ws.util.common.Validator;
import com.yj.ws.util.json.ResultObj;

@RequestMapping("app/fansUser")
@Controller
public class FansUserController extends BaseController{
	private static final Logger log = Logger.getLogger(FansUserController.class);
	@Autowired
	private FansUserService fansUserService;
	@Autowired
	private UserService userService;
	
	/**
	 * 搜索粉丝，进行添加
	 * @param phone
	 * @param provinceId
	 * @param cityId
	 * @param sex
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/seacher.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String seacher(String phone,Long userId,Integer provinceId,Integer cityId,Integer sex,HttpServletResponse response,HttpServletRequest request){
		if(phone==null || phone.length()==0){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PARAM_NULL, "login.api###RequestParam is null");
			return robj.toString();
		}
		if(!Validator.isMobileNum(phone)){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PHONE_ERROR, "homepageindex.api###Phone number is incorrect");
			return robj.toString();
		}
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("phone", phone);
			map.put("userId", userId);
			User user=userService.selectUser(map);
			if(user==null){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NO_PHONEID, "no id and phone");
				return robj.toString();
			}
			int vip=0;
			int fansNumber=user.getFansNumber();
			Date vipStart=user.getVipStart();
			Date vipEnd =user.getVipEnd();
			Date dt=DateUtil.timeToTime(new Date(), "yyyy-MM-dd");
			if(vipStart!=null && vipEnd!=null){
				if(dt.getTime()>=vipStart.getTime() && dt.getTime()<=vipEnd.getTime()){
					vip=1;
				}
			}
			if(fansNumber==0 && vip==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NO_FANSNUMBER, "no fansNumber");
				return robj.toString();
			}
			if(fansNumber>=0){
				fansNumber=50;         //每次只能增加50个
			}
			//先往粉丝表中插入50条信息
			FansUser fansUser=new FansUser();
			fansUser.setFansTime(dt);
			fansUser.setFansUserId(userId);
			fansUser.setFansNumber(fansNumber);
			/*fansUserService.insertFuns(fansUser);    //把粉丝加入到粉丝表
			//得到刚刚增加的粉丝
			map.put("fansUserId", userId);
			map.put("isdelete", 2);
			List<FansUser> fansList=fansUserService.getFans(map);
			fansUserService.updateFans(map);
			//user.setFansNumber(user.getFansNumber()-50);
			int fansUsers=user.getFansNumber()-fansList.size();
			if(fansUsers<0){
				fansUsers=0;
			}
			Long userid=user.getUserId();
			User users=new User();
			users.setFansNumber(fansUsers);
			users.setUserId(userid);
			userService.updateUserMoney(users);*/
			Map<String,Object> pmap=new HashMap<String,Object>();
			List<FansUser> fansList=fansUserService.insertFansAndGet(fansUser, user,vip);
			pmap.put("fansList", fansList);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",pmap);
			return robj.toString();
		}catch(Exception e){
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "login.api###exception:",e.getMessage());
			return robj.toString();
		}
	}
	/**
	 * 查看一级，
	 * @param phone
	 * @param userId
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getOneFans.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String getOneFans(String phone,Long userId,Integer page,HttpServletResponse response,HttpServletRequest request){
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("phone", phone);
			map.put("userId", userId);
			int count=userService.pdId(map);
			if(count!=1){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NO_PHONEID, "no id and phone");
				return robj.toString();
			}
			if(page==null || page<=0){
				page=1;
			}
			PageUtil pageUtil=new PageUtil();
			pageUtil.setPageId(page);
			pageUtil.setPageSize(10);
			map.put("pageUtil", pageUtil);
			List<Map<String, Object>> onefans=userService.getOnefriends(map);
			int oneNumber=userService.getfriends(userId);
			int twoNumber=userService.getTwoFriendCount(userId);
			int pageCount=0;
			if(count>0){
				pageCount=(oneNumber/10)+1;
			}
			Map<String, Object> pmap=new HashMap<String, Object>();
			pmap.put("onefans", onefans);
			pmap.put("oneNumber", oneNumber);
			pmap.put("twoNumber", twoNumber);
			pmap.put("pageCount", pageCount);
			pmap.put("page", page);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",pmap);
			return robj.toString();
		}catch(Exception e){
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "login.api###exception:",e.getMessage());
			return robj.toString();
		}
	}
	/**
	 * 查看二级粉丝
	 * @param phone
	 * @param userId
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getTwoFans.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String getTwoFans(String phone,Long userId,Integer page,HttpServletResponse response,HttpServletRequest request){
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("phone", phone);
			map.put("userId", userId);
			int count=userService.pdId(map);
			if(count!=1){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NO_PHONEID, "no id and phone");
				return robj.toString();
			}
			PageUtil pageUtil=new PageUtil();
			if(page==null || page<=0){
				page=1;
			}
			pageUtil.setPageId(page);
			pageUtil.setPageSize(10);
			map.put("pageUtil", pageUtil);
			int oneNumber=userService.getfriends(userId);
			int twoNumber=userService.getTwoFriendCount(userId);
			int pageCount=0;
			if(count>0){
				pageCount=(oneNumber/10)+1;
			}
			List<Map<String, Object>> twofans=userService.getTwofriends(map);
			Map<String, Object> pmap=new HashMap<String, Object>();
			pmap.put("twofans", twofans);
			pmap.put("oneNumber", oneNumber);
			pmap.put("twoNumber", twoNumber);
			pmap.put("page", page);
			pmap.put("pageCount", pageCount);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",pmap);
			return robj.toString();
		}catch(Exception e){
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "login.api###exception:",e.getMessage());
			return robj.toString();
		}
	}
}

