<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!-- JQuery UI 插件 -->
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-ui/jquery-ui-1.8.custom.css" />
<script type="text/javascript" src="<%=path%>/content/jq-ui/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#datepicker").datepicker( {
			dateFormat : 'yy-mm-dd',
			dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
			firstDay : 1,
			monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
			showMonthAfterYear: true,
			minDate : new Date()
		});

	});
</script>
<p>日期: <input type="text" id="datepicker" size="30" /></p>

测试主页
<a rel="ajax" href="<%=path%>/test/goModifyTest">修改</a>

<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-uploadify/uploadify.css" />
<script type="text/javascript" src="<%=path%>/content/jq-uploadify/jquery.uploadify.v2.1.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/content/jq-uploadify/swfobject.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#uploadify").uploadify({
		'uploader'		: '<%=path%>/content/jq-uploadify/uploadify.swf',
		'script'		: '<%=path%>/img/avatarUpload',
		'cancelImg'		: '<%=path%>/content/jq-uploadify/cancel.png',
		'fileDataName'	: 'files',
		'mothod'		: 'POST',
		'queueID'		: 'fileQueue',
		'buttonText'	: 'OPEN',
		'auto'			: false,
		'multi'			: false,
		'onComplete'	: function() {
			$('#content').load('<%=path%>/img/selectUpload', ajax);
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
<h2 class="caption">上传头像：</h2>
<div id="fileQueue"></div>
<input id="uploadify" type="file" name="files"/><br/>
<a href="javascript:$('#uploadify').uploadifyUpload();"">开始上传</a>
<p><a href="javascript:$('#uploadify').uploadifyClearQueue()">取消</a></p>


<form action="<%=path%>/img/avatarUpload" enctype="multipart/form-data" method="post">
<input id="uploadify" type="file" name="files"/><br/>
<input type="submit" value="sure"></input>
</form>