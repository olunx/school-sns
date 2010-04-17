<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript">
	$(function() {
		$("#twitter").load("<%=path%>/topic/goAddTopic", ajax);
	});
</script>
微博：
<div id="twitter">
</div>


<c:choose>

	<c:when test="${pageBean.list==null}">
		<a>没有数据</a>
	</c:when>
	<c:otherwise>
	<form onSubmit="post(this);return false;"  action="<%=path%>/topic/deleteManyTopic" method="post">
	
		<c:forEach items="${pageBean.list}" var="topic">
		<div class="box">
			<div class="left">
		        <div class="avatar"><img src="<%=path %>${topic.author.avatar.minFileUrl}" /></div>
		        <div class="name">${topic.author.name}</div>
		    </div>
		    <div class="center">
	        	<div class="text">
	            	主要内容 : ${topic.content} <br/>时间：<fmt:formatDate value="${topic.time}" pattern="yyyy-MM-dd HH:mm" />
	            </div>
	            <c:choose>
					<c:when test="${topic.reply != null}">
						<c:forEach items="${topic.reply}" var="reply">
							<div class="post">${reply.author.name} 回复： ${reply.content}</div>
						</c:forEach>
					</c:when>
				</c:choose>
		    </div>
		    <div class="right">
		        <div class="reply"><a target="content" href="<%=path%>/topic/goReplyTopic?id=${topic.id }">回复</a> </div>
		        <div class="delete"><a target="content" href="<%=path%>/topic/deleteTopic?id=${topic.id }&page=${page}" class="btn_del">删除</a></div>
		        <input type="checkbox" name="ids" value="${topic.id }" />
		    </div>
		</div>
		</c:forEach>
		
		<div id="pagecount" style="margin:5px;float:left;">
		<p>共 ${pageBean.allRow} 条记录 共 ${pageBean.totalPage} 页 当前第 ${pageBean.currentPage}页</p>
		<c:choose>
			<c:when test="${pageBean.currentPage == 1}">
				<a><span>首页</span></a>
				<a><span>上一页</span></a>
			</c:when>
			<c:otherwise>
				<a target="content" href="<%=path%>/topic/listTopic?page=1"><span>首页</span></a>
				<a target="content" href="<%=path%>/topic/listTopic?page=${pageBean.currentPage-1}"><span>上一页</span></a>
			</c:otherwise>
		</c:choose> <c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<a target="content" href="<%=path%>/topic/listTopic?page=${pageBean.currentPage+1}"><span>下一页</span></a>
				<a target="content" href="<%=path%>/topic/listTopic?page=${pageBean.totalPage}"><span>尾页</span></a>
			</c:when>
			<c:otherwise>
				<a><span>下一页</span></a>
				<a><span>尾页</span></a>
			</c:otherwise>
		</c:choose>
		</div>

		<select name="cmd">
			<option value="0" selected="selected">批量操作，请选择</option>
			<option value="1">删除</option>
		</select> <input type="submit" value="确定" />
		
		</form>
		
	</c:otherwise>
</c:choose>

