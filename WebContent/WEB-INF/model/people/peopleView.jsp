<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<h2 class="caption">查看用户</h2>
<p><label> id： </label> <input type="text" name="people.id" value="${people.id}" /></p>
<p><label> 用户名： </label> <input type="text" name="people.username" value="${people.username}" /></p>
<p><label> 密码： </label> <input type="text" name="people.password" value="${people.password}" /></p>
<p><label> 昵称： </label> <input type="text" name="people.nickname" value="${people.nickname}" /></p>
<p><label> 姓名： </label> <input type="text" name="people.name" value="${people.name}" /></p>
<p><label> 宿舍： </label> <input type="text" name="people.dorm" value="${people.dorm}" /></p>
<p><label> 手机： </label> <input type="text" name="people.phoneNo" value="${people.phoneNo}" /></p>
<p><label> QQ： </label> <input type="text" name="people.qq" value="${people.qq}" /></p>
<p><label> 邮箱： </label> <input type="text" name="people.email" value="${people.email}" /></p>
<p><label> 头像： </label> <img src="<%=path %>/avatar/${people.id}"></img></p>
<p><label> 参加的小组： </label>
<c:if test="${people.groups != null}">
	<c:forEach items="${people.groups}" var="group">
		 <input type="text" value="${group.name}" />
	</c:forEach>
</c:if>
</p>