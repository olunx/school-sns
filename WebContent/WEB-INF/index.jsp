<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<jsp:include page="header.jsp"></jsp:include>
<div id="main">
<div id="content">
<jsp:include page="center.jsp"></jsp:include>
</div>
<jsp:include page="sidebar.jsp"></jsp:include>
</div>
<jsp:include page="footer.jsp"></jsp:include>