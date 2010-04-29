<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String url = request.getServerName();
%>
<div id="sidebar">
	<div class="mod search">
	<form action="<%=path %>/people/searchPeople" method="post" onSubmit="post(this);return false;"  Class="form" >
		<input id="keyword" type="text" name="search"/>
		<input id="searchbtn" type="submit" value="搜索"/>
	</form>
	</div>
	<div class="mod userDetail">
		<div class="avatar">
			<a target="content" href="<%=path %>/t/${student.username}"><img src="<%=path%>/avatar/${student.id}"></img></a>
		</div>
		<dl class="myinfo">
			<dt>我的广播</dt>
			<dd><a target="content" href="<%=path %>/t/${student.username}">${twittersize}</a></dd>
			<dt>我的粉丝</dt>
			<dd><a target="content" href="<%=path %>/people/listFollowerPeople">${fn:length(student.follower)}</a></dd>
		</dl>
		<div class="myotherinfo">
			<p>${student.name }</p>
			<p>性别：<c:choose><c:when test="${student.sex == 1}">男</c:when><c:otherwise>女</c:otherwise></c:choose> 所在地：${student.school.province.name }
			</p>
			<p>我关注的：<a target="content" href="<%=path %>/people/listFriendPeople">${fn:length(student.friends)}</a>人 | 最近来访：<a target="content" href="<%=path %>/people/listVisitorPeople">${fn:length(student.visitors)}</a>人
			</p>
		</div>
	</div>
	<c:if test="${student.classes == null}">
	<div class="mod" id="information">
		<a target="content" href="<%=path %>/goPerfectReg">继续完善资料</a>
		<c:if test="${student.classes != null and student.permission == 1}">你已申请加入“${student.classes.name}”班级,审核中</c:if>
	</div>
	</c:if> 
	<div id="widget">
	</div>
	<c:if test="${student != null}">
		<c:if test="${maybeMeet != null}">
	<div id="recommend" class="mod clearfix">
	<h2>推荐认识</h2>
	<ul class="imglist">
	<c:forEach items="${maybeMeet}" var="peopler" end="5">
		<li>
			<a target="content" href="<%=path%>/t/${peopler.username }"><img src="<%=path %>/avatar/${peopler.id}" width="50"></img></a>
			<a target="content" href="<%=path%>/t/${peopler.username }">${peopler.name}</a>
		</li>
	</c:forEach>
	</ul>
	</div>
		</c:if>
</c:if>

<div class="mod">
<h2>参加的小组</h2>
<ul>
<c:if test="${student != null}">
	<c:choose>
		<c:when test="${empty student.groups}">
			<li>你还没有加入任何小组</li>
		</c:when>
		<c:otherwise>
			<c:forEach items="${student.groups}" var="group">
				 <li><a target="content" href="<%=path%>/group/viewGroup?id=${group.id}">${group.name}</a></li>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</c:if>
</ul>
</div>

<div class="mod clearfix">
<h2>我的好友</h2>
	<c:if test="${student != null &&student.friends != null}">
		<ul class="imglist">
		<c:forEach items="${student.friends}" var="friend">
			<li>
				<a target="content" href="<%=path%>/t/${friend.username }"><img src="<%=path %>/avatar/${friend.id}" width="50"></img></a><br/>
				<a target="content" href="<%=path%>/t/${friend.username }">${friend.name}</a><br/>
			</li>
		</c:forEach>
		</ul>
	</c:if>
</div>
	<c:if test="${!empty student && !empty student.visitors}">
	<div class="mod clearfix">
	<h2>最近访问</h2>
	<ul class="imglist">
	<c:forEach items="${student.visitors}" var="visitor">
		<li title="<fmt:formatDate value="${visitor.time }" pattern="MM-dd hh:mm" />">
			<a target="content" href="<%=path%>/t/${visitor.people.username }"><img src="<%=path %>/avatar/${visitor.people.id}"/></a>
			<a target="content" href="<%=path%>/t/${visitor.people.username }">${visitor.people.name}</a>
		</li>
	</c:forEach>
	</ul>
	</div>
	</c:if>

</div>