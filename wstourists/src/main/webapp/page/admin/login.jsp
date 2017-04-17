<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="webkit" name="renderer">
<meta content="IE=9, IE=8, chrome=1" http-equiv="X-UA-Compatible">
<meta content="" name="keywords">
<meta content="" name="description">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("path", path);
	request.setAttribute("basePath", basePath);
%> 
 <input id="basePath" type="hidden" value="${basePath}"/>
 <title>人脉宝管理平台</title>
<style type="text/css">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312" />
<title>管理平台</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow:hidden;
}
.STYLE3 {color: #528311; font-size: 12px; }
.STYLE4 {
	color: #42870a;
	font-size: 12px;
}
-->
</style></head>

<body>
 <form  action="${basePath}admin/sysLogin" method="post">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="#e5f6cf">&nbsp;</td>
  </tr>
  <tr>
    <td height="608" background="${basePath}common/images/login_03.gif"><table width="862" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="266" background="${basePath}common/images/login_04.gif">&nbsp;</td>
      </tr>
      <tr>
        <td height="95"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="424" height="95" background="${basePath}common/images/login_06.gif">&nbsp;</td>
            <td width="183" background="${basePath}common/images/login_07.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="21%" height="30"><div align="center"><span class="STYLE3">用户</span></div></td>
                <td width="79%" height="30"><input type="text" name="userName" id="userName"  style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></td>
              </tr>
              <tr>
                <td height="30"><div align="center"><span class="STYLE3">密码</span></div></td>
                <td height="30"><input  type="password" name="passWord" id="passWord" style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></td>
              </tr>
              <tr>
              	<td height="30"><input name="codes" type="text" class="yzm" id="codes" value="验证码" onclick="JavaScript:this.value=''" style="height:20px; width:50px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"/></td>
                <td height="30"><div align="center"><%-- <a href="javascript:void(0);" ><img onclick="change()" height="20px" id="myimg" src="${basePath}admin/code/getCode" /></a> --%>
                	 <span class="log-img"><img width="50px" height="20px" id="myimg" alt="看不清楚,请点击更换新的验证码" src="${basePath}admin/code/getCode" onclick="this.src=this.src+'?'" title="看不清楚,请点击更换新的验证码" style="vertical-align:top;cursor:pointer;"/></span>
                </div></td>
              </tr>
              <tr>
                <td height="30">&nbsp;</td>
                <td height="30"><input type="submit"  value="登录"    /></td>
              </tr>
            </table></td>
            <td width="255" background="${basePath}common/images/login_08.gif">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="247" valign="top" background="${basePath}common/images/login_09.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="22%" height="30">&nbsp;</td>
            <td width="56%">&nbsp;</td>
            <td width="22%">&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="44%" height="20">&nbsp;</td>
                <td width="56%" class="STYLE4">版本 2008V1.0 </td>
              </tr>
            </table></td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td bgcolor="#a2d962">&nbsp;</td>
  </tr>
</table>
</form>
</html>

 
    <%-- <form  action="${basePath}admin/sysLogin" method="post">
    <ul> 
    <li><input name="userName" id="userName" type="text"  value="admin" onclick="JavaScript:this.value=''"/></li>
    <li><input name="passWord" id="passWord" type="password"  value="" onclick="JavaScript:this.value=''"/></li>
    <li><input name="codes" type="text" class="yzm" id="codes" value="验证码" onclick="JavaScript:this.value=''"/><a href="javascript:void(0);" ><img onclick="change()" height="40px" id="myimg" src="${basePath}admin/code/getCode" /></a></li> 
    <li><input type="submit"  value="登录"    /><label><label><a href="#">忘记密码？</a></label></li>
    </ul>
    </form> --%>


