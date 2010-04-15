<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript">
	$(function() {
		$("#box").load("<%=path%>/topic/goAddTopic", ajax);
		$("#feed").load("<%=path%>/feed/listFeed", ajax);

	    $("a[target='box']").click(function(){
	        var href = $(this).attr('href');
	        $('#box').css("height", $('#box').css("height"));
	        $('#box').fadeOut("normal", function(){
	        	$('#box').load(href);
	        	$('#box').css("height", "auto").fadeIn("normal");
			});
	        return false;
	    });
	});
</script>
消息：
<div id="news">
<div id="box"></div>
<div>
 <a target="box" href="<%=path%>/topic/goAddTopic">微博</a>
 <a>图片</a>
 <a>视频</a>
 <a>链接</a>
 <a>文件 </a>
 <a target="box" href="<%=path%>/vote/goAddVote">投票</a>
 <a target="box" href="<%=path%>/issue/goAddIssue">问答</a>
 <a target="box" href="<%=path%>/goods/goAddGoods">交换</a>
</div>
</div>
<br/><br/>
好友动态：
<div id="feed">

</div>
