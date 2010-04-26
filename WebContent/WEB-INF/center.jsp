<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript">
	$(function() {
		$("#topic_list").load("<%=path%>/feed/listFeed", list);
		initHighslide("<%=path%>", "480", "400");
	});

	function list(){
	    $("a[target='list']").click(function(){
	    	var href = $(this).attr('href');
		    $("#more_feed").remove();

	        $.ajax({
	            url: href,
	            type: 'GET',
	            success: function(result){
	        		$("#topic_list").append(result);
	        		list();
	            }
	        });
	        
	        return false;
	    });

	    ajax();
    }
</script>

<div id="news">
	<div id="box">
		<jsp:include page="./model/twitter/twitterAdd.jsp"></jsp:include>
	</div>
</div>

<h2>最新动态：</h2>
<div id="feed">
	<div id="topic_list"></div>
</div>