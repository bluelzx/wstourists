<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("path", path);
	request.setAttribute("basePath", basePath);
%> 
  <!-- <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${basePath}static/boot/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="${basePath}static/boot/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="${basePath}static/boot/assets/css/main-min.css" rel="stylesheet" type="text/css" />
    
    <script type="text/javascript" src="${basePath}static/boot/assets/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="${basePath}static/boot/assets/js/bui-min.js"></script>
	<script type="text/javascript" src="${basePath}static/boot/assets/js/common/main-min.js"></script>
	<script type="text/javascript" src="${basePath}static/boot/assets/js/config-min.js"></script> -->
	<input id="basePath" type="hidden" value="${basePath}"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${basePath}static/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}static/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}static/css/style.css" />
    <script type="text/javascript" src="${basePath}static/js/jquery.js"></script>
    <script type="text/javascript" src="${basePath}static/js/jquery.sorted.js"></script>
    <script type="text/javascript" src="${basePath}static/js/bootstrap.js"></script>
    <script type="text/javascript" src="${basePath}static/js/ckform.js"></script>
    <script type="text/javascript" src="${basePath}static/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${basePath}static/js/common.js"></script>
	<script type="text/javascript" src="${basePath}static/js/jquery-2.0.3.min.js"></script>	
	<link type="text/css" href="${basePath}pjCs/base.css" rel="stylesheet" />
	<link type="text/css" href="${basePath}pjCs/common.css" rel="stylesheet" />
	<link type="text/css" href="${basePath}pjCs/Css.css" rel="stylesheet" />
	<link rel="stylesheet" href="${basePath}pjCs/default.css" type="text/css" />
	<link rel="stylesheet" href="${basePath}pjCs/outReg.css" type="text/css" />
	<script type="text/javascript" src="${basePath}pjJs/jquery.boxi.js"></script>
	<script type="text/javascript" src="${basePath}pjJs/wap.js"></script>
	<script type="text/javascript" src="${basePath}pjJs/Util.js"></script>
	<script type="text/javascript">browserRedirect("/m");</script>
	<script type="text/javascript" src="${basePath}pjJs/payfor.js"></script>
	
	  <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }
    </style>


 