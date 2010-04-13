<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-highslide/highslide.css" />
<script type="text/javascript" src="<%=path%>/content/jq-highslide/highslide-full.min.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	if (parent.window.hs) {
		setTimeout(function() {
			//想返回什么数据到前端，可以写到这里。
			parent.$("#pic img").attr("src", "<%=path%>${image.minFileUrl}");
			parent.$("#oriFileName").attr("value", "${image.oriFileName}");
			parent.$("#bigFileName").attr("value", "${image.bigFileName}");
			parent.$("#bigFileUrl").attr("value", "${image.bigFileUrl}");
			parent.$("#minFileName").attr("value", "${image.minFileName}");
			parent.$("#minFileUrl").attr("value", "${image.minFileUrl}");
			parent.window.hs.close();
		}, 1000);
	}
});
</script> 

上传成功，准备关闭...