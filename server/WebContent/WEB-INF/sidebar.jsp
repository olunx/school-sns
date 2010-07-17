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
			<a onclick="ajaxload(this);return false;" href="<%=path %>/t/${people.username}"><img src="<%=path%>/avatar/${people.id}"></img></a>
		</div>
		<dl class="myinfo">
			<dt><a onclick="ajaxload(this);return false;" href="<%=path %>/t/${people.username}">我的广播</a></dt>
			<dd><a onclick="ajaxload(this);return false;" href="<%=path %>/t/${people.username}">${twittersize}</a></dd>
			<dt><a onclick="ajaxload(this);return false;" href="<%=path %>/people/listFollowerPeople">我的听众</a></dt>
			<dd><a onclick="ajaxload(this);return false;" href="<%=path %>/people/listFollowerPeople">${fn:length(people.follower)}</a></dd>
		</dl>
		<div class="myotherinfo">
			<p>${student.name }</p>
			<p>性别：<c:choose><c:when test="${student.sex == 1}">男</c:when><c:otherwise>女</c:otherwise></c:choose> 所在地：${people.school.province.name }
			</p>
			<p><a onclick="ajaxload(this);return false;" href="<%=path %>/people/listFriendPeople">我关注的：${fn:length(people.friends)}人</a>  | <a onclick="ajaxload(this);return false;" href="<%=path %>/people/listVisitorPeople">最近来访：${fn:length(people.visitors)}人</a></p>
		</div>
	</div>
	<c:if test="${people.classes == null}">
	<div class="mod" id="information">
		<c:if test="${people.school != null}"><a onclick="ajaxload(this);return false;" href="<%=path %>/goPerfectReg">继续完善资料</a></c:if>
		<c:if test="${people.classes != null and people.permission == 1}">你已申请加入“${people.classes.name}”班级,审核中</c:if>
	</div>
	</c:if> 
	<div id="widget">
	</div>
	<c:if test="${people != null}">
		<c:if test="${maybeMeet != null}">
	<div id="recommend" class="mod clearfix">
	<h2>推荐认识</h2>
	<p><a onclick="ajaxload(this);return false;" href="<%=path %>/friend/viewFriend">更多</a></p>
	<ul class="imglist">
	<c:forEach items="${maybeMeet}" var="peopler" end="3">
		<li>
			<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${peopler.username }"><img src="<%=path %>/avatar/${peopler.id}" width="50"></img></a>
			<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${peopler.username }">${peopler.name}</a>
		</li>
	</c:forEach>
	</ul>
	<ul class="imglist">
	<c:forEach items="${mutualfriend}" var="mf" end="3">
		<li>
			<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${mf.people.username }"><img src="<%=path %>/avatar/${mf.people.id}" width="50"></img></a>
			<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${mf.people.username }">${mf.people.name}</a>|${fn:length(mf.mutual)}
		</li>
	</c:forEach>
	</ul>
	</div>
		</c:if>
</c:if>

<div class="mod">
<h2>参加的小组</h2>
<ul>
<c:if test="${people != null}">
	<c:choose>
		<c:when test="${empty people.groups}">
			<li>你还没有加入任何小组</li>
		</c:when>
		<c:otherwise>
			<c:forEach items="${people.groups}" var="group">
				 <li><a onclick="ajaxload(this);return false;" href="<%=path%>/group/viewGroup?id=${group.id}">${group.name}</a></li>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</c:if>
</ul>
</div>

<div class="mod clearfix">
<h2>我的好友</h2>
	<c:if test="${people != null && people.friends != null}">
		<ul class="imglist">
		<c:forEach items="${people.friends}" var="friend">
			<li>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${friend.username }"><img src="<%=path %>/avatar/${friend.id}" width="50"></img></a><br/>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${friend.username }">${friend.name}</a><br/>
			</li>
		</c:forEach>
		</ul>
	</c:if>
</div>
	<c:if test="${!empty people && !empty people.visitors}">
	<div class="mod clearfix">
	<h2>最近访问</h2>
	<ul class="imglist">
	<c:forEach items="${people.visitors}" var="visitor">
		<li title="<fmt:formatDate value="${visitor.time }" pattern="MM-dd hh:mm" />">
			<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${visitor.people.username }"><img src="<%=path %>/avatar/${visitor.people.id}"/></a>
			<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${visitor.people.username }">${visitor.people.name}</a>
		</li>
	</c:forEach>
	</ul>
	</div>
	</c:if>

</div>