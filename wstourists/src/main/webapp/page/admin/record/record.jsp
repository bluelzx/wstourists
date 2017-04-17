<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>后台管理系统</title>
    <%@ include file="/common/metaandlink.jsp"%>
    <script type="text/javascript" src="${basePath}pjJs/jquery-2.1.3.min.js"></script>	
   <script type="text/javascript" src="${basePath}baseJs/record/record.js"></script>
</head>
<body id="body">	
<input type="hidden" id="path" value="${path}"/>
<form id="searchForm" action="${path}/sysUser/showUser"  method="post"> 
<div id="hbNav">
     
    </div>
    
        <div id="divContent">
                <div id="search">                
        <table cellspacing="0" cellpadding="0" border="0" width="100%" style="text-align: left;">
            <tbody>
              <tr >
              <td><strong>资金类型：</strong>   
              <select id="recordClss" width="1">
              	<option value="" class="inp-text inp-w140">--</option>
              	<option value="1" class="inp-text inp-w140">提现</option>
              	<option value="2" class="inp-text inp-w140">签到获取</option>
              	<option value="3" class="inp-text inp-w140">vip充值</option>
              </select>               
              </td>
                <td><strong>资金状态：</strong>
                     <select id="recordState">
                     	<option value="" class="inp-text inp-w140">--</option>
              			<option value="1" class="inp-text inp-w140">申请</option>
              			<option value="2" class="inp-text inp-w140">处理</option>
              			<option value="3" class="inp-text inp-w140">未返回</option>
              			<option value="4" class="inp-text inp-w140">失败</option>
              			<option value="5" class="inp-text inp-w140">取消 </option>
              		</select>   
                </td>
               <td><strong>资金记录人：</strong>
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
    </form>
 
	
  <div id="tabaleDatas">
  </div>
	
</div>
</body>


