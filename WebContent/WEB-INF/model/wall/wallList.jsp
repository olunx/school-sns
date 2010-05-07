<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
%>
<div class="buttons">
<a onclick="show('#pvote');" href="#" class="regular">投票</a>
 <a onclick="show('#pissue');" href="#" class="regular">问答</a>
 <a onclick="show('#pgoods');" href="#" class="regular">交换</a>
 <a onclick="show('#pgroup');" href="#" class="regular">群组</a>
</div>

<p><br/></p>
<div id="pvote">
<h2>投票</h2>
<div id="vote_list"></div>
</div>
<div id="pissue">
<h2>问答</h2>
<div id="issue_list"></div>
</div>
<div id="pgoods">
<h2>交换</h2>
<div id="goods_list"></div>
</div>
<div id="pgroup">
<h2>群组</h2>
<div id="group_list"></div>
</div>
<script type="text/javascript">
	$(function(){
		updateSidebar();
	});

	function diaplayAll() {
		$('#pissue').slideUp('normal');
		$('#pgoods').slideUp('normal');
		$('#pgroup').slideUp('normal');
	}

	function show(target) {
		diaplayAll();
		$('#pvote').slideUp('normal');
		$(target).slideDown('normal');
	}
	
	diaplayAll();

	loadContent("<%=path%>/vote/listVote","#vote_list");
	loadContent("<%=path%>/goods/listGoods","#goods_list");
	loadContent("<%=path%>/issue/listIssue","#issue_list");
	loadContent("<%=path%>/group/listGroup","#group_list");

</script>
