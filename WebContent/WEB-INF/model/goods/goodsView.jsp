<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://gdpu.cn/functions" prefix="my"%>
<%
	String path = request.getContextPath();
%>
<div class="goods">
<h2>${goods.name }${goods.state == 1? "(可交换)" :"(不可交换)" }</h2>
<p class="desc">${goods.content }   <c:if test="${goods.owner.id == student.id}"><a target="content" href="<%=path %>/goods/deleteGoods?id=${goods.id}">注销该货品</a></c:if> </p>
<p>货品价钱：${goods.value } 元</p>
<p>货品数量：${goods.quantity }</p>
<p>拥有者 ：${goods.owner.name }</p>
<c:choose>
	<c:when test="${goods.image != null}">
	<img class="goodspic" src="<%=path %>/${goods.image.minFileUrl }"/>
	</c:when>
</c:choose>

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
</div>
<h2>用户评论</h2>
<c:choose>
	<c:when test="${empty goods.reply}">
		<a target="content" href="<%=path %>/goods/goReplyGoods?id=${goods.id}&rid=-1">还没有评论哦！我来抢沙发^o^</a> 
	</c:when>
	<c:otherwise>
		<div class="class_msg_list">
		<c:forEach items="${goods.reply}" var="reply">
		<div class="class_msg">
			<div class="avatar">
				<img src="<%=path %>/avatar/${reply.author.id}" />
			</div>
			<div class="msg">
			<div class="operate">
			<p class="time" title="${reply.time }"><a target="content" href="<%=path %>/goods/goReplyGoods?id=${goods.id}&rid=${reply.id != null ? reply.id : -1 }">回复</a> ${my:formatDate(reply.time)}</p>
			</div>
				<p class="text">${reply.author.name }： ${reply.content}</p>
				
				<c:if test="${! empty reply.reply}">
				<div class="reply">
					<c:forEach items="${reply.reply}" var="subreply">
					<p>
						${subreply.author.name }： ${subreply.content}
						${my:formatDate(subreply.time)}
					</p>
					</c:forEach>
				</div>
				</c:if>
			</div>
		<div class="clear"></div>
		</div>
		</c:forEach>
		</div>
		<a target="content" href="<%=path %>/goods/goReplyGoods?id=${goods.id}&rid=-1">我也来说几句</a> 
	</c:otherwise>
</c:choose>
