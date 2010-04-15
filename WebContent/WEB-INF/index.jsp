<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="submenu.jsp"></jsp:include>
<div id="main" class="corner">
<div id="content">
<script type="text/javascript">
	$(function() {
		$("#twitter").load("<%=path%>/topic/listMyTopic");
		$("#feed").load("<%=path%>/feed/listFeed", ajax);
	});
</script>
微博：
<div id="twitter">

</div>
<br/><br/>
好友动态：
<div id="feed">

</div>
</div>
<jsp:include page="sidebar.jsp"></jsp:include>
</div>
<jsp:include page="footer.jsp"></jsp:include>