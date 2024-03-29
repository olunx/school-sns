<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript">
$(document).ready(function() {
	initHighslide("<%=path%>", "840", "640");
});
</script>

<h2 class="caption">修改群组</h2>
<form onSubmit="post(this);return false;" action="<%=path%>/group/modifyGroup" method="post">
<p><label> 小组名称： </label> <input type="text" name="group.name" value="${group.name}" /></p>
<p><label> 简介： </label> <input type="text" name="group.intro" value="${group.intro}" /></p>
<p><label> 作品： </label> <input type="text" name="group.works" value="${group.works}" /></p>
<p><label> 类型： </label> <input type="text" name="group.type" value="${group.type}" /></p>
<p><label> 成员： </label>
<c:if test="${group.members != null}">
	<c:forEach items="${group.members}" var="people">
		<br/><input type="checkbox" name="ids" value="${people.id}" />${people.name}
	</c:forEach>
</c:if>
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
</div>
<p class="paddingmin"><input type="submit" value="提交" /> <input type="reset" value="重置" /></p>
<input type="hidden" name="group.id" value="${group.id}"/>
</form>
