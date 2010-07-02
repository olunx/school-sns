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
	<ul class="friendlist">
		<c:forEach items="${mutualfriend}" var="mf">
			<li>
			<div style="float: left"><a onclick="ajaxload(this);return false;" href="<%=path%>/t/${mf.people.username }"><img src="<%=path %>/avatar/${mf.people.id}" width="50"></img></a></div>
			<div style="float: left"><a onclick="ajaxload(this);return false;" href="<%=path%>/t/${mf.people.username }">${mf.people.name}</a><br/>
			共同好友${fn:length(mf.mutual)}<br/>
			<a onclick="ajaxload(this);return false;" href="<%=path%>/people/followPeople?id=${mf.people.id}&page=${page}">
					 <c:choose>
					 	<c:when test="${my:isMyFriend(friends,mf.people)}">删除好友</c:when>
					 	<c:otherwise>加为好友</c:otherwise>
					 </c:choose>
			</a>
			</div>
			</li>
		</c:forEach>
	</ul>
	</c:otherwise>
</c:choose>

<h2>你可能认识他们（${maybeMeet == null? 0 : fn:length(maybeMeet)}）</h2>
<c:choose>
	<c:when test="${maybeMeet == null}">
					暂无可认识的好友！
	</c:when>
	<c:otherwise>
	<ul class="friendlist">
		<c:forEach items="${maybeMeet}" var="peopler">
			<li>
			<div style="float: left"><a onclick="ajaxload(this);return false;" href="<%=path%>/t/${peopler.username }"><img src="<%=path %>/avatar/${peopler.id}" width="50"></img></a></div>
			<div style="float: left"><a onclick="ajaxload(this);return false;" href="<%=path%>/t/${peopler.username }">${peopler.name}</a><br/>
			<a onclick="ajaxload(this);return false;" href="<%=path%>/people/followPeople?id=${people.id}&page=${page}">
					 <c:choose>
					 	<c:when test="${my:isMyFriend(friends,people)}">删除好友</c:when>
					 	<c:otherwise>加为好友</c:otherwise>
					 </c:choose>
			</a>
			</div>
			</li>
		</c:forEach>
	</ul>
	</c:otherwise>
</c:choose>

<h2>你的同学们</h2>
<c:choose>
	<c:when test="${schoolmates == null}">
					暂无同班好友！
	</c:when>
	<c:when test="${people.institute == null}">
					<a onclick="ajaxload(this);return false;" href="<%=path %>/goPerfectReg">继续完善资料,认识更多同学</a>
	</c:when>
	<c:when test="${institutemates == null}">
					暂无同一学院的好友！
	</c:when>
	<c:otherwise>
		<div>
			${people.school.name }的同学们<br/><br/>
			<ul class="friendlist">
				<c:forEach items="${schoolmates}" var="peopler" varStatus="i">
					<c:if test="${i.count == 6}"><br/><br/><br/><br/><br/><br/></c:if>
					<li>
					<div style="float: left"><a onclick="ajaxload(this);return false;" href="<%=path%>/t/${peopler.username }"><img src="<%=path %>/avatar/${peopler.id}" width="50"></img></a></div>
					<div style="float: left"><a onclick="ajaxload(this);return false;" href="<%=path%>/t/${peopler.username }">${peopler.name}</a><br/>
					<a onclick="ajaxload(this);return false;" href="<%=path%>/people/followPeople?id=${people.id}&page=${page}">
						 <c:choose>
						 	<c:when test="${my:isMyFriend(friends,people)}">删除好友</c:when>
						 	<c:otherwise>加为好友</c:otherwise>
						 </c:choose>
					</a>
					</div>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div>
			${people.school.name } - ${people.institute.name }的同学们<br/><br/>
			<ul class="friendlist">
				<c:forEach items="${institutemates}" var="peopler" varStatus="i">
					<c:if test="${i.count == 6}"><br/><br/><br/><br/><br/><br/></c:if>
					<li>
					<div style="float: left"><a onclick="ajaxload(this);return false;" href="<%=path%>/t/${peopler.username }"><img src="<%=path %>/avatar/${peopler.id}" width="50"></img></a></div>
					<div style="float: left"><a onclick="ajaxload(this);return false;" href="<%=path%>/t/${peopler.username }">${peopler.name}</a><br/>
					<a onclick="ajaxload(this);return false;" href="<%=path%>/people/followPeople?id=${people.id}&page=${page}">
						 <c:choose>
						 	<c:when test="${my:isMyFriend(friends,people)}">删除好友</c:when>
						 	<c:otherwise>加为好友</c:otherwise>
						 </c:choose>
					</a>
					</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</c:otherwise>
</c:choose>
