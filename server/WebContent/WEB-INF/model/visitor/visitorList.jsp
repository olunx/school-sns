<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
%>

<c:choose>
	<c:when test="${pageBean.list == null}">
					没有数据！
			</c:when>
	<c:otherwise>
		<c:forEach items="${pageBean.list}" var="visitor">
			<ul class="ul">
				<li>
					<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${visitor.people.username }"><img src="<%=path %>/avatar/${visitor.people.id}" width="50"></img></a><br/>
					<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${visitor.people.username }">${visitor.people.name}</a><br/>
					<fmt:formatDate value="${visitor.time }" pattern="MM-dd" />	
				</li>
			</ul>
		</c:forEach>
	</c:otherwise>
</c:choose>

