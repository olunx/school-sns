<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript">
	$(function() {
		list("#topic_list");
	});

	function list(target) {
		$("a[target='list']").click(function() {
			var href = $(this).attr('href');
			$("#more_list").remove();
			
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
	<c:when test="${pageBean.list==null}">
		<a>没有数据</a>
	</c:when>
	<c:otherwise>
		<c:forEach items="${pageBean.list}" var="twitter">
			<div class="list">
			<div class="avatar"><img src="<%=path%>/avatar/${twitter.author.id}" /></div>
			<div class="topic_msg">
			<div class="time" title="${twitter.time }">${my:formatDate(twitter.time)}</div>
			<p class="content"><a href="#">${twitter.author.name}</a> ${twitter.content} <c:if test="${twitter.image != null}">
				<img src="<%=path%>${twitter.image.minFileUrl}" />
			</c:if></p>
			<div class="operate"><a target="content" href="<%=path%>/twitter/deleteTwitter?id=${twitter.id }&page=${page}" class="btn_del">删除</a>
			<a target="content" href="<%=path%>/twitter/goReplyTwitter?id=${twitter.id }">回复</a></div>
			<c:choose>
				<c:when test="${twitter.reply != null && fn:length(twitter.reply)>0}">
					<div class="reply"><c:forEach items="${twitter.reply}" var="reply">
						<div class="reply_list">
						<div class="reply_avatar"><img src="<%=path%>/avatar/${reply.author.id}" /></div>
						<p class="reply_content"><a href="#">${reply.author.name}</a> ${reply.content}<br />
						<span class="replytime">${my:formatDate(reply.time)}</span></p>
						</div>
						<div class="clear"></div>
					</c:forEach></div>
				</c:when>
			</c:choose></div>
			</div>
			<div class="clear "></div>
			<div class="linedot"></div>
		</c:forEach>


		<div id="more_list"><c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<br/><a target="list" href="<%=path%>/twitter/listTwitter?page=${pageBean.currentPage+1}" ><span>更多...</span></a>
			</c:when>
			<c:otherwise>
				<br/><a><span>没有了！</span></a>
			</c:otherwise>
		</c:choose>
		</div>
	</c:otherwise>
</c:choose>
