<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
	String url = request.getServerName();
%>
<link type="text/css" rel="stylesheet" href="<%=path%>/content/images/intro.css" />
<div id="LUI">
  <ul>
    <li class="detail">
      <h4><span class="userName">${people.name }</span><span> (性别：<c:choose><c:when test="${people.sex == 1}">男</c:when><c:otherwise>女</c:otherwise></c:choose> | 所在地：${people.school.province.name }) </span><br>
      <a class="link" onclick="ajaxload(this);return false;" href="<%=path %>/t/${people.username}">http://<%=url %>/t/${people.username}</a></h4>
      <div class="userNums">
      <a onclick="ajaxload(this);return false;" href="<%=path %>/t/${people.username}">广播<strong>${pageBean.allRow}</strong>条</a><span>|</span>
      <a onclick="ajaxload(this);return false;" href="<%=path %>/people/listFollowerPeople?id=${people.id}">听众<strong class="followNum" >${fn:length(people.follower)}</strong>人</a><span>|</span>
      <a onclick="ajaxload(this);return false;" href="<%=path %>/people/listFriendPeople?id=${people.id}">他收听<strong>${fn:length(people.friends)}</strong>人</a></div>
      <div class="funBox">
        <div class="left">
        <a onclick="ajaxload(this);return false;" href="<%=path%>/people/followPeople?id=${people.id}">
		 	<c:choose>
			 	<c:when test="${my:isMyFriend(friends,people)}">取消关注</c:when>
			 	<c:otherwise>设为关注</c:otherwise>
		 	</c:choose>
	 	</a>
        </div>
        <div class="right">
			<a>上次登陆：
			<c:if test="${people.lastlogin == null}">
				从未登陆过
			</c:if>
			<fmt:formatDate value="${people.lastlogin }" pattern="yyyy-MM-dd" />
			</a>
        </div>
      </div>
    </li>
    <li class="pic"><a onclick="ajaxload(this);return false;" href="<%=path %>/t/${people.username}"><img src="<%=path%>/avatar/${people.id}" width="124"></img></a></li>
  </ul>
</div>
	
		<h2>他的广播：</h2>
			<jsp:include page="./twitterList.jsp"></jsp:include>
	 <div>
	<h2>最近访问</h2> 
		<ul class="ul">
		<c:forEach items="${people.visitors}" var="visitor">
			<li>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${visitor.people.username }"><img src="<%=path %>/avatar/${visitor.people.id}" width="50"></img></a><br/>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${visitor.people.username }">${visitor.people.name}</a><br/>
				<fmt:formatDate value="${visitor.time }" pattern="MM-dd" />	
			</li>
		</c:forEach>
		</ul>
	</div>
