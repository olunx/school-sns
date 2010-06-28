<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
%>

<script type="text/javascript">
$.getJSON("<%=path%>/twitter/twitterWall",function(data){
	$("#wall").empty();
	$.each(data.twitterList,function(i,item){
		var img;
		if (item.author.avatar!=null)
			img= item.author.avatar.minFileUrl;
		else 
			img = "";
		$("#wall").append("<div id='twitter_"+i+"' class='tw_item' style=\"display:block;\"><div class=\"avatar\"><img src=\"<%=path%>"+img+"\" /></div><div class='msg'>"+item.author.name+"<br/>"+item.content+"</div></div>");
	});

	//alert($(".test").size());
	var myfun=new Array();
	for (var i = 0;i<$(".test").size();i++){
		myfun[i] = function (){$(".test:hidden:first").fadeIn(1000,function(){
			$(document).dequeue("myani");
		});};
	}
	//$(document).queue("myani",myfun);
	//$(document).dequeue("myani");	
}); 
</script>
<div id="wall">
正在获取内容...
</div>