<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>

<c:choose>

	<c:when test="${pageBean.list==null}">
		<a>没有数据</a>
	</c:when>
	<c:otherwise>

		<form onSubmit="post(this);return false;"  action="<%=path%>/classfee/deleteMany" method="post">
		<table class="table">
			<tr>
				<th><a rel="checkall">全选</a></th>
				<th>班费事件</th>
				<th>类别</th>
				<th>费用</th>
				<th>经手人</th>
				<th>创建时间</th>
				<th>修改</th>
				<th>删除</th>
			</tr>
			<c:forEach items="${pageBean.list}" var="classfee">
				<tr>
					<td><input type="checkbox" name="ids" value="${classfee.id }" /></td>
					<td><a target="content" href="<%=path%>/classfee/viewClassfee?id=${classfee.id }">${classfee.event }</a></td>
					<td>${classfee.fee lt 0 ? "支出":"收入" }</td>
					<td>${classfee.fee } 元</td>
					<td>${classfee.cmaker.name }</td>
					<td><fmt:formatDate value="${classfee.time}" pattern="yyyy-MM-dd HH:mm" /></td>
					<td><a target="content" href="<%=path%>/classfee/goModifyClassfee?id=${classfee.id }" class="btn_edit">修改</a></td>
					<td><a target="content" href="<%=path%>/classfee/deleteClassfee?id=${classfee.id }&page=${page}" class="btn_del">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td>总计：</td>
				<td>${totalMoney }元</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		
		<div id="pagecount">
		<p>共 ${pageBean.allRow} 条记录 共 ${pageBean.totalPage} 页 当前第 ${pageBean.currentPage}页</p>
		<c:choose>
			<c:when test="${pageBean.currentPage == 1}">
				<a><span>首页</span></a>
				<a><span>上一页</span></a>
			</c:when>
			<c:otherwise>
				<a target="content" href="<%=path%>/classfee/listClassfee?page=1"><span>首页</span></a>
				<a target="content" href="<%=path%>/classfee/listClassfee?page=${pageBean.currentPage-1}"><span>上一页</span></a>
			</c:otherwise>
		</c:choose> <c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<a target="content" href="<%=path%>/classfee/listClassfee?page=${pageBean.currentPage+1}"><span>下一页</span></a>
				<a target="content" href="<%=path%>/classfee/listClassfee?page=${pageBean.totalPage}"><span>尾页</span></a>
			</c:when>
			<c:otherwise>
				<a><span>下一页</span></a>
				<a><span>尾页</span></a>
			</c:otherwise>
		</c:choose></div>

		<select name="cmd">
			<option value="0" selected="selected">批量操作，请选择</option>
			<option value="1">删除</option>
		</select> <input type="submit" value="确定" />
		
		</form>
	</c:otherwise>
</c:choose>
