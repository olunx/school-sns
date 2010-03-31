<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>

<c:choose>
	<c:when test="${pageBean.list == null}">
					没有成绩数据！
			</c:when>
	<c:otherwise>
		<table class="table">
			<tr>
				<td>学号</td>
				<td>姓名</td>
				<td>科目</td>
				<td>分数</td>
				<td>更新日期</td>
			</tr>
			<c:forEach items="${pageBean.list}" var="score">
				<tr>
					<td>${score.student.sno}</td>
					<td>${score.student.name}</td>
					<td>${score.subject}</td>
					<td>${score.marks}</td>
					<td>${score.time }</td>
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
				<a target="content" href="<%=path%>/score/listScore?page=1"><span>首页</span></a>
				<a target="content" href="<%=path%>/score/listScore?page=${pageBean.currentPage-1}"><span>上一页</span></a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<a target="content" href="<%=path%>/score/listScore?page=${pageBean.currentPage+1}"><span>下一页</span></a>
				<a target="content" href="<%=path%>/score/listScore?page=${pageBean.totalPage}"><span>尾页</span></a>
			</c:when>
			<c:otherwise>
				<a><span>下一页</span></a>
				<a><span>尾页</span></a>
			</c:otherwise>
		</c:choose>
		</div>

	</c:otherwise>
</c:choose>

