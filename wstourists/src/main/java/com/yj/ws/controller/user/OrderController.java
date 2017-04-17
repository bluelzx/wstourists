package com.yj.ws.controller.user;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yj.ws.codes.Codes;
import com.yj.ws.controller.back.user.BaseController;
import com.yj.ws.dto.OrderInfo;
import com.yj.ws.dto.tourists.OrderTto;
import com.yj.ws.pojo.Capital;
import com.yj.ws.pojo.ShortMessageLogs;
import com.yj.ws.pojo.User;
import com.yj.ws.pojo.WithDrawRecord;
import com.yj.ws.service.CapitalService;
import com.yj.ws.service.ShortMessageLogsService;
import com.yj.ws.service.SignUserService;
import com.yj.ws.service.UserService;
import com.yj.ws.service.WithDrawRecordService;
import com.yj.ws.util.code.PageUtil;
import com.yj.ws.util.code.ServerSetting;
import com.yj.ws.util.common.DateUtil;
import com.yj.ws.util.common.HttpSender;
import com.yj.ws.util.common.ResultCode;
import com.yj.ws.util.json.ResultObj;
/**
 * 订单管理，包括订单申请，支付宝返回
 * @author yj
 *
 */
@Controller
@RequestMapping("app/order")
public class OrderController extends BaseController{
	@Autowired
	private WithDrawRecordService withDrawRecordService;
	@Autowired 
	private UserService userService;
	@Autowired
	private CapitalService capitalService;
	@Autowired
	private SignUserService signUserService;
	@Autowired
	private ShortMessageLogsService shortMessageLogsService;
	private final static double TEST_PRICE=0.01;
	
