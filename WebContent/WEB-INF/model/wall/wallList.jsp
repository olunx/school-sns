<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>

<script type="text/javascript">
	$(function(){
		updateSidebar();

		diaplayAll();
		
		$("#vote_list").load("<%=path%>/vote/listVote",function(){
			ajax('#content');
		});
		$("#goods_list").load("<%=path%>/goods/listGoods",function(){
			ajax('#content');
		});
		$("#issue_list").load("<%=path%>/issue/listIssue",function(){
			ajax('#content');
		});
		$("#group_list").load("<%=path%>/group/listGroup",function(){
			ajax('#content');
		});

	});

	function diaplayAll() {
		$('#pvote').slideUp('normal');
		$('#pissue').slideUp('normal');
		$('#pgoods').slideUp('normal');
		$('#pgroup').slideUp('normal');
	}

	function show(target) {
		diaplayAll();
		$(target).slideDown('normal');
	}
</script>

<h2>欢迎来到操场，操场这里可热闹呢~</h2>

<p>
<a onclick="show('#pvote');" href="#">投票</a>
 <a onclick="show('#pissue');" href="#">问答</a>
 <a onclick="show('#pgoods');" href="#">交换</a>
 <a onclick="show('#pgroup');" href="#">群组</a>
</p>

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