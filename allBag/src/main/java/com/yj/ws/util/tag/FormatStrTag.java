package com.yj.ws.util.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author 作者 严俊
 * @version  类说明
 */
public class FormatStrTag extends TagSupport {
	private String value;
	private int len;

	public int doStartTag() throws JspException {
		String strs = getSplitStr(value, len);
		try {
			JspWriter out = pageContext.getOut();
			out.write(strs);
//			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}
	public static String getSplitStr(String str,int len){
		int slen =0;
		if(str != null){
			slen = str.length();
			 if(slen <= len){
				 return str;
			 }
			String sstr = str.substring(0,len)+"...";
			return sstr;
		}
		return null;
	}
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}
}
