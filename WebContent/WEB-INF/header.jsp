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
<link type="text/css" rel="stylesheet" href="<%=path%>/content/images/layout.css" />
<link id="themecss" type="text/css" rel="stylesheet" href="<%=path%>/content/images/theme/beige/theme.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-ui/jquery-ui-1.8.custom.css" />

<!-- JQuery库 -->
<!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script> -->
<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>

<script type="text/javascript" src="<%=path %>/content/js/jquery.doubleSelect.min.js"></script>
<!-- JQuery UI 插件 -->
<!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>-->
<script type="text/javascript" src="<%=path%>/content/jq-ui/jquery-ui-1.8.custom.min.js" ></script>

<!-- 常用库，确保这段代码在最下方 -->
<script type="text/javascript" src="<%=path%>/content/js/common.js"></script>
</head>
<body>

<div id="header">
<div class="logo"><a href="<%=path%>/home"><img src="<%=path%>/content/images/logo.png"/></a></div>
<div class="menuk">
<ul class="menu">
	<li><a onclick="ajaxload(this);return false;" href="<%=path%>/center">我的大厅</a></li>
	<li><a onclick="ajaxload(this);return false;" href="<%=path%>/twitter/listTwitter">微博</a></li>
	<c:if test="${student != null}">
		<li><a onclick="ajaxload(this);return false;" href="<%=path %>/school/viewSchool?id=${user.school.id}">学校</a></li>
		<li><a onclick="ajaxload(this);return false;" href="<%=path %>/classes/viewClasses?id=${user.classes == null? -1: user.classes.id}">班级</a></li>
	</c:if>
	<li><a onclick="ajaxload(this);return false;" href="<%=path%>/wall/listWall">广场</a></li>
	<li rel="submenu"><a href="javascript:;">聊天馆</a>
		<dl>
		<dt><a onclick="ajaxload(this);return false;" href="<%=path%>/chat/pubChat">公共聊天室</a></dt>
		<dt><a onclick="ajaxload(this);return false;" href="<%=path%>/chat/priChat">匿名聊天室</a></dt>
		</dl>
	</li>
</ul>
<div class="nav_account"><c:choose>
	<c:when test="${isAccess!=null}">
		 <a onclick="ajaxload(this);return false;" href="<%=path %>/people/goModifyPeople?id=${student.id}">设置</a> | 
		<a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>/mail/boxMail">小纸箱</a>
        | <a href="<%=path%>/logout">退出</a>
	</c:when>

	<c:otherwise>
		<a href="#">登录</a>
      | <a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>/goRegister">注册</a>
     <!-- <a href="<%=path%>/goRegister">注册</a>	-->
      </c:otherwise>

</c:choose></div>
<div id="dialog"></div>
</div>
<div id="themechanger">
<ul>
<li><a href="#" class="blue">蓝色</a></li>
<li><a href="#" class="green">绿色</a></li>
<li><a href="#" class="gray">灰色</a></li>
<li><a href="#" class="beige">悲歌</a></li>
<li><a href="#" class="pink">粉红色</a></li>
<li><a href="#" class="red">红色</a></li>
<li><a href="#" class="purple">紫色</a></li>
</ul>
<script type="text/javascript">
	$("#themechanger a").click(function(){
		var themename = $(this).attr("class");
		$("#themecss").attr("href","<%=path%>/content/images/theme/"+themename+"/theme.css");
	});
</script>
</div>
</div>

<div id="container">