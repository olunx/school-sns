<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript">
<!--
$(function(){
	updateSidebar();
});
//-->
</script>
<div class="school form">
<div class="school_pic">
<img src="<%=path %>/${school.avatar.minFileUrl}" width="80"/>
</div>
<p>学校名称：${school.name}</p>
<p>学校简介：${school.content}</p>
<p>学校地址：${school.address}</p>
<p>
管理员：
	<c:forEach items="${school.admin}" var="admin">
			<a target="content" href="<%=path%>/student/viewStudent?id=${admin.id }">${admin.name}</a>
	</c:forEach>
</p>
<p>
<c:choose>
	<c:when test="${isAdmin}">
		学校管理：<br/>
		<a target="content" href="<%=path %>/school/goModifySchool?id=${school.id}">修改学校资料</a>
	</c:when>
	<c:otherwise>
		<a target="content" href="<%=path %>/school/joinAdminSchool?id=${school.id}">申请加入学校管理员</a>
	</c:otherwise>
</c:choose>
| <a target="content" href="<%=path%>/classes/viewClasses?id=${student.classes.id}">我的班级</a>
| <a target="content" href="<%=path%>/school/listSchool">访问其它学校</a>
| <a target="content" href="<%=path%>/people/schoolPeople?id=${school.id}">学校成员</a>
</p>
</div>
<div id="information">
	<c:if test="${student.classes == null}"><a class="letterspacing" target="content" href="<%=path %>/goPerfectReg">继续完善资料认识很多同学</a></c:if> 
	<c:if test="${student.classes != null and student.permission == 1}">你已申请加入“ ${student.classes.name} ”班级,审核中</c:if>
</div>

<div>
<h2>最近访问：</h2>
	<ul class="ul">
	<c:forEach items="${school.visitor}" var="visitor">
		<li>
			<a target="content" href="<%=path%>/t/${visitor.people.username }"><img src="<%=path %>/avatar/${visitor.people.id}" width="50"></img></a><br/>
			<a target="content" href="<%=path%>/t/${visitor.people.username }">${visitor.people.name}</a><br/>
			<fmt:formatDate value="${visitor.time }" pattern="MM-dd" />	
		</li>
	</c:forEach>
	</ul>
<div class="clear"></div>
</div>
<div>
<h2>学校新人：</h2>
		<ul class="ul">
	<c:forEach items="${peoplenew}" var="people">
			<li>
				<a target="content" href="<%=path%>/t/${people.username }"><img src="<%=path %>/avatar/${people.id}" width="50"></img></a><br/>
				<a target="content" href="<%=path%>/t/${people.username }">${people.name}</a>
			</li>
	</c:forEach>
		</ul>
<div class="clear"></div>
</div>
<div>
<h2>人气王:</h2>
		<ul class="ul">
	<c:forEach items="${peoplehot}" var="people">
			<li>
				<a target="content" href="<%=path%>/t/${people.username }"><img src="<%=path %>/avatar/${people.id}" width="50"></img></a><br/>
				<a target="content" href="<%=path%>/t/${people.username }">${people.name}</a>
			</li>
	</c:forEach>
		</ul>
<div class="clear"></div>
</div>

<!-- 学校的Feed -->
<script type="text/javascript">
<!--
	$(function(){
		$("#topic_list").load("<%=path%>/feed/listSchoolFeed");
	});
//-->
</script>
<div id="topic_list"></div>

