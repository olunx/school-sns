<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript">
	$(function() {
		list("#group_list");
	});

	function list(target) {
		$("a[target='list']").click(function() {
			var href = $(this).attr('href');
			$("#group_more_list").remove();
			
			$.ajax( {
				url : href,
				type : 'GET',
				success : function(result) {
					$(target).append(result);
				}
			});

			return false;
		});

		ajax();
	}
</script>
<c:choose>
	<c:when test="${pageBean.list == null}">
					没有数据！
	</c:when>
	<c:otherwise>
		<c:forEach items="${pageBean.list}" var="group">
			<div class="ilist">
			<div class="iavatar"><img src="<%=path %>${group.pic.minFileUrl}" /></div>
			<div class="imsg">
			<div class="iname">管理员：${group.admin.name}</div>
			<p class="icontent">小组：
			<a target="content" href="<%=path%>/group/viewGroup?id=${group.id}&page=${page}">${group.name}</a>
			<br/>  简介： ${group.intro}
			</p>
			<div class="ioperate">
			 <a target="content" href="<%=path%>/group/joinGroup?id=${group.id}&page=${page}">
			 <c:choose>
			 	<c:when test="${my:isMyGroup(groups,group)}">退出</c:when>
			 	<c:otherwise>加入</c:otherwise>
			 </c:choose>
			 </a>
			</div>
			</div>
			</div>
			<div class="clear "></div>
			<div class="linedot"></div>
		</c:forEach>

		<div id="group_more_list"><c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<br/><a target="list" href="<%=path%>/group/listGroup?page=${pageBean.currentPage+1}"><span>更多...</span></a>
			</c:when>
		</c:choose>
		</div>

	</c:otherwise>
</c:choose>
