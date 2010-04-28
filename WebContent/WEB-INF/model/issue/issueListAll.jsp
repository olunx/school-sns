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
		list("#issue_list");
	});

	function list(target) {
		$("a[target='list']").click(function() {
			var href = $(this).attr('href');
			$("#issue_more_list").remove();
			
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
		没有该学生的提问数据！
	</c:when>
	<c:otherwise>
		<c:forEach items="${pageBean.list}" var="issue">
			<div class="ilist">
			<div class="iavatar"><img src="<%=path %>" /></div>
			<div class="imsg">
			<div class="iname">提问者：${issue.owner.name }</div>
			<p class="icontent">问题名称：
			<a target="content" href="<%=path%>/issue/viewIssue?id=${issue.id }">${issue.name}</a>
			<br/>  悬赏：${issue.value}
			<br/>  问题描述：${fn:substring(fn:replace(issue.content,"<","&lt;"),0,50)}
			</p>
			<div class="ioperate">
			 状态：${issue.answer == null? "未解决" :"已解决" } | 发布时间：${my:formatDate(issue.airTime)}
			</div>
			</div>
			</div>
			<div class="clear "></div>
			<div class="linedot"></div>
		</c:forEach>

		<div id="issue_more_list"><c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<br/><a target="list" href="<%=path%>/issue/listIssue?page=${pageBean.currentPage+1}"><span>更多...</span></a>
			</c:when>
		</c:choose>
		</div>

	</c:otherwise>
</c:choose>
