<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript">
	$(function() {
		$("#feed").load("<%=path%>/feed/listFeed", ajax);
		initHighslide("<%=path%>", "480", "400");
	});
</script>
<div id="information">
	<c:if test="${student.classes == null}"><a target="content" href="<%=path %>/goPerfectReg">继续完善资料认识很多同学</a></c:if> 
	<c:if test="${student.classes != null and student.permission == 1}">你已申请加入“${student.classes.name}”班级,审核中</c:if>
</div>
消息：
<div id="news">
<div id="box">
<form onSubmit="post(this);return false;" action="<%=path%>/twitter/addTwitter" method="post">
<label>发表：</label>
<div class="paddingmin"><textarea name="twitter.content" id="demo" rows="10" cols="50" style="width: 500px; height: 150px"></textarea>
<br />
<!-- 上传成功后，图片将插到这里。 -->
<input id="oriFileName" type="hidden" name="image.oriFileName" value=""/>
<input id="bigFileName" type="hidden" name="image.bigFileName" value=""/>
<input id="bigFileUrl" type="hidden" name="image.bigFileUrl" value=""/>
<input id="minFileName" type="hidden" name="image.minFileName" value=""/>
<input id="minFileUrl" type="hidden" name="image.minFileUrl" value=""/>
</div>
<p class="paddingmin">
<input type="submit" value="提交" />
<input type="reset" value="重置" /></p>
</form>
</div>
<div id="pic">
	<img src=""></img></div>
<div>
 <a>微博</a>
 <a id="photo" onclick="return hs.htmlExpand(this, { objectType: 'iframe', targetY: 'photo'} )" href="<%=path%>/image/goUploadImage">图片</a>
 <a>视频</a>
 <a>链接</a>
 <a>文件 </a>
 <a id="vote" onclick="return hs.htmlExpand(this, { objectType: 'iframe', targetY: 'vote'} )" href="<%=path%>/vote/goAddVote">投票</a>
 <a id="issue" onclick="return hs.htmlExpand(this, { objectType: 'iframe', targetY: 'issue' } )" href="<%=path%>/issue/goAddIssue">问答</a>
 <a id="goods" onclick="return hs.htmlExpand(this, { objectType: 'iframe', targetY: 'goods' } )" href="<%=path%>/goods/goAddGoods">交换</a>
</div>
</div>
<h2>好友动态：</h2>
<div id="feed"></div>
