<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript">
	$(function() {
		initHighslide("<%=path%>", "480", "400");
	});
</script>

<div id="news">
	<div id="box">
		<jsp:include page="./model/twitter/twitterAdd.jsp"></jsp:include>
	</div>
</div>

<h2>最新动态：</h2>
<div id="feed" class="feed">
</div>
<script type="text/javascript">
		onLoading("#feed");
		$("#feed").load("<%=path%>/feed/listFeed", function() {
			offLoading();
		});
</script>