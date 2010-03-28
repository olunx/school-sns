<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/submenu.jsp"></jsp:include>
<div id="main">
	<div id="content">
		<form action="modifyNotice?id=${id }&page=${page }" method="post">
		<p><label>标题：</label><input type="text" name="title" value="${title }" /></p>
		<p><label>内容：</label> <textarea name="content" rows="10" cols="50" >${content }</textarea></p>
		<p><input type="submit" value="更新公告"></p>
		</form>
	</div>
	<jsp:include page="/WEB-INF/sidebar.jsp"></jsp:include>
</div>
<jsp:include page="/WEB-INF/footer.jsp"></jsp:include>