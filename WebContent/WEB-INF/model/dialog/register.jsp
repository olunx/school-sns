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

	 $(document).ready(function() {

			$("#datepicker").datepicker( {
				
				dateFormat : 'yy-mm-dd',
				dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
				firstDay : 1,
				monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
				showMonthAfterYear: true,
				changeYear: true,
				maxDate: new Date()
			});

		});
</script>
<form action="<%=path %>/register" method="post" onsubmit="post(this);return false;" Class="form" >
	<p><label>账号：</label>
	<input type="text" name="user.username" />
	</p>
	<p><label>密码：</label>
	<input type="text" name="user.password" />
	</p>
	<p><label>确认：</label>
	<input type="text" name="repassword" />
	</p>
	<p><label>真实姓名：</label>
	<input type="text" name="user.name" />
	</p>
	<p><label>性别：</label>
	<input type="radio" name="user.sex" value="1"/>男
	<input type="radio" name="user.sex" value="0"/>女
	</p>
	<p><label>生日：</label>
	<input id="datepicker" type="text" name="birthday" />
	</p>
	<p><label>电子邮箱：</label>
	<input type="text" name="user.email" />
	</p>
	<div id="thirdselect">
		<label>选择学校：</label>
		<select id="first" size="1"><option value="">--</option></select>
		<select id="second" name="scId" size="1"><option value="">--</option></select>
		
	</div>
	<p><input type="checkbox" name="protocol" value="1" />我已阅读并接受<a href="">“服务条款”</a>
	</p>
	<input type="submit" value="提交问题"/>
</form>