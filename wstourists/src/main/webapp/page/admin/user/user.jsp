<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>后台管理系统</title>
    <%@ include file="/common/metaandlink.jsp"%>
    <script type="text/javascript" src="${basePath}pjJs/jquery-2.1.3.min.js"></script>	
   <script type="text/javascript" src="${basePath}baseJs/user/user.js"></script>
</head>
<body id="body">	
<input type="hidden" id="path" value="${path}"/>
<div id="hbNav">
     
    </div>
    
        <div id="divContent">
                <div id="search">                
        <table cellspacing="0" cellpadding="0" border="0" width="100%" style="text-align: left;">
            <tbody>
              <tr >
              <td><strong>VIP等级：</strong>   
              	<select id="vipLevel">
              		<option value="">--</option>
              		<option value="1">试用VIP</option>
              		<option value="2">普通VIP</option>
              		<option value="3">超级VIP</option>
              	</select>
              </td>
               
               <td><strong>用户号：</strong>
                   <input type="text"  class="inp-text inp-w140" id="phone"/> 
                    </td>
                 </tr>
                 <tr>
                
                 <td><strong>申请日期：</strong>从    <input type="text"  class="inp-text inp-w140" id="startTime" readonly="readonly" onfocus="WdatePicker({isShowWeek:true})"/> 
                至    <input type="text"  class="inp-text inp-w140" id="endTime" readonly="readonly" onfocus="WdatePicker({isShowWeek:true})"/> 
                    </td>
                <td>
                </td>
                 <td><input id="btSearch" onclick="searchform();" type="button" value=" 查 询 " />
                    <input id="tbClear" onclick="ResetControl();" type="button" value=" 重 置 " />
                </td>
            
                
                </tr>

            </tbody> 
          </table>
        </div>
    </div>
 
	
  <div id="tabaleDatas">
  </div>
	
</div>
</body>


