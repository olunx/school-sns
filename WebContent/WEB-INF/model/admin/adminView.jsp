<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>
<div>
管理员:
	<c:forEach items="${admins}" var="admin">
			<a onclick="ajaxload(this);return false;" href="<%=path%>/student/viewStudent?id=${admin.id }">${admin.name}</a>
	</c:forEach>
	<br/>
</div>
<div>
	<a class="letterspacing" onclick="ajaxload(this);return false;" href="<%=path%>/school/listSchool">查看全部学校</a>
    <a class="letterspacing" onclick="ajaxload(this);return false;" href="<%=path %>/school/goAddSchool">添加学校</a>
</div>

