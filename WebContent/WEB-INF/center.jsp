<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript">
	$(function() {
		$("#box").load("<%=path%>/topic/goAddTopic", ajax);
		$("#feed").load("<%=path%>/feed/listFeed", ajax);

	});
</script>
<div id="information">
	<c:if test="${student.classes == null}"><a class="letterspacing" target="content" href="<%=path %>/goPerfectReg">继续完善资料认识很多同学</a></c:if> 
	<c:if test="${student.classes != null and student.permission == 1}">你已申请加入“${student.classes.name}”班级,审核中</c:if>
</div>
消息：
<div id="news">
<div id="box"></div>
<div>
 <a>微博</a>
 <a>图片</a>
 <a>视频</a>
 <a>链接</a>
 <a>文件 </a>
 <a id="vote" onclick="return hs.htmlExpand(this, { objectType: 'iframe', targetY: 'vote'} )" href="<%=path%>/vote/goAddVote">投票</a>
 <a id="issue" onclick="return hs.htmlExpand(this, { objectType: 'iframe', targetY: 'issue' } )" href="<%=path%>/issue/goAddIssue">问答</a>
 <a id="goods" onclick="return hs.htmlExpand(this, { objectType: 'iframe', targetY: 'goods' } )" href="<%=path%>/goods/goAddGoods">交换</a>
</div>
</div>
<br/><br/>
好友动态：
<div id="feed"></div>
