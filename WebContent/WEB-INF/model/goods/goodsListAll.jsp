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
		listMore("#goods_more_list", "#wall");
	});
</script>
<h2>交换</h2>
<c:choose>
	<c:when test="${pageBean.list == null}">
		没有货品数据！
	</c:when>
	<c:otherwise>
		<c:forEach items="${pageBean.list}" var="goods">
			<div class="ilist clearfix">
				<div class="iavatar"><img src="<%=path %>/avatar/${goods.owner.id}" /></div>
				<div class="imsg">
				<div class="iname">拥有者：${goods.owner.name }</div>
				<p class="icontent">
				<a onclick="ajaxload(this);return false;" href="<%=path%>/goods/viewGoods?id=${goods.id }">${goods.name} <span class="money">（${goods.value}元）</span></a>
				</p>
				<p class="desc">${fn:substring(fn:replace(goods.content,"<","&lt;"),0,50)}</p>
				
				<div class="ioperate">
				 状态：${goods.state == 1? "可交换" :"正常" } | ${my:formatDate(goods.airTime) }
				</div>
				</div>
			</div>
		</c:forEach>

		<div id="goods_more_list"><c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
			<div class="buttons">
			<a class="regular long center" target="list" href="<%=path%>/goods/listGoods?page=${pageBean.currentPage+1}" >更多...</a>
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
