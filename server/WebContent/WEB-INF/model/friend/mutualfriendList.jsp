<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://gdpu.cn/functions" prefix="my"%>
<%
	String path = request.getContextPath();
%>

<h2>他们和你有共同好友（${mutualfriend == null? 0 : fn:length(mutualfriend)}）<c:if test="${mutualfriend != null}"><a onclick="ajaxload(this);return false;" href="<%=path%>/friend/mutualFriend">全部</a></c:if></h2>
<c:choose>
	<c:when test="${mutualfriend == null}">
					暂无共同好友！
	</c:when>
	<c:otherwise>
		<c:forEach items="${mutualfriend}" var="mf">
		<div class="ilist clearfix">
			<div style="float: left"><a onclick="ajaxload(this);return false;" href="<%=path%>/t/${mf.people.username }"><img src="<%=path %>/avatar/${mf.people.id}" width="80"></img></a></div>
			<div style="float: left"><a onclick="ajaxload(this);return false;" href="<%=path%>/t/${mf.people.username }">${mf.people.name}</a><br/>
			现居${mf.people.school.province.name} | ${mf.people.school.name}<br/>
			<a onclick="ajaxload(this);return false;" href="<%=path%>/people/followPeople?id=${mf.people.id}&page=${page}">
					 <c:choose>
					 	<c:when test="${my:isMyFriend(friends,mf.people)}">删除好友</c:when>
					 	<c:otherwise>加为好友</c:otherwise>
					 </c:choose>
			</a>
			</div>
				<div style="float: left">共同好友${fn:length(mf.mutual)}<br/></div>
				<c:forEach items="${mf.mutual}" var="people">
				<div style="float: left">
				<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${people.username }"><img src="<%=path %>/avatar/${people.id}" width="50"></img></a><br/>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${people.username }">${people.name}</a><br/>
				</div>
			</c:forEach>
		</div>
			<br/><br/>
		</c:forEach>
	</c:otherwise>
</c:choose>

