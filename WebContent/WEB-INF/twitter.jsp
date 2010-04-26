<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
	String url = request.getServerName();
%>
	<div>
		<a target="content" href="<%=path %>/t/${people.username}"><img src="<%=path%>/avatar/${people.id}" width="124"></img><br/></a>
		${people.name }<br>
		<a target="content" href="<%=path %>/t/${people.username}">http://<%=url %>/t/${people.username}</a><br/>
		他广播的：<a target="content" href="<%=path %>/twitter/listOtherTwitter?otherId=${people.id}">${fn:length(pageBean.list)}</a>条 |
		他关注的：${fn:length(people.friends)}人 |
		关注他的：${fn:length(people.follower)}人 |
		最近来访：${fn:length(people.visitors)}人
		<br />
	 	<a target="content" href="<%=path%>/people/followPeople?id=${people.id}">
		 	<c:choose>
			 	<c:when test="${my:isMyFriend(friends,people)}">取消关注</c:when>
			 	<c:otherwise>设为关注</c:otherwise>
		 	</c:choose>
	 	</a>
	</div>
	
	<script type="text/javascript">
	$(function() {
		$("#topic_list").load("<%=path%>/twitter/listOtherTwitter?otherId=${people.id}");
	});
	</script>
	<div id="myTopic">
		<h2>他的广播：</h2>
		<div id="box">
			<div id="topic_list"></div>
		</div>
	</div>
	
	<br />
	
	 <div>
	 <br />
	 <p>他关注的：<br/>
			<c:forEach items="${people.friends}" var="friends">
			<ul class="ul">
				<li>
					<a target="content" href="<%=path%>/student/viewStudent?id=${friends.id }"><img src="<%=path %>/avatar/${friends.id}" width="50"></img></a><br/>
					<a target="content" href="<%=path%>/student/viewStudent?id=${friends.id }">${friends.name}</a><br/>
				</li>
			</ul>
			</c:forEach>
	 </p>
	 </div>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	 <div>
	 <p>最近访问：<br/>
			<c:forEach items="${people.visitors}" var="visitor">
			<ul class="ul">
				<li>
					<a target="content" href="<%=path%>/t/${visitor.people.username }"><img src="<%=path %>/avatar/${visitor.people.id}" width="50"></img></a><br/>
					<a target="content" href="<%=path%>/t/${visitor.people.username }">${visitor.people.name}</a><br/>
					<fmt:formatDate value="${visitor.time }" pattern="MM-dd" />	
				</li>
			</ul>
			</c:forEach>
	 </p>
	</div>
