<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="page" uri="/WEB-INF/page-tag.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="${basePath}static/boot/assets/css/common.css" rel="stylesheet" type="text/css" />
 <%@ include file="/common/metaandlink.jsp"%>
</head>
<body>
    <font color="red">当前状态下总金额为：${totalMoney}</font>
	<table class="TableList" width="100%" style="font-family:'Microsoft YaHei',Arial,Helvetica,sans-serif,'宋体'">
	<thead>
		<tr class="TableHeader"> 
			<th>用户Id</th>
			<th>用户电话</th>
			
			<th>资金类型</th>
			<th>资金金额</th>
			<th>申请时间</th>
			<th>处理时间</th>
			<th>资金状态</th>
			<th>提现类型</th>
			<th>提现账号</th>
			<th>VIP等级</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="item" varStatus="xh">
			<tr class="${trClz}">
				<td>${item.userId }</td>
				<td>${item.phone}</td>
				
				<td>
					<c:if test="${item.recordClss==1}">
						提现
					</c:if>
					<c:if test="${item.recordClss==2}">
						签到获取
					</c:if>
					<c:if test="${item.recordClss==3}">
						vip充值
					</c:if>
				</td>
				<td><fmt:formatNumber value="${item.recordMomey}" pattern="#,##0.00#"/></td>
				<td><fmt:formatDate value="${item.recordTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><fmt:formatDate value="${item.handleTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>
					<c:if test="${item.recordState==1}">
						申请
					</c:if>
					<c:if test="${item.recordState==2}">
						处理
					</c:if>
					<c:if test="${item.recordState==3}">
						未返回  
					</c:if>
					<c:if test="${item.recordState==4}">
						失败 
					</c:if>
					<c:if test="${item.recordState==5}">
						取消
					</c:if>
				</td>
				<td>
					<c:if test="${item.accountClass==1}">
						支付宝
					</c:if>
					<c:if test="${item.accountClass==2}">
						微信
					</c:if>
				</td>
				<td>${item.cardNumber}</td>
				<td>
					<c:if test="${item.caclss==1}">
						试用
					</c:if>
					<c:if test="${item.caclss==2}">
						普通
					</c:if>
					<c:if test="${item.caclss==3}">
						超级 
					</c:if>
			    </td>
			    <td>
			       <c:if test="${item.recordClss==1}">
			       		<c:if test="${item.recordState==1}">
			         		<a onclick="changeClss(${item.recordId})" href="#">审核</a>
			         	</c:if>
			         	<c:if test="${item.recordState==2}">
						处理
						</c:if>
						<c:if test="${item.recordState==3}">
						未返回  
						</c:if>
						<c:if test="${item.recordState==4}">
						失败 
						</c:if>
						<c:if test="${item.recordState==5}">
						取消
						</c:if>
			       </c:if>
			    </td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<input type="hidden" id="curPage" name="curPage" value="${pageUtil.pageId}">
<page:createPager pageSize="${pageSize}" totalPage="${totalPage}"
		totalCount="${totalCount}" curPage="${curPage}" />

</body>
</html>

<!-- <fmt:formatDate value="${bean.investTime }" pattern="yyyy-MM-dd HH:mm:ss" /> -->
<!--  <fmt:formatNumber value="${packets.redHaveAmount}" pattern="#,##0.00#"/>
 -->

