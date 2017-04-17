<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<%@ include file="/common/metaandlink.jsp"%>
<head>
    <title>后台管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${basePath}static/css/mains//dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="${basePath}static/css/mains/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="${basePath}static/css/mains/main-min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${basePath}static/js/jquery-2.0.3.min.js"></script>
	<script type="text/javascript" src="${basePath}static/js/mains/bui-min.js"></script>
	<script type="text/javascript" src="${basePath}static/js/mains/main-min.js"></script>
	<script type="text/javascript" src="${basePath}static/js/mains/config-min.js"></script>
</head>
<body>

<div class="header">

    <div class="dl-title">
        <!--<img src="/chinapost/Public/assets/img/top.png">-->
    </div>

    <div class="dl-log">欢迎您，<span class="dl-log-user">root</span><a href="/chinapost/index.php?m=Public&a=logout" title="退出系统" class="dl-log-quit">[退出]</a>
    </div>
</div>
<div class="content">
    <div class="dl-main-nav">
        <div class="dl-inform"><div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div></div>
        <ul id="J_Nav"  class="nav-list ks-clear">
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-home">系统管理</div></li>		
	    <li class="nav-item dl-selected"><div class="nav-item-inner nav-order">业务管理</div></li>
        </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">

    </ul>
</div>

<script>
    BUI.use('common/main',function(){
        var config = [{id:'1',menu:[{text:'系统管理',items:[{id:'12',text:'机构管理',href:'Node/index.html'},{id:'3',text:'角色管理',href:'Role/index.html'},{id:'4',text:'用户管理',href:'User/index.html'},{id:'6',text:'菜单管理',href:'Menu/index.html'}]}]},{id:'7',homePage : '9',menu:[{text:'业务管理',items:[{id:'9',text:'查询业务',href:'Node/index.html'}]}]}];
        new PageUtil.MainPage({
            modulesConfig : config
        });
    });
</script>
<div style="text-align:center;">
<p>来源：<a href="http://www.mycodes.net/" target="_blank">源码之家</a></p>
</div>
</body>
</html>



<!-- <div class="main">
  <table width="100%">
    <tr>
      <td class="in-left-td" id="divMenu">
          <div class="in-left">
          <div class="in-user pad10 mar-b10">
              <img width="45px" src="" alt="" />
            <p class="in-main-p"><em style="font-size:12px">姓名：${users.name}</em></p>
        
          <div class="clr"></div>
          </div>
          <div class="nav-list">
           <UL id="menustr">	
           <c:if test="${!empty glist}">
           	<c:forEach items="${glist}" var="gl">
				<LI class=li-bg>
					<DIV class="nav-li li-ico" ><A href="javascript:void(0);">${gl.menuName}</A></DIV>
					<UL class="none nav-li-ul">
						<c:if test="${!empty clisy}">
						<c:forEach items="${clisy}" var="cl">
							<c:if test="${cl.partId==gl.menuId}">
								<LI>
									<A href="${basePath}${cl.menuURL}?menuId=${cl.menuId}" target="mainFrame" >${cl.menuName}</A>
								</LI>
							</c:if>
						</c:forEach>
						</c:if>
					</UL>

				</LI>
			 </c:forEach>
			 </c:if>
			</UL>
          </div>
        </div></td>
      <td class="in-center-td"><div class="in-center"><a href="javascript:void(0);"><img src=""  onclick="ShowMeNu()" id="imgmiddle" /></a></div></td>
      <td class="in-right-td" style="height: 100%; border: 1px solid #99CCFF; height:700px">
          <iframe src="${basePath}/page/admin/mains/main.jsp" frameborder="0"  width="100%" height="100%"  name="mainFrame" id="MainFrame"></iframe>
      </td>
    </tr>
  </table>
</div> -->
<!--mian end-->







