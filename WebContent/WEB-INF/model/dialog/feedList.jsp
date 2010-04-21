<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
%>
<c:choose>
	<c:when test="${pageBean.list == null}">
					没有数据！
	</c:when>
	<c:otherwise>
		<div class="feed">
		<c:forEach items="${pageBean.list}" var="feed">
			<div class="feed_list">
				<div class="time" title="${feed.time}">${my:formatDate(feed.time)}</div>
				<div class="msg"><a href="#">${feed.author.name}</a> 
				<c:choose>
					<c:when test="${feed.type ==  'add_twitter'}">
					叽叽歪歪的说：${feed.message}。
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
						创建了群组  
						<a target="content" href="<%=path %>/group/viewGroup?id=${feed.msgId}">${feed.message}</a> 。
					</c:when>
					<c:when test="${feed.type == 'join_group'}">
						加入了   
						<a target="content" href="<%=path %>/group/viewGroup?id=${feed.msgId}">${feed.message}</a>   小组。
					</c:when>
					<c:when test="${feed.type == 'add_friend'}">
						添加 了  
						<a target="content" href="<%=path %>/people/viewPeople?id=${feed.msgId}">${feed.message}</a>   为好友。
					</c:when>
					<c:when test="${feed.type == 'add_goods'}">
						有  
						<a target="content" href="<%=path %>/goods/viewGoods?id=${feed.msgId}">${feed.message}</a>   可以交换。
					</c:when>
					<c:when test="${feed.type == 'add_issue'}">
						发起问题  
						<a target="content" href="<%=path %>/issue/viewIssue?id=${feed.msgId}">${feed.message}</a>  。
					</c:when>
					<c:when test="${feed.type == 'add_vote'}">
						发起投票  
						<a target="content" href="<%=path %>/issue/viewIssue?id=${feed.msgId}">${feed.message}</a>  。
					</c:when>
					<c:otherwise>
						${feed.author.name} ${feed.type} ${feed.message}
					</c:otherwise>
				</c:choose>
				</div>
			</div>
		</c:forEach>
		</div>
		<div id="pagecount">
		<p>共  ${pageBean.allRow} 条记录 共 ${pageBean.totalPage} 页 当前第 ${pageBean.currentPage}页</p>
		<c:choose>
			<c:when test="${pageBean.currentPage == 1}">
				<a><span>首页</span></a>
				<a><span>上一页</span></a>
			</c:when>
			<c:otherwise>
				<a target="content" href="<%=path%>/feed/listFeed?page=1"><span>首页</span></a>
				<a target="content" href="<%=path%>/feed/listFeed?page=${pageBean.currentPage-1}"><span>上一页</span></a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<a target="content" href="<%=path%>/feed/listFeed?page=${pageBean.currentPage+1}"><span>下一页</span></a>
				<a target="content" href="<%=path%>/feed/listFeed?page=${pageBean.totalPage}"><span>尾页</span></a>
			</c:when>
			<c:otherwise>
				<a><span>下一页</span></a>
				<a><span>尾页</span></a>
			</c:otherwise>
		</c:choose>
		</div>
		
	</c:otherwise>
</c:choose>