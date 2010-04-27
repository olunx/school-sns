<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
%>
<!-- <link type="text/css" rel="stylesheet" href="<%=path%>/content/images/layout.css" /> -->
<div class="issue_question">
<h2 class="title">[${issue.answer == null? "未解决" :"已解决" }]${issue.name }</h2>
<p class="q_user">提问者 ：<c:choose><c:when test="${issue.state == 1}">匿名提问</c:when><c:otherwise>${issue.owner.name }</c:otherwise></c:choose></p>
<p><span class="money">悬赏：${issue.value }朵小红花</span>  <span class="time">${my:formatDate(issue.airTime)}</span></p>
<p class="content">${issue.content }</p>
<c:if test="${issue.answer == null}">
	<a target="content" href="<%=path %>/issue/goReplyIssue?id=${issue.id}&rid=-1">我来回答</a> 
</c:if>
</div>
	<c:choose>
		<c:when test="${issue.answer != null}">
		<div class="issue_best">
			<h2>最佳答案</h2>
			<p>
			${issue.answer.author.name }： ${issue.answer.content} 
			</p>
			<span class="time">${my:formatDate(issue.answer.time) }</span>
			<!-- 
				<c:if test="${! empty issue.answer.reply}">
					<c:forEach items="${issue.answer.reply}" var="subreply">
						&nbsp;&nbsp;&nbsp;&nbsp; ${subreply.author.name }： ${subreply.content}
						<fmt:formatDate value="${subreply.time }" pattern="yyyy-MM-dd HH:mm" />
						<br />
					</c:forEach>
				</c:if>
			
			<p>解决时间：<fmt:formatDate value="${issue.deadline }" pattern="yyyy-MM-dd HH:mm" /></p>
			 -->
		</div>
	</c:when>
	</c:choose>

<c:if test="${issuedown != null and issue.owner.id == student.id}">
		<h2>相关内容:</h2>
		<ul class="push"> 
		<c:forEach items="${issuedown}" var="issue">
				<li>#<a target="content" href="<%=path%>/issue/viewIssue?id=${issue.id }">${issue.name}</a></li>
		</c:forEach>
		</ul>
</c:if>
<c:if test="${issueup != null and issue.owner.id == student.id}">
		<h2>等待您来回答:</h2>
		<ul class="push"> 
		<c:forEach items="${issueup}" var="issue">
				<li>*<a target="content" href="<%=path%>/issue/viewIssue?id=${issue.id }">${issue.name}</a></li>
		</c:forEach>
		</ul>
</c:if>
<div class="issue_otherans">
<h2>其它回答</h2>
<c:choose>
	<c:when test="${empty issue.reply}">
		<a target="content" href="<%=path %>/issue/goReplyIssue?id=${issue.id}&rid=-1">还没有回答哦！我来抢沙发^o^</a> 
	</c:when>
	<c:otherwise>
			<c:forEach items="${issue.reply}" var="reply">
			<div class="list">
				<div class="avatar">
					<img src="<%=path %>/avatar/${reply.author.id}" />
				</div>
				<div class="text">
					<p>${reply.author.name }： ${reply.content}</p>
					<span class="time">${my:formatDate(reply.time) }</span>
					<!-- 
					<c:if test="${! empty reply.reply}">
						<c:forEach items="${reply.reply}" var="subreply">
							&nbsp;&nbsp;&nbsp;&nbsp; ${subreply.author.name }： ${subreply.content}
							<span class="time">${my:formatDate(subreply.time) }</span>
							<br />
						</c:forEach>
					</c:if>
					-->
					<div class="operate">
					<!-- 
						<c:if test="${issue.answer == null}">
						<a target="content" href="<%=path %>/issue/goReplyIssue?id=${issue.id}&rid=${reply.id != null ? reply.id : -1 }">回复</a>
						</c:if>
					-->
						<c:if test="${issue.answer == null and issue.owner.id == student.id}">
						<a target="content" href="<%=path %>/issue/answerIssue?id=${issue.id}&rid=${reply.id != null ? reply.id : -1 }">设为最佳答案</a> 
						</c:if>
					</div>
				</div>
				<div class="clear"></div>
			</div>
			</c:forEach>
	</c:otherwise>
</c:choose>
</div>
