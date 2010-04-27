<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript">
$(function(){
	updateSidebar();
	$("#pvote").load("<%=path%>/vote/listVote",function(){
		ajax('#content');
	});
	$("#pgoods").load("<%=path%>/goods/listGoods",function(){
		ajax('#content');
	});
	$("#pissue").load("<%=path%>/issue/listIssue",function(){
		ajax('#content');
	});
	$("#pgroup").load("<%=path%>/group/listGroup",function(){
		ajax('#content');
	});
	
});

</script>
<h2>欢迎来到操场，操场这里可热闹呢~</h2>
<h2>投票</h2>
<div id="pvote">

</div>
<h2>交换</h2>
<div id="pgoods">

</div>
<h2>问答</h2>
<div id="pissue">

</div>
<h2>群组</h2>
<div id="pgroup">

</div>