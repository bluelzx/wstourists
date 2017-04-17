package com.yj.ws.controller.user;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;















import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yj.ws.codes.Codes;
import com.yj.ws.controller.back.user.BaseController;
import com.yj.ws.pojo.Ips;
import com.yj.ws.pojo.ShortMessageLogs;
import com.yj.ws.pojo.User;
import com.yj.ws.pojo.WithDrawRecord;
import com.yj.ws.service.CityService;
import com.yj.ws.service.IpsService;
import com.yj.ws.service.ShortMessageLogsService;
import com.yj.ws.service.UserService;
import com.yj.ws.service.WithDrawAccountService;
import com.yj.ws.service.WithDrawRecordService;
import com.yj.ws.util.common.CityByPhone;
import com.yj.ws.util.common.DateUtil;
import com.yj.ws.util.common.HttpClientPhone;
import com.yj.ws.util.common.HttpSender;
import com.yj.ws.util.common.ResultCode;
import com.yj.ws.util.common.Validator;
import com.yj.ws.util.json.ResultObj;

/**
 * 用户相关
 * @author Administrator
 *
 */
@SuppressWarnings("all")
@RequestMapping("app/user")
@Controller
public class UserController {
	private static final Logger log = Logger.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private ShortMessageLogsService shortMessageLogsService;           //短信
	@Autowired
	private CityService cityService;
	@Autowired
	private WithDrawAccountService withDrawAccountService;
	@Autowired
	private WithDrawRecordService withDrawRecordService;
	@Autowired
	private IpsService ipsService;
	
