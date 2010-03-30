<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>

<c:choose>
	<c:when test="${pageBean.list == null}">
					没有数据！
			</c:when>
	<c:otherwise>
		<form method="post" onSubmit="post(this);return false;" action="<%=path%>/notice/deleteManyNotice">
		<table class="table">
			<tr>
				<th><a rel="checkall">全选</a></th>
				<th>姓名</th>
				<th>学校</th>
				<th>性别</th>
				<th>状态</th>
				<th>编辑</th>
				<th>删除</th>
			</tr>
			<c:forEach items="${pageBean.list}" var="notice">
				<tr>
					<td><input type="checkbox" name="nids" value="${notice.id}" /></td>
					<td><a target="content" href="<%=path%>/student/viewStudent?id=${student.id}&page=${page}">${student.name}</a></td>
					<td>${studen.school}</td>
					<td>${student.sex}</td>
					<td>${student.status}</td>
					<td><a target="content" href="<%=path%>/student/goModifyStudent?id=${student.id }&page=${page}" class="btn_edit">编辑</a></td>
					<td><a target="content" href="<%=path%>/student/deleteStudent?id=${student.id }&page=${page}" class="btn_del">删除</a></td>
				</tr>
			</c:forEach>
		</table>

		<div id="pagecount">
		<p>共  ${pageBean.allRow} 条记录 共 ${pageBean.totalPage} 页 当前第 ${pageBean.currentPage}页</p>
		<c:choose>
			<c:when test="${pageBean.currentPage == 1}">
				<a><span>首页</span></a>
				<a><span>上一页</span></a>
			</c:when>
			<c:otherwise>
				<a target="content" href="<%=path%>/student/listStudent?page=1"><span>首页</span></a>
				<a target="content" href="<%=path%>/student/listStudent?page=${pageBean.currentPage-1}"><span>上一页</span></a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<a target="content" href="<%=path%>/student/listStudent?page=${pageBean.currentPage+1}"><span>下一页</span></a>
				<a target="content" href="<%=path%>/student/listStudent?page=${pageBean.totalPage}"><span>尾页</span></a>
			</c:when>
			<c:otherwise>
				<a><span>下一页</span></a>
				<a><span>尾页</span></a>
			</c:otherwise>
		</c:choose>
		</div>

		<select name="cmd">
			<option value="0" selected="selected">批量操作，请选择</option>
			<option value="1">删除</option>
		</select> <input type="submit" value="确定" /></form>
	</c:otherwise>
</c:choose>

