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
		list("#goods_list");
	});

	function list(target) {
		$("a[target='list']").click(function() {
			var href = $(this).attr('href');
			$("#goods_more_list").remove();
			
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
		没有该学生的货品数据！
	</c:when>
	<c:otherwise>
		<c:forEach items="${pageBean.list}" var="goods">
			<div class="ilist">
			<div class="iavatar"><img src="<%=path %>${goods.image}" /></div>
			<div class="imsg">
			<div class="iname">拥有者：${goods.owner.name }</div>
			<p class="icontent">物品名称：
			<a target="content" href="<%=path%>/goods/viewGoods?id=${goods.id }">${goods.name}</a>
			<br/>  物品估价：${goods.value}
			<br/>  物品说明：${fn:substring(fn:replace(goods.content,"<","&lt;"),0,50)}
			</p>
			<div class="ioperate">
			 状态：${goods.state == 1? "可交换" :"正常" } | 发布时间：${my:formatDate(goods.airTime) }
			</div>
			</div>
			</div>
			<div class="clear "></div>
			<div class="linedot"></div>
		</c:forEach>

		<div id="goods_more_list"><c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<br/><a target="list" href="<%=path%>/goods/listGoods?page=${pageBean.currentPage+1}"><span>更多...</span></a>
			</c:when>
		</c:choose>
		</div>

	</c:otherwise>
</c:choose>
