<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
%>
<h2 class="caption">查看群组</h2>
<p><label> 小组名称： </label> <input type="text" name="group.name" value="${group.name}" /></p>
<p><label> 简介： </label> <input type="text" name="group.intro" value="${group.intro}" /></p>
<p><label> 图片： </label> <input type="text" name="group.pic" value="${group.pic}" /></p>
<p><label> 作品： </label> <input type="text" name="group.works" value="${group.works}" /></p>
<p><label> 类型： </label> <input type="text" name="group.type" value="${group.type}" /></p>
<p><label> 管理员： </label> <input type="text" name="group.admin" value="${group.admin.name}" /></p>
<p><label> 成员： </label>
<c:if test="${group.member != null}">
	<c:forEach items="${group.member}" var="people">
		<br/><input type="text" value="${people.name}" />
	</c:forEach>
</c:if>
</p>
