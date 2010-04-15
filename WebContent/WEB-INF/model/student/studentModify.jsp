<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-highslide/highslide.css" />
<script type="text/javascript" src="<%=path%>/content/jq-highslide/highslide-full.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    hs.graphicsDir = '<%=path%>/content/jq-highslide/graphics/';
    hs.align = 'center';
    hs.outlineType = 'rounded-white';
    hs.wrapperClassName = 'draggable-header';
    hs.transitions = ['expand', 'crossfade'];
	hs.useBox = true;
	hs.width = 680;
	hs.height = 450;
});
</script>

<h2 class="caption">修改</h2>
<form onSubmit="post(this);return false;" action="<%=path%>/student/modifyStudent" method="post">
<p><label> 用户名： </label> <input type="text" name="student.username" value="${student.username}" /></p>
<p><label> 密码： </label> <input type="text" name="student.password" value="${student.password}" /></p>
<p><label> 学号： </label> <input type="text" name="student.sno" value="${student.sno}" /></p>
<p><label> 昵称： </label> <input type="text" name="student.nickname" value="${student.nickname}" /></p>
<p><label> 姓名： </label> <input type="text" name="student.name" value="${student.name}" /></p>
<p><label> 宿舍： </label> <input type="text" name="student.dorm" value="${student.dorm}" /></p>
<p><label> 手机： </label> <input type="text" name="student.phoneNo" value="${student.phoneNo}" /></p>
<p><label> QQ： </label> <input type="text" name="student.qq" value="${student.qq}" /></p>
<p><label> 邮箱： </label> <input type="text" name="student.email" value="${student.email}" /></p>
<a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>/image/goAddImage">上传头像</a>
<!-- 上传成功后，图片将插到这里。 -->
<div id="pic">
<img src="<%=path %>${student.avatar.minFileUrl}"></img>
<input id="oriFileName" type="hidden" name="image.oriFileName" value="${student.avatar.oriFileName}"/>
<input id="bigFileName" type="hidden" name="image.bigFileName" value="${student.avatar.bigFileName}"/>
<input id="bigFileUrl" type="hidden" name="image.bigFileUrl" value="${student.avatar.bigFileUrl}"/>
<input id="minFileName" type="hidden" name="image.minFileName" value="${student.avatar.minFileName}"/>
<input id="minFileUrl" type="hidden" name="image.minFileUrl" value="${student.avatar.minFileUrl}"/>
</div>
<p class="paddingmin"><input type="submit" value="提交" /> <input type="reset" value="重置" /></p>
<input type="hidden" name="student.id" value="${student.id}"/>
</form>
