<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://gdpu.cn/functions" prefix="my"%>
<%
	String path = request.getContextPath();
%>

<c:choose>
	<c:when test="${pageBean.list == null}">
					没有找到相关货品数据！
			</c:when>
	<c:otherwise>
		<table class="table">
			<tr>
				<th class="ta_left">名称</th>
				<th>状态</th>
				<th>价值</th>
				<th>拥有者</th>
				<th class="ta_right">时间 </th>
			</tr>
			<c:forEach items="${pageBean.list}" var="goods">
				<tr>
					<td class="ta_left"><a onclick="ajaxload(this);return false;" href="<%=path %>/goods/viewGoods?id=${goods.id }">${goods.name}</a></td>
					<td>${goods.state == 1? "可交换" :"正常" }</td>
					<td>${goods.value}</td>
					<td>${goods.owner.name }</td>
					<td class="ta_right">${my:formatDate(goods.airTime) }</td>
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
				<a onclick="ajaxload(this);return false;" href="<%=path%>/goods/listGoods?page=1"><span>首页</span></a>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/goods/listGoods?page=${pageBean.currentPage-1}"><span>上一页</span></a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<a onclick="ajaxload(this);return false;" href="<%=path%>/goods/listGoods?page=${pageBean.currentPage+1}"><span>下一页</span></a>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/goods/listGoods?page=${pageBean.totalPage}"><span>尾页</span></a>
			</c:when>
			<c:otherwise>
				<a><span>下一页</span></a>
				<a><span>尾页</span></a>
			</c:otherwise>
		</c:choose>
		</div>

	</c:otherwise>
</c:choose>