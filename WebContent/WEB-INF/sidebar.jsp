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
<div id="recommend" class="mod">
<h2>推荐认识</h2>
	<c:if test="${student != null}">
				<ul>
		<c:if test="${peoplers != null}">
		<br /></>可能认识：<br />
			<c:forEach items="${peoplers}" var="peopler">
				<li>
					<a target="content" href="<%=path%>/student/viewStudent?id=${peopler.id }"><img src="<%=path %>/${peopler.avatar.minFileUrl}" width="50"></img></a><br/>
					<a target="content" href="<%=path%>/student/viewStudent?id=${peopler.id }">${peopler.name}</a><br/>
				</li>
			</c:forEach>
		</c:if>
		
		<c:if test="${schoolers != null}">
		<br />同校：<br />
			<c:forEach items="${schoolers}" var="schooler">
				<li>
					<a target="content" href="<%=path%>/student/viewStudent?id=${schooler.id }"><img src="<%=path %>/${schooler.avatar.minFileUrl}" width="50"></img></a><br/>
					<a target="content" href="<%=path%>/student/viewStudent?id=${schooler.id }">${schooler.name}</a><br/>
				</li>
			</c:forEach>
		</c:if>
		<c:if test="${classesers != null}">
		<br />同班：<br />
			<c:forEach items="${classesers}" var="classeser">
				<li>
					<a target="content" href="<%=path%>/student/viewStudent?id=${classeser.id }"><img src="<%=path %>/${classeser.avatar.minFileUrl}" width="50"></img></a><br/>
					<a target="content" href="<%=path%>/student/viewStudent?id=${classeser.id }">${classeser.name}</a><br/>
				</li>
			
			</c:forEach>
		</c:if>
			</ul>
		<br />
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