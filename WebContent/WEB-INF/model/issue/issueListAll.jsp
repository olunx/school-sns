<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://gdpu.cn/functions" prefix="my"%>
<%
	String path = request.getContextPath();
%>

<script type="text/javascript">
	$(function() {
		listMore("issue_more_list","#issue_list");
	});
</script>
<c:choose>
	<c:when test="${pageBean.list == null}">
		没有该学生的提问数据！
	</c:when>
	<c:otherwise>
		<c:forEach items="${pageBean.list}" var="issue">
			<div class="ilist clearfix">
			<div class="iavatar"><img src="<%=path %>/avatar/${issue.owner.id}" /></div>
			<div class="imsg">
			<div class="iname">提问者：${issue.owner.name }</div>
			<p class="icontent">
			<a target="content" href="<%=path%>/issue/viewIssue?id=${issue.id }">${issue.name} <span class="money">(${issue.value}朵)</span></a>
			</p>
			<p class="desc">${fn:substring(fn:replace(issue.content,"<","&lt;"),0,50)}</p>
			<div class="ioperate">
			 状态：${issue.answer == null? "未解决" :"已解决" } | ${my:formatDate(issue.airTime)}
			</div>
			</div>
			</div>
		</c:forEach>

		<div id="issue_more_list"><c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
			<div class="buttons">
			<a class="regular long center" target="list" href="<%=path%>/issue/listIssue?page=${pageBean.currentPage+1}" >更多...</a>
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
