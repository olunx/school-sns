<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript">
	$(function() {
		list("#topic_list");
	});

	function list(target) {
		$("a[target='list']").click(function() {
			var href = $(this).attr('href');
			$("#more_list").remove();
			
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

	$(function(){
		//回复按钮
		$("span[rel=replybtn]").unbind("click").click(function(){
			var id = $(this).attr("rev");

			var editorid = "#reply_editor_"+id;
			if ($(this).parent().find(editorid).size()==0){
				$(this).parent().append('<div class="reply_editor" id="reply_editor_'+id+'"><form onSubmit="post(this,\'#topic_list\');return false;" action="<%=path%>/twitter/replyTwitter" method="post"><textarea name="twitter.content" onblur="onEditorBlur('+id+')"></textarea><input type="hidden" name="id" value="'+id+'" /><input type="submit" value="回复" /> <a href="javascript:;" onclick="switchEditor(0,'+id+')">取消</a></form></div>');
			};

			switchEditor(1,id);
		});

		$(".reply").each(function(){
			var list = $(this).find(".reply_list");
			$(this).find('.morereply').remove();
			var num = list.size()-3;
			if (num > 0) {
				$(this).prepend("<a class='morereply' href='javascript:;' onclick='$(this).parent().find(\".reply_list\").css(\"display\",\"block\");$(this).find(\".reply_list + .clear\").css(\"display\",\"block\");$(this).remove();'>展开更多"+num+"条回复</a>");
				$(this).find(".reply_list:lt("+num+")").css("display","none");
				$(this).find(".reply_list:lt("+num+") + .clear").css("display","none");
			};
		});
	});
	
	function switchEditor(isenable,id){
		if (isenable){
			$("#replybtn_"+id).css("display","none");
			$("#reply_editor_"+id).css("display","block");
			$("#reply_editor_"+id).find("textarea").focus();
		} else {
			$("#replybtn_"+id).css("display","block");
			$("#reply_editor_"+id).css("display","none");
		}
	}
	
	function onEditorBlur(id){
		if ($("#reply_editor_"+id).find("textarea").val()=="")
		{
			setTimeout("switchEditor(0,"+id+");",200);
		}
	};
</script>

<c:choose>
	<c:when test="${pageBean.list==null}">
		<a>没有数据</a>
	</c:when>
	<c:otherwise>
		<c:forEach items="${pageBean.list}" var="twitter">
			<div class="list">
			<div class="avatar"><img src="<%=path%>/avatar/${twitter.author.id}" /></div>
			<div class="topic_msg">
			<div class="time" title="${twitter.time }">${my:formatDate(twitter.time)}</div>
			<p class="content"><a href="#">${twitter.author.name}</a> ${twitter.content} <c:if test="${!empty twitter.image && !empty twitter.image.minFileUrl}">
				<img src="<%=path%>${twitter.image.minFileUrl}" />
			</c:if></p>
			<div class="operate"><a target="content" href="<%=path%>/twitter/deleteTwitter?id=${twitter.id }&page=${page}" class="btn_del">删除</a></div>
			<div class="reply">
				<c:choose>
					<c:when test="${twitter.reply != null && fn:length(twitter.reply)>0}">
						<c:forEach items="${twitter.reply}" var="reply">
							<div class="reply_list">
							<div class="reply_avatar"><img src="<%=path%>/avatar/${reply.author.id}" /></div>
							<p class="reply_content"><a href="#">${reply.author.name}</a> ${reply.content}<br />
							<span class="replytime">${my:formatDate(reply.time)}</span></p>
							</div>
							<div class="clear"></div>
						</c:forEach>
					</c:when>
				</c:choose>
				<div class="reply_list">
					<span id="replybtn_${twitter.id }" class="replybtn textinput" rel="replybtn" rev="${twitter.id }">我也说一句...</span>
				</div>
				<div class="clear"></div>
			</div>
			</div>
			</div>
			<div class="clear "></div>
			<div class="linedot"></div>
		</c:forEach>


		<div id="more_list"><c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<br/><a target="list" href="<%=path%>/twitter/listTwitter?page=${pageBean.currentPage+1}" ><span>更多...</span></a>
			</c:when>
			<c:otherwise>
				<br/><a><span>没有了！</span></a>
			</c:otherwise>
		</c:choose>
		</div>
	</c:otherwise>
</c:choose>
