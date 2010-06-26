<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
%>

<script type="text/javascript">
	$(function() {
		list("#group_more_list", "#wall");
	});
</script>
<h2>群组</h2>
<c:choose>
	<c:when test="${pageBean.list == null}">
					没有数据！
	</c:when>
	<c:otherwise>
		<c:forEach items="${pageBean.list}" var="group">
			<div class="ilist clearfix">
			<div class="iavatar">
			<c:choose>
				<c:when test="${!empty group.pic.minFileUrl}"><img src="<%=path %>${group.pic.minFileUrl}" /></c:when>
				<c:otherwise><img src="<%=path %>/avatar/${group.admin.id}" /></c:otherwise>
			</c:choose>
			</div>
				<div class="imsg">
				<div class="iname">管理员：${group.admin.name}</div>
				<p class="icontent">
				<a onclick="ajaxload(this);return false;" href="<%=path%>/group/viewGroup?id=${group.id}&page=${page}">${group.name}</a>
				</p>
				<p class="desc">${group.intro}</p>
				<div class="ioperate">
				 <a onclick="ajaxload(this);return false;" href="<%=path%>/group/joinGroup?id=${group.id}&page=${page}">
				 <c:choose>
				 	<c:when test="${my:isMyGroup(groups,group)}">退出</c:when>
				 	<c:otherwise>加入</c:otherwise>
				 </c:choose>
				 </a>
				</div>
				</div>
			</div>
		</c:forEach>

		<div id="group_more_list"><c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
			<div class="buttons">
			<a class="regular long center" target="list" href="<%=path%>/group/listGroup?page=${pageBean.currentPage+1}" >更多...</a>
			</div>
			</c:when>
			<c:otherwise>
				<div class="buttons">
				<a class="negative long center" href="#" onClick="$.scrollTo(0 , 800 );">没有了！回到顶部</a>
				</div>
			</c:otherwise>
		</c:choose>
		</div>

	</c:otherwise>
</c:choose>