	/**
	 * 点击vip充值返回信息
	 * @param phone
	 * @param userId
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/clickVip.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String clickVip(String phone,Long userId,HttpServletResponse response,HttpServletRequest request){   
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("phone", phone);
			map.put("userId", userId);
			int count=userService.pdId(map);
			if(count!=1){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NO_PHONEID, "no id and phone");
				return robj.toString();
			}
			Capital capital=new Capital();
			capital.setBestClass(3);                                  //VIP相关信息bestclass为1
			List<Capital> capitalList=capitalService.selectCapital(capital);
			Map<String, Object> pmap=new HashMap<String, Object>();
			pmap.put("capitalList", capitalList);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",pmap);
			return robj.toString();
		}catch(Exception e){
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "login.api###exception:",e.getMessage());
			return robj.toString();
		}
	}
	/**
	 * 得到订单
	 * @param phone
	 * @param userId
	 * @param startTime
	 * @param endTime
	 * @param page
	 * @param state
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getOrder.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String getOrder(String phone,Long userId,String startTime,String endTime,Integer page,
			Integer state,HttpServletResponse response,HttpServletRequest request){       
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("phone", phone);
			map.put("userId", userId);
			int count=userService.pdId(map);
			if(count!=1){
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
			map.put("state", state);
			map.put("pageUtil", pageUtil);
			map.put("inorout", 1);
			map.put("endTime", et);
			map.put("startTime", st);
			map.put("caclss", 3);
			List<OrderTto> orderList=new ArrayList<OrderTto>();
			Map<String, Object> pmap=new HashMap<String, Object>();
			List<WithDrawRecord> recordList=withDrawRecordService.getRecord(map);
			if(recordList!=null && recordList.size()>0){
				for (WithDrawRecord withDrawRecord : recordList) {
					OrderTto order=new OrderTto();
					order.setOrderId(withDrawRecord.getRecordId());
					order.setOrderMoney(withDrawRecord.getRecordMomey());
					order.setOrderClass(withDrawRecord.getRecordClss());
					order.setOrderState(withDrawRecord.getRecordState());
					order.setOrderTime(withDrawRecord.getRecordTime());
					order.setPayNo(withDrawRecord.getFback1());
					order.setUserId(withDrawRecord.getUserId());
					/*
					 *订单详情
					 */
					Capital capital=new Capital();
					capital.setBestClass(withDrawRecord.getRecordClss());
					capital.setCaclss(withDrawRecord.getCaclss());
					List<Capital> capitalList=capitalService.selectCapital(capital);
					order.setCapital(capitalList);
					orderList.add(order);
				}
			}
			pmap.put("orderList", orderList);
			int countOrder=withDrawRecordService.getRecordCount(map);
			int pageCount=0;
			if(countOrder>0){
				pageCount=(countOrder/10)+1;
			}
			pmap.put("pageCount", pageCount);
			pmap.put("page", page);
			//根据id得到订单                      
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",pmap);
			return robj.toString();
		}catch(Exception e){
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "login.api###exception:",e.getMessage());
			return robj.toString();
		}
	}
	
	/**
	 * 增加订单
	 * @param phone
	 * @param userId
	 * @param caclss
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addOrder.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String addOrder(String phone,Long userId,Integer recordClss,HttpServletResponse response,HttpServletRequest request){
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("phone", phone);
			map.put("userId", userId);
			User user=userService.selectUser(map);
			if(user==null){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NO_PHONEID, "no id and phone");
				return robj.toString();
			}
			if(recordClss==null){
				ResultObj robj = new ResultObj(ResultCode.RESULT_PARAM_NULL, "caclssis null");
				return robj.toString();
			}
			Date vipStart=user.getVipStart();
			Date vipEnd =user.getVipEnd();
			Date dt=new Date();
			if(vipStart!=null && vipEnd!=null){
				if(dt.getTime()>=vipStart.getTime() && dt.getTime()<=vipEnd.getTime()){
					ResultObj robj = new ResultObj(ResultCode.RESULT_HAVE_VIP, "用户是VIP");
					return robj.toString();
				}
			}
			if(user.getFback2()!=null && user.getFback2().equals("1")){
				ResultObj robj = new ResultObj(ResultCode.RESULT_SY_VIP, "用户已经试用");
				return robj.toString();
			}
			//根据vip得到金额
			Capital capital=new Capital();
			capital.setCaclss(3);
			capital.setBestClass(recordClss);
			BigDecimal money=signUserService.getMoney(capital);
			WithDrawRecord record=new WithDrawRecord();
			String fback1=OrderInfo.payCode();
			record.setRecordClss(recordClss);             //vip充值
			record.setRecordMomey(money);
			record.setRecordState(1);          //提交
			record.setUserId(userId); 
			record.setInorout(2);
			record.setFback1(fback1);
			record.setCaclss(3);
			record.setRecordTime(DateUtil.timeToTime(new Date(), "yyy-MM-dd  HH:mm:ss"));
			Long recordId=withDrawRecordService.insertRecord(record);
			Map<String, Object> pmap=new HashMap<String, Object>();
			pmap.put("orderId", recordId);
			pmap.put("money", money);
			pmap.put("payNo", fback1);
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success",pmap);
			return robj.toString();
		}catch(Exception e){
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "login.api###exception:",e.getMessage());
			return robj.toString();
		}
	}
	/**
	 * 取消订单
	 * @param phone
	 * @param userId
	 * @param orderId
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/offOrder.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String offOrder(String phone,Long userId,Long orderId,HttpServletResponse response,HttpServletRequest request){   
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("phone", phone);
			map.put("userId", userId);
			int count=userService.pdId(map);
			if(count!=1){
				ResultObj robj = new ResultObj(ResultCode.RESULT_NO_PHONEID, "no id and phone");
				return robj.toString();
			}
			WithDrawRecord record=new WithDrawRecord();
			record.setRecordId(orderId);
			record.setRecordState(5);
			withDrawRecordService.updateRecord(record);      //修改状态为取消
			ResultObj robj = new ResultObj(ResultCode.RESULT_SUCCESS, "success");
			return robj.toString();
		}catch(Exception e){
			log.error(e);
			ResultObj robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "login.api###exception:",e.getMessage());
			return robj.toString();
		}
	}
	
	/**
	 * 支付
	 * @param phone
	 * @param userId
	 * @param payNo
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/alipay.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String alipay(String phone,String userId,Long orderId,String payNo,HttpServletResponse response,HttpServletRequest request){
		log.info(" in order/getOrderInfoById.api method.");
		ResultObj robj = null;
		if(payNo==null || payNo==""){
			robj = new ResultObj(ResultCode.RESULT_PARAM_NULL, "order is null");
			log.info("in order/alipay.api###requestparam is null");
			return robj.toString();
		}
		try{
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("phone", phone);
			map.put("userId", userId);
			int count=userService.pdId(map);
			if(count!=1){
				robj = new ResultObj(ResultCode.RESULT_NO_PHONEID, "no id and phone");
				return robj.toString();
			}
			//OrderDto4 order = orderService.getOrderInfoById2(orderId);
			WithDrawRecord record=new WithDrawRecord();
			record.setFback1(payNo);
			List<WithDrawRecord> recordList=withDrawRecordService.getRecordByClass(record);
			if(null != recordList){
				WithDrawRecord records=new WithDrawRecord();
				records=recordList.get(0);
				if(records.getRecordState()!=1){
					HashMap<String,Object> hmap = new HashMap<String,Object>();
					hmap.put("orderId", records.getRecordId());
					hmap.put("payNo", records.getFback1());
					//double totalprice=order.getSubTotal()-order.getCoupon()+order.getFreight();
					Capital capital=new Capital();
					capital.setCaclss(records.getCaclss());
					capital.setBestClass(1);
					List<Capital> capitalList=capitalService.selectCapital(capital);
					String substr="";
					if(capitalList!=null){
						Capital capitals=capitalList.get(0);
						substr=capitals.getRemark();
					}
					String notify =ServerSetting.notifyUrl;
					String str =OrderInfo.getOrderInfo(records.getFback1(), substr,substr, BigDecimal.valueOf(TEST_PRICE).toString(), notify);
					hmap.put("content", str);
					robj = new ResultObj(ResultCode.RESULT_SUCCESS,"success",hmap);
					log.info("in order/getpayinfo.api###success");
					return robj.toString();	
				}else{
					robj = new ResultObj(ResultCode.RESULT_PARAM_ERROR,"in order/getpayinfo.api###订单状态不符合支付要求");
					log.info("in order/getpayinfo.api###订单状态不符合支付要求");
					return robj.toString();	
				}
			}else{
				robj = new ResultObj(ResultCode.RESULT_PARAM_ERROR, "success",new Object());
				log.info("in order/getOrderInfoById.api###success");
				return robj.toString();
			}
		}catch(Exception e){
			e.printStackTrace();
			robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "in order/getOrderInfoById.api###exception:"+e.getMessage());
			log.info("in order/getOrderInfoById.api###exception::"+e.getMessage());
			return robj.toString();
		}
	}
	@RequestMapping(value="/callbacks.api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String callbacks( HttpServletRequest request,String out_trade_no,String trade_status ) throws Exception {
        //接收支付宝返回的请求参数
		ResultObj robj = null;
		/*Map requestParams = request.getParameterMap();
        JSONObject json = JSONObject.fromObject(requestParams);
        String trade_status = json.get("trade_status").toString().substring(2,json.get("trade_status").toString().length()-2);
        String out_trade_no = json.get("out_trade_no").toString().substring(2,json.get("out_trade_no").toString().length()-2);
        String notify_id = json.get("notify_id").toString().substring(2,json.get("notify_id").toString().length()-2);*/
        /* System.out.println("====================================================");
        System.out.println(json.toString());
        System.out.println("支付宝回调地址！");
        System.out.println("商户的订单编号：" + out_trade_no);
        System.out.println("支付的状态：" + trade_status);  */  
		String start="";
        try {
        	start=out_trade_no.substring(0,2);
        	if(start=="" && !start.equals("as")){            //tl
        		robj = new ResultObj(ResultCode.RESULT_NOT_SET, "pay erro:");
            	return robj.toString();
        	}
        	out_trade_no=out_trade_no.substring(2,out_trade_no.length()-2);
        	if(trade_status==null && trade_status.length()>0){
        		robj = new ResultObj(ResultCode.RESULT_NOT_SET, "pay erro:");
            	return robj.toString();
        	}
        	if(trade_status.equals("TRADE_SUCCESS")){
        		WithDrawRecord records = null;
				WithDrawRecord record = new WithDrawRecord();
				record.setFback1(out_trade_no);
				List<WithDrawRecord> recordList = this.withDrawRecordService.getRecordByClass(record);
				if (recordList != null && recordList.size() > 0){
					records = (WithDrawRecord)recordList.get(0);
				}else{
					robj = new ResultObj(212, "支付码错误");
					return robj.toString();
				}
				int state = records.getRecordState();
				if (state == 2){
					robj = new ResultObj(211, "having User:");
					return robj.toString();
				}
				/*
				 * 短信
				 */
				
				Map<String, Object> pmap=new HashMap<String, Object>();
				pmap.put("userId", records.getUserId());
				User users=userService.selectUser(pmap);
				if(users==null){
					robj = new ResultObj(211, "pay erro:");
					return robj.toString();
				}
				String phone=users.getPhoneNumber();
				Date dt = new Date();
				String createtime = DateUtil.dateFormatToString(dt, "yyyy-MM-dd HH:mm:ss");
				String strtime = DateUtil.dateFormatToString(dt, "yyyyMMddHHmmss");
				Random rd = new Random();
				int maths = rd.nextInt(99) + 100;
				StringBuffer strTemp = new StringBuffer(Codes.qianming + "尊敬的" + phone + "用户,您于" + createtime  + "充值VIP成功,感谢您的支持");
				String returnString = HttpSender.batchSend(phone, strTemp.toString(), "1", null);
				ShortMessageLogs message = new ShortMessageLogs();
				String serialNumber = strtime + '-' + maths;
				message.setChannel("");
				message.setCreateTime(DateUtil.stringToDate(createtime, "yyyy-MM-dd"));
				message.setPhinoClss(Integer.valueOf(1));
				message.setTelePhone(phone);
				message.setSerialNumber(serialNumber);
				message.setContent(strTemp.toString());
				shortMessageLogsService.insertMessage(message);
				Long messageId = message.getId();

				int index = returnString.indexOf(",");
				if (index != -1){
					String str = returnString.substring(index - 1);
					if (str.equals("0")) {
						this.shortMessageLogsService.updateMessage(messageId);
					}
				}
				//end
				
				Integer recordClss = records.getRecordClss();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date startTime = sdf.parse(sdf.format(dt));
				Date endTime = startTime;
				if (recordClss == 1){
					endTime = DateUtil.addTime(startTime, "yyyy-MM-dd", 2, 1);
				}else if (recordClss == 3){
					endTime = DateUtil.addTime(startTime, "yyyy-MM-dd", 1, 3);
				}
				else if (recordClss== 2){
					endTime = DateUtil.addTime(startTime, "yyyy-MM-dd", 1, 3);
				}
				User user = new User();
				user.setVipStart(startTime);
				user.setVipEnd(endTime);
				user.setVipLevel(recordClss);
				user.setUserId(records.getUserId());
				if (recordClss.equals(1) ) {
					user.setFback2("1");
				}
				userService.updateUserMoney(user);
				record.setFback1(out_trade_no);
				record.setRecordState(2);
				withDrawRecordService.updateRecord(record);
				
				robj = new ResultObj(200, "success");
				return robj.toString();
			}else{
				WithDrawRecord record = new WithDrawRecord();
				record.setFback1(out_trade_no);
				record.setRecordState(4);
				this.withDrawRecordService.updateRecord(record);
				robj = new ResultObj(211, "pay erro:");
				return robj.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			robj = new ResultObj(ResultCode.RESULT_EXCEPTION, "order"+e.getMessage());
			log.info("in order"+e.getMessage());
			return robj.toString();
		}
        
    }

	
}



















