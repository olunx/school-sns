<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
%>

<c:choose><c:when test="${pageBean.list==null}">
<a>没有数据</a>
</c:when><c:otherwise><div id="topic_list"><c:forEach items="${pageBean.list}" var="topic"><div class="list">
<div class="avatar">
<c:choose>
	<c:when test="${topic.author.avatar.minFileUrl!=null}"><img src="<%=path%>${topic.author.avatar.minFileUrl}" /></c:when>
	<c:otherwise><img src="<%=path%>/content/images/avatar.jpg" /></c:otherwise>
</c:choose>
</div>
<div class="topic_msg">
<div class="time" title="${topic.time }">${my:formatDate(topic.time)}</div>
<p class="content"><a href="#">${topic.author.name}</a> ${topic.content}</p>
      		<div class="operate">
			<a onclick="ajaxload(this);return false;" href="<%=path%>/topic/deleteTopic?id=${topic.id }&page=${page}" class="btn_del">删除</a> <a onclick="ajaxload(this);return false;" href="<%=path%>/topic/goReplyTopic?id=${topic.id }">回复</a>
			</div>
            <c:choose>
				<c:when test="${topic.reply != null && fn:length(topic.reply)>0}">
 				<div class="reply"><c:forEach items="${topic.reply}" var="reply">
						<div class="reply_list">
						<div class="reply_avatar"><c:choose>
							<c:when test="${reply.author.avatar.minFileUrl!=null}">
								<img src="<%=path%>${reply.author.avatar.minFileUrl}" />
							</c:when>
							<c:otherwise>
								<img src="<%=path%>/content/images/avatar.jpg" />
							</c:otherwise>
						</c:choose></div>
						<p class="reply_content"><a href="#">${reply.author.name}</a> ${reply.content}<br />
						<span class="replytime">${my:formatDate(reply.time)}</span></p>
						</div>
						<div class="clear"></div>
					</c:forEach>
				</div>
				</c:when>
			</c:choose>
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
				<a onclick="ajaxload(this);return false;" href="<%=path%>/topic/listTopic?page=1"><span>首页</span></a>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/topic/listTopic?page=${pageBean.currentPage-1}"><span>上一页</span></a>
			</c:otherwise>
		</c:choose> <c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<a onclick="ajaxload(this);return false;" href="<%=path%>/topic/listTopic?page=${pageBean.currentPage+1}"><span>下一页</span></a>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/topic/listTopic?page=${pageBean.totalPage}"><span>尾页</span></a>
			</c:when>
			<c:otherwise>
				<a><span>下一页</span></a>
				<a><span>尾页</span></a>
			</c:otherwise>
		</c:choose>
		</div>
		
	</c:otherwise>
</c:choose>

