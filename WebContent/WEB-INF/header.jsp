<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@	taglib uri="/struts-tags" prefix="s" %>
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
<link type="text/css" rel="stylesheet" href="<%=path%>/content/images/footer.css" />
<!-- 登录页专用件 -->
<link type="text/css" rel="stylesheet" href="<%=path%>/content/images/login.css" />
<!-- JQuery库 -->
<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>
<!-- 验证插件 -->
<link type="text/css" rel="stylesheet" href="<%=path%>/content/images/validationEngine.jquery.css" />
<script type="text/javascript" src="<%=path%>/content/js/jquery.validationEngine-cn.js" ></script>
<script type="text/javascript" src="<%=path%>/content/js/jquery.validationEngine.js" ></script>
<!-- JQuery UI 插件 -->
<link type="text/css" rel="stylesheet" href="<%=path%>/content/images/jq-ui-theme/jquery-ui-1.8.custom.css" />	
<script type="text/javascript" src="<%=path%>/content/js/jquery-ui-1.8.custom.min.js"></script>
        
<script type="text/javascript">
			$(document).ready(function(){
				$("form[rel='validate']").validationEngine();
				
				// Dialog			
				$('#dialog').dialog({
					autoOpen: false,
					width: 250,
					show: 'slide',
					hide: 'slide',
					resizable: false,
					buttons: { "Ok": function() { $(this).dialog("close"); } }
				});
				
				// Dialog Link
				$("a[rel='dialog']").click(function(){
					$('#dialog').load($(this).attr('href'));
					$('#dialog').dialog('open');
					return false;
				});
				
			});
</script>
</head>
<body background="<%=path%>/content/images/bg.jpg">
<div class="header_k">
  <div id="header">
    <div class="headerwarp">
      <div class="logo"><a href="">LOGO</a></div>
      <div class="menuk">
        <ul class="menu">
          <li><a href="<%=path%>/login.jsp">首页</a></li>
          <li><a href="#">学校</a></li>
          <li><a href="#">群组</a></li>
          <li><a href="#">消息</a></li>
          <li><a href="#">博客</a></li>
        </ul>
        <div class="nav_account"><span class="loginName">欢迎</span> 
        <a rel="dialog" href="<%=path%>/login/goLogin">登录</a> | 
        <a href="">注册</a></div>
        <div id="dialog" />
      </div>
    </div>
  </div>
</div>
<div id="container">
