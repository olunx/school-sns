<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
%>

<script type="text/javascript" src="<%=path%>/content/jq-ui/jquery-ui-1.8.custom.min.js"></script>
<script type="text/JavaScript">
$(document).ready(function() {
    hs.graphicsDir = '<%=path%>/content/jq-highslide/graphics/';
    hs.align = 'center';
    hs.outlineType = 'rounded-white';
    hs.wrapperClassName = 'draggable-header';
    hs.transitions = ['expand', 'crossfade'];
	hs.useBox = true;
	hs.width = 680;
	hs.height = 450;
});
</script>
<form action="<%=path %>/school/modifySchool" method="post" onSubmit="post(this);return false;"  Class="form" >
	<input type="hidden" name="id" value="${id }"/>
	<p><label>上传校徽：</label></p>
	<a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>/image/goUploadImage">上传图片</a>
	<!-- 上传成功后，图片将插到这里。 -->
	<div id="pic">
		<img src=""></img>
		<input id="oriFileName" type="hidden" name="image.oriFileName" value=""/>
		<input id="bigFileName" type="hidden" name="image.bigFileName" value=""/>
		<input id="bigFileUrl" type="hidden" name="image.bigFileUrl" value=""/>
		<input id="minFileName" type="hidden" name="image.minFileName" value=""/>
		<input id="minFileUrl" type="hidden" name="image.minFileUrl" value=""/>
	</div>
	<p><label>学校简介：</label>
	<input type="text" name="content" />
	</p>
	<p><label>学校地址：</label>
	<input type="text" name="address" />
	</p>
	<input type="submit" value="提交问题"/>
</form>