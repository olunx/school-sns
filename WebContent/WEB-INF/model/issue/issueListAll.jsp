<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>

<c:choose>
	<c:when test="${pageBean.list == null}">
					没有找到相关提问数据！
			</c:when>
	<c:otherwise>
		<table class="table">
			<tr>
					<td>提问</td>
					<td>描述</td>
					<td>状态</td>
					<td>悬赏</td>
					<td>拥有者</td>
					<td>时间</td>
			</tr>
		<c:forEach items="${pageBean.list}" var="issue">
				<tr>
					<td><a target="content" href="<%=path%>/issue/viewIssue?id=${issue.id }">${issue.name}</a></td>
					<td>${fn:substring(fn:replace(issue.content,"<","&lt;"),0,20)}...</td>
					<td>${issue.answer == null? "未解决" :"已解决" }</td>
					<td>${issue.value }</td>
					<td>${issue.owner.name }</td>
					<td><fmt:formatDate value="${issue.airTime}" pattern="yyyy-MM-dd HH:mm" /></td>
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
				<a target="content" href="<%=path%>/issue/listIssue?page=1"><span>首页</span></a>
				<a target="content" href="<%=path%>/issue/listIssue?page=${pageBean.currentPage-1}"><span>上一页</span></a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<a target="content" href="<%=path%>/issue/listIssue?page=${pageBean.currentPage+1}"><span>下一页</span></a>
				<a target="content" href="<%=path%>/issue/listIssue?page=${pageBean.totalPage}"><span>尾页</span></a>
			</c:when>
			<c:otherwise>
				<a><span>下一页</span></a>
				<a><span>尾页</span></a>
			</c:otherwise>
		</c:choose>
		</div>

	</c:otherwise>
</c:choose>