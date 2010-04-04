<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-uploadify/uploadify.css" />
<script type="text/javascript" src="<%=path%>/content/jq-uploadify/jquery.uploadify.v2.1.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/content/jq-uploadify/swfobject.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#uploadify").uploadify({
		'uploader'		: '<%=path%>/content/jq-uploadify/uploadify.swf',
		'script'		: '<%=path%>/score/scoreUpload',
		'cancelImg'		: '<%=path%>/content/jq-uploadify/cancel.png',
		'fileDataName'	: 'files',
		'mothod'		: 'POST',
		'queueID'		: 'fileQueue',
		'buttonText'	: 'OPEN',
		'auto'			: false,
		'multi'			: false,
		'onComplete'	: function() {
			$('#content').load('<%=path%>/score/listScore', ajax);
		}
	});
});
</script>
<style type="text/css">
#fileQueue {
	width: 380px;
	height: 70px;
	overflow: auto;
	border: 1px solid #E5E5E5;
	margin-bottom: 10px;
}
</style>

<br/>
<br/>
		<h2 class="caption">批量导入学生成绩：</h2>
		<div id="fileQueue"></div>
		<input id="uploadify" type="file" name="files"/><br/>
		<a href="javascript:$('#uploadify').uploadifyUpload();"">开始上传</a>
		<p><a href="javascript:$('#uploadify').uploadifyClearQueue()">取消</a></p>
