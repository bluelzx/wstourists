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
</head>
<body>

	<table class="TableList" width="100%" style="font-family:'Microsoft YaHei',Arial,Helvetica,sans-serif,'宋体'">
	<thead>
		<tr class="TableHeader"> 
			<th>用户Id</th>
			<th>用户电话</th>
			
			<th>姓名</th>
			<th>加粉人数</th>
			<th>充值金额</th>
			<th>总金额</th>
			<th>冻结资金</th>
			<th>VIP等级</th>
			<th>VIP开始时间</th>
			<th>VIP结束时间</th>
			<th>提现总额</th>
			<th>赚取资金</th>
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="item" varStatus="xh">
			<tr class="${trClz}">
				<td>${item.userId }</td>
				<td>${item.phoneNumber}</td>
				
				<td>
					${item.realName}
				</td>
				<td>${item.fansNumber}</td>
				<td><fmt:formatNumber value="${item.playTotal}" pattern="#,##0.00#"/></td>
				<td><fmt:formatNumber value="${item.totalMoney}" pattern="#,##0.00#"/></td>
				<td><fmt:formatNumber value="${item.frozenFund}" pattern="#,##0.00#"/></td>
				<td>
					<c:if test="${item.vipLevel==1}">
						试用
					</c:if>
					<c:if test="${item.vipLevel==2}">
						普通
					</c:if>
					<c:if test="${item.vipLevel==3}">
						超级 
					</c:if>
				</td>
				<td><fmt:formatDate value="${item.vipStart }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><fmt:formatDate value="${item.vipEnd }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><fmt:formatNumber value="${item.withdrawTotal}" pattern="#,##0.00#"/></td>
				<td><fmt:formatNumber value="${item.earnMoney}" pattern="#,##0.00#"/></td>
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

