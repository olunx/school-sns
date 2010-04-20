<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>

<c:choose><c:when test="${pageBean.list==null}">
<a>没有数据</a>
</c:when><c:otherwise><div id="topic_list"><c:forEach items="${pageBean.list}" var="topic"><div class="list">
<div class="avatar"><img src="<%=path %>${topic.author.avatar.minFileUrl}" /></div>
<div class="topic_msg">
<div class="time"><fmt:formatDate value="${topic.time}" pattern="yyyy-MM-dd HH:mm" /></div>
<p><a href="#">${topic.author.name}</a> ${topic.content}</p>
      		<div class="operate"><a target="content" href="<%=path%>/topic/goReplyTopic?id=${topic.id }">回复</a>
			<a target="content" href="<%=path%>/topic/deleteTopic?id=${topic.id }&page=${page}" class="btn_del">删除</a></div>
 		<div class="reply_list">
            <c:choose>
				<c:when test="${topic.reply != null}">
					<c:forEach items="${topic.reply}" var="reply">
						<div class="post">${reply.author.name} 回复： ${reply.content}</div>
					</c:forEach>
				</c:when>
			</c:choose>
		</div>
</div>
</div>
		<div class="clear "></div>
		<div class="linedot">　</div>
		</c:forEach>
		</div>
		
		<div id="pagecount" style="margin:5px;float:left;">
		<p>共 ${pageBean.allRow} 条记录 共 ${pageBean.totalPage} 页 当前第 ${pageBean.currentPage}页</p>
		<c:choose>
			<c:when test="${pageBean.currentPage == 1}">
				<a><span>首页</span></a>
				<a><span>上一页</span></a>
			</c:when>
			<c:otherwise>
				<a target="content" href="<%=path%>/topic/listTopic?page=1"><span>首页</span></a>
				<a target="content" href="<%=path%>/topic/listTopic?page=${pageBean.currentPage-1}"><span>上一页</span></a>
			</c:otherwise>
		</c:choose> <c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<a target="content" href="<%=path%>/topic/listTopic?page=${pageBean.currentPage+1}"><span>下一页</span></a>
				<a target="content" href="<%=path%>/topic/listTopic?page=${pageBean.totalPage}"><span>尾页</span></a>
			</c:when>
			<c:otherwise>
				<a><span>下一页</span></a>
				<a><span>尾页</span></a>
			</c:otherwise>
		</c:choose>
		</div>
		
	</c:otherwise>
</c:choose>

