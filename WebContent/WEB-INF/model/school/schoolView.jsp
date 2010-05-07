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
<c:if test="${!empty school.avatar.minFileUrl}">
	<img src="<%=path %>/${school.avatar.minFileUrl}" width="80"/>
</c:if>
</div>
<h3>${school.name}</h3>
<p class="desc">${school.content}</p>
<p>学校地址：${school.address}</p>
<p>
管理员：
	<c:forEach items="${school.admin}" var="admin">
			<a onclick="ajaxload(this);return false;" href="<%=path%>/student/viewStudent?id=${admin.id }">${admin.name}</a>
	</c:forEach>
</p>
<p>
<c:choose>
	<c:when test="${isAdmin}">
		学校管理：<br/>
		<a onclick="ajaxload(this);return false;" href="<%=path %>/school/goModifySchool?id=${school.id}">修改学校资料</a>
	</c:when>
	<c:otherwise>
		<a onclick="ajaxload(this);return false;" href="<%=path %>/school/joinAdminSchool?id=${school.id}">申请加入学校管理员</a>
	</c:otherwise>
</c:choose>
| <a onclick="ajaxload(this);return false;" href="<%=path%>/classes/viewClasses?id=${student.classes.id}">我的班级</a>
| <a onclick="ajaxload(this);return false;" href="<%=path%>/school/listSchool">访问其它学校</a>
| <a onclick="ajaxload(this);return false;" href="<%=path%>/people/schoolPeople?id=${school.id}">学校成员</a>
</p>
</div>
<div id="information">
	<c:if test="${student.classes == null}"><a class="letterspacing" onclick="ajaxload(this);return false;" href="<%=path %>/goPerfectReg">继续完善资料认识很多同学</a></c:if> 
	<c:if test="${student.classes != null and student.permission == 1}">你已申请加入“ ${student.classes.name} ”班级,审核中</c:if>
</div>

<!-- 学校的Feed -->
<script type="text/javascript">
<!--
	$(function(){
		$("#feed").load("<%=path%>/feed/listSchoolFeed");
	});
//-->
</script>
<h2>最新动态</h2>
<div id="feed" class="feed"></div>

<div>
<h2>最近访问</h2>
	<ul class="imglist">
	<c:forEach items="${school.visitor}" var="visitor">
		<li>
			<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${visitor.people.username }"><img src="<%=path %>/avatar/${visitor.people.id}" width="50"></img></a><br/>
			<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${visitor.people.username }">${visitor.people.name}</a><br/>
			<!--<fmt:formatDate value="${visitor.time }" pattern="MM-dd" />	-->
		</li>
	</c:forEach>
	</ul>
<div class="clear"></div>
</div>
<div>
<h2>学校新人</h2>
		<ul class="imglist">
	<c:forEach items="${peoplenew}" var="people">
			<li>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${people.username }"><img src="<%=path %>/avatar/${people.id}" width="50"></img></a><br/>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${people.username }">${people.name}</a>
			</li>
	</c:forEach>
		</ul>
<div class="clear"></div>
</div>
<div>
<h2>人气王:</h2>
		<ul class="imglist">
	<c:forEach items="${peoplehot}" var="people">
			<li>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${people.username }"><img src="<%=path %>/avatar/${people.id}" width="50"></img></a><br/>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${people.username }">${people.name}</a>
			</li>
	</c:forEach>
		</ul>
<div class="clear"></div>
</div>


