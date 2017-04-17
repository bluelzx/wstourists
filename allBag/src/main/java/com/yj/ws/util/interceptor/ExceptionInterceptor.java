package com.yj.ws.util.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.yj.ws.util.common.CommonConstant;
import com.yj.ws.util.common.DateUtil;
import com.yj.ws.util.common.MethodUtil;




/**
 * author:yanjun
 * 
 * @类说明：全局异常拦截
 */
public class ExceptionInterceptor implements HandlerExceptionResolver {
	private final static Logger log = Logger.getLogger(ExceptionInterceptor.class);
	// 系统日志默认DAO扩展信息
	private String expandMessage = CommonConstant.SYSTEM_LOG_DAO_MESSAGE;

	@SuppressWarnings("static-access")
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception) {

		Map<String, Object> map = new HashMap<String, Object>();
		if (exception != null) {
			map.put("msg", exception.getMessage());
//			String msg = exception.toString();
			/*
			 * 记录请求参数
			 */
			logParam(request, handler, exception);
			/*
			 * 记录异常到错误日志文件
			 */
			logLogFile(exception);

			response.setStatus(response.SC_SERVICE_UNAVAILABLE);
		}
		
		String requestType = request.getHeader("X-Requested-With");
		
		if("XMLHttpRequest".equals(requestType)){
			MethodUtil.toJsonMsg(response, 551, "程序异常，请联系技术人员！");
		}else{
			return new ModelAndView("/error/cy500");
		}
		return null;
	}

	/**
	 * 记录参数
	 * 
	 * @param request
	 * @param handler
	 * @param ex
	 */
	@SuppressWarnings("unchecked")
	private void logParam(HttpServletRequest request, Object handler,
			Exception ex) {
		StringBuffer msg = new StringBuffer();
		msg.append("[uri＝").append(request.getRequestURI()).append("]");
		Enumeration<String> enumer = request.getParameterNames();
		while (enumer.hasMoreElements()) {
			String name = (String) enumer.nextElement();
			String[] values = request.getParameterValues(name);
			msg.append("[").append(name).append("=");
			if (values != null) {
				int i = 0;
				for (String value : values) {
					i++;
					msg.append(value);
					if (i < values.length) {
						msg.append("｜");
					}
				}
			}
			msg.append("]");
		}
		log.error(msg, ex);
	}

	/**
	 * 向日志文件写入异常日志。
	 * 
	 * @throws Exception
	 */
	private void logLogFile(Exception exception) {
		StackTraceElement[] elements = exception.getStackTrace();
		for (StackTraceElement element : elements) {
			if (element.getClassName().indexOf(
					CommonConstant.SYSTEM_LOG_INTERCEPTOR_PACKAGE) < 0)
				continue;
			String fileName = element.getFileName();
			StringBuffer sb = new StringBuffer();
			sb.append(DateUtil.dateToString());
			sb.append("[");
			sb.append(this.extendErrorType(fileName));
			sb.append("]");
			sb.append(CommonConstant.SYSTEM_LOG_CLASS_POSITION);
			sb.append(element.getClassName());
			sb.append("；");
			sb.append(CommonConstant.SYSTEM_LOG_METHOD_POSITION);
			sb.append(element.getMethodName());
			sb.append("； ");
			sb.append(CommonConstant.SYSTEM_LOG_LINENUMBER_POSITION);
			sb.append(element.getLineNumber());
			sb.append("；");
			sb.append(CommonConstant.SYSTEM_LOG_ERRORTYPE_POSITION);
			sb.append(exception.getMessage());
			log.error(sb.toString());
		}
	}

	/**
	 * 返回指定JAVA文件发生的异常类型
	 * 
	 * @param fileName
	 *            -发生异常的JAVA文件
	 * @return String -系统日志文件定为的异常描述信息
	 */
	private String extendErrorType(String fileName) {
		String errorType = this.expandMessage;
		if (fileName.indexOf(CommonConstant.SYSTEM_LOG_USER_DAO) > 0) {
			errorType = CommonConstant.SYSTEM_LOG_DAO_MESSAGE;
		} else if (fileName.indexOf(CommonConstant.SYSTEM_LOG_USER_SERVICE) > 0) {
			errorType = CommonConstant.SYSTEM_LOG_SERVICE_MESSAGE;
		} else if (fileName.indexOf(CommonConstant.SYSTEM_LOG_USER_CONTROLLER) > 0) {
			errorType = CommonConstant.SYSTEM_LOG_CONTROLLER_MESSAGE;
		} else {
			errorType = CommonConstant.SYSTEM_LOG_OTHER_MESSAGE;
		}
		return errorType;
	}

}
