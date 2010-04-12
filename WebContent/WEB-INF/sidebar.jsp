<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<div id="sidebar">
<div class="mod">
<h2>个人信息</h2>
<p>
什么东东都有。
</p>
</div>
<div class="mod">
<h2>参加的小组</h2>
<ul>
<c:if test="${student != null}">
	<c:if test="${student.group != null}">
		<c:forEach items="${student.group}" var="group">
			 <br/>
			 <li><a target="content" href="<%=path%>/group/viewGroup?id=${group.id}">${group.name}</a></li>
		</c:forEach>
	</c:if>
</c:if>
</ul>
</div>

<div class="mod">
<h2>我的好友</h2>
<ul>
<c:if test="${student != null}">
	<c:if test="${student.friends != null}">
		<c:forEach items="${student.friends}" var="friend">
			 <br/>
			 <li><a target="content" href="<%=path%>/student/viewStudent?id=${friend.id}">${friend.name}</a></li>
		</c:forEach>
	</c:if>
</c:if>
</ul>
</div>

</div>