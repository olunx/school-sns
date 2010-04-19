<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>

<p>最近访问：<br/>
		<c:forEach items="${school.visitor}" var="visitor">
				<img src="<%=path %>/${visitor.people.avatar.minFileUrl}" width="80"></img><br/>
				<a target="content" href="<%=path%>/student/viewStudent?id=${visitor.people.id }">${visitor.people.name}</a><br/>
				<fmt:formatDate value="${visitor.time }" pattern="yyyy-MM-dd HH:mm" />	<br />
				-----------------------------------<br />
		</c:forEach>
</p>



<!--


货品名称：${goods.name } <br />
货品图片：<c:choose>
			<c:when test="${goods.image != null}">
			<img src="<%=path %>/${goods.image.minFileUrl }" width="80"/><br />
			</c:when>
			<c:otherwise>
				<img src="" />图片无法显示<br />
			</c:otherwise>
		 </c:choose>
货品描述：${goods.content }	<br />
货品状态：${goods.state == 1? "正常(可交换)" :"正常(不可交换)" } <br />
货品价钱：${goods.value } <br />
货品数量：${goods.quantity } <br />
拥有者 ：${goods.owner.name } <br />
货品记录：${goods.record } <br />
<br />
<c:if test="${goodslist != null and goods.owner.id == student.id}">
		这些货品可能对你有用哦~~~~~~~~~^o^~~~~~~~~<br />
		-----------------------------------<br />
		<c:forEach items="${goodslist}" var="goodslike">
				货品名称：<a target="content" href="<%=path%>/goods/viewGoods?id=${goodslike.id }">${goodslike.name}</a><br />
				货品描述：${goodslike.content }	<br />
				货品状态：${goodslike.state == 1? "可交换" :"正常" } <br />
				货品价钱：${goodslike.value } <br />
				货品数量：${goodslike.quantity } <br />
				拥有者 ：${goodslike.owner.name } <br />
				-----------------------------------<br />
		</c:forEach>
</c:if>
<br />
用户评论：<br />
<c:choose>
	<c:when test="${empty goods.reply}">
		<a target="content" href="<%=path %>/goods/goReplyGoods?id=${goods.id}&rid=-1">还没有评论哦！我来抢沙发^o^</a> 
	</c:when>
	<c:otherwise>
			<c:forEach items="${goods.reply}" var="reply">
				${reply.author.name }： ${reply.content}
				<fmt:formatDate value="${reply.time }" pattern="yyyy-MM-dd HH:mm" />
				<a target="content" href="<%=path %>/goods/goReplyGoods?id=${goods.id}&rid=${reply.id != null ? reply.id : -1 }">回复</a> <br />
					<c:if test="${! empty reply.reply}">
						<c:forEach items="${reply.reply}" var="subreply">
							&nbsp;&nbsp;&nbsp;&nbsp; ${subreply.author.name }： ${subreply.content}
							<fmt:formatDate value="${subreply.time }" pattern="yyyy-MM-dd HH:mm" />
							<br />
						</c:forEach>
					</c:if>
			</c:forEach>
		<a target="content" href="<%=path %>/goods/goReplyGoods?id=${goods.id}&rid=-1">我也来说几句</a> 
	</c:otherwise>
</c:choose>
-->