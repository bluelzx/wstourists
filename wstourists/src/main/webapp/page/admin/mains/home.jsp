<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>后台管理系统</title>
    <%@ include file="/common/metaandlink.jsp"%>
 	<input id="basePath" type="hidden" value="${basePath}"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${basePath}static/boot/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="${basePath}static/boot/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="${basePath}static/boot/assets/css/main-min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${basePath}static/boot/assets/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="${basePath}static/boot/assets/js/bui-min.js"></script>
	<script type="text/javascript" src="${basePath}static/boot/assets/js/common/main-min.js"></script>
	<script type="text/javascript" src="${basePath}static/boot/assets/js/config-min.js"></script>
</head>
<body>

<div class="header">

    <div class="dl-title">
        <!--<img src="/chinapost/Public/assets/img/top.png">-->
    </div>

    <div class="dl-log">欢迎您，<span class="dl-log-user">${userName}</span><a href="${basePath}admin/exit" title="退出系统" class="dl-log-quit">[退出]</a><a href="${basePath}admin/sysLogin" title="刷新系统" class="dl-log-quit">[刷新]</a>
    </div>
</div>
<div class="content">
    <div class="dl-main-nav">
        <div class="dl-inform"><div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div></div>
        <ul id="J_Nav"  class="nav-list ks-clear">
        	<li class="nav-item dl-selected">
        		<div class="nav-item-inner nav-home">首页</div>
        	</li>
            <c:forEach items="${glist}" var="glist">
            	<li class="nav-item dl-selected">
            		<div class="nav-item-inner nav-home">${glist.menuName}</div>
            	</li>	
            </c:forEach>
	    	<input type="hidden" id="sysMenus" name="sysMenus" value="${menus}" />
        </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">

    </ul>
</div>
<script>
    BUI.use('common/main',function(){
    	var sysMenus=$("#sysMenus").val().toString();
    	//alert(sysMenus);
    	//alert(${menus});
    	//[{id:'001',menu:[{text:'系统管理',items:[{id:'001001',text:'用户管理',href:'asa/as'}]}]}]
    	//[{id:'1',menu:[{text:'业务管',items:[{id:'12',text:'机构管理',href:'Node/index.html'},{id:'3',text:'角色管理',href:'Role/index.html'},{id:'4',text:'用户管理',href:'User/index.html'},{id:'6',text:'菜单管理',href:'Menu/index.html'}]}]},{id:'7',homePage : '9',menu:[{text:'系统管理',items:[{id:'9',text:'查询业务',href:'Node/index.html'}]}]}]
       // var config = sysMenus; //[{id:'001',menu:[{text:'系统管理',items:[{id:'001001',text:'用户管理',href:'asa/as'}]}]}];
        //config="["+sysMenus+"]";
        //configs="[{id:'001',menu:[{text:'系统管理',items:[{id:'001001',text:'用户管理',href:'asa/as'}]}]}]";
        eval("var config = "+sysMenus); 
        //alert(config);
        new PageUtil.MainPage({
            modulesConfig : config
        });
    });
</script>

</body>
</html>


