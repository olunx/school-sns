<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript">
	$(function() {
		$("#feed").load("<%=path%>/feed/listFeed", ajax);
	});
</script>
微博：
<div id="twitter">
<form onSubmit="post(this);return false;" action="<%=path%>/topic/addMyTopic" method="post">
<label>发表：</label>
<div class="paddingmin"><textarea name="topic.content" id="demo" rows="10" cols="50" style="width: 500px; height: 150px"></textarea>
<br />
</div>
<a>图片    视频    链接    文件    投票</a>
<p class="paddingmin"><input type="submit" value="提交" /> <input type="reset" value="重置" /></p>
</form>
</div>
<br/><br/>
好友动态：
<div id="feed">

</div>
