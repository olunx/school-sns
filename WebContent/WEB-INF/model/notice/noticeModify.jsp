<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
		<form action="<%=path %>/notice/modifyNotice?id=${id }&page=${page }" method="post">
		<p><label>标题：</label><input type="text" name="title" value="${title }" /></p>
		<p><label>内容：</label> <textarea name="content" rows="10" cols="50" >${content }</textarea></p>
		<p><input type="submit" value="更新公告"></p>
		</form>
