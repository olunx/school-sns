<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/submenu.jsp"></jsp:include>
<div id="main">
	<div id="content">
	<h2>${notice.title }</h2>
	${notice.content }
	</div>
	<jsp:include page="/WEB-INF/sidebar.jsp"></jsp:include>
</div>
<jsp:include page="/WEB-INF/footer.jsp"></jsp:include>