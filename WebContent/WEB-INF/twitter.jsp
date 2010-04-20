<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="submenu.jsp"></jsp:include>
<div id="main" class="corner">
<script type="text/javascript">
$(function() {
	$("#content").load("<%=path%>/topic/listOtherTopic?otherId=${id}", ajax);
});
</script>
<div id="content">

</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>