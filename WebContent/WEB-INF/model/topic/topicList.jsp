<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>
<form onSubmit="post(this);return false;" action="<%=path%>/topic/addTopic" method="post">
<label>发表：</label>
<div class="paddingmin"><textarea name="topic.content" id="demo" rows="10" cols="50" style="width: 600px; height: 195px"></textarea>
<br />
</div>
<a>图片    视频    链接    文件    投票</a>
<p class="paddingmin"><input type="submit" value="提交" /> <input type="reset" value="重置" /><input type="hidden" name="topic.istopic" value="true" /></p>
</form>

<c:choose>

	<c:when test="${pageBean.list==null}">
		<a>没有数据</a>
	</c:when>
	<c:otherwise>

		<form onSubmit="post(this);return false;"  action="<%=path%>/topic/deleteManyTopic" method="post">
		<table class="table">
			<tr>
				<th><a rel="checkall">全选</a></th>
				<th>标题</th>
				<th>类别</th>
				<th>内容</th>
				<th>作者</th>
				<th>创建时间</th>
				<th>删除</th>
			</tr>
			<c:forEach items="${pageBean.list}" var="topic">
				<tr>
					<td><input type="checkbox" name="ids" value="${topic.id }" /></td>
					<td>${topic.title}</td>
					<td></td>
					<td>${topic.content}</td>
					<td>${topic.author.name}</td>
					<td><fmt:formatDate value="${topic.time}" pattern="yyyy-MM-dd HH:mm" /></td>
					<td><a target="content" href="<%=path%>/topic/deleteTopic?id=${topic.id }&page=${page}" class="btn_del">删除</a></td>
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
		</c:choose></div>

		<select name="cmd">
			<option value="0" selected="selected">批量操作，请选择</option>
			<option value="1">删除</option>
		</select> <input type="submit" value="确定" />
		
		</form>
	</c:otherwise>
</c:choose>