	/**
	 * 验证码获取到用户信息(登录)
	 * @return
	 */
	//@RequestMapping("/login")
	@RequestMapping(value="/login.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String login(String phone,HttpServletRequest request){
		if(phone==null || phone.length()==0){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PARAM_NULL, "homepageindex.api###RequestParam is null");
			return robj.toString();
		}
		if(!Validator.isMobileNum(phone)){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PHONE_ERROR, "homepageindex.api###Phone number is incorrect");
			return robj.toString();
		}
		try {
			Date dt=new Date();
			String createtime=DateUtil.dateFormatToString(dt, "yyyy-MM-dd");
			Map telMap=new HashMap();
			telMap.put("createtime", createtime);
			telMap.put("telePhone", phone);
			int count=shortMessageLogsService.messageCount(telMap);				//得到当天短信次数
			if(count>3){
				ResultObj robj = new ResultObj(ResultCode.RESULT_PHONE_TIMES_CODE, "homepageindex.api###Phone long tinmes");
				return robj.toString();
			}
			//得到产生的验证码
			char[] rand=HttpClientPhone.getRandom();
			String temp=new String(rand);
			StringBuffer strTemp=new StringBuffer(Codes.qianming+"尊敬的"+phone+"用户,您的手机验证码:"+temp+",请在5分钟内验证");
			//发送短信
			//HttpSender.batchSend(phone, strTemp.toString(), "1", null);
			ShortMessageLogs message=new ShortMessageLogs();
			//String id= UUID.randomUUID().toString();
			String strtime=DateUtil.dateFormatToString(dt, "yyyyMMddHHmmss");
			Random rd = new Random();
			int maths =rd.nextInt(99)+100;
			//message.setId(id);
			//shortMessageLogsService.addMessage(message);
			//发送信息
			//boolean result=true;
			String returnString=HttpSender.batchSend(phone, strTemp.toString(), "1", null);						//短信发送接口    测试环境不用
			
			String serialNumber=strtime+'-'+maths;
			message.setChannel("");
			message.setCreateTime(DateUtil.stringToDate(createtime, "yyyy-MM-dd HH:mm:ss"));
			message.setPhinoClss(1);
			message.setTelePhone(phone);
			message.setContent(strTemp.toString());
			message.setSerialNumber(serialNumber);
			shortMessageLogsService.insertMessage(message);
			Long messageId=message.getId();
			//发送短信
			/*String returnString=HttpSender.batchSend(phone, strTemp.toString(), "1", null);
			String returnString="";*/
			int index=returnString.indexOf(",");
			if(index!=-1){
				String str=returnString.substring(index-1);
				if(str.equals("0")){
					shortMessageLogsService.updateMessage(messageId);          //短信发送成功
				}
			}
			HashMap<String,Object> map = new HashMap<String,Object>();
			request.getSession().setAttribute(phone, temp);
			request.getSession().setMaxInactiveInterval(300);
			map.put("result", true);
			//map.put("temp", temp);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",map);
			return robj.toString();
			/*}else{
				ResultObj robj = new ResultObj(ResultCode.RESULT_PHONE_NOT_SET, "SMS send failure");
				return robj.toString();
			}*/
			
		} catch (Exception e) {
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "getsmscode.api:",e.getMessage());
			return robj.toString();
		}
	}
	/**
	 * 得到验证码,注册
	 */
	@RequestMapping(value="/getsmscode.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String getsmscode(String phone,String msmcode,HttpServletResponse response,HttpServletRequest request){
		if(phone==null || phone.length()==0){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PARAM_NULL, "login.api###RequestParam is null");
			return robj.toString();
		}
		if(!Validator.isMobileNum(phone)){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PHONE_ERROR, "homepageindex.api###Phone number is incorrect");
			return robj.toString();
		}
		if(msmcode==null || msmcode.length()==0){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PARAM_NULL, "login.api###RequestParam is null");
			return robj.toString();
		}
		try {
			String temp=(String) request.getSession().getAttribute(phone);
			if(temp==null || temp.length()==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_PHONE_NOT_CODE, "Verification code expired");
				return robj.toString();
			}
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("phone", phone);
			if(temp.equals(msmcode)){							//验证码获取成功   取用户信息
				User user=userService.selectUser(map);
				if(user!=null){
					map.put("userId", user.getUserId());
					ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",map);
					return robj.toString();
				}else{							//注册
					/*Map<String, String> cityMap=CityByPhone.getCity(phone);
					Integer province=cityService.getCityId(cityMap.get("province"));
					Integer city=cityService.getCityId(cityMap.get("city"));*/
					Date dt=new Date();
					String createtime=DateUtil.dateFormatToString(dt, "yyyy-MM-dd");
					User zuser=new User();
					zuser.setPhoneNumber(phone);
					zuser.setFansNumber(50);
					zuser.setIsjoin(1);
					zuser.setIsdelete(2);
					zuser.setVipLevel(0);
					/*zuser.setProvince(province);
					zuser.setCity(city);*/
					zuser.setCrateDate(DateUtil.stringToDate(createtime, "yyyy-MM-dd"));
					/*得到ip,进而得到推荐人
					 */
					String ip = request.getHeader("x-forwarded-for");   
				    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
				        ip = request.getHeader("Proxy-Client-IP");   
				    }   
				    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
				        ip = request.getHeader("WL-Proxy-Client-IP");   
				    }   
				    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
				        ip = request.getRemoteAddr();   
				        if(ip.equals("127.0.0.1")){     
				            //根据网卡取本机配置的IP     
				            InetAddress inet=null;     
				            try {     
				                inet = InetAddress.getLocalHost();     
				            } catch (UnknownHostException e) {     
				                e.printStackTrace();     
				            }     
				            ip= inet.getHostAddress();     
				        }  
				    } 
				    Ips ips=new Ips();
				    Date times=DateUtil.timeToTime(new Date(), "yyyy-MM-dd HH:mm:ss");
				    ips.setIp(ip);
				    ips.setTimes(times);
				    List<Ips> listIps=ipsService.selectBytimes(ips);
					if(listIps!=null && listIps.size()>0){            //有推荐人
						Ips ips2=listIps.get(0);
						String othenPhone =ips2.getPhone();
						if(othenPhone!=null && !othenPhone.equals("") ){
							Map<String, Object> othenmap=new HashMap<String, Object>();
							othenmap.put("othenPhone", othenPhone);
							User othenUsers=userService.selectUser(othenmap);
							if(othenUsers==null){
								userService.insertUser(zuser);
								map.put("userId", zuser.getUserId());
								map.put("phone", phone);
								ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",map);
								return robj.toString();
							}
							//推荐人奖励0.5元
							othenUsers.setEarnMoney((othenUsers.getEarnMoney()!=null ? othenUsers.getEarnMoney():new BigDecimal("0")).add(new BigDecimal("0.05")));
							othenUsers.setEarnMoney((othenUsers.getTotalMoney()!=null ? othenUsers.getTotalMoney() :new BigDecimal("0")).add(new BigDecimal("0.05")));
							othenUsers.setFansNumber(othenUsers.getFansNumber()+100);
							zuser.setParentId(othenUsers.getUserId());
							Long oparentId=othenUsers.getUserId();
							Date dts=new Date();
							String createtimes=DateUtil.dateFormatToString(dts, "yyyy-MM-dd HH:mm:ss");
							WithDrawRecord orecord=new WithDrawRecord();
							orecord.setRecordClss(4);          //推荐人
							orecord.setInorout(1);                 //收入
							orecord.setUserId(othenUsers.getUserId());
							orecord.setRecordTime(DateUtil.stringToDate(createtimes, "yyyy-MM-dd HH:mm:ss"));
							orecord.setRecordMomey(new BigDecimal("0.05"));
							withDrawRecordService.insertRecord(orecord);         //增加推荐人的记录
							userService.updateUserMoney(othenUsers);          //推荐人增加金额
							
							//找到推荐人的推荐人
							Long oouserId=othenUsers.getParentId();
							if(oouserId!=null){
								Map<String, Object> oothenmap=new HashMap<String, Object>();
							
								oothenmap.put("userId", oouserId);
								User oothenUsers=userService.selectUser(oothenmap);
								if(oothenUsers!=null){
									WithDrawRecord oorecord=new WithDrawRecord();
									oorecord.setRecordClss(4);          //推荐人
									oorecord.setInorout(1);                 //收入
									oorecord.setUserId(oouserId);
									orecord.setRecordTime(DateUtil.stringToDate(createtime, "yyyy-MM-dd HH:mm:ss"));
									oorecord.setRecordMomey(new BigDecimal("0.02"));
									oothenUsers.setEarnMoney((oothenUsers.getEarnMoney()!=null ? oothenUsers.getEarnMoney():new BigDecimal("0")).add(new BigDecimal("0.02")));
									oothenUsers.setEarnMoney((oothenUsers.getTotalMoney()!=null ? oothenUsers.getTotalMoney() :new BigDecimal("0")).add(new BigDecimal("0.02")));
									userService.updateUserMoney(oothenUsers);          //推荐人的推荐人增加金额
									withDrawRecordService.insertRecord(oorecord);         //增加推荐人的推荐人记录
								}
							}
						}
					}
					userService.insertUser(zuser);
					map.put("userId", zuser.getUserId());
					map.put("phone", phone);
					ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",map);
					return robj.toString();
				}
			}else{
				ResultObj robj = new ResultObj(ResultCode.RESULT_PHONE_ERROR_CODE, "Verification code error");
				return robj.toString();
			}
		} catch (Exception e) {
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "login.api###exception:",e.getMessage());
			return robj.toString();
		}
	}
	@RequestMapping(value="/getmain.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String getmain(String phone,Long userId,HttpServletResponse response,HttpServletRequest request){
		if(phone==null || phone.length()==0){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PARAM_NULL, "login.api###RequestParam is null");
			return robj.toString();
		}
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("phone", phone);
			map.put("userId", userId);
			User user=userService.selectUser(map);                       //得到用户信息
			if(user==null){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NO_PHONEID, "no id and phone");
				return robj.toString();
			}
			/*map.put("name", user.getUserName());
			map.put("vipStart", user.getVipStart());              //vip 开始时间
			map.put("vipEnd", user.getVipEnd());							//vip结束时间
			map.put("vipLevel", user.getVipLevel());                   //vip等级
			map.put("fansNumber", user.getFansNumber());							//能够加的粉丝数目
			map.put("totalMoney", user.getTotalMoney());                  //总金额
			map.put("frozenFund", user.getFrozenFund());							//冻结金额
			map.put("earnMoney", user.getEarnMoney());						//赚取金额
			map.put("withdrawTotal", user.getWithdrawTotal());				//提现金额
			map.put("isjoin", user.getIsjoin());							//是否允许加粉,1是，2否
			map.put("isdelete", user.getIsdelete());	*/						//是否被禁用
			Date vipStart = user.getVipStart();
			Date vipEnd = user.getVipEnd();
			int vipLevel=user.getVipLevel();
			if(vipLevel!=0){
				Date dt = new Date();
				if (vipStart == null && vipEnd == null && dt.getTime() < vipStart.getTime() && dt.getTime() > vipEnd.getTime()){
					/*Map<String, Object> maps=new HashMap<String, Object>();
					maps.put("vipLevel", vipLevel);
					maps.put("userId", userId);*/
					User users=new User();
					users.setVipLevel(0);
					users.setUserId(userId);
					userService.updateUserMoney(users);
					user.setVipLevel(0);
				}
			}
			Map<String, Object> pmap=new HashMap<String, Object>();
			user.setUserPassword("");
			user.setUserId(null);
			pmap.put("user", user);
			//String imgurl="";
			//map.put("imgurl", imgurl);
			int subfuns=userService.getfriends(userId);         //得到粉丝数目
			List<Map<String, Object>> withmap=withDrawAccountService.selectLast(userId);
			if(withmap!=null && withmap.size()>0){
				Map<String, Object> with=withmap.get(0);
				pmap.put("carCode", with.get("carCode")!=null ? with.get("carCode").toString():"");
				pmap.put("name", with.get("name")!=null ? with.get("name").toString():"");
				pmap.put("carClass", with.get("carClass")!=null ? with.get("carClass").toString():0);
			}else{
				pmap.put("carCode", "");
				pmap.put("name", "");
				pmap.put("carClass", 0);
			}
			pmap.put("subfuns", subfuns);						//得到下级用户
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",pmap);
			return robj.toString();
		} catch (Exception e) {
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "login.api###exception:",e.getMessage());
			return robj.toString();
		}
	}
	/**
	 * 得到其他首页信息，预留
	 * @param phone
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/othermain.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String othermain(String phone,Long userId,HttpServletResponse response,HttpServletRequest request){
		if(phone==null || phone.length()==0){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PARAM_NULL, "login.api###RequestParam is null");
			return robj.toString();
		}
		if(!Validator.isMobileNum(phone)){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PHONE_ERROR, "homepageindex.api###Phone number is incorrect");
			return robj.toString();
		}
		try {
			int funs=userService.getfriends(userId);         //得到粉丝数目
			Map map=new HashMap();
			map.put("funs", funs);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",map);
			return robj.toString();
		}catch(Exception e){
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "login.api###exception:",e.getMessage());
			return robj.toString();
		}
	}
	/**
	 * 修改是否允许加入  1是  2否
	 * @param phone
	 * @param userId
	 * @param isjoin
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updateIsjoin.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String updateIsjoin(String phone,Long userId,Integer isjoin,HttpServletResponse response,HttpServletRequest request){
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("phone", phone);
			map.put("userId", userId);
			int count=userService.pdId(map);
			if(count!=1){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NO_PHONEID, "no id and phone");
				return robj.toString();
			}
			User user=new User();
			user.setIsjoin(isjoin);
			userService.updateUserMoney(user);                                    //修改是否允许加入
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success");
			return robj.toString();
		}catch(Exception e){
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "login.api###exception:",e.getMessage());
			return robj.toString();
		}
	}
	/**
	 * 推荐人
	 * @param phone
	 * @param userId
	 * @param othenPgone
	 * @param request
	 * @return
	 */
	@RequestMapping("/getOthen")
	public String getOthen(String othenPhone,HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");   
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
	        ip = request.getHeader("Proxy-Client-IP");   
	    }   
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
	        ip = request.getHeader("WL-Proxy-Client-IP");   
	    }   
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
	        ip = request.getRemoteAddr();   
	        if(ip.equals("127.0.0.1")){     
	            //根据网卡取本机配置的IP     
	            InetAddress inet=null;     
	            try {     
	                inet = InetAddress.getLocalHost();     
	            } catch (UnknownHostException e) {     
	                e.printStackTrace();     
	            }     
	            ip= inet.getHostAddress();     
	        }  
	    }   
	       // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
	    if(ip != null && ip.length() > 15){    
	        if(ip.indexOf(",")>0){     
	            ip = ip.substring(0,ip.indexOf(","));     
	        }     
	    }  
	    if(othenPhone!=null && othenPhone.length()>0){
	    	Ips ips=new Ips();
	    	Date times=DateUtil.timeToTime(new Date(), "yyyy-MM-dd HH:mm:ss");
	    	ips.setIp(ip);
	    	ips.setPhone(othenPhone);
	    	ips.setTimes(times);
	    	ipsService.insertIps(ips);
	    }
	    return "page/share";   
		/*try {
			Map<String, Object> map=new HashMap<String, Object>();
			if(othenPhone==null && othenPhone.length()==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_PARAM_NULL, "othenPhone is null");
				return robj.toString();
			}
			map.put("phone", phone);
			map.put("userId", userId);
			User user=userService.selectUser(map);
			if(user==null){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NO_PHONEID, "no id and phone");
				return robj.toString();
			}
			if(user.getParentId()!=null){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NO_OTHEN, "已经有推荐人");
				return robj.toString();
			}
			Map<String, Object> othenmap=new HashMap<String, Object>();
			othenmap.put("othenPhone", othenPhone);
			User othenUsers=userService.selectUser(othenmap);
			//推荐人奖励0.5元
			othenUsers.setEarnMoney(othenUsers.getEarnMoney().add(new BigDecimal("0.05")));
			othenUsers.setEarnMoney(othenUsers.getTotalMoney().add(new BigDecimal("0.05")));
			othenUsers.setFansNumber(othenUsers.getFansNumber()+100);
			if(othenPhone.equals(phone)){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NO_OTHEN, "不能推荐自己");
				return robj.toString();
			}
			if(othenUsers==null){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NO_OTHEN, "改推荐人没有注册");
				return robj.toString();
			}
			Long oparentId=othenUsers.getUserId();
			if(oparentId.equals(userId)){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NO_OTHEN, "不能互相推荐");
				return robj.toString();
			}
			Date dt=new Date();
			String createtime=DateUtil.dateFormatToString(dt, "yyyy-MM-dd HH:mm:ss");
			WithDrawRecord orecord=new WithDrawRecord();
			orecord.setRecordClss(4);          //推荐人
			orecord.setInorout(1);                 //收入
			orecord.setUserId(othenUsers.getUserId());
			orecord.setRecordTime(DateUtil.stringToDate(createtime, "yyyy-MM-dd HH:mm:ss"));
			orecord.setRecordMomey(new BigDecimal("0.05"));
			withDrawRecordService.insertRecord(orecord);         //增加推荐人的记录
			userService.updateUserMoney(othenUsers);          //推荐人增加金额
			
			//找到推荐人的推荐人
			Long oouserId=othenUsers.getParentId();
			if(oouserId!=null){
				Map<String, Object> oothenmap=new HashMap<String, Object>();
			
				oothenmap.put("userId", oouserId);
				User oothenUsers=userService.selectUser(oothenmap);
				if(oothenUsers!=null){
					WithDrawRecord oorecord=new WithDrawRecord();
					oorecord.setRecordClss(4);          //推荐人
					oorecord.setInorout(1);                 //收入
					oorecord.setUserId(oouserId);
					orecord.setRecordTime(DateUtil.stringToDate(createtime, "yyyy-MM-dd HH:mm:ss"));
					oorecord.setRecordMomey(new BigDecimal("0.02"));
					oothenUsers.setEarnMoney(oothenUsers.getEarnMoney().add(new BigDecimal("0.02")));
					oothenUsers.setEarnMoney(oothenUsers.getTotalMoney().add(new BigDecimal("0.02")));
					userService.updateUserMoney(oothenUsers);          //推荐人的推荐人增加金额
					withDrawRecordService.insertRecord(oorecord);         //增加推荐人的推荐人记录
				}
			}
			
			User users=new User();
			users.setParentId(othenUsers.getUserId());
			users.setUserId(userId);
			userService.updateUserMoney(users);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success");
			return robj.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;*/
	}
	
}










