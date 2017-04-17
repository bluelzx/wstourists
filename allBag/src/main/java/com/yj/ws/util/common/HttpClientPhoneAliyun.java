package com.yj.ws.util.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;









//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.NameValuePair;
//import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.commons.httpclient.params.HttpMethodParams;
//import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.log4j.Logger;

import com.google.gson.Gson;



/**
 * 
 * 验证码相关
 *
 */
public class HttpClientPhoneAliyun {
	public static final Integer NUM = 6;
	private static final Logger log = Logger.getLogger(HttpClientPhone.class);
	/*
	 * 消息发送
	 */
	public static boolean sendSms(String msg,String phone) {
		try {
			if(phone==null || phone.length()==0){
				return false;
			}
		/*	HttpClient httpclient=new HttpClient();
			String url="https://sms.aliyuncs.com/?Action=SingleSendSms";
			String SignName="个人";
			String TemplateCode="SMS_37820060";
			String RecNum="13058127893";
			Map<String, String> map=new HashMap<String, String>();
			map.put("phone", "sda");
			map.put("msgcode", "sda");
			String ParamString=new Gson().toJson(map);
			//String[] ParamString=new {"no":"123456"};
			PostMethod postmethod=new PostMethod(url);
			NameValuePair[] date = { new NameValuePair("SignName",SignName),
				new NameValuePair("SignName",SignName),
				new NameValuePair("TemplateCode",TemplateCode),
				new NameValuePair("RecNum",RecNum),
				new NameValuePair("ParamString",ParamString)};
			postmethod.setRequestBody(date);
			int responseCode=httpclient.executeMethod(postmethod);
			if(responseCode == HttpURLConnection.HTTP_OK){
				System.out.println("============");
				return true;
			}else {
				
				return false;
			}*/
			return true;
		} catch (Exception e) {
			log.error(e);
			return false;
		}
	}
	
	//产生四个随机数
	public static char[] getRandom()
	{
		//源始的内容
		String str = "0123456789";
		//存放随机数
		char[] rands = new char[NUM];
		Random  random   = new Random();
		for (int i=0;i<NUM;i++)
		{
			int index = random.nextInt(10);
			rands[i] = str.charAt(index);
		}
		return rands;
	}
	
	public static void main(String[] args) {
		String phone="13058127893";
		//list.add("18676576551");
		HttpClientPhoneAliyun.sendSms("您好",phone);
	}
}
