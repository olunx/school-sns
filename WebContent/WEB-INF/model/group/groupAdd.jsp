<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
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
	hs.width = 680;
	hs.height = 450;
});
</script>
<h2 class="caption">添加群组</h2>
<form onSubmit="post(this);return false;" action="<%=path%>/group/addGroup" method="post">
<p><label> 小组名称： </label> <input type="text" name="group.name" /></p>
<p><label> 简介： </label> <input type="text" name="group.intro" /></p>
<p><label> 作品： </label> <input type="text" name="group.works" /></p>
<p><label> 类型： </label> <input type="text" name="group.type" /></p>
<a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>/image/goUploadImage">上传群组图片</a>
<!-- 上传成功后，图片将插到这里。 -->
<div id="pic">
<img src="<%=path %>${people.avatar.minFileUrl}"></img>
<input id="oriFileName" type="hidden" name="image.oriFileName" value="${group.pic.oriFileName}"/>
<input id="bigFileName" type="hidden" name="image.bigFileName" value="${group.pic.bigFileName}"/>
<input id="bigFileUrl" type="hidden" name="image.bigFileUrl" value="${group.pic.bigFileUrl}"/>
<input id="minFileName" type="hidden" name="image.minFileName" value="${group.pic.minFileName}"/>
<input id="minFileUrl" type="hidden" name="image.minFileUrl" value="${group.pic.minFileUrl}"/>
</div>
<p class="paddingmin"><input type="submit" value="提交" /> <input type="reset" value="重置" /></p>
</form>
