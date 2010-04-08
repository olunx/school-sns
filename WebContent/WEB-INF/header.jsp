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
<link type="text/css" rel="stylesheet" href="<%=path%>/content/images/content.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/content/images/footer.css" />
<!-- JQuery库 -->
<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>
<!-- colorbox -->
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-colorbox/colorbox.css" />
<script type="text/javascript" src="<%=path%>/content/jq-colorbox/jquery.colorbox-min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {

		$("a[rel='dialog']").colorbox();
		
		//注册事件
			$("a[target='content']").click(function() {
				var href = $(this).attr('href');
				loadContent(href);
				return false;
			});

		});

	//注册二级事件
	function ajax() {
		$("a[rel='dialog']").colorbox();
		$("#content a[target='content']").click(function() {
			var href = $(this).attr('href');
			loadContent(href);
			return false;
		});
	};

	//加载数据
	function loadContent(href) {
		var content = $('#content');
		content.fadeOut('fast', function() {
			onLoading();//打开loading
			content.load(href, function() {
				offLoading();//关闭loading
				content.fadeIn('normal', ajax);
			});
		});
	}

	//打开loading
	function onLoading() {
		$('#main').append('<span id="loading"><img src="<%=path%>/content/jq-colorbox/images/loading.gif" />加载中...</span>');
		$('#loading').fadeIn('fast');
	}
	//关闭loading
	function offLoading() {
		$('#loading').remove();
	}
	
	//提交表单数据
	function post(obj) {
		var content = $('#content');
		var urlStr = $(obj).attr('action');
		var dataStr = decodeURIComponent($(obj).serialize());
		content.fadeOut('fast', function() {
			onLoading();//打开loading
			$.ajax( {
				url : urlStr,
				data : dataStr,
				type : 'POST',
				success : function(result) {
					content.html(result);
					offLoading();//关闭loading
					content.fadeIn('normal', ajax);
				}
			});
		});
	}
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
 <a rel="dialog" href="<%=path%>/mail/listMyReceMail">小纸条</a>
 <a rel="dialog" href="<%=path%>/login/goLogin">登录</a> | <a href="">注册</a></div>
<div id="dialog" /></div>
</div>
</div>
</div>
</div>
<div id="container">