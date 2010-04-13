<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path %>/content/js/jquery.doubleSelect.min.js"></script>
<script type="text/JavaScript">
 $(document).ready(function()
 {		
		var selectoptions = ${jsonmap};
	    $('#first').doubleSelect('second', selectoptions);      
 });
</script>
<form action="<%=path %>/issue/addIssue" method="post" onsubmit="post(this);return false;" Class="form" >
	<p><label>你的提问：</label>
	<input type="text" name="issue.name" />
	</p>
	<p><label>补充说明：</label>
	<textarea name="issue.content" id="demo" rows="50" cols="150" style="width: 600px; height: 150px"></textarea>
	</p>
	<p>
		<label>提问类型：</label>
		<select id="first" size="1"><option value="">--</option></select>
		<select id="second" name="itid" size="1"><option value="">--</option></select>
	</p>
	悬赏财富：
	<input type="text" name="issue.value" value="0"/>
	<p>
	匿名提问：<input type="checkbox" name="issue.state" value="1" />
	</p>
	<input type="submit" value="提交问题"/>
</form>