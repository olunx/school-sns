<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>
<div>
班级名称：${classes.name} <br/>
班级公告：${classes.intro} <br/>
入学年份：${classes.entryYear}<br/>
管理员:
		<c:forEach items="${classes.admins}" var="admin">
				<a target="content" href="<%=path%>/student/viewStudent?id=${admin.id }">${admin.name}</a>
		</c:forEach>
		<br/>
	
<c:choose>
	<c:when test="${isAdmin}">
		<form onSubmit="post(this);return false;" action="<%=path%>/classes/noticeClasses" method="post">
			<label>发布班级公告：</label>
			<input type="hidden" name="id" value="${classes.id}" />
			<div class="paddingmin"><textarea name="content" id="demo" rows="10" cols="50" style="width: 500px; height: 150px"></textarea>
			</div>
			<input type="submit" value="提交" /> <input type="reset" value="重置" />
		</form>
	</c:when>
	<c:otherwise>
		<a target="content" href="<%=path %>/classes/joinAdminClasses?id=${classes.id}">申请加入学校管理员</a>
	</c:otherwise>
</c:choose>
<div>
	<a target="content" href="<%=path%>/classfee/listClassfee">查看班费</a>
</div>
<div>
	申请入班级的人：
	<c:forEach items="${applicant}" var="people">
		<a target="content" href="<%=path%>/student/viewStudent?id=${people.id }">${people.name}</a>申请加入班级,
		<a target="content" href="<%=path%>/classes/auditClasses?id=${people.id }&audit=1">通过申请</a>，
		<a target="content" href="<%=path%>/classes/auditClasses?id=${people.id }&audit=0">拒绝申请</a>
	</c:forEach>
</div>
<div>
	班级留言：<br/>
	<c:choose>
	<c:when test="${empty classes.replys}">
		<a target="content" href="<%=path %>/classes/goReplyClasses?id=${classes.id}&rid=-1">还没有评论哦！我来抢沙发^o^</a> 
	</c:when>
	<c:otherwise>
			<c:forEach items="${classes.replys}" var="reply">
				${reply.author.name }： ${reply.content}
				<fmt:formatDate value="${reply.time }" pattern="yyyy-MM-dd HH:mm" />
				<a target="content" href="<%=path %>/classes/goReplyClasses?id=${classes.id}&rid=${reply.id != null ? reply.id : -1 }">回复</a> <br />
					<c:if test="${! empty reply.reply}">
						<c:forEach items="${reply.reply}" var="subreply">
							&nbsp;&nbsp;&nbsp;&nbsp; ${subreply.author.name }： ${subreply.content}
							<fmt:formatDate value="${subreply.time }" pattern="yyyy-MM-dd HH:mm" />
							<br />
						</c:forEach>
					</c:if>
			</c:forEach>
		<a target="content" href="<%=path %>/classes/goReplyClasses?id=${classes.id}&rid=-1">我也来说几句</a> 
	</c:otherwise>
</c:choose>
</div>
</div>
<div>
	这里是给班级Feed的
</div>
<div>
	<p>最近访问：<br/>
			<c:forEach items="${classes.visitors}" var="visitor">
			<ul>
				<li>
					<a target="content" href="<%=path%>/student/viewStudent?id=${visitor.people.id }"><img src="<%=path %>/${visitor.people.avatar.minFileUrl}" width="50"></img></a><br/>
					<a target="content" href="<%=path%>/student/viewStudent?id=${visitor.people.id }">${visitor.people.name}</a><br/>
					<fmt:formatDate value="${visitor.time }" pattern="HH:mm MM-dd" />	
				</li>
			</ul>
			</c:forEach>
	</p>
</div>
<div>
	班级新人：<br/>
	<c:forEach items="${peoplenew}" var="people">
		<ul>
			<li>
				<a target="content" href="<%=path%>/student/viewStudent?id=${people.id }"><img src="<%=path %>/${people.avatar.minFileUrl}" width="50"></img></a><br/>
				<a target="content" href="<%=path%>/student/viewStudent?id=${people.id }">${people.name}</a><br/>
			</li>
		</ul>
	</c:forEach>
</div>
<div>
	人气王:<br/>
	<c:forEach items="${peoplehot}" var="people">
		<ul>
			<li>
				<a target="content" href="<%=path%>/student/viewStudent?id=${people.id }"><img src="<%=path %>/${people.avatar.minFileUrl}" width="50"></img></a><br/>
				<a target="content" href="<%=path%>/student/viewStudent?id=${people.id }">${people.name}</a><br/>
			</li>
		</ul>
	</c:forEach>
</div>

