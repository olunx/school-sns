<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="submenu.jsp"></jsp:include>
<div id="main" class="corner">
<div id="content">
好友动态：
<c:if test="${feeds != null}">
	<c:forEach items="${feeds}" var="feed">
		 <ul>
		 <li>${feed.author.name} ${feed.time}</li>
		 <li>${feed.type}</li>
		 <li>${feed.message}</li>
		 </ul>
	</c:forEach>
</c:if>
</div>
<jsp:include page="sidebar.jsp"></jsp:include>
</div>
<jsp:include page="footer.jsp"></jsp:include>