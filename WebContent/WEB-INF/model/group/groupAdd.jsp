<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript">
$(document).ready(function() {
	initHighslide("<%=path%>", "840", "640");
});
</script>
<h2 class="caption">添加群组</h2>
<form class="form" onSubmit="post(this);return false;" action="<%=path%>/group/addGroup" method="post">
	<p>
	<label> 小组名称： </label> <input type="text" name="group.name" />
	<s:fielderror><s:param>group.name</s:param></s:fielderror>
	</p>
	<p>
	<label> 简介： </label> <input type="text" name="group.intro" />
	<s:fielderror><s:param>group.intro</s:param></s:fielderror>
	</p>
	<p>
	<label> 作品： </label> <input type="text" name="group.works" />
	<s:fielderror><s:param>group.works</s:param></s:fielderror>
	</p>
	<p>
	<label> 类型： </label> <input type="text" name="group.type" />
	<s:fielderror><s:param>group.type</s:param></s:fielderror>
	</p>
	<a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>/image/goUploadImage">上传群组图片</a>
	<!-- 上传成功后，图片将插到这里。 -->
	<div id="pic">
	<img src="<%=path %>${people.avatar.minFileUrl}"></img>
	<input id="oriFileName" type="hidden" name="image.oriFileName" value="${group.pic.oriFileName}"/>
	<input id="bigFileName" type="hidden" name="image.bigFileName" value="${group.pic.bigFileName}"/>
	<input id="bigFileUrl" type="hidden" name="image.bigFileUrl" value="${group.pic.bigFileUrl}"/>
	<input id="minFileName" type="hidden" name="image.minFileName" value="${group.pic.minFileName}"/>
	<input id="minFileUrl" type="hidden" name="image.minFileUrl" value="${group.pic.minFileUrl}"/>
	<s:fielderror><s:param>image.oriFileName</s:param></s:fielderror>
	</div>
	<p class="paddingmin"><input type="submit" value="提交" /> <input type="reset" value="重置" /></p>
</form>
