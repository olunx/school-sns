<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>
<c:choose>

	<c:when test="${pageBean.list==null}">
		<a>没有数据</a>
	</c:when>
	<c:otherwise>

		<form onSubmit="post(this);return false;"  action="<%=path%>/mail/deleteManyMail" method="post">
		<table class="table">
			<tr>
				<th><a rel="checkall">全选</a></th>
				<th>标题</th>
				<th>内容</th>
				<th>发送者</th>
				<th>接收者</th>
				<th>创建时间</th>
				<th>删除</th>
			</tr>
			<c:forEach items="${pageBean.list}" var="mail">
				<tr>
					<td><input type="checkbox" name="ids" value="${mail.id }" />
					 <c:if test="${mail.isreaded == false}"><a style="color:red;">未读</a></c:if>
					 <a target="content" href="<%=path%>/mail/goReplyMail?id=${mail.id }">回复</a>
					</td>
					<td>
					<a target="content" href="<%=path%>/mail/viewMail?id=${mail.id}">${mail.title}</a>
					 <c:if test="${mail.reply != null}">(${fn:length(mail.reply)})</c:if>
					 <c:if test="${mail.hasreply == true}"><a style="color:red;">有新回复</a></c:if>
					</td>
					<td>${mail.content}</td>
					<td>${mail.sender.name}</td>
					<td>${mail.receiver.name}</td>
					<td><fmt:formatDate value="${mail.time}" pattern="yyyy-MM-dd HH:mm" /></td>
					<td><a target="content" href="<%=path%>/mail/deleteMail?id=${mail.id }&page=${page}" class="btn_del">删除</a></td>
				</tr>
			</c:forEach>
		</table>
		
		<div id="pagecount">
		<p>共 ${pageBean.allRow} 条记录 共 ${pageBean.totalPage} 页 当前第 ${pageBean.currentPage}页</p>
		<c:choose>
			<c:when test="${pageBean.currentPage == 1}">
				<a><span>首页</span></a>
				<a><span>上一页</span></a>
			</c:when>
			<c:otherwise>
				<a target="content" href="<%=path%>/mail/listMail?page=1"><span>首页</span></a>
				<a target="content" href="<%=path%>/mail/listMail?page=${pageBean.currentPage-1}"><span>上一页</span></a>
			</c:otherwise>
		</c:choose> <c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<a target="content" href="<%=path%>/mail/listMail?page=${pageBean.currentPage+1}"><span>下一页</span></a>
				<a target="content" href="<%=path%>/mail/listMail?page=${pageBean.totalPage}"><span>尾页</span></a>
			</c:when>
			<c:otherwise>
				<a><span>下一页</span></a>
				<a><span>尾页</span></a>
			</c:otherwise>
		</c:choose></div>

		<select name="cmd">
			<option value="0" selected="selected">批量操作，请选择</option>
			<option value="1">删除</option>
		</select> <input type="submit" value="确定" />
		
		</form>
	</c:otherwise>
</c:choose>

