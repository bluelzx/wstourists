package com.yj.ws.util.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author yanjun
 * 
 * @类说明：页面分页tag实体类
 */
public class Pager extends TagSupport {

	private static final long serialVersionUID = -2613705016796991725L;
	private Integer curPage;
	private Integer totalPage;
	private Integer pageSize = 10;// 后续系统参数配置
	private Integer totalCount = 0;

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	/*public int doStartTag() throws JspException {

		JspWriter out = pageContext.getOut();
		if(totalPage <= 1){
			return super.doStartTag();
		}
		if(curPage <= 0){
			curPage = 1;
		}

		try {
		out.print("<div id=\"fliplista\" class=\"flip\"><div class=\"pageDivClass\">");
		out.print("第"+curPage+"页/共"+totalPage+"页&nbsp;");
		if(curPage > 1){
			out.print("<a href=\"javascript:goPage(1);\">首页</a>&nbsp;");
			out.print("<a href=\"javascript:goPage(" + (curPage - 1) + ")\">上一页</a>&nbsp;");
		}else{
			out.print("<a href=\"javascript:void(0);\">首页</a>&nbsp;");
			out.print("<a href=\"javascript:void(0);\">上一页</a>&nbsp;");
		}
		if(totalPage > 6){
			for(int i = 1; i <= 3; i ++){
				if(i == curPage){
					out.print("<a class=\"curPageColor\" href=\"javascript:goPage("+i+")\">"+i+"</a>&nbsp;");
				}else{
					out.print("<a href=\"javascript:goPage("+i+")\">"+i+"</a>&nbsp;");
				}
			}
			out.print("...");
			for(int i = totalPage; i >= totalPage - 3; i ++){
				if(i == curPage){
					out.print("<a class=\"curPageColor\" href=\"javascript:goPage("+i+")\">"+i+"</a>&nbsp;");
				}else{
					out.print("<a href=\"javascript:goPage("+i+")\">"+i+"</a>&nbsp;");
				}
			}
		}else{
			for(int i = 1; i <= totalPage; i ++){
				if(i == curPage){
					out.print("<a class=\"curPageColor\" href=\"javascript:goPage("+i+")\">"+i+"</a>&nbsp;");
				}else{
					out.print("<a href=\"javascript:goPage("+i+")\">"+i+"</a>&nbsp;");
				}
			}
		}
		if(curPage < totalPage){
			out.print("<a href=\"javascript:goPage(" + (curPage + 1) + ")\">下一页</a>&nbsp;");
			out.print("<a href=\"javascript:goPage("+totalPage+")\">末页</a>");
		}else{
			out.print("<a href=\"javascript:void(0);\">下一页</a>&nbsp;");
			out.print("<a href=\"javascript:void(0);\">末页</a>");
		}
		out.print("</div></div>");
	} catch (IOException e) {}
	
	return super.doStartTag();

	}*/
	
	
	
