<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
%>

<c:choose>
	<c:when test="${pageBean.list == null}">
					没有数据！
			</c:when>
	<c:otherwise>
		<form method="post" onSubmit="post(this);return false;" action="<%=path%>/group/deleteManyGroup">
		<table class="table">
			<tr>
				<th><a rel="checkall">全选</a></th>
				<th>名称</th>
				<th>简介</th>
				<th>图片</th>
				<th>作品</th>
				<th>类型</th>
				<th>管理员</th>
				<th>帖子</th>
				<th>成员</th>
				<th>编辑</th>
				<th>删除</th>
			</tr>
			<c:forEach items="${pageBean.list}" var="group">
				<tr>
					<td><input type="checkbox" name="ids" value="${group.id}" /></td>
					<td>
					<a target="content" href="<%=path%>/group/viewGroup?id=${group.id}&page=${page}">${group.name}</a>
					 <a target="content" href="<%=path%>/group/joinGroup?id=${group.id}&page=${page}">加入</a>
					</td>
					<td>${group.intro}</td>
					<td>${group.pic}</td>
					<td>${group.works}</td>
					<td>${group.type}</td>
					<td>${group.admin.name}</td>
					<td><c:if test="${group.post != null}">(${fn:length(group.post)})</c:if></td>
					<td><c:if test="${group.member != null}">(${fn:length(group.member)})</c:if></td>
					<td><a target="content" href="<%=path%>/group/goModifyGroup?id=${group.id }&page=${page}" class="btn_edit">编辑</a></td>
					<td><a target="content" href="<%=path%>/group/deleteGroup?id=${group.id }&page=${page}" class="btn_del">删除</a></td>
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
				<a target="content" href="<%=path%>/group/listGroup?page=1"><span>首页</span></a>
				<a target="content" href="<%=path%>/group/listGroup?page=${pageBean.currentPage-1}"><span>上一页</span></a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<a target="content" href="<%=path%>/group/listGroup?page=${pageBean.currentPage+1}"><span>下一页</span></a>
				<a target="content" href="<%=path%>/group/listGroup?page=${pageBean.totalPage}"><span>尾页</span></a>
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

