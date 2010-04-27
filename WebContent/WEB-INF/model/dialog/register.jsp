<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
	String path = request.getContextPath();
%>
<!-- JQuery库 -->
<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>

<!-- JQ验证插件 -->
<script type="text/javascript" src="<%=path%>/content/jq-validate/jquery.form.js" ></script>
<script type="text/javascript" src="<%=path%>/content/jq-validate/jquery.validate.pack.js" ></script>
<script type="text/javascript" src="<%=path%>/content/jq-validate/messages_cn.js" ></script>

<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-ui/jquery-ui-1.8.custom.css" />
<script type="text/javascript" src="<%=path%>/content/jq-ui/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="<%=path %>/content/js/jquery.doubleSelect.min.js"></script>

<!-- 常用库，确保这段代码在最下方 -->
<script type="text/javascript" src="<%=path%>/content/js/common.js"></script>

<script type="text/JavaScript">
	 $(document).ready(function(){
		$('#inputform').validate({
			submitHandler: function() {
				commit($('#inputform'),'');
				if (parent.window.hs) {
					parent.$('#username').val($('#username').val());
					parent.$('#password').val($('#password').val());
					parent.$('#submit').click();
					parent.window.hs.close();
				}
			}
		});
			
		var schoolselect = ${schoolmap};
	    $('#first').doubleSelect('second', schoolselect);      
	 });
</script>
<form id="inputform" action="<%=path %>/register" method="post"  class="form" >
	<p><label>账号：</label>
	<input class="required" id="username" minlength="5" maxlength="13" type="text" name="user.username" /> 
	<s:fielderror><s:param>user.username</s:param></s:fielderror>
	</p>
	<p><label>密码：</label>
	<input class="required" id="password" type="password" name="user.password" />
	<s:fielderror><s:param>user.password</s:param></s:fielderror>
	</p>
	<p><label>确认：</label>
	<input class="required" type="password" name="repassword" />
	<s:fielderror><s:param>repassword</s:param></s:fielderror>
	</p>
	<p><label>真实姓名：</label>
	<input class="required" minlength="2" maxlength="13" type="text" name="user.name" />
	<s:fielderror><s:param>user.name</s:param></s:fielderror>
	</p>
	<p><label>性别：</label>
	<input type="radio" name="user.sex" value="1"/ checked="checked">男
	<input type="radio" name="user.sex" value="2"/>女
	</p>
	<p><label>电子邮箱：</label>
	<input class="required email" minlength="3" maxlength="32" type="text" name="user.email" />
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