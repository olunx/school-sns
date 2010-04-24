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
					<a target="content" href="<%=path%>/student/viewStudent?id=${peopler.id }"><img src="<%=path %>/${peopler.avatar.minFileUrl}" width="50"></img></a><br/>
					<a target="content" href="<%=path%>/student/viewStudent?id=${peopler.id }">${peopler.name}</a><br/>
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