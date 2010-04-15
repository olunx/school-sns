<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<h2 class="caption">添加群组</h2>
<form onSubmit="post(this);return false;" action="<%=path%>/group/addGroup" method="post">
<p><label> 小组名称： </label> <input type="text" name="group.name" /></p>
<p><label> 简介： </label> <input type="text" name="group.intro" /></p>
<p><label> 图片： </label> <input type="text" name="group.pic" /></p>
<p><label> 作品： </label> <input type="text" name="group.works" /></p>
<p><label> 类型： </label> <input type="text" name="group.type" /></p>
<p class="paddingmin"><input type="submit" value="提交" /> <input type="reset" value="重置" /></p>
</form>
