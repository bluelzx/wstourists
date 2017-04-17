<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>后台管理系统</title>
    <%@ include file="/common/metaandlink.jsp"%>
    <script type="text/javascript" src="${basePath}pjJs/jquery-2.1.3.min.js"></script>	
    <script type="text/javascript" src="${basePath}baseJs/users/users.js"></script>
</head>
<body id="body">	
<input type="hidden" id="path" value="${path}"/>
<form id="searchForm" action="${path}/sysUser/showUser"  method="post"> 
<div id="hbNav">
     
    </div>
    
        <div id="divContent">
                <div id="search">                
        <table cellspacing="0" cellpadding="0" border="0" width="100%" style="text-align: right;">
           <!--  <tbody>
              <tr >
              <td><strong>申请号：</strong>   
              <input type="text"  class="inp-text inp-w140" />                 
                </td>
                <td><strong>借款人：</strong>
                    <input type="text"  class="inp-text inp-w140" /> 
                   </td>
               <td><strong>证件号码：</strong>
                   <input type="text"  class="inp-text inp-w140" /> 
                    </td>
              <td><strong>信贷顾问：</strong>
                                    <input type="text"  class="inp-text inp-w140" />  
                  </td>
                <td><strong>申请日期：</strong>从    <input type="text"  class="inp-text inp-w140" /> 
                至    <input type="text"  class="inp-text inp-w140" /> 
                    </td>
                 </tr>
                 <tr>
                 <td><strong>贷款状态：</strong>
                 <select class="inp-text inp-w140" >
                 	<option value="1" class="inp-text inp-w140" >贷款申请</option>
                 	<option value="2" class="inp-text inp-w140">贷款审批</option>
                 </select>
                     
                </td>
                 <td>
                </td>
                <td>
                </td>
                 <td><input id="btSearch" onclick="searchform();" type="button" value=" 查 询 " class="sele btn"/>
                    <input id="tbClear" onclick="ResetControl();" type="button" value=" 重 置 " class="sele btn"/>
                </td>
                <td>
                
                </td>
                
                </tr>

            </tbody> -->
          </table>
        </div>
    </div>
    </form>
    <div align="center">
	<form id="searchForm" action="${path}/master/workhuilOaManager/lookOwerOa.action"  method="post">    
	<table width="100%" class="TableList">
		<tr height="10px;" style="font-family:'Microsoft YaHei',Arial,Helvetica,sans-serif,'宋体'">
			<td></td>
		</tr>   
	</table>
  </form>
  <div id="tabaleDatas">
  </div>
	<%-- <table class="TableList" width="100%" style="font-family:'Microsoft YaHei',Arial,Helvetica,sans-serif,'宋体'">
			<tr class="TableHeader"> 
				<th>用户名</th>
				<th>员工编号</th>
				<th>所在部门</th>
				<th>手机号</th>
				<th>员工住址</th>
				<th>性别</th>
				<th>身份证号</th>
				<th>操作</th>
			</tr> 
		 	<c:choose>
			<c:when test="${empty pm.datas}">
				<tr>
					<td colspan="6"></td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="bean" items="${pm.datas}">
					<tr>
						<td>${bean.userName }</td>
						<td>${bean.userNO}</td>
						<td>${bean.userIsDept}</td>
						<td>${bean.userMob }</td>
						<td>${bean.userAddr}</td>
						<td>${bean.userSex}</td>
						<td>${bean.userIDCard }</td>
						<td>
						<a href="javascript:void(0)" onclick="updateUser(${bean.userID})">编辑</a>
						<a href="javascript:void(0)" onclick="delUser(${bean.userID})">删除</a></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>   --%>
</div>
</body>


