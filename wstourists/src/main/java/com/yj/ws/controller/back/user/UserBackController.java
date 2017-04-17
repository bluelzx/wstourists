package com.yj.ws.controller.back.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yj.ws.pojo.User;
import com.yj.ws.pojo.WithDrawRecord;
import com.yj.ws.service.UserService;
import com.yj.ws.util.common.MethodUtil;

@SuppressWarnings("all")
@RequestMapping("admin/userBack")
@Controller
public class UserBackController extends BaseController{
	private static final Logger log = Logger.getLogger(UserBackController.class);
	@Autowired
	private UserService userService;
	@RequestMapping("/getUser")
	public String updateRecord(String curPage,String phone,String startTime,String endTime,Integer vipLevel,
			HttpServletResponse response,HttpServletRequest request){
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("phone", phone);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("vipLevel", vipLevel);
			if (MethodUtil.isEmpty(curPage)) {
				curPage = "0";// 当前页数
			}
			int count =userService.selectUserCount(map);
			String limit = this.getLimit(Integer.parseInt(curPage), count);
			map.put("limit", limit);
			List<User> userList=userService.selectUserBack(map);
			request.setAttribute("list", userList);
			// set分页参数
			this.initPage(request, Integer.parseInt(curPage), null, count);
			return "page/admin/user/user_list";
		}catch(Exception e){
			log.info(e.getMessage());
			return null;
		}
	}
}
