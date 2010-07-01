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
		listMore("#vote_more_list","#wall");
	});
</script>
<h2>投票</h2>
<c:choose>
	<c:when test="${pageBean.list == null}">
					没有数据！
	</c:when>
	<c:otherwise>
		<c:forEach items="${pageBean.list}" var="vote">
			<div class="ilist clearfix">
			<div class="iavatar"><img src="<%=path %>/avatar/${vote.author.id}" /></div>
			<div class="imsg">
				<div class="iname">发起者：${vote.author.name }</div>
				<p class="icontent">
				<a onclick="ajaxload(this);return false;" href="<%=path %>/vote/goVotingVote?vid=${vote.id}" >${fn:substring(fn:replace(vote.title,"<","&lt;"),0,20)}</a>
				</p>
				<p class="desc">${fn:substring(fn:replace(vote.summary,"<","&lt;"),0,50)}</p>
				<div class="ioperate">
				 ${my:formatDate(vote.airTime) }
				</div>
			</div>
			</div>
		</c:forEach>

		<div id="vote_more_list"><c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
			<div class="buttons">
			<a class="regular long center" target="list" href="<%=path%>/vote/listVote?page=${pageBean.currentPage+1}" >更多...</a>
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
