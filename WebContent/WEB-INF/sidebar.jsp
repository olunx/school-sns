<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String url = request.getServerName();
%>
<div id="sidebar">
<div class="mod">
<div>
<form action="<%=path %>/people/searchPeople" method="post" onSubmit="post(this);return false;"  Class="form" >
	<input type="text" name="search">
	<input type="submit" value="搜索">
</form>
</div>
<br/>
<div class="mod">
		<a target="content" href="<%=path %>/t/${student.username}"><img src="<%=path%>/avatar/${student.id}" width="104"></img><br/></a>
		${student.name }<br>
		<a target="content" href="<%=path %>/t/${student.username}">http://<%=url %>/t/${student.username}</a><br/>
		他广播的：<a target="content" href="<%=path %>/twitter/listMyTwitter">${fn:length(pageBean.list)}</a>条 |
		他关注的：${fn:length(student.friends)}人 |
		关注他的：${fn:length(student.follower)}人 |
		最近来访：${fn:length(student.visitors)}人
		<br />
</div>
<p>
什么东东都有。
</p>
</div>
<div id="widget">
	<div id="information">
		<c:if test="${student.classes == null}"><a target="content" href="<%=path %>/goPerfectReg">继续完善资料认识很多同学</a></c:if> 
		<c:if test="${student.classes != null and student.permission == 1}">你已申请加入“${student.classes.name}”班级,审核中</c:if>
	</div>
</div>
<div id="recommend" class="mod">
<h2>推荐认识</h2>
	<c:if test="${student != null}">
		<c:if test="${maybeMeet != null}">
			<c:forEach items="${maybeMeet}" var="peopler" end="5">
			<ul>
				<li>
					<a target="content" href="<%=path%>/t/${peopler.username }"><img src="<%=path %>/avatar/${peopler.id}" width="50"></img></a><br/>
					<a target="content" href="<%=path%>/t/${peopler.username }">${peopler.name}</a><br/>
				</li>
			</ul>
			</c:forEach>
		</c:if>
		
</c:if>

</div>
<div class="mod">
<h2>参加的小组</h2>
<ul>
<c:if test="${student != null}">
	<c:if test="${student.groups != null}">
		<c:forEach items="${student.groups}" var="group">
			 <br/>
			 <li><a target="content" href="<%=path%>/group/viewGroup?id=${group.id}">${group.name}</a></li>
		</c:forEach>
	</c:if>
</c:if>
</ul>
</div>

<div class="mod">
<h2>我的好友</h2>
<c:if test="${student != null}">
	<c:if test="${student.friends != null}">
		<c:forEach items="${student.friends}" var="friend">
			 <ul>
				<li>
					<a target="content" href="<%=path%>/t/${friend.username }"><img src="<%=path %>/avatar/${friend.id}" width="50"></img></a><br/>
					<a target="content" href="<%=path%>/t/${friend.username }">${friend.name}</a><br/>
				</li>
			</ul>
		</c:forEach>
	</c:if>
</c:if>
</div>

<div class="mod">
<h2>最近访问：</h2>
<c:forEach items="${student.visitors}" var="visitor">
	<ul class="ul">
		<li>
			<a target="content" href="<%=path%>/t/${visitor.people.username }"><img src="<%=path %>/avatar/${visitor.people.id}" width="50"></img></a><br/>
			<a target="content" href="<%=path%>/t/${visitor.people.username }">${visitor.people.name}</a><br/>
			<fmt:formatDate value="${visitor.time }" pattern="MM-dd" />	
		</li>
	</ul>
</c:forEach>
</div>

</div>