<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript">
	$(function() {
		$("#feed").load("<%=path%>/feed/listFeed", ajax);
		$("#box").load("<%=path%>/twitter/goAddTwitter", ajax);
		
		initHighslide("<%=path%>", "480", "400");

	    $("a[rel='ajaxupload']").click(function() {
	    	return hs.htmlExpand(this, { objectType: 'iframe'} );
		});
		
	});
</script>
<div id="information">
	<c:if test="${student.classes == null}"><a target="content" href="<%=path %>/goPerfectReg">继续完善资料认识很多同学</a></c:if> 
	<c:if test="${student.classes != null and student.permission == 1}">你已申请加入“${student.classes.name}”班级,审核中</c:if>
</div>

<div id="news">
	<div id="box">
	</div>
</div>

<h2>好友动态：</h2>
<div id="feed"></div>
