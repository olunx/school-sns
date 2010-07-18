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
<link id="themecss" type="text/css" rel="stylesheet" href="" />
<!-- <%=path%>/content/images/theme/beige/theme.css -->
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
<div id="themechanger">
<ul>
<li class="blue" title="blue"><a href="#">蓝色</a></li>
<li class="green" title="green"><a href="#">绿色</a></li>
<li class="gray" title="gray"><a href="#">灰色</a></li>
<li class="beige" title="beige"><a href="#">悲歌</a></li>
<li class="pink" title="pink"><a href="#">粉红色</a></li>
<li class="red" title="red"><a href="#">红色</a></li>
<li class="purple" title="purple"><a href="#">紫色</a></li>
</ul>
<script type="text/javascript">
	//检查cookie，换主题
	var cookie= new $Cookie();
	var oldThemeName = cookie.get('iTheme');
	if (oldThemeName == null) oldThemeName="blue";
	changeCss(oldThemeName);
	
	$("#themechanger li").click(function(){
		var themename = $(this).attr("title");
		changeCss(themename);
		var cookie= new $Cookie();
		cookie.set("iTheme",themename);
	});

	function changeCss(themename){
		$("#themecss").attr("href","<%=path%>/content/images/theme/"+themename+"/theme.css");
		$("#themechanger ."+themename).addClass("current").siblings().removeClass("current");
	}

	function $Cookie(){
		this.set=function(name,value){
			var exdate=new Date();
			exdate.setDate(exdate.getDate()+30);
			document.cookie=name+"="+value+";expires="+exdate.toGMTString(); 
		};
		this.get=function(name){
			var cookies=document.cookie.split("; ");
			for(var i=0;i<cookies.length;i++){
				var s=cookies[i].split("=");
				if(s[0]==name)return s[1];
			}
		}
	}

</script>
</div>
<div class="menuk">
<ul class="menu">
	<li><a rel="header_index" onclick="ajaxload(this);return false;" href="<%=path%>/center">我的大厅</a></li>
	<li><a rel="header_twitter" onclick="ajaxload(this);return false;" href="<%=path%>/twitter/listTwitter">微博</a></li>
	<c:if test="${student != null}">
		<li><a rel="header_school" onclick="ajaxload(this);return false;" href="<%=path %>/school/viewSchool?id=${user.school.id}">学校</a></li>
		<li><a rel="header_classes" onclick="ajaxload(this);return false;" href="<%=path %>/classes/viewClasses?id=${user.classes == null? -1: user.classes.id}">班级</a></li>
	</c:if>
	<li><a rel="header_square" onclick="ajaxload(this);return false;" href="<%=path%>/wall/listWall">广场</a></li>
	<li><a onclick="ajaxload(this);return false;" href="<%=path%>/chat/priChat">倾诉</a></li>
	<c:if test="${admin != null}">
		<li><a rel="header_admin" onclick="ajaxload(this);return false;" href="<%=path %>/admin/viewAdmin">管理员</a></li>
	</c:if>
</ul>
<div class="nav_account"><c:choose>
	<c:when test="${isAccess!=null}">
		 <a rel="header_info" onclick="ajaxload(this);return false;" href="<%=path %>/people/goModifyPeople?id=${student.id}">个人信息</a> | 
		<a rel="header_mailbox" onclick="ajaxload(this);return false;" href="<%=path%>/mail/boxMail">小纸箱</a>
        | <a rel="header_exit" href="<%=path%>/logout">退出</a>
	</c:when>

	<c:otherwise>
		<a href="#">登录</a>
      | <a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>/goRegister">注册</a>
     <!-- <a href="<%=path%>/goRegister">注册</a>	-->
      </c:otherwise>

</c:choose></div>
<div id="dialog"></div>
</div>
</div>

<div id="container">