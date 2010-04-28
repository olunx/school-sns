<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path%>/content/js/jquery.scrollTo-min.js"></script>
<script type="text/javascript">
$(function() {
	listMore("#more_list", "#topic_list");
});
</script>
<c:choose>
	<c:when test="${pageBean.list == null}">
					没有数据！
	</c:when>
	<c:otherwise>
		<c:forEach items="${pageBean.list}" var="feed">
			<div class="list">
			<div class="avatar"><img src="<%=path%>/avatar/${feed.author.id}" /></div>
			<div class="topic_msg">
			<div class="time" title="${feed.time}">${my:formatDate(feed.time)}</div>
			<p class="content"><a target="content" href="<%=path%>/t/${feed.author.username}">${feed.author.name}</a> <c:choose>
				<c:when test="${feed.type ==  'add_twitter'}">
						叽叽歪歪的说：${feed.message}。
					</c:when>
				<c:when test="${feed.type ==  'add_link'}">
						分享了链接：${feed.link}<br />并叽歪的说：${feed.message}
					</c:when>
				<c:when test="${feed.type ==  'add_video'}">
						分享了视频：${feed.link}<br />并叽歪的说：${feed.message}
					</c:when>
				<c:when test="${feed.type == 'reply_twitter'}">
						回复了微博   ${feed.message}。
					</c:when>
				<c:when test="${feed.type ==  'add_topic'}">
						 发表了主题：${feed.message}。
					</c:when>
				<c:when test="${feed.type == 'reply_topic'}">
						回复了   ${feed.message}  主题。
					</c:when>
				<c:when test="${feed.type == 'add_group'}">
						创建了小组  
						<a target="content" href="<%=path%>/group/viewGroup?id=${feed.msgId}">${feed.message}</a> 。
					</c:when>
				<c:when test="${feed.type == 'join_group'}">
						加入了   
						<a target="content" href="<%=path%>/group/viewGroup?id=${feed.msgId}">${feed.message}</a>   小组。
					</c:when>
				<c:when test="${feed.type == 'add_friend'}">
						添加 了  
						<a target="content" href="<%=path%>/people/viewPeople?id=${feed.msgId}">${feed.message}</a>   为好友。
					</c:when>
				<c:when test="${feed.type == 'add_goods'}">
						有  
						<a target="content" href="<%=path%>/goods/viewGoods?id=${feed.msgId}">${feed.message}</a>   可以交换。
					</c:when>
				<c:when test="${feed.type == 'add_issue'}">
						发起问题  
						<a target="content" href="<%=path%>/issue/viewIssue?id=${feed.msgId}">${feed.message}</a>  。
					</c:when>
				<c:when test="${feed.type == 'add_vote'}">
						发起投票  
						<a target="content" href="<%=path%>/vote/goVotingVote?vid=${feed.msgId}">${feed.message}</a>  。
					</c:when>
				<c:otherwise>
						${feed.author.name} ${feed.type} ${feed.message}
					</c:otherwise>
			</c:choose></p>
			</div>
			</div>
			<div class="clear "></div>
			<div class="linedot"></div>
		</c:forEach>

		<div id="more_list"><c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<div class="buttons">
				<a class="regular long center" target="list" href="<%=path%>/feed/listSchoolFeed?page=${pageBean.currentPage+1}" >更多...</a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="buttons">
				<a class="negative long center" href="#" onClick="$.scrollTo(0 , 800 );">没有了！回到顶部</a>
				</div>
			</c:otherwise>
		</c:choose></div>

	</c:otherwise>
</c:choose>
