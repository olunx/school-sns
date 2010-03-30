<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
%>
<h2 class="caption">添加学生</h2>
<form onSubmit="post(this);return false;" action="<%=path%>/student/addStudent" method="post">
<p><label> 头像： </label> <input type="text" name="student.avatar" /></p>
<p><label> 用户名： </label> <input type="text" name="student.username" /></p>
<p><label> 密码： </label> <input type="text" name="student.password" /></p>
<p><label> 学号： </label> <input type="text" name="student.sno" /></p>
<p><label> 昵称： </label> <input type="text" name="student.nickname" /></p>
<p><label> 姓名： </label> <input type="text" name="student.name" /></p>
<p><label> 宿舍： </label> <input type="text" name="student.dorm" /></p>
<p><label> 手机： </label> <input type="text" name="student.phoneNo" /></p>
<p><label> QQ： </label> <input type="text" name="student.qq" /></p>
<p><label> 邮箱： </label> <input type="text" name="student.mail" /></p>
<p class="paddingmin"><input type="submit" value="提交" /> <input type="reset" value="重置" /></p>
</form>

<link type="text/css" rel="stylesheet" href="<%=path%>/content/images/uploadify.css" />
<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/content/js/swfobject.js"></script>
<script type="text/javascript" src="<%=path%>/content/js/jquery.uploadify.v2.1.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#uploadify").uploadify({
		'uploader'		: '<%=path%>/content/images/uploadify.swf',
		'script'		: '<%=path%>/student/studentUpload',
		'cancelImg'		: '<%=path%>/content/images/cancel.png',
		'fileDataName'	: 'files',
		'mothod'		: 'POST',
		'queueID'		: 'fileQueue',
		'buttonText'	: 'OPEN',
		'auto'			: false,
		'multi'			: false
	});
});
</script>
<style type="text/css">
#fileQueue {
	width: 380px;
	height: 70px;
	overflow: auto;
	border: 1px solid #E5E5E5;
	margin-bottom: 10px;
}
</style>

<br/>
<br/>
<h2 class="caption">批量注册：</h2>
<div id="fileQueue"></div>
<input id="uploadify" type="file" name="files"/><br/>
<a href="javascript:$('#uploadify').uploadifyUpload();"">开始上传</a>
<p><a href="javascript:$('#uploadify').uploadifyClearQueue()">取消</a></p>

