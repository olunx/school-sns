<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>

<a target="content" href="<%=path %>/goods/listGoods" >全部学生的成绩</a>	<br />
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
				<td>单价</td>
				<td>时间</td>
				<td>删除</td>
			</tr>
			<c:forEach items="${pageBean.list}" var="goods">
				<tr>
					<td><a target="content" href="<%=path%>/goods/viewGoods?id=${goods.id }">${goods.name}</a></td>
					<td>${fn:substring(fn:replace(goods.content,"<","&lt;"),0,20)}...</td>
					<td>${goods.quantity}</td>
					<td>${goods.state == 1? "可交换" :"正常" }</td>
					<td>${goods.value}</td>
					<td><fmt:formatDate value="${goods.airTime}" pattern="yyyy-MM-dd HH:mm" /></td>
					<td><a target="content" href="<%=path %>/goods/deleteGoods?id=${goods.id }">删除</a></td>
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

<br />
热门货品
<br />
<c:choose>
	<c:when test="${goodshot != null}">
				<tr>
					<td>名称</td>
					<td>描述</td>
					<td>数量</td>
					<td>状态</td>
					<td>价值</td>
					<td>拥有者</td>
					<td>时间</td>
				</tr>
			<c:forEach items="${goodshot}" var="goodshot">
				<tr>
					<td><a target="content" href="<%=path%>/goods/viewGoods?id=${goodshot.id }">${goodshot.name}</a></td>
					<td>${fn:substring(fn:replace(goodshot.content,"<","&lt;"),0,20)}...</td>
					<td>${goodshot.state == 1? "可交换" :"正常" }</td>
					<td>${goodshot.value }</td>
					<td>${goodshot.quantity }</td>
					<td>${goodshot.owner.name }</td>
					<td><fmt:formatDate value="${goodshot.airTime}" pattern="yyyy-MM-dd HH:mm" /></td>
				</tr>
			</c:forEach>
	</c:when>
	<c:otherwise>
		&nbsp;&nbsp;暂无热门货品
	</c:otherwise>
</c:choose>
<br />
最新货品
<br />
<c:choose>
	<c:when test="${goodsnew != null}">
				<tr>
					<td>名称</td>
					<td>描述</td>
					<td>数量</td>
					<td>状态</td>
					<td>价值</td>
					<td>拥有者</td>
					<td>时间</td>
				</tr>
			<c:forEach items="${goodsnew}" var="goodsnew">
				<tr>
					<td><a target="content" href="<%=path%>/goods/viewGoods?id=${goodsnew.id }">${goodsnew.name}</a></td>
					<td>${fn:substring(fn:replace(goodsnew.content,"<","&lt;"),0,20)}...</td>
					<td>${goodsnew.state == 1? "可交换" :"正常" }</td>
					<td>${goodsnew.value }</td>
					<td>${goodsnew.quantity }</td>
					<td>${goodsnew.owner.name }</td>
					<td><fmt:formatDate value="${goodsnew.airTime}" pattern="yyyy-MM-dd HH:mm" /></td>
				</tr>
			</c:forEach>
	</c:when>
	<c:otherwise>
		&nbsp;&nbsp;暂无最新货品
	</c:otherwise>
</c:choose>
<br />
<br />
<form onSubmit="post(this);return false;" class="form" action="<%=path %>/goods/searchGoods" method="post">
	<select name="gtid">  
		<option value="-1">全部</option>
		<c:forEach items="${goodsType}" var="gst">
			<option value="${gst.id}">${gst.name}</option>
		</c:forEach>
	</select>
	<input type="text" name="search" />
	<input type="submit" value="搜索"/>
</form>