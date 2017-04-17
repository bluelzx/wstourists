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
<script type="text/javascript" src="${basePath}baseJs/users/users.js"></script>
 <%@ include file="/common/metaandlink.jsp"%>
</head>
<body>
	<table class="TableList" width="100%" style="font-family:'Microsoft YaHei',Arial,Helvetica,sans-serif,'宋体'">
	<thead>
		<tr class="TableHeader"> 
			<th>用户Id</th>
			<th>用户名</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="item" varStatus="xh">
			<c:choose>
				<c:when test="${xh.count % 2 == 0}">
					<c:set var="trClz" value="tab_even" />
				</c:when>
				<c:otherwise>
					<c:set var="trClz" value="" />
				</c:otherwise>
			</c:choose>
			<tr class="${trClz}">
				<td>${item.userId }</td>
				<td>${item.userName }</td>
				
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

