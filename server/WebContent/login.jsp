<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>School Social</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/content/images/login.css" />
</head>
<body>

<div id="header">
<div class="logo"><a href="<%=path%>/home"><img src="<%=path%>/content/images/logo.png"/></a></div>
</div>
<div id="login-main">
<!-- 登录页专用件 -->
    <div id="mbox">
      <div id="loginbox">
        <div id="logintitle">请登录：</div>
        <div id="avatar"><img src="<%=path%>/content/images/avatar.jpg" /></div>
        <div id="login">
          <form id="inputform" action="authLogin" method="post">
            <div class="row">
              <label for="username">用户名</label>
              <input type="text" name="username" id="username" class="t_input" value="0707501101" />
            </div>
            <div class="row">
              <label for="password">密　码</label>
              <input type="password" name="password" id="password" class="t_input" value="0707501101" />
            </div>
            <div class="submitrow">
              <input class="btn blue" type="submit" value="登录" />
              <a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>/goRegister">注册</a>
            </div>
          </form>
        </div><!-- end of login -->
      </div><!-- end of loginbox -->
    </div><!-- end fof mbox -->
</div><!--login-main-->
<!-- JQuery库 -->
<!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script> -->
<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>
<!-- highslide -->
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-highslide/highslide.css" />
<script type="text/javascript" src="<%=path%>/content/jq-highslide/highslide-full.min.js"></script>
<script type="text/javascript" src="<%=path%>/content/js/highslide-init.js"></script>
<!--[if IE]> 
		<script type="text/javascript" src="<%=path%>/content/jq-highcharts/highcharts-ie.js"></script> 
<![endif]-->
<script type="text/javascript">
	initHighslide("<%=path%>", "640", "480");
</script>
</body>
</html>