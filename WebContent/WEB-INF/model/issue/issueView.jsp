<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>


<p>提问：${issue.name } 状态：${issue.answer == null? "未解决" :"已解决" }</p>
<p>补充说明：${issue.content }</p>
<p>悬赏财富：${issue.value }</p>
<p>拥有者 ：
	<c:choose>
		<c:when test="${issue.state == 1}">
			匿名提问
		</c:when>
		<c:otherwise>
			 ${issue.owner.name }
		</c:otherwise>
	</c:choose>
</p>
<p>提问日期：<fmt:formatDate value="${issue.airTime }" pattern="yyyy-MM-dd HH:mm" /></p>
<p>最佳答案：<br />
	<c:choose>
		<c:when test="${issue.answer == null}">
			<a target="content" href="<%=path %>/issue/goReplyIssue?id=${issue.id}&rid=-1">还没有最佳答案哦！我也来回答^o^</a> 
		</c:when>
		<c:otherwise>
			<p>
			${issue.answer.author.name }： ${issue.answer.content}
			<fmt:formatDate value="${issue.answer.time }" pattern="yyyy-MM-dd HH:mm" /><br/>
				<c:if test="${! empty issue.answer.reply}">
					<c:forEach items="${issue.answer.reply}" var="subreply">
						&nbsp;&nbsp;&nbsp;&nbsp; ${subreply.author.name }： ${subreply.content}
						<fmt:formatDate value="${subreply.time }" pattern="yyyy-MM-dd HH:mm" />
						<br />
					</c:forEach>
				</c:if>
			</p>
			<p>解决时间：<fmt:formatDate value="${issue.deadline }" pattern="yyyy-MM-dd HH:mm" /></p>
		</c:otherwise>
	</c:choose>
</p>

<br />
<c:if test="${issuedown != null and issue.owner.id == student.id}">
		相关内容:<br />
		<ul class="push"> 
		<c:forEach items="${issuedown}" var="issue">
				<li>#<a target="content" href="<%=path%>/issue/viewIssue?id=${issue.id }">${issue.name}</a></li>
		</c:forEach>
		</ul>
</c:if>
<br />
<c:if test="${issueup != null and issue.owner.id == student.id}">
		等待您来回答:<br />
		<ul class="push"> 
		<c:forEach items="${issueup}" var="issue">
				<li>*<a target="content" href="<%=path%>/issue/viewIssue?id=${issue.id }">${issue.name}</a></li>
		</c:forEach>
		</ul>
</c:if>
<br />
其它回答：<br />
<c:choose>
	<c:when test="${empty issue.reply}">
		<a target="content" href="<%=path %>/issue/goReplyIssue?id=${issue.id}&rid=-1">还没有评论哦！我来抢沙发^o^</a> 
	</c:when>
	<c:otherwise>
			<c:forEach items="${issue.reply}" var="reply">
				${reply.author.name }： ${reply.content}
				<fmt:formatDate value="${reply.time }" pattern="yyyy-MM-dd HH:mm" />
				<c:if test="${issue.answer == null}">
				<a target="content" href="<%=path %>/issue/goReplyIssue?id=${issue.id}&rid=${reply.id != null ? reply.id : -1 }">回复</a>
				</c:if>
				<c:if test="${issue.answer == null and issue.owner.id == student.id}">
				<a target="content" href="<%=path %>/issue/answerIssue?id=${issue.id}&rid=${reply.id != null ? reply.id : -1 }">答案</a> 
				</c:if><br />
					<c:if test="${! empty reply.reply}">
						<c:forEach items="${reply.reply}" var="subreply">
							&nbsp;&nbsp;&nbsp;&nbsp; ${subreply.author.name }： ${subreply.content}
							<fmt:formatDate value="${subreply.time }" pattern="yyyy-MM-dd HH:mm" />
							<br />
						</c:forEach>
					</c:if>
					<br/>-----------------------------------<br />
			</c:forEach>
	</c:otherwise>
</c:choose>
