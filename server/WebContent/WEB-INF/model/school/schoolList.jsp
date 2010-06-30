<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>
<div>
<form action="<%=path %>/school/searchSchool" method="post" onSubmit="post(this);return false;"  Class="form" >
	<input type="text" name="search">
	<input type="submit" value="搜索">
</form>
</div>
<c:choose>
	<c:when test="${pageBean.list==null}">
		<a>没有数据</a>
	</c:when>
	<c:otherwise>

		<form onSubmit="post(this);return false;"  action="<%=path%>/achool/deleteManySchool" method="post">
		<table class="table">
			<tr>
				<th>
				<a rel="checkall" >全选</a>
				</th>
				<th>地区</th>
				<th>学校</th>
				<th>学院个数</th>
				<th>管理员个数</th>
				<c:if test="${admin != null}">
				<th>修改</th>
				<th>删除</th>
				</c:if>
			</tr>
			<c:forEach items="${pageBean.list}" var="school">
				<tr>						
					<td>
						<input type="checkbox" name="ids" value="${school.id }" />
					</td>
					<td>
						${school.province.name}
					</td>
					<td>
						<a onclick="ajaxload(this);return false;" rev="#content" href="<%=path %>/school/viewSchool?id=${school.id}" >${school.name}</a>
					</td>
					<td>
						${fn:length(school.institute)}
					</td>
					<td>
						${fn:length(school.admin)}
					</td>
					<c:if test="${admin != null}">
					<td>
						<a onclick="ajaxload(this);return false;" href="<%=path %>/school/modifySchool?id=${school.id}" class="btn_del">修改</a>
					</td>
					<td>
						<a onclick="ajaxload(this);return false;" href="<%=path %>/school/deleteSchool?id=${school.id}" class="btn_del">删除</a>
					</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
		
		<div id="pagecount">
		<p>共 ${pageBean.allRow} 条记录 共 ${pageBean.totalPage} 页 当前第 ${pageBean.currentPage}页</p>
		<c:choose>
			<c:when test="${pageBean.currentPage == 1}">
				<a><span>首页</span></a>
				<a><span>上一页</span></a>
			</c:when>
			<c:otherwise>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/school/listSchool?page=1"><span>首页</span></a>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/school/listSchool?page=${pageBean.currentPage-1}"><span>上一页</span></a>
			</c:otherwise>
		</c:choose> <c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<a onclick="ajaxload(this);return false;" href="<%=path%>/school/listSchool?page=${pageBean.currentPage+1}"><span>下一页</span></a>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/school/listSchool?page=${pageBean.totalPage}"><span>尾页</span></a>
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
