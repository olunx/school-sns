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

<script type="text/javascript" src="<%=path%>/content/js/jquery.corner.js"></script>

<!-- 常用库，确保这段代码在最下方 -->
<script type="text/javascript" src="<%=path%>/content/js/common.js"></script>

<!-- highslide -->
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-highslide/highslide.css" />
<script type="text/javascript" src="<%=path%>/content/jq-highslide/highslide-full.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    //class为corner时为圆角
	if (!$.browser.msie) {
        $(".corner").corner("8px");
        $("#submenu").corner("left 8px");
        $("#content").corner("right bottom 8px");
    }
    
    hs.graphicsDir = '<%=path%>/content/jq-highslide/graphics/';
    hs.align = 'center';
    hs.outlineType = 'rounded-white';
    hs.wrapperClassName = 'draggable-header';
    hs.transitions = ['expand', 'crossfade'];
	hs.useBox = true;
	hs.width = 680;
	hs.height = 450;
});
</script>

</head>
<body>

<div id="header">
<div class="logo"><a href="">LOGO</a></div>
<div class="menuk corner">
<ul class="menu">
	<li><a href="<%=path%>/home">首页</a></li>
	<c:if test="${student != null}">
		<li><a target="content" href="<%=path %>/school/viewSchool?id=${user.school.id}">学校</a></li>
		<li><a target="content" href="<%=path %>/classes/viewClasses?id=${user.classes == null? -1: user.classes.id}">班级</a></li>
	</c:if>
	<li><a href="#">消息</a></li>
	<li><a href="#">博客</a></li>
</ul>
<div class="nav_account"><c:choose>
	<c:when test="${isAccess!=null}">
		<span class="loginName">欢迎，${user.name }</span>
		<a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>/mail/boxMail">小纸箱</a>
        | <a href="<%=path%>/logout">退出</a>
	</c:when>

	<c:otherwise>
		<a href="#">登录</a>
      | <!--<a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>/goRegister">注册</a>	-->
      <a href="<%=path%>/goRegister">注册</a>
      </c:otherwise>

</c:choose></div>
<div id="dialog"></div>
</div>

</div>

<div id="container">