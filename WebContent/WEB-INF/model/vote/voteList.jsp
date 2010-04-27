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
		list("#vote_list");
	});

	function list(target) {
		$("a[target='list']").click(function() {
			var href = $(this).attr('href');
			$("#vote_more_list").remove();
			
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
		<c:forEach items="${pageBean.list}" var="vote">
			<div class="ilist">
			<div class="iavatar"><img src="<%=path %>" /></div>
			<div class="imsg">
			<div class="iname">发起者：${vote.author.name }</div>
			<p class="icontent">投票主题：
			<a target="content" href="<%=path %>/vote/goVotingVote?vid=${vote.id}" >${fn:substring(fn:replace(vote.title,"<","&lt;"),0,20)}</a>
			<br/>  简介：${fn:substring(fn:replace(vote.summary,"<","&lt;"),0,50)}
			</p>
			<div class="ioperate">
			 创建时间：${my:formatDate(vote.airTime) }
			</div>
			</div>
			</div>
			<div class="clear "></div>
			<div class="linedot"></div>
		</c:forEach>

		<div id="vote_more_list"><c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<br/><a target="list" href="<%=path%>/vote/listVote?page=${pageBean.currentPage+1}"><span>更多...</span></a>
			</c:when>
		</c:choose>
		</div>

	</c:otherwise>
</c:choose>
