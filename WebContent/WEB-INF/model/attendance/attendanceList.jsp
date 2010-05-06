<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>
<a onclick="ajaxload(this);return false;" rev="#class" href="<%=path%>/attendance/goAddAttendance">添加考勤记录</a>
<c:choose>
	<c:when test="${pageBean.list==null}">
			没有数据	
	</c:when>
	<c:otherwise>
		<form onSubmit="post(this,'#class');return false;" action="<%=path%>/attendance/deleteManyAttendance" method="post">
		<table class="table">
			<thead>
				<tr>
					<th><a rel="checkall">全选</a></th>
					<th>周次</th>
					<th>星期</th>
					<th>课程</th>
					<th>逃课人员</th>
					<th>记录者</th>
					<th>时间</th>
					<th>删除</th>
				</tr>
			</thead>
			<c:forEach items="${pageBean.list}" var="attendance">
				<tr>
					<td><input type="checkbox" name="ids" value="${attendance.id}" /></td>
					<td>${attendance.week}</td>
					<td>${attendance.day}</td>
					<td>
					<c:if test="${attendance.course!=null}">
					<c:catch var="e">
						<c:forEach items="${attendance.course}" var="c">周${c.whatDay} - ${c.name}, </c:forEach>
					</c:catch>
					</c:if>
					</td>
					<td><c:forEach items="${attendance.students}" var="s">${s.name},</c:forEach></td>
					<td>${attendance.clerk.name}</td>
					<td>${attendance.time}</td>
					<td><a onclick="ajaxload(this);return false;" href="<%=path%>/attendance/deleteAttendance?id=${attendance.id}" class="btn_del">删除</a></td>
				</tr>
			</c:forEach>
		</table>
		<div id="pagecount">
		<p>共 ${pageBean.allRow} 条记录 共 ${pageBean.totalPage} 页 当前第 ${pageBean.currentPage}页</p>
		<c:choose>
			<c:when test="${pageBean.currentPage == 1}">
				<a><span>首页</span></a>
				<a><span>上一页</span></a>
			</c:when>
			<c:otherwise>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/attendance/listAttendance?page=1"><span>首页</span></a>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/attendance/listAttendance?page=${pageBean.currentPage-1}"><span>上一页</span></a>
			</c:otherwise>
		</c:choose> <c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<a onclick="ajaxload(this);return false;" href="<%=path%>/attendance/listAttendance?page=${pageBean.currentPage+1}"><span>下一页</span></a>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/attendance/listAttendance?page=${pageBean.totalPage}"><span>尾页</span></a>
			</c:when>
			<c:otherwise>
				<a><span>下一页</span></a>
				<a><span>尾页</span></a>
			</c:otherwise>
		</c:choose></div>

		<select name="cmd">
			<option value="0" selected="selected">批量操作，请选择</option>
			<option value="1">删除</option>
		</select> <input type="submit" value="确定" /></form>
	</c:otherwise>
</c:choose>


