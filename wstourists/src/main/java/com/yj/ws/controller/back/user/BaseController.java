package com.yj.ws.controller.back.user;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.yj.ws.util.code.PageUtil;
import com.yj.ws.util.tag.Pager;



/**
 * 
 * @内容
 * @FileName BaseController.java
 * @author 
 * @创建日期�?
 * 
 */
public class BaseController extends MultiActionController {
	protected final Logger log = Logger.getLogger(BaseController.class);
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	/**
	 * 获取分页参数
	 * @param curPage 当前页数
	 * @param rowCount 数据总条�?
	 * @return
	 */
	protected String getLimit(Integer curPage, Integer rowCount){
		PageUtil page = new PageUtil();
		page.setPageId(curPage);//当前页数
		page.setRowCount(rowCount);//数据总条�?
		page.splitPageInstance();//生成实例
		return page.getLimit();//返回limit 
	}
	
	
	/**
	 * 初始化分页相关信�?
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @param totalCount
	 */
	protected void initPage(Map<String, Object> map, Integer curPage,
			Integer pageSize, Integer totalCount) {
		if (null == pageSize || pageSize.equals("")) {
			pageSize = 10; // 后续配置获取
		}
		if (pageSize > 20) {
			pageSize = 20;
		}
		Integer totalPage = (totalCount + pageSize - 1) / pageSize;
		if (null == curPage) {
			curPage = 1;
		} else if (curPage > totalPage) {
			curPage = totalPage;
		}
		map.put("startIndex", Pager.getStartIndex(curPage, pageSize));
		map.put("totalPage", totalPage);
		map.put("pageSize", pageSize);
		map.put("totalCount", totalCount);
		map.put("curPage", curPage);
	}
	
	
	/**
	 * 初始化分页相关信�?
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @param totalCount
	 */
	protected void initPage(HttpServletRequest request, Integer curPage,
			Integer pageSize, Integer totalCount) {
		if (null == pageSize || pageSize.equals("")) {
			pageSize = 10; // 后续配置获取
		}
		if (pageSize > 20) {
			pageSize = 20;
		}
		Integer totalPage = (totalCount + pageSize - 1) / pageSize;
		if (null == curPage) {
			curPage = 1;
		} else if (curPage > totalPage) {
			curPage = totalPage;
		}
		request.setAttribute("startIndex", Pager.getStartIndex(curPage, pageSize));
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("curPage", curPage);
	}

	/**
	 * 将相关数据放入model
	 * @param model
	 * @param list
	 * @param map
	 */
	@SuppressWarnings("rawtypes")
	protected void initResult(Model model, List list,
			Map<String, Object> map) {
		model.addAttribute("list", list);
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry m = (Map.Entry) it.next();
			model.addAttribute(m.getKey().toString(), m.getValue());
		}
	}
	
	/**
	 * 将相关数据放入model
	 * @param model
	 * @param list
	 * @param map
	 */
	@SuppressWarnings("rawtypes")
	protected void initResult(HttpServletRequest request, List list,
			Map<String, Object> map) {
		request.setAttribute("list", list);
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry m = (Map.Entry) it.next();
			request.setAttribute(m.getKey().toString(), m.getValue());
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void writeListData(List dataList, int totalCount,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		JSONArray jsonArray = JSONArray.fromObject(dataList);
		JSONObject json = new JSONObject();
		json.put("total", totalCount);
		json.put("data", jsonArray.toString());
		response.getWriter().write(json.toString());
	}
	
	/**
	 * 返回数据到前�?
	 * @param result
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public void writeListData(List dataList, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		JSONArray jsonArray = JSONArray.fromObject(dataList);
		
		response.getWriter().write(jsonArray.toString());
	}
	/**
	 * 请求分发，每个请求都会进行初始化操作
	 * 用于进行参数的设�?
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	protected void inItParam(HttpServletRequest request,HttpServletResponse response){
		this.request = request;  
        this.response = response;  
        this.session = request.getSession(); 
	}
	/**
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected Map<String, String> getRequestParamMap(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String, String> remap = new HashMap<String, String>();
		try {
			Enumeration eretion = request.getParameterNames();
			while (eretion.hasMoreElements()) {
				String elem = (String) eretion.nextElement();
				String result = request.getParameter(elem);
				remap.put(elem, result);
			}
		} catch (Exception e) {
			// TODO: handle exception
			remap = new HashMap<String, String>();
			log.error("getRequestParamMap have exception:" + e);
		}
		return remap;
	}
	public void writeResult(boolean success, String message,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		JSONObject json = new JSONObject();
		json.put("success", success);
		json.put("msg", message);
		response.getWriter().write(json.toString());
	}	
	
}
