<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
%>
<a rel="ajaxupload" lang="{upload:'<%=path %>/people/peopleUpload',complete:'<%=path %>/people/classesPeople?id=1',allowtype:/^(xls)$/i}" href="javascript:void()">上传学生信息</a>
<c:choose>
	<c:when test="${pageBean.list == null}">
					没有数据！
			</c:when>
	<c:otherwise>
		<form method="post" onSubmit="post(this);return false;" action="<%=path%>/people/deleteManyPeople">
		<table class="table">
			<tr>
				<th><a rel="checkall">全选</a></th>
				<th>用户名</th>
				<th>加为好友</th>
				<th>姓名</th>
				<th>宿舍</th>
				<th>状态</th>
				<c:if test="${admin != null}">
				<th>编辑</th>
				<th>删除</th>
				</c:if>
			</tr>
			<c:forEach items="${pageBean.list}" var="people">
				<tr>
					<td><input type="checkbox" name="ids" value="${people.id}" /></td>
					<td><a onclick="ajaxload(this);return false;" href="<%=path %>/t/${people.username}">${people.username}</a>
					 <a onclick="ajaxload(this);return false;" href="<%=path%>/mail/goAddMail?receiverId=${people.id}">传纸条</a></td>
					 <td><a onclick="ajaxload(this);return false;" href="<%=path%>/people/followPeople?id=${people.id}&page=${page}">
					 <c:choose>
					 	<c:when test="${my:isMyFriend(friends,people)}">删除好友</c:when>
					 	<c:otherwise>加为好友</c:otherwise>
					 </c:choose>
					 </a></td>
					<td>${people.name}</td>
					<td>${people.dorm}</td>
					<td>${people.status}</td>
					<c:if test="${admin != null}">
					<td><a onclick="ajaxload(this);return false;" href="<%=path%>/people/goModifyPeople?id=${people.id }&page=${page}" class="btn_edit">编辑</a></td>
					<td><a onclick="ajaxload(this);return false;" href="<%=path%>/people/deletePeople?id=${people.id }&page=${page}" class="btn_del">删除</a></td>
					</c:if>
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
				<a onclick="ajaxload(this);return false;" href="<%=path%>/people/listPeople?page=1"><span>首页</span></a>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/people/listPeople?page=${pageBean.currentPage-1}"><span>上一页</span></a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<a onclick="ajaxload(this);return false;" href="<%=path%>/people/listPeople?page=${pageBean.currentPage+1}"><span>下一页</span></a>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/people/listPeople?page=${pageBean.totalPage}"><span>尾页</span></a>
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

