<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

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
	<s:fielderror><s:param>user.username</s:param></s:fielderror>
	</p>
	<p><label>密码：</label>
	<input type="password" name="user.password" />
	<s:fielderror><s:param>user.password</s:param></s:fielderror>
	</p>
	<p><label>确认：</label>
	<input type="password" name="repassword" />
	<s:fielderror><s:param>repassword</s:param></s:fielderror>
	</p>
	<p><label>真实姓名：</label>
	<input type="text" name="user.name" />
	<s:fielderror><s:param>user.name</s:param></s:fielderror>
	</p>
	<p><label>性别：</label>
	<input type="radio" name="user.sex" value="1"/ checked="checked">男
	<input type="radio" name="user.sex" value="2"/>女
	</p>
	<p><label>电子邮箱：</label>
	<input type="text" name="user.email" />
	<s:fielderror><s:param>user.email</s:param></s:fielderror>
	</p>
	<div id="select">
		<label>选择学校：</label>
		<select id="first" size="1"><option value="">--</option></select>
		<select id="second" name="schoolId" size="1"><option value="">--</option></select>
	</div>
	<p><label>注册身份：</label>
	<input type="radio" name="identity" value="1" checked="checked"/>学生
	<input type="radio" name="identity" value="2"/>老师(注册老师需要申请)
	</p>
	<p>
	<input type="checkbox" name="protocol" value="1" checked="checked"/>我已阅读并接受<a href="">“服务条款”</a>
	<s:fielderror><s:param>protocol</s:param></s:fielderror>
	</p>
	<input type="submit" value="提交问题"/>
</form>