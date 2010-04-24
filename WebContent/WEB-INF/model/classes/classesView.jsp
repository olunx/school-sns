<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript">
<!--
$(function(){
	updateSidebar();
	updateSidebar($("#class_sidebar").html(),true);
});
//-->
</script>
<div class="form">
<ul class="nav">
	<li><a target="content" href="<%=path%>/classes/viewClasses?id=${id}">班级首页</a></li>
	<li><a target="content" href="<%=path%>/classfee/listClassfee">查看班费</a></li>
	<li><a class="letterspacing" target="content" href="<%=path%>/course/listCourse">课程表</a></li>
	<li><a class="letterspacing" target="content" href="<%=path%>/attendance/listAttendance">考勤记录</a></li>
</ul>
<div class="clear"></div>
<p><label>班级名称：</label>${classes.name} <label>入学年份：</label>${classes.entryYear}</p>
<p>
<label>管理员：</label>
		<c:forEach items="${classes.admins}" var="admin">
				<a target="content" href="<%=path%>/student/viewStudent?id=${admin.id }">${admin.name}</a>
		</c:forEach>
</p>
<p><label>班级公告：</label>${classes.intro} </p>
<c:choose>
	<c:when test="${isAdmin}">
		<form class="form" onSubmit="post(this);return false;" action="<%=path%>/classes/noticeClasses" method="post">
		<p><label>发布公告：</label><input class="w_middle" type="text" name="content"/><input type="hidden" name="id" value="${classes.id}" /><input type="submit" value="发布" /></p>
		
		</form>
	</c:when>
	<c:otherwise>
		<p>
			<a target="content" href="<%=path %>/classes/joinAdminClasses?id=${classes.id}">申请加入班级管理员</a>
		</p>
	</c:otherwise>
</c:choose>

<c:choose>
<c:when test="${applicant!=null}">
	<div>
		申请入班级的人：<br />
		<c:forEach items="${applicant}" var="people">
			<a target="content" href="<%=path%>/student/viewStudent?id=${people.id }">${people.name}</a>申请加入班级,
			<a target="content" href="<%=path%>/classes/auditClasses?id=${people.id }&audit=1">通过申请</a>，
			<a target="content" href="<%=path%>/classes/auditClasses?id=${people.id }&audit=0">拒绝申请</a>
			<br/>
		</c:forEach>
	</div>
</c:when>
</c:choose>
<div>
	<h2>班级留言：</h2>
	<c:choose>
	<c:when test="${empty classes.replys}">
		<a target="content" href="<%=path %>/classes/goReplyClasses?id=${classes.id}&rid=-1">还没有留言哦！我来抢沙发^o^</a> 
	</c:when>
	<c:otherwise>
		<div class="class_msg_list">
		<c:forEach items="${classes.replys}" var="reply">
		<div class="class_msg">
			<div class="avatar">
				<c:choose>
					<c:when test="${reply.author.avatar.minFileUrl!=null}"><img src="<%=path %>/${reply.author.avatar.minFileUrl}" /></c:when>
					<c:otherwise><img src="<%=path%>/content/images/avatar.jpg" /></c:otherwise>
				</c:choose>
			</div>
			<div class="operate">
			<p class="time" title="${reply.time }"><a target="content" href="<%=path %>/classes/goReplyClasses?id=${classes.id}&rid=${reply.id != null ? reply.id : -1 }">回复</a> ${my:formatDate(reply.time)}</p>
			</div>
			<div class="msg">
				<p class="text">${reply.author.name }： ${reply.content}</p>
				
				<c:if test="${! empty reply.reply}">
				<div class="reply">
					<c:forEach items="${reply.reply}" var="subreply">
					<p>
						${subreply.author.name }： ${subreply.content}
						${my:formatDate(subreply.time)}
					</p>
					</c:forEach>
				</div>
				</c:if>
			</div>
		<div class="clear"></div>
		</div>
		</c:forEach>
		</div>
		<a target="content" href="<%=path %>/classes/goReplyClasses?id=${classes.id}&rid=-1">我也来说几句</a> 
	</c:otherwise>
</c:choose>
</div>
</div>
<div>
	这里是给班级Feed的
</div>

<div id="class_sidebar" style="display: none;">
<div class="mod">
<h2>最近访问：</h2>
	<ul>
	<c:forEach items="${classes.visitors}" var="visitor">
		<li>
			<a target="content" href="<%=path%>/t/${visitor.people.username }"><img src="<%=path %>/${visitor.people.avatar.minFileUrl}" width="50"></img></a>
			<a target="content" href="<%=path%>/t/${visitor.people.username }">${visitor.people.name}</a>
			<fmt:formatDate value="${visitor.time }" pattern="MM-dd" />	
		</li>
	</c:forEach>
	</ul>
<div class="clear"></div>
</div>
<div class="mod">
<h2>班级新人：</h2>
	<ul>
	<c:forEach items="${peoplenew}" var="people">
		<li>
			<a target="content" href="<%=path%>/t/${people.username }"><img src="<%=path %>/${people.avatar.minFileUrl}" width="50"></img></a>
			<a target="content" href="<%=path%>/t/${people.username }">${people.name}</a>
		</li>
	</c:forEach>
	</ul>
	<div class="clear"></div>
</div>
<div class="mod">
	<h2>人气王:</h2>
	<ul>
	<c:forEach items="${peoplehot}" var="people">
		<li>
			<a target="content" href="<%=path%>/t/${people.username }"><img src="<%=path %>/${people.avatar.minFileUrl}" width="50"></img></a>
			<a target="content" href="<%=path%>/t/${people.username }">${people.name}</a>
		</li>
	</c:forEach>
	</ul>
	<div class="clear"></div>
</div>
</div>

