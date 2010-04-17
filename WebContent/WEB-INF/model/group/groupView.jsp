<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>
<h2 class="caption">查看群组</h2>
<p><label> 小组名称： </label> <input type="text" name="group.name" value="${group.name}" /></p>
<p><label> 简介： </label> <input type="text" name="group.intro" value="${group.intro}" /></p>
<p><label> 图片： </label> <input type="text" name="group.pic" value="${group.pic}" /></p>
<p><label> 作品： </label> <input type="text" name="group.works" value="${group.works}" /></p>
<p><label> 类型： </label> <input type="text" name="group.type" value="${group.type}" /></p>
<p><label> 管理员： </label> <input type="text" name="group.admin" value="${group.admin.name}" /></p>
<p><label> 成员： </label>
<c:if test="${group.members != null}">
	<c:forEach items="${group.members}" var="people">
		<br/><input type="text" value="${people.name}" />
	</c:forEach>
</c:if>
</p>

<br />
群组话题：<br />
<a target="content" href="<%=path %>/group/goAddTopicGroup?id=${group.id}">添加话题。</a>
<br />
<c:choose>
	<c:when test="${empty group.post}">
		该群组还没有话题。
	</c:when>
	<c:otherwise>
			<c:forEach items="${group.post}" var="post">
				<div class="box">
					<div class="left">
				        <div class="avatar">${post.title}</div>
				        <div class="name">${post.author.name }</div>
				    </div>
				    <div class="center">
				        <div class="text"> 内容： ${post.content}<br/></>时间: <fmt:formatDate value="${post.time }" pattern="yyyy-MM-dd HH:mm" /></div>
						<c:choose>
							<c:when test="${post.reply != null}">
								<c:forEach items="${post.reply}" var="reply">
									<div class="post">${reply.author.name} 回复： ${reply.content}</div>
								</c:forEach>
							</c:when>
						</c:choose>
				    </div>
				    <div class="right">
				        <div class="reply"><a target="content" href="<%=path %>/topic/goReplyTopic?id=${post.id}">回复</a></div>
				        <input type="checkbox" name="ids" value="" />
				    </div>
				</div>
			</c:forEach>
	</c:otherwise>
</c:choose>