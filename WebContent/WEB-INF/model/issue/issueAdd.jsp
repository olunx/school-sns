<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
%>
<!-- JQuery库 -->
<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/content/js/jquery.doubleSelect.min.js"></script>
<script type="text/JavaScript">
 $(document).ready(function()
 {		
		var selectoptions = ${jsonmap};
	    $('#first').doubleSelect('second', selectoptions);      
 });
</script>
<form action="<%=path %>/issue/addIssue" method="post" onsubmit="post(this);return false;" class="form" >
	<p>
		<label>提问类型：</label>
		<select class="w_small" id="first" size="1"><option value="">--</option></select>
		<select class="w_small" id="second" name="itid" size="1"><option value="">--</option></select>
	</p>
	<p><label>你的提问：</label>
	<input class="w_long" type="text" name="issue.name" />
	<s:fielderror><s:param>issue.name</s:param></s:fielderror>
	</p>
	<p><label>补充说明：</label>
	<textarea class="textarea" name="issue.content" id="demo" rows="10" cols="60"></textarea>
	</p>

	<p>
	<label>悬赏财富：</label>
	<input class="w_middle"  type="text" name="issue.value" value="0"/>
	</p>
	<s:fielderror><s:param>issue.value</s:param></s:fielderror>
	<p>
	<label>匿名提问：</label><input type="checkbox" id="chk_niming" name="issue.state" value="1" /><label for="chk_niming"> 需要匿名提问请打钩</label>
	</p>
	<input type="submit" value="提交问题"/>
</form>