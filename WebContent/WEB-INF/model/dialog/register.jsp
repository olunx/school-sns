<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
%>

<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-ui/jquery-ui-1.8.custom.css" />
<script type="text/javascript" src="<%=path%>/content/jq-ui/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="<%=path %>/content/js/jquery.doubleSelect.min.js"></script>
<script type="text/JavaScript">
	 $(document).ready(function()
	 {		
			var schoolselect = ${schoolmap};
		    $('#first').doubleSelect('second', schoolselect);      
	        
	 });
</script>
<form action="<%=path %>/register" method="post"  Class="form" >
	<p><label>账号：</label>
	<input type="text" name="user.username" />
	</p>
	<p><label>密码：</label>
	<input type="text" name="user.password" />
	</p>
	<p><label>确认：</label>
	<input type="text" name="repassword" />
	</p>
	<p><label>性别：</label>
	<input type="radio" name="user.sex" value="1"/>男
	<input type="radio" name="user.sex" value="0"/>女
	</p>
	<p><label>电子邮箱：</label>
	<input type="text" name="user.email" />
	</p>
	<div id="select">
		<label>选择学校：</label>
		<select id="first" size="1"><option value="">--</option></select>
		<select id="second" name="schoolId" size="1"><option value="">--</option></select>
		
	</div>
	<p><label>注册身份：</label>
	<input type="radio" name="identity" value="1" checked="checked"/>学生
	<input type="radio" name="identity" value="0"/>老师(注册老师需要申请)
	</p>
	<p><input type="checkbox" name="protocol" value="1" />我已阅读并接受<a href="">“服务条款”</a>
	</p>
	<input type="submit" value="提交问题"/>
</form>