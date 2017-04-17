package com.yj.ws.controller.back.user;


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

import com.yj.ws.controller.back.user.BaseController;
import com.yj.ws.controller.user.CityController;
import com.yj.ws.pojo.SysUsers;
import com.yj.ws.pojo.User;
import com.yj.ws.pojo.WithDrawRecord;
import com.yj.ws.service.UserService;
import com.yj.ws.service.WithDrawRecordService;
import com.yj.ws.util.code.PageUtil;
import com.yj.ws.util.common.DateUtil;
import com.yj.ws.util.common.MethodUtil;
import com.yj.ws.util.common.ResultCode;
import com.yj.ws.util.json.ResultObj;

@Controller
@RequestMapping("admin/withDrawBack")
public class WithDrawBackController extends BaseController{
	private static final Logger log = Logger.getLogger(WithDrawBackController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private WithDrawRecordService withDrawRecordService;
	
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
	@RequestMapping("/gethRecord")
	public String getTwoRecord(String curPage,String phone,String startTime,String endTime,Integer recordState,
			Integer recordClss,HttpServletResponse response,HttpServletRequest request){      
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("phone", phone);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("recordState", recordState);
			map.put("recordClss", recordClss);
			if (MethodUtil.isEmpty(curPage)) {
				curPage = "0";// 当前页数
			}
			Integer count=withDrawRecordService.hgetCount(map);
			//分页
			String limit = this.getLimit(Integer.parseInt(curPage), count);
			map.put("limit", limit);
			List<WithDrawRecord> queryList=withDrawRecordService.hgetRecord(map);
			Double totalMoney=withDrawRecordService.hgetMoney(map);
			request.setAttribute("list", queryList);
			request.setAttribute("totalMoney", totalMoney);
			// set分页参数
			this.initPage(request, Integer.parseInt(curPage), null, count);
			return "page/admin/record/record_list";
		}catch(Exception e){
			logger.info(e.getMessage());
			return null;
		}
		
	}
	
	@RequestMapping("/updateRecord")
	public String updateRecord(String curPage,String phone,String startTime,String endTime,Integer recordState,
			Integer recordClss,HttpServletResponse response,HttpServletRequest request,Long recordId){  
		try{
			
			Map<String, Object> umap = new HashMap<String, Object>();
			umap.put("recordState", 2);
			umap.put("recordId", recordId);
			withDrawRecordService.updateState(umap);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("phone", phone);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("recordState", recordState);
			map.put("recordClss", recordClss);
			if (MethodUtil.isEmpty(curPage)) {
				curPage = "0";// 当前页数
			}
			Integer count=withDrawRecordService.hgetCount(map);
			//分页
			String limit = this.getLimit(Integer.parseInt(curPage), count);
			map.put("limit", limit);
			List<WithDrawRecord> queryList=withDrawRecordService.hgetRecord(map);
			Double totalMoney=withDrawRecordService.hgetMoney(map);
			request.setAttribute("list", queryList);
			request.setAttribute("totalMoney", totalMoney);
			// set分页参数
			this.initPage(request, Integer.parseInt(curPage), null, count);
			return "page/admin/record/record_list";
		}catch(Exception e){
			logger.info(e.getMessage());
			return null;
		}
	}
}





