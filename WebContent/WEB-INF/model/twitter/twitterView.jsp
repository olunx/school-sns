<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
%>
	<div>
		<a href="<%=path %>/t/${people.username}"><img src="<%=path%>/avatar/${people.id}" width="124"></img><br/></a>
		${people.name }<br>
		<a href="<%=path %>/t/${people.username}"><%=path %>/t/${people.username}</a><br/>
		他广播的：${fn:length(pageBean.list)}条 |
		他关注的：${fn:length(people.friends)}人 |
		关注他的：${fn:length(people.follower)}人 |
		最近来访：${fn:length(people.visitors)}人
		<br />
		
		 <c:choose>
		 	<c:when test="${my:isMyFriend(friends,people)}">取消关注</c:when>
		 	<c:otherwise>设为关注</c:otherwise>
		 </c:choose>
	</div>
	
	<div>
		这里是他的发表
		<c:choose>
		<c:when test="${pageBean.list==null}">
		<a>没有数据</a>
		</c:when>
		<c:otherwise>
			<div id="topic_list"><c:forEach items="${pageBean.list}" var="twitter"><div class="list">
			<div class="avatar">
				<img src="<%=path%>/avatar/${twitter.author.id}" />
			</div>
			<div class="topic_msg">
			<div class="time" title="${twitter.time }">${my:formatDate(twitter.time)}</div>
			<p class="content"><a href="#">${twitter.author.name}</a> ${twitter.content}
			<c:if test="${!empty twitter.image && !empty twitter.image.minFileUrl}"><img src="<%=path %>${twitter.image.minFileUrl}" /></c:if>
			</p>
			      		<div class="operate">
						<a target="content" href="<%=path%>/twitter/deleteTwitter?id=${twitter.id }&page=${page}" class="btn_del">删除</a> <a target="content" href="<%=path%>/twitter/goReplyTwitter?id=${twitter.id }">回复</a>
						</div>
			            <c:choose>
							<c:when test="${twitter.reply != null && fn:length(twitter.reply)>0}">
			 				<div class="reply"><c:forEach items="${twitter.reply}" var="reply">
									<div class="reply_list">
									<div class="reply_avatar">
										<img src="<%=path%>/avatar/${reply.author.id}" />
									</div>
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
					<a target="content" href="<%=path%>/twitter/listTwitter?page=1"><span>首页</span></a>
					<a target="content" href="<%=path%>/twitter/listTwitter?page=${pageBean.currentPage-1}"><span>上一页</span></a>
				</c:otherwise>
			</c:choose> <c:choose>
				<c:when test="${pageBean.currentPage != pageBean.totalPage}">
					<a target="content" href="<%=path%>/twitter/listTwitter?page=${pageBean.currentPage+1}"><span>下一页</span></a>
					<a target="content" href="<%=path%>/twitter/listTwitter?page=${pageBean.totalPage}"><span>尾页</span></a>
				</c:when>
				<c:otherwise>
					<a><span>下一页</span></a>
					<a><span>尾页</span></a>
				</c:otherwise>
			</c:choose>
			</div>
			
		</c:otherwise>
		</c:choose>
	</div>
	<br />
	 <div>
	 <br />
	 <p>他关注的：<br/>
			<c:forEach items="${people.friends}" var="friends">
			<ul>
				<li>
					<a target="content" href="<%=path%>/student/viewStudent?id=${friends.id }"><img src="<%=path %>/avatar/${friends.id}" width="50"></img></a><br/>
					<a target="content" href="<%=path%>/student/viewStudent?id=${friends.id }">${friends.name}</a><br/>
				</li>
			</ul>
			</c:forEach>
	 </p>
	 </div>
	 
	 <div>
	 <p>最近访问：<br/>
			<c:forEach items="${people.visitors}" var="visitor">
			<ul>
				<li>
					<a target="content" href="<%=path%>/student/viewStudent?id=${visitor.people.id }"><img src="<%=path %>/avatar/${visitor.people.id}" width="50"></img></a><br/>
					<a target="content" href="<%=path%>/student/viewStudent?id=${visitor.people.id }">${visitor.people.name}</a><br/>
					<fmt:formatDate value="${visitor.time }" pattern="HH:mm MM-dd" />	
				</li>
			</ul>
			</c:forEach>
	 </p>
	</div>
