<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>School Social</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/content/images/reset.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/content/images/layout.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/content/images/header.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/content/images/login.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/content/images/footer.css" />
</head>
<body background="<%=path%>/content/images/bg.jpg">
<div class="header_k">
  <div id="header">
    <div class="headerwarp">
      <div class="logo"><a href="">LOGO</a></div>
      <div class="menuk">
        <ul class="menu">
          <li><a href="<%=path %>/login.jsp">首页</a></li>
          <li><a href="#">学校</a></li>
          <li><a href="#">群组</a></li>
          <li><a href="#">消息</a></li>
          <li><a href="#">博客</a></li>
        </ul>
        <div class="nav_account"><span class="loginName">欢迎</span> <a href="">登录</a> | <a href="">注册</a> </div>
      </div>
    </div>
  </div>
</div>
<div id="container">