	/*public int doStartTag() throws JspException {

		JspWriter out = pageContext.getOut();
		if(totalPage <= 1){
			return super.doStartTag();
		}
		if(curPage <= 0){
			curPage = 1;
		}

		try {
		out.print(" <div class=\"pagination-box\"><ul class=\"clearfix\">");
//		out.print("第"+curPage+"页/共"+totalPage+"页&nbsp;");
		if(curPage > 1){
			out.print("<li> <a class=\"page_prev\" href=\"javascript:goPage(1);\">首页</a> </li> &nbsp;");
			out.print("<li> <a class=\"page_prev\" href=\"javascript:goPage(" + (curPage - 1) + ")\"></a> </li>&nbsp;");
		}else{
			out.print("<li><a class=\"page_prev\" href=\"javascript:void(0);\">首页</a></li>&nbsp;");
			out.print("<li><a class=\"page_prev\" href=\"javascript:void(0);\"></a></li>&nbsp;");
		}
		
		
		if(totalPage > 6){
			for(int i = 1; i <= 3; i ++){
				if(i == curPage){
					out.print("<li><a class=\"current\" href=\"javascript:goPage("+i+")\">"+i+"</a></li>&nbsp;");
				}else{
					out.print("<li><a href=\"javascript:goPage("+i+")\">"+i+"</a></li>&nbsp;");
				}
			}
			out.print("<li><span class=\"ellipse\">...</span></li>");
			for(int i = totalPage - 2; i <= totalPage ; i++){
				if(i == curPage){
					out.print("<li><a class=\"current\" href=\"javascript:goPage("+i+")\">"+i+"</a></li>&nbsp;");
				}else{
					out.print("<li><a href=\"javascript:goPage("+i+")\">"+i+"</a></li>&nbsp;");
				}
			}
		}else{
			for(int i = 1; i <= totalPage; i ++){
				if(i == curPage){
					out.print("<li><a class=\"current\" href=\"javascript:goPage("+i+")\">"+i+"</a></li>&nbsp;");
				}else{
					out.print("<li><a href=\"javascript:goPage("+i+")\">"+i+"</a></li>&nbsp;");
				}
			}
		}
		
		
		if(curPage < totalPage){
			out.print("<li><a class=\"page_next\" href=\"javascript:goPage(" + (curPage + 1) + ")\"></a></li>&nbsp;");
			out.print("<li><a href=\"javascript:goPage("+totalPage+")\">末页</a></li>");
		}else{
			out.print("<li><a class=\"page_next\" href=\"javascript:void(0);\"></a>&nbsp;");
			out.print("<li><a href=\"javascript:void(0);\">末页</a>");
		}
		out.print("</ul></div>");
	} catch (IOException e) {}
	
	return super.doStartTag();

	}*/
	
	
	public int doStartTag() throws JspException {

		JspWriter out = pageContext.getOut();
		if(totalPage <= 1){
			return super.doStartTag();
		}
		if(curPage <= 0){
			curPage = 1;
		}

		 StringBuffer fenye = new StringBuffer();
		try {
			fenye.append(" <div class=\"pagination-box\"><ul class=\"clearfix\">");
		// 前一页
		if(curPage > 1){
			fenye.append("<li> <a class=\"page_prev\" href=\"javascript:goPage(" + (curPage - 1) + ") ;\"></a> </li>");
		}else{
			fenye.append("<li><a class=\"page_prev\" href=\"javascript:void(0);\"></a></li>");
		}
		
		
		if(totalPage <11){
			for(int i = 1; i <= totalPage  ; i++){
				if(i == curPage){
					fenye.append("<li><a class=\"current\" href=\"javascript:goPage("+i+");\">"+i+"</a></li>");
				}else{
					fenye.append("<li><a href=\"javascript:goPage("+i+");\">"+i+"</a></li>");
				}
			}
		}else{
			if(curPage <7 ){
				for(int i = 1; i <= (curPage+2<totalPage?curPage+2:totalPage)  ; i++){
					if(i == curPage){
						fenye.append("<li><a class=\"current\" href=\"javascript:goPage("+i+");\">"+i+"</a></li>");
					}else{
						fenye.append("<li><a href=\"javascript:goPage("+i+");\">"+i+"</a></li>");
					}
				}
				if(curPage+2 <= totalPage){
					fenye.append("<li><span class=\"ellipse\">...</span></li>");
				}
				for(int i = totalPage-1; i <= totalPage  ; i++){
					if(i == curPage){
						fenye.append("<li><a class=\"current\" href=\"javascript:goPage("+i+");\">"+i+"</a></li>");
					}else{
						fenye.append("<li><a href=\"javascript:goPage("+i+");\">"+i+"</a></li>");
					}
				}
			}else if(curPage>=7){
				for(int i = 1; i <= 2  ; i++){
					fenye.append("<li><a href=\"javascript:goPage("+i+");\">"+i+"</a></li>");
				}
				fenye.append("<li><span class=\"ellipse\">...</span></li>");
				if(curPage +4 >= totalPage){
					for(int i = (totalPage-6<=curPage?totalPage-6:curPage-2); i <= totalPage   ; i++){
						if(i == curPage){
							fenye.append("<li><a class=\"current\" href=\"javascript:goPage("+i+");\">"+i+"</a></li>");
						}else{
							fenye.append("<li><a href=\"javascript:goPage("+i+");\">"+i+"</a></li>");
						}
					}
				}else{
					for(int i =curPage-2; i <= curPage +2; i++){
						if(i == curPage){
							fenye.append("<li><a class=\"current\" href=\"javascript:goPage("+i+");\">"+i+"</a></li>");
						}else{
							fenye.append("<li><a href=\"javascript:goPage("+i+");\">"+i+"</a></li>");
						}
					}
					if(curPage +4 < totalPage-1){
						fenye.append("<li><span class=\"ellipse\">...</span></li>");
					}
					for(int i =totalPage-1; i <= totalPage; i++){
						if(i == curPage){
							fenye.append("<li><a class=\"current\" href=\"javascript:goPage("+i+");\">"+i+"</a></li>");
						}else{
							fenye.append("<li><a href=\"javascript:goPage("+i+");\">"+i+"</a></li>");
						}
					}
				}
			}
		}
		
		
		
		//后一页
		if(curPage < totalPage){
			fenye.append("<li><a class=\"page_next\" href=\"javascript:goPage(" + (curPage + 1) + ")\"></a></li>");
		}else{
			fenye.append("<li><a class=\"page_next\" href=\"javascript:void(0);\"></a></li>");
		}
		fenye.append("</ul></div>");
//		System.out.println(fenye.toString());
		out.print(fenye.toString());
	} catch (IOException e) {}
	
	return super.doStartTag();

	}

	public static Integer getStartIndex(Integer pageNum, Integer pageSize) {
		Integer res = 0;
		if (pageNum > 0) {
			res = (pageNum - 1) * pageSize;
		}
		return res;
	}

}
