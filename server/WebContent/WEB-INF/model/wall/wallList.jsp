<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
%>

<div class="buttons">
<a onclick="ajaxload(this);return false;" rev="#wall" href="<%=path%>/vote/listVote" class="regular">投票</a>
 <a onclick="ajaxload(this);return false;" rev="#wall" href="<%=path%>/issue/listIssue" class="regular">问答</a>
 <a onclick="ajaxload(this);return false;" rev="#wall" href="<%=path%>/goods/listGoods" class="regular">交换</a>
 <a onclick="ajaxload(this);return false;" rev="#wall" href="<%=path%>/group/listGroup" class="regular">群组</a>
</div>

<div id="wall">
</div>
<script type="text/javascript">
	$(function(){
		updateSidebar();
	});

	function diaplayAll() {
		$('#pissue').slideUp('normal');
		$('#pgoods').slideUp('normal');
		$('#pgroup').slideUp('normal');
		$('#pvote').slideUp('normal');
	}

	function show(target) {
		diaplayAll();
		$(target).slideDown('normal');
	}
	
	//diaplayAll();
	//$('#pvote').slideDown('normal');
	loadContent("<%=path%>/vote/listVote","#wall");

</script>
