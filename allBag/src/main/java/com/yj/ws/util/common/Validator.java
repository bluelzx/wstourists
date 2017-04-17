package com.yj.ws.util.common;

import java.util.regex.Pattern;


public class Validator {
	/**
	 * 验证是否为合法邮箱信息
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		String emailPattern = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
		return Pattern.matches(emailPattern, email);
	}
	
	/**
	 * 验证是否为标准用户名
	 * @param str
	 * @return
	 */
	public static boolean isNormalName(String str){
		String namePattern = "[\u4E00-\u9FA5A-Za-z]+";
		return Pattern.matches(namePattern, str);
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isValideUserName(String str) {
		String p="^[_a-zA-Z][_a-zA-Z\\d]{4,}";
		return Pattern.matches(p, str);
	}
	/**
	 * 验证密码
	 * @param str
	 * @return
	 */
	public static boolean isValidePassword(String str) {
		String p=".{6,}";
		return Pattern.matches(p, str);
	}
	
	
	/**
	 * 手机号码 移动：134[0-8],135,136,137,138,139,150,151,157,158,159,182,187,188、178、147,170,183
	 * 联通：130,131,132,147,152,155,156,185,186 电信：133,1349,153,180,189，176,145
	 */
	public static final String mobilePattern = "^1(3[0-9]|4[57]|5[0-35-9]|7[08]|8[0235-9]|47)\\d{8}$";
	/**
	 * 10 * 中国移动：China Mobile 11 *
	 * 134[0-8],135,136,137,138,139,150,151,157,158,159,182,187,188 12、178、147,170,183
	 */
	public static final String cmPattern = "^1(34[0-8]|(3[5-9]|47|5[017-9]|70|77|8[2378])\\d)\\d{7}$";
	/**
	 * 15 * 中国联通：China Unicom 16 * 130,131,132,152,155,156,185,186 17
	 */
	public static final String cuPattern = "^1(3[0-2]|5[256]|8[56])\\d{8}$";
	/**
	 * 20 * 中国电信：China Telecom 21 * 133,1349,153,180,181,189 22,177
	 */
	public static final String ctPattern = "^1((33|53|8[019])[0-9]|349)\\d{7}$";
	/**
	 * 25 * 大陆地区固话及小灵通 26 * 区号：010,020,021,022,023,024,025,027,028,029 27 *
	 * 号码：七位或八位 28
	 */

	public static boolean isMobileNum(String mobileNum) {
		
		if (Pattern.matches(mobilePattern, mobileNum)
				|| Pattern.matches(cmPattern, mobileNum)
				|| Pattern.matches(cuPattern, mobileNum)
				|| Pattern.matches(ctPattern, mobileNum)) {
			return true;
		}
		return false;
	
	}
	
	
	public enum MobileBrand{
		CHINA_MOBILE,CHINA_TELECOM,CHINA_UNICOM,UNKNOWN
	}
	
	public static MobileBrand detectBrand(String mobileNum){
		if(Pattern.matches(cmPattern, mobileNum)){
			return MobileBrand.CHINA_MOBILE;
		}else if(Pattern.matches(cuPattern, mobileNum)){
			return MobileBrand.CHINA_UNICOM;
		}else if(Pattern.matches(ctPattern, mobileNum)){
			return MobileBrand.CHINA_TELECOM;
		}else{
			return MobileBrand.UNKNOWN;
		}
	}
	
	/**
	 * 验证字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str){
		return str!=null && !str.trim().isEmpty() ? false:true;
	}
	
	/* 
	public static void main(String[] args){
		System.out.println(Validator.isMobileNum("18319491639"));
	}*/
}
