<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
%>

<div class="form">
<ul id="classnav" class="buttons nav">
	<li><a rel="classes_index" onclick="ajaxload(this);return false;" href="<%=path%>/classes/viewClasses?id=${id}">班级首页</a></li>
	<li><a rel="classes_fee" onclick="ajaxload(this);return false;" rev="#class" href="<%=path%>/classfee/listClassfee">查看班费</a></li>
	<li><a rel="classes_course" onclick="ajaxload(this);return false;" rev="#class" href="<%=path%>/course/listCourse">课程表</a></li>
	<li><a rel="classes_atten" onclick="ajaxload(this);return false;" rev="#class" href="<%=path%>/attendance/listAttendance">考勤记录</a></li>
	<li><a rel="classes_score" onclick="ajaxload(this);return false;" rev="#class" href="<%=path%>/score/queryScore">成绩</a></li>
	<li><a rel="classes_people" onclick="ajaxload(this);return false;" rev="#class" href="<%=path%>/people/classesPeople?id=${classes.id}">班级成员</a></li>
</ul>
<div class="clear"></div>
<div id="class">
<h2>${classes.name}(${classes.entryYear})</h2>
<p>
<label>管理员：</label>
		<c:forEach items="${classes.admins}" var="admin">
				<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${admin.username }">${admin.name}</a>
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
			<a onclick="ajaxload(this);return false;" href="<%=path %>/classes/joinAdminClasses?id=${classes.id}">申请加入班级管理员</a>
		</p>
	</c:otherwise>
</c:choose>

<c:choose>
<c:when test="${applicant!=null}">
	<div>
		申请入班级的人<br />
		<c:forEach items="${applicant}" var="people">
			<a onclick="ajaxload(this);return false;" href="<%=path%>/student/viewStudent?id=${people.id }">${people.name}</a>申请加入班级,
			<a onclick="ajaxload(this);return false;" href="<%=path%>/classes/auditClasses?id=${people.id }&audit=1">通过申请</a>，
			<a onclick="ajaxload(this);return false;" href="<%=path%>/classes/auditClasses?id=${people.id }&audit=0">拒绝申请</a>
			<br/>
		</c:forEach>
	</div>
</c:when>
</c:choose>
<div>
	<h2>班级留言</h2>
	<c:choose>
	<c:when test="${empty classes.replys}">
		<a onclick="ajaxload(this);return false;" href="<%=path %>/classes/goReplyClasses?id=${classes.id}&rid=-1">还没有留言哦！我来抢沙发^o^</a> 
	</c:when>
	<c:otherwise>
		<div class="class_msg_list">
		<c:forEach items="${classes.replys}" var="reply">
		<div class="class_msg">
			<div class="avatar">
				<img src="<%=path %>/avatar/${reply.author.id}" />
			</div>
			<div class="msg">
			<div class="operate">
			<p class="time" title="${reply.time }"><a onclick="ajaxload(this);return false;" href="<%=path %>/classes/goReplyClasses?id=${classes.id}&rid=${reply.id != null ? reply.id : -1 }">回复</a> ${my:formatDate(reply.time)}</p>
			</div>
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
		<div class="class_reply">
			<h2>留言</h2>
			<form onSubmit="post(this);return false;" action="<%=path%>/classes/replyClasses" method="post">
				<textarea name="reply.content" id="demo" rows="4" cols="50"></textarea>
				<p><input type="submit" value="发表留言" /> <input type="reset" value="重置" /></p>
				<input type="hidden" name="id" value="${id}" />
				<input type="hidden" name="rid" value="-1" />
			</form>
		</div>
	</c:otherwise>
</c:choose>
</div>

<!-- 班级的Feed -->
<h2>最新动态</h2>
<div id="feed" class="feed"></div>

	<div id="class_sidebar" style="display: block">
	<div>
	<h2>最近访问</h2>
		<ul class="imglist">
		<c:forEach items="${classes.visitors}" var="visitor">
			<li>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${visitor.people.username }"><img src="<%=path %>/avatar/${visitor.people.id}" width="50"></img></a>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${visitor.people.username }">${visitor.people.name}</a>
				<!--<fmt:formatDate value="${visitor.time }" pattern="MM-dd" />	-->
			</li>
		</c:forEach>
		</ul>
	<div class="clear"></div>
	</div>
	<div>
	<h2>班级新人</h2>
		<ul class="imglist">
		<c:forEach items="${peoplenew}" var="people">
			<li>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${people.username }"><img src="<%=path %>/avatar/${people.id}" width="50"></img></a>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${people.username }">${people.name}</a>
			</li>
		</c:forEach>
		</ul>
		<div class="clear"></div>
	</div>
	<div>
		<h2>人气王</h2>
		<ul class="imglist">
		<c:forEach items="${peoplehot}" var="people">
			<li>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${people.username }"><img src="<%=path %>/avatar/${people.id}" width="50"></img></a>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${people.username }">${people.name}</a>
			</li>
		</c:forEach>
		</ul>
		<div class="clear"></div>
	</div>
	</div>
</div>
</div>
<script type="text/javascript">
<!--
$(function(){
	updateSidebar();
	$("#feed").load("<%=path%>/feed/listClassFeed");
//	updateSidebar($("#class_sidebar").html(),true);
	if ($("#classnav").size()>1) $("#classnav:first").remove();
});
//-->
</script>