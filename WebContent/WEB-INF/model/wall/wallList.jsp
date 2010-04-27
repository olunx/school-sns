<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
%>
<!-- codaslide -->
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-codaslide/coda-slider-2.0.css" />
<script type="text/javascript" src="<%=path%>/content/jq-codaslide/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="<%=path%>/content/jq-codaslide/jquery.coda-slider-2.0.js"></script>

<script type="text/javascript">
	$(function(){
		updateSidebar();
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

		$('#coda-slider-1').codaSlider({
			dynamicArrows: false,
			dynamicTabs: false
		});
	});
</script>

<h2>欢迎来到操场，操场这里可热闹呢~</h2>

<div id="coda-nav-1" >
<ul>
<li class="tab1"><a href="#1">投票</a></li>
<li class="tab2"><a href="#2">问答</a></li>
<li class="tab3"><a href="#3">交换</a></li>
<li class="tab4"><a href="#4">群组</a></li>
</ul>
</div>

<div class="coda-slider" id="coda-slider-1">
	<div class="panel_container" >
		<div class="panel" >
			<div class="panel-wrapper">
				<h2>投票</h2>
				<div id="vote_list"></div>
			</div>
		</div>
		<div class="panel" >
			<div class="panel-wrapper">
				<h2>问答</h2>
				<div id="issue_list"></div>
			</div>
		</div>
		<div class="panel" >
			<div class="panel-wrapper">
				<h2>交换</h2>
				<div id="goods_list"></div>
			</div>
		</div>
		<div class="panel" >
			<div class="panel-wrapper">
				<h2>群组</h2>
				<div id="group_list"></div>
			</div>
		</div>
	</div>
</div>