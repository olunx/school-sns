<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
%>
<a rel="ajaxupload" rev="{upload:'<%=path %>/course/courseUpload',complete:'<%=path %>/course/listCourse',allowtype:/^(xls)$/i}" href="javascript:void()">上传课程</a>
<c:choose>
	<c:when test="${courseList==null}">
					还没有添加课程呢！
	</c:when>
	<c:otherwise>
		<table class="table">
			<tr>
				<th>课程</th>
				<th>星期</th>
				<th>起始节</th>
				<th>年度</th>
				<th>删除</th>
			</tr>
			<c:forEach items="${courseList}" var="course">
				<tr>
					<td>${course.name }</td>
					<td>${course.whatDay }</td>
					<td> ${course.startLesson } - ${course.endLesson} </td>
					<td>${course.year }/${course.term }</td>
					
					<td><a target="content" href="<%=path%>/course/deleteCourse?id=${course.id }&page=${page}" class="btn_del">删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:otherwise>
</c:choose>
