<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<form onSubmit="post(this);return false;" action="<%=path%>/group/replyGroup" method="post">
<label>回复：</label>
<div class="paddingmin"><textarea name="topic.content" id="demo" rows="10" cols="50" style="width: 500px; height: 150px"></textarea>
<br />
</div>
<p class="paddingmin"><input type="submit" value="提交" /> <input type="reset" value="重置" />
<input type="hidden" name="id" value="${id}" />
<input type="hidden" name="topicId" value="${topicId}" />
</form>