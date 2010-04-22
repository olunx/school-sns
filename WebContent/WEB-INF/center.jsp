<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript">
	$(function() {
		$("#feed").load("<%=path%>/feed/listFeed", ajax);
		$("#box").load("<%=path%>/twitter/goAddTwitter", ajax);
		initHighslide("<%=path%>", "480", "400");

//	    $("a[target='box']").click(function(){
//	        var href = $(this).attr('href');
//	        $('#box').load(href);
//	        return false;
//	    });

	    $("a[target='box']").click(function() {
//	    	$('#box').load("<%=path%>/twitter/goAddTwitter", ajax);
	    	return hs.htmlExpand(this, { objectType: 'iframe', targetY: 'photo'} );
		});
		
	});
</script>
<div id="information">
	<c:if test="${student.classes == null}"><a target="content" href="<%=path %>/goPerfectReg">继续完善资料认识很多同学</a></c:if> 
	<c:if test="${student.classes != null and student.permission == 1}">你已申请加入“${student.classes.name}”班级,审核中</c:if>
</div>
消息：
<div id="news">
<div id="box">
</div>
<div>
 <a id="photo" target="box" href="<%=path%>/image/goUploadImage">图片</a>
 <a>视频</a>
 <a>链接</a>
 <a>文件 </a>
 <a id="vote" target="box" href="<%=path%>/vote/goAddVote">投票</a>
 <a id="issue" target="box" href="<%=path%>/issue/goAddIssue">问答</a>
 <a id="goods" target="box" href="<%=path%>/goods/goAddGoods">交换</a>
</div>
</div>
<h2>好友动态：</h2>
<div id="feed"></div>
