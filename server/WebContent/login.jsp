<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	Object isAccess = session.getAttribute("isAccess");
	if (isAccess!=null && isAccess.equals("true")){
		response.sendRedirect(path+"/home");
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>School Social</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/content/images/layout.css" />
<link id="themecss" type="text/css" rel="stylesheet" href="" />
<!-- <%=path%>/content/images/theme/beige/theme.css -->

<!-- JQuery库 -->
<!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script> -->
<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>

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

<div id="login-main">
<!-- 登录页专用件 -->
<link type="text/css" rel="stylesheet" href="<%=path%>/content/images/login.css" />
    <div id="mbox">
      <div id="loginbox">
        <div id="logintitle">请登录：</div>
        <div id="avatar"><img src="" /></div>
        <div id="login">
          <form id="inputform" action="authLogin" method="post">
            <div class="row">
              <label for="username">用户名</label>
              <input type="text" name="username" id="username" class="t_input" value="admin" />
            </div>
            <div class="row">
              <label for="password">密　码</label>
              <input type="password" name="password" id="password" class="t_input" value="admin" />
            </div>
            <div class="submitrow">
              <input id="submit" type="submit" name="loginsubmit" class="submit" value="登录" />
              <a href="#">忘记密码?</a>
            </div>
          </form>
        </div>
        <!-- end of login -->
      </div>
      <!-- end of loginbox -->
    </div>
    <!-- end fof mbox -->
  <jsp:include page="/WEB-INF/footer.jsp"></jsp:include>