<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>

<c:choose>
	<c:when test="${pageBean.list == null}">
					没有该学生的货品数据！
			</c:when>
	<c:otherwise>
		<table class="table">
			<tr>
				<td>名称</td>
				<td>描述</td>
				<td>数量</td>
				<td>状态</td>
				<td>价值</td>
				<td>拥有者</td>
			</tr>
			<c:forEach items="${pageBean.list}" var="goods">
				<tr>
					<td><a target="content" href="<%=path %>/goods/viewGoods?id=${goods.id }">${goods.name}</a></td>
					<td>${goods.content}</td>
					<td>${goods.quantity}</td>
					<td>${goods.state }</td>
					<td>${goods.value}</td>
					<td>${goods.owner.name }</td>
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
				<a target="content" href="<%=path%>/goods/listGoods?page=1"><span>首页</span></a>
				<a target="content" href="<%=path%>/goods/listGoods?page=${pageBean.currentPage-1}"><span>上一页</span></a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<a target="content" href="<%=path%>/goods/listGoods?page=${pageBean.currentPage+1}"><span>下一页</span></a>
				<a target="content" href="<%=path%>/goods/listGoods?page=${pageBean.totalPage}"><span>尾页</span></a>
			</c:when>
			<c:otherwise>
				<a><span>下一页</span></a>
				<a><span>尾页</span></a>
			</c:otherwise>
		</c:choose>
		</div>

	</c:otherwise>
</c:choose>