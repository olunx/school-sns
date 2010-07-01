<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://gdpu.cn/functions" prefix="my"%>
<%
	String path = request.getContextPath();
%>

<h2>共同好友</h2>
<c:choose>
	<c:when test="${mutualfriend == null}">
					暂无共同好友！
	</c:when>
	<c:otherwise>
	<ul class="imglist">
		<c:forEach items="${mutualfriend}" var="mf">
			<li>
			<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${mf.people.username }"><img src="<%=path %>/avatar/${mf.people.id}" width="50"></img></a>
			<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${mf.people.username }">${mf.people.name}</a>|${fn:length(mf.mutual)}
			</li>
		</c:forEach>
	</ul>
	</c:otherwise>
</c:choose>

<h2>可能认识</h2>
<c:choose>
	<c:when test="${maybeMeet == null}">
					暂无可认识的好友！
	</c:when>
	<c:otherwise>
	<ul class="imglist">
		<c:forEach items="${maybeMeet}" var="peopler">
			<li>
			<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${peopler.username }"><img src="<%=path %>/avatar/${peopler.id}" width="50"></img></a>
			<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${peopler.username }">${peopler.name}</a>
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
	<c:otherwise>
	${people.school.name }的同学们<br/><br/>
	<ul class="imglist">
		<c:forEach items="${schoolmates}" var="peopler" varStatus="i">
			<c:if test="${i.count == 6}"><br/><br/><br/><br/><br/><br/></c:if>
			<li>
			<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${peopler.username }"><img src="<%=path %>/avatar/${peopler.id}" width="50"></img></a>
			<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${peopler.username }">${peopler.name}</a>
			</li>
		</c:forEach>
	</ul>
	</c:otherwise>
</c:choose>
