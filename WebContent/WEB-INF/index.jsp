<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="submenu.jsp"></jsp:include>
<div id="main" class="corner">
<div id="content">
<jsp:include page="center.jsp"></jsp:include>
</div>
<jsp:include page="sidebar.jsp"></jsp:include>
</div>
<script type="text/javascript">
	var url = window.location.href;
	var userid = url.substr(url.indexOf("#")+1,url.length);
	if (url.indexOf("#")>0 && userid!=""){
		$("#content").load("<%=path%>/topic/listOtherTopic?otherId="+userid, ajax);
	};
</script>

<jsp:include page="footer.jsp"></jsp:include>