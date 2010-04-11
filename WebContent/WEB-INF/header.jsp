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
<!-- <link type="text/css" rel="stylesheet" href="<%=path%>/content/images/reset.css" />-->
<link type="text/css" rel="stylesheet" href="<%=path%>/content/images/layout.css" />
<link id="themecss" type="text/css" rel="stylesheet" href="<%=path%>/content/images/blue/theme_blue.css" />

<!-- JQuery库 -->
<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>
<!-- colorbox -->
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-colorbox/colorbox.css" />
<script type="text/javascript" src="<%=path%>/content/jq-colorbox/jquery.colorbox-min.js"></script>

<script type="text/javascript" src="<%=path%>/content/js/jquery.corner.js"></script>

<!-- 常用库，确保这段代码在最下方 -->
<script type="text/javascript" src="<%=path%>/content/js/common.js"></script>
</head>
<body>

  <div id="header">
      <div class="logo"><a href="">LOGO</a></div>
      <div class="menuk corner">
        <ul class="menu">
          <li><a href="<%=path%>/home">首页</a></li>
          <li><a href="#">学校</a></li>
          <li><a href="#">群组</a></li>
          <li><a href="#">消息</a></li>
          <li><a href="#">博客</a></li>
        </ul>
        <div class="nav_account">
        <c:choose>
        	<c:when test="${isAccess!=null}"><span class="loginName">欢迎，${user.name }</span> <a rel="dialog" href="<%=path%>/mail/listMyReceMail">小纸条</a> | <a href="<%=path%>/logout">退出</a></c:when>
        	<c:otherwise><a rel="dialog" href="<%=path%>/goLogin">登录</a> | <a href="">注册</a> </c:otherwise>
        </c:choose>
		</div>
        <div id="dialog"></div>
    </div>

</div>

<div id="container">
