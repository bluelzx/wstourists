package com.yj.ws.controller.user;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yj.ws.codes.Codes;
import com.yj.ws.controller.back.user.BaseController;
import com.yj.ws.pojo.ShortMessageLogs;
import com.yj.ws.pojo.User;
import com.yj.ws.pojo.WithDrawRecord;
import com.yj.ws.service.ShortMessageLogsService;
import com.yj.ws.service.UserService;
import com.yj.ws.service.WithDrawRecordService;
import com.yj.ws.util.code.PageUtil;
import com.yj.ws.util.common.DateUtil;
import com.yj.ws.util.common.HttpSender;
import com.yj.ws.util.common.ResultCode;
import com.yj.ws.util.json.ResultObj;

@Controller
@RequestMapping("app/withDraw")
public class WithDrawController extends BaseController{
	@Autowired
	private UserService userService;
	@Autowired
	private WithDrawRecordService withDrawRecordService;
	@Autowired
	private ShortMessageLogsService shortMessageLogsService;           //短信
	/**
	 * 用户提现
	 * @param phone
	 * @param userId
	 * @param response
	 * @param request
	 * @param money
	 * @return
	 */
	@RequestMapping(value="/record.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String record(String phone,Long userId,Double money,String carCode,String name,
			Integer accountClass,HttpServletResponse response,HttpServletRequest request){
		if(phone==null || phone.length()==0){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PARAM_NULL, "login.api###RequestParam is null");
			return robj.toString();
		}
		if(money==null ){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PARAM_NULL, "money is null");
			return robj.toString();
		}
		if(carCode==null || carCode.length()==0){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PARAM_NULL, "carCode is null");
			return robj.toString();
		}
		if(accountClass==null){
			accountClass=1;
		}
		String classes="";
		if(accountClass==1){
			classes="支付宝";
		}else{
			classes="微信";
		}
		try {
			BigDecimal bmoney=new BigDecimal(money);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("phone", phone);
			map.put("userId", userId);
			map.put("money", bmoney);
			map.put("carCode", carCode);
			map.put("rname", name);
			map.put("accountClass", accountClass);
			int userCount=userService.pdId(map);
			if(userCount==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NO_PHONEID, "no id and phone");
				return robj.toString();
			}
			map=withDrawRecordService.callAccount(map);
			Integer code=(Integer) map.get("codes");
			if(code.equals(0)){
				Date dt=new Date();
				String createtime=DateUtil.dateFormatToString(dt, "yyyy-MM-dd HH:mm:ss");
				String strtime=DateUtil.dateFormatToString(dt, "yyyyMMddHHmmss");
				Random rd = new Random();
				int maths =rd.nextInt(99)+100;
				StringBuffer strTemp=new StringBuffer(Codes.qianming+"尊敬的"+phone+"用户,您于"+new Date()+"申请提现"+money+"成功,我们将尽快转入您"+classes+"账号"+carCode+",敬请查收！");
				String returnString=HttpSender.batchSend(phone, strTemp.toString(), "1", null);						//短信发送接口    测试环境不用
				ShortMessageLogs message=new ShortMessageLogs();
				String serialNumber=strtime+'-'+maths;
				message.setChannel("");
				message.setCreateTime(DateUtil.stringToDate(createtime, "yyyy-MM-dd"));
				message.setPhinoClss(1);
				message.setTelePhone(phone);
				message.setSerialNumber(serialNumber);
				message.setContent(strTemp.toString());
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
			}
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",map);
			return robj.toString();
		} catch (Exception e) {
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "login.api###exception:",e.getMessage());
			return robj.toString();
		}
	}
	/**
	 * 收入
	 * @param phone
	 * @param userId
	 * @param money
	 * @param startTime
	 * @param endTime
	 * @param recordClss 类型 ：资金类型，1,提现，2签到获取，3vip充值
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getOneRecord.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String getOneRecord(String phone,Long userId,String startTime,String endTime,Integer page,
			Integer recordClss,HttpServletResponse response,HttpServletRequest request){      
		if(phone==null || phone.length()==0){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PARAM_NULL, "login.api###RequestParam is null");
			return robj.toString();
		}
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("phone", phone);
			map.put("userId", userId);
			int userCount=userService.pdId(map);
			if(userCount==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NO_PHONEID, "no id and phone");
				return robj.toString();
			}
			Date st=null;
			if(page==null || page<=0){
				page=1;
			}
			if(startTime!=null && startTime.length()!=0){
				st=DateUtil.stringToDate(startTime, "yyyy-MM-dd");
			}
			Date et=null;
			if(endTime!=null && endTime.length()!=0){
				et=DateUtil.stringToDate(endTime, "yyyy-MM-dd");
			}
			PageUtil pageUtil=new PageUtil();
			pageUtil.setPageId(page);
			pageUtil.setPageSize(10);
			map.put("pageUtil", pageUtil);
			map.put("endTime", et);
			map.put("startTime", st);
			map.put("recordClss", recordClss);
			map.put("inorout", 1);
			int count=withDrawRecordService.getRecordCount(map);
			int pageCount=0;
			if(count>0){
				pageCount=(count/10)+1;
			}
			List<WithDrawRecord> recordList=withDrawRecordService.getRecord(map);
			Map<String, Object> pmap=new HashMap<String, Object>();
			pmap.put("recordList", recordList);
			pmap.put("pageCount", pageCount);
			pmap.put("page", page);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",pmap);
			return robj.toString();
		} catch (Exception e) {
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "login.api###exception:",e.getMessage());
			return robj.toString();
		}
	}
	/**
	 * 
	 * @param phone
	 * @param userId
	 * @param money
	 * @param startTime
	 * @param endTime
	 * @param recordClss 类型 ：资金类型，1,提现，2签到获取，3vip充值
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getTwoRecord.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String getTwoRecord(String phone,Long userId,String startTime,String endTime,Integer page,
			Integer recordClss,HttpServletResponse response,HttpServletRequest request){      
		if(phone==null || phone.length()==0){
			ResultObj robj = new ResultObj(ResultCode.RESULT_PARAM_NULL, "login.api###RequestParam is null");
			return robj.toString();
		}
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("phone", phone);
			map.put("userId", userId);
			int userCount=userService.pdId(map);
			if(userCount==0){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NO_PHONEID, "no id and phone");
				return robj.toString();
			}
			Date st=null;
			if(startTime!=null && startTime.length()!=0){
				st=DateUtil.stringToDate(startTime, "yyyy-MM-dd");
			}
			Date et=null;
			if(endTime!=null && endTime.length()!=0){
				et=DateUtil.stringToDate(endTime, "yyyy-MM-dd");
			}
			if(page==null || page<=0){
				page=1;
			}
			PageUtil pageUtil=new PageUtil();
			pageUtil.setPageId(page);
			pageUtil.setPageSize(10);
			map.put("pageUtil", pageUtil);
			map.put("inorout", 2);
			map.put("endTime", et);
			map.put("startTime", st);
			map.put("recordClss", recordClss);
			List<WithDrawRecord> recordList=withDrawRecordService.getRecord(map);
			Map<String, Object> pmap=new HashMap<String, Object>();
			int count=withDrawRecordService.getRecordCount(map);
			int pageCount=0;
			if(count>0){
				pageCount=(count/10)+1;
			}
			pmap.put("pageCount", pageCount);
			pmap.put("page", page);
			pmap.put("recordList", recordList);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",pmap);
			return robj.toString();
		} catch (Exception e) {
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "login.api###exception:",e.getMessage());
			return robj.toString();
		}
	}
}



