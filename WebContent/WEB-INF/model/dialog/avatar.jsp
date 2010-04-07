<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>
<!-- JQuery 图片选择 插件 -->
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-imgareaselect/css/imgareaselect-default.css" />
<script type="text/javascript" src="<%=path%>/content/jq-imgareaselect/jquery.imgareaselect.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#photo').imgAreaSelect( {
			aspectRatio : '1:1',
			handles : true,
			fadeSpeed : 200
		});
	});
</script>
<c:if test="${targetsFilePath != null}">
	<c:forEach items="${targetsFileUrl}" var="img">
		<img id="photo" src="<%=path%>/${img}" />
	</c:forEach>
</c:if>