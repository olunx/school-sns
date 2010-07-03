<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
%>

<script type="text/javascript">
	$(function() {
		listMore("#more_list", "#feed");
	});
</script>
<c:choose>
	<c:when test="${pageBean.list == null}">
					没有数据。
	</c:when>
	<c:otherwise>
		<c:forEach items="${pageBean.list}" var="beans">
			<div class="list clearfix">
		<c:forEach items="${beans.list}" var="feed" varStatus="status">
			<c:choose>
			<c:when test="${status.index == 0}"><!-- 只显示一次 -->
			<div class="avatar"><img src="<%=path%>/avatar/${feed.author.id}" /></div>
			<a class="name" onclick="ajaxload(this);return false;" href="<%=path%>/t/${feed.author.username}">${feed.author.name}</a>
			</c:when>
			</c:choose>
			<div class="msg">
			<c:choose>
				<c:when test="${feed.type ==  'add_twitter'}">
						叽叽歪歪的说：${feed.message}
						 <span class="time" title="${feed.time}">${my:formatDate(feed.time)}</span>
				</c:when><c:when test="${feed.type ==  'add_link'}">
						分享了链接：${feed.link}<br />并叽歪的说：${feed.message}
						 <span class="time" title="${feed.time}">${my:formatDate(feed.time)}</span>
				</c:when><c:when test="${feed.type ==  'add_video'}">
						分享了视频：${feed.link}<br />并叽歪的说：${feed.message}
						 <span class="time" title="${feed.time}">${my:formatDate(feed.time)}</span>
				</c:when><c:when test="${feed.type == 'reply_twitter'}">
						回复了微博：${feed.message}
						 <span class="time" title="${feed.time}">${my:formatDate(feed.time)}</span>
				</c:when><c:when test="${feed.type ==  'add_topic'}">
						 发表了主题：${feed.message}
						  <span class="time" title="${feed.time}">${my:formatDate(feed.time)}</span>
				</c:when><c:when test="${feed.type == 'reply_topic'}">
						回复了  ${feed.message}  主题
						 <span class="time" title="${feed.time}">${my:formatDate(feed.time)}</span>
				</c:when><c:when test="${feed.type == 'add_group'}">
						创建了小组 
						<a onclick="ajaxload(this);return false;" rev="#content" href="<%=path%>/group/viewGroup?id=${feed.msgId}">${feed.message}</a> 。
						 <span class="time" title="${feed.time}">${my:formatDate(feed.time)}</span>
				</c:when><c:when test="${feed.type == 'join_group'}">
						加入了   
						<a onclick="ajaxload(this);return false;" rev="#content" href="<%=path%>/group/viewGroup?id=${feed.msgId}">${feed.message}</a>   小组。
						 <span class="time" title="${feed.time}">${my:formatDate(feed.time)}</span>
				</c:when><c:when test="${feed.type == 'add_friend'}">
						关注 
						<a onclick="ajaxload(this);return false;" rev="#content" href="<%=path%>/t/${feed.whose.username}">${feed.message}</a> ，并表示希望成为TA的忠实fans。
						 <span class="time" title="${feed.time}">${my:formatDate(feed.time)}</span>
				</c:when><c:when test="${feed.type == 'del_friend'}">
						不想再关注  
						<a onclick="ajaxload(this);return false;" rev="#content" href="<%=path%>/t/${feed.whose.username}">${feed.message}</a>了。
						 <span class="time" title="${feed.time}">${my:formatDate(feed.time)}</span>
				</c:when><c:when test="${feed.type == 'add_goods'}">
						有  
						<a onclick="ajaxload(this);return false;" rev="#content" href="<%=path%>/goods/viewGoods?id=${feed.msgId}">${feed.message}</a>  可以交换。
						 <span class="time" title="${feed.time}">${my:formatDate(feed.time)}</span>
				</c:when><c:when test="${feed.type == 'add_issue'}">
						发起问题  
						<a onclick="ajaxload(this);return false;" rev="#content" href="<%=path%>/issue/viewIssue?id=${feed.msgId}">${feed.message}</a>  。
						 <span class="time" title="${feed.time}">${my:formatDate(feed.time)}</span>
				</c:when><c:when test="${feed.type == 'add_vote'}">
						发起投票  
						<a onclick="ajaxload(this);return false;" rev="#content" href="<%=path%>/vote/goVotingVote?vid=${feed.msgId}">${feed.message}</a>  。
						 <span class="time" title="${feed.time}">${my:formatDate(feed.time)}</span>
					</c:when>
				<c:otherwise>
						${feed.author.name} ${feed.type} ${feed.message}
					</c:otherwise>
			</c:choose>
			</div>
		</c:forEach>
			</div>
		</c:forEach>

		<div id="more_list"><c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<div class="buttons">
				<a class="regular long center" target="list" href="<%=path%>/feed/${moreAction }?page=${pageBean.currentPage+1}" >更多...</a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="buttons">
				<a class="negative long center" href="#" onClick="$.scrollTo(0 , 800 );">没有了！回到顶部</a>
				</div>
			</c:otherwise>
		</c:choose>
		</div>

	</c:otherwise>
</c:choose>
