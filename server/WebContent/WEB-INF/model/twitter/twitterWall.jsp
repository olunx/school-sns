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
	$("#twall").empty();
	$.each(data.twitterList,function(i,item){
		var img;
		if (item.author.avatar!=null)
			img= item.author.avatar.minFileUrl;
		else 
			img = "";
		var html = "<div id='twitter_"+i+"' class='tw_item clearfix' style=\"display:none;\">";
		html = html + "<div class='msg'><div class='msg_top'><div class='msg_center'>"+item.author.name+"<br/>"+item.content+"</div></div></div>";
		html = html + "<div class=\"avatar\"><img src=\"<%=path%>"+img+"\" /></div></div>";
		$("#twall").append(html);
		//alert(html);
		//alert($("#twall").html());
		var top = Math.round(Math.random()*500);
		var left = Math.round(Math.random()*300);
		$("#twitter_"+i).css({"top":top,"left":left});
		$("#twitter_"+i).easydrag();
		//$("#twitter_"+i).click(function(){
		//	$(this).addClass("twitter_on").siblings().removeClass("twitter_on");
		//});
		
	});
	
	var myfun=new Array();
	for (var i = 0;i<$(".tw_item").size();i++){
		myfun[i] = function (){$(".tw_item:hidden:first").animate({top:'-=20px',opacity: 'show'},1000,function(){
			if ($(".tw_item:visible").size()>5){
				$(".tw_item:visible:first").animate({top:'+=20px',opacity: 'hide'},1000,function(){$(this).remove();});
			}
			$(document).dequeue("myani");
			
		});};
	}
	$(document).queue("myani",myfun);
	$(document).dequeue("myani");	
}); 
</script>
<div id="twall">
正在获取内容...
</div>