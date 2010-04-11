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

<br/><br/><br/>

<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-highslide/highslide.css" />
<script type="text/javascript" src="<%=path%>/content/jq-highslide/highslide-full.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    hs.graphicsDir = '<%=path%>/content/jq-highslide/graphics/';
    hs.align = 'center';
    hs.outlineType = 'rounded-white';
    hs.wrapperClassName = 'draggable-header';
    hs.transitions = ['expand', 'crossfade'];
	hs.useBox = true;
	hs.width = 640;
	hs.height = 480;
});
</script>
<a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>/avatar/goAddAvatar">上传头像</a>

