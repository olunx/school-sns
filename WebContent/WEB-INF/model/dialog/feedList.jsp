<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<c:choose>
	<c:when test="${pageBean.list == null}">
					没有数据！
			</c:when>
	<c:otherwise>
		<table class="table">
			<tr>
				<th>姓名</th>
				<th>时间</th>
				<th>类型</th>
				<th>消息</th>
			</tr>
			<c:forEach items="${pageBean.list}" var="feed">
				<tr>
					<td>${feed.author.name}</td>
					<td>${feed.time}</td>
					<td>${feed.type}</td>
					<td>${feed.message}</td>
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
				<a target="content" href="<%=path%>/feed/listFeed?page=1"><span>首页</span></a>
				<a target="content" href="<%=path%>/feed/listFeed?page=${pageBean.currentPage-1}"><span>上一页</span></a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<a target="content" href="<%=path%>/feed/listFeed?page=${pageBean.currentPage+1}"><span>下一页</span></a>
				<a target="content" href="<%=path%>/feed/listFeed?page=${pageBean.totalPage}"><span>尾页</span></a>
			</c:when>
			<c:otherwise>
				<a><span>下一页</span></a>
				<a><span>尾页</span></a>
			</c:otherwise>
		</c:choose>
		</div>
		
	</c:otherwise>
</c:choose>