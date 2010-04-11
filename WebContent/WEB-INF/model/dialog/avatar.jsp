<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!-- JQuery库 -->
<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>
<!-- colorbox -->
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-colorbox/colorbox.css" />
<script type="text/javascript" src="<%=path%>/content/jq-colorbox/jquery.colorbox-min.js"></script>

<!-- JQuery 图片选择 插件 -->
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-imgareaselect/css/imgareaselect-animated.css" />
<script type="text/javascript" src="<%=path%>/content/jq-imgareaselect/jquery.imgareaselect.min.js"></script>
<script type="text/javascript">
	function preview(img, selection) {
	    $('#x').val(selection.x1);
	    $('#y').val(selection.y1);
	    $('#width').val(selection.width);
	    $('#height').val(selection.height);    
	}

	$(document).ready(function() {
		$('#photo').imgAreaSelect( {
			aspectRatio : '',
			handles : true,
			fadeSpeed : 200,
			maxWidth: 120,
			maxHeight: 120,
			x1: 10,
			y1: 10,
			x2: 80,
			y2: 80,
			onSelectChange: preview
		});
	});

	function closes() {
		parent.$("*").colorbox.close();
		//$("*").colorbox.close();
	}
</script>
<c:if test="${image != null}">
	<img id="photo" src="<%=path%>${image.bigFilePath}" />
	<div>
		<form onSubmit="closes();" action="<%=path %>/avatar/modifyAvatar" method="post">
		 x1: <input type="text" id="x" name="x" value="0" />
		 y1: <input type="text" id="y" name="y" value="0" />
		 width: <input type="text" id="width" name="width" value="0" />
		 height: <input type="text" id="height" name="height" value="0" />
		 <input type="submit" value="确定" />
		 </form>
	</div>
</c:if>