<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>
<div>
<img src="<%=path %>/${school.avatar.minFileUrl}" width="80"></img><br/>
地区：${school.province.name }<br/>
学校名称：${school.name} <br/>
学校简介：${school.content} <br/>
学校地区：${school.address}<br/>
管理员:
		<c:forEach items="${school.admin}" var="admin">
				<a target="content" href="<%=path%>/student/viewStudent?id=${admin.id }">${admin.name}</a>
		</c:forEach>
		<br/>
	
<c:choose>
	<c:when test="${isAdmin}">
		学校管理：<br/>
		<a target="content" href="<%=path %>/school/goModifySchool?id=${school.id}">修改学校资料</a>
	</c:when>
	<c:otherwise>
		<a target="content" href="<%=path %>/school/joinAdminSchool?id=${school.id}">申请加入学校管理员</a>
	</c:otherwise>
</c:choose>

</div>
<div id="information">
	<c:if test="${student.classes == null}"><a class="letterspacing" target="content" href="<%=path %>/goPerfectReg">继续完善资料认识很多同学</a></c:if> 
	<c:if test="${student.classes != null and student.permission == 1}">你已申请加入“ ${student.classes.name} ”班级,审核中</c:if>
</div>
<div>
	这里是给学校Feed的
</div>
<div>
	<p>最近访问：<br/>
			<c:forEach items="${school.visitor}" var="visitor">
			<ul>
				<li>
					<a target="content" href="<%=path%>/student/viewStudent?id=${visitor.people.id }"><img src="<%=path %>/${visitor.people.avatar.minFileUrl}" width="50"></img></a><br/>
					<a target="content" href="<%=path%>/student/viewStudent?id=${visitor.people.id }">${visitor.people.name}</a><br/>
					<fmt:formatDate value="${visitor.time }" pattern="HH:mm MM-dd" />	
				</li>
			</ul>
			</c:forEach>
	</p>
</div>
<div>
	学校新人：<br/>
	<c:forEach items="${peoplenew}" var="people">
		<ul>
			<li>
				<a target="content" href="<%=path%>/student/viewStudent?id=${people.id }"><img src="<%=path %>/${people.avatar.minFileUrl}" width="50"></img></a><br/>
				<a target="content" href="<%=path%>/student/viewStudent?id=${people.id }">${people.name}</a><br/>
			</li>
		</ul>
	</c:forEach>
</div>
<div>
	人气王:<br/>
	<c:forEach items="${peoplehot}" var="people">
		<ul>
			<li>
				<a target="content" href="<%=path%>/student/viewStudent?id=${people.id }"><img src="<%=path %>/${people.avatar.minFileUrl}" width="50"></img></a><br/>
				<a target="content" href="<%=path%>/student/viewStudent?id=${people.id }">${people.name}</a><br/>
			</li>
		</ul>
	</c:forEach>
</div>

