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

		<form onSubmit="post(this);return false;"  action="<%=path%>/vote/deleteMany" method="post">
		<table class="table">
			<tr>
				<th>
				<a rel="checkall" >全选</a>
				</th>
				<th>投票主题</th>
				<th>投票描述</th>
				<th>创建人</th>
				<th>创建日期</th>
				<th>结束日期</th>
				<th>删除</th>
				<th>看图</th>
			</tr>
			<c:forEach items="${pageBean.list}" var="vote">
				<tr>						
					<td>
						<input type="checkbox" name="ids" value="${vote.id }" />
					</td>
					
					<td>
						<a target="content" href="<%=path %>/vote/goVotingVote?vid=${vote.id}" >${fn:substring(fn:replace(vote.title,"<","&lt;"),0,20)}</a>
					</td>
					<td>
						${fn:substring(fn:replace(vote.summary,"<","&lt;"),0,20)}...
					</td>
					<td>
						${vote.author.name }
					</td>
					<td>
						<fmt:formatDate value="${vote.airTime}" pattern="yyyy-MM-dd HH:mm"/>
					</td>
					<td>
						<fmt:formatDate value="${vote.deadline}" pattern="yyyy-MM-dd HH:mm"/>
					</td>
					<td>
						<a target="content" href="<%=path %>/vote/deleteVote?vid=${vote.id}" class="btn_del"></a>
					</td>
					<td>
						<a target="content" href="<%=path %>/vote/viewVote?vid=${vote.id}" >看图</a>
					</td>
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
				<a target="content" href="<%=path%>/vote/listVote?page=1"><span>首页</span></a>
				<a target="content" href="<%=path%>/vote/listVote?page=${pageBean.currentPage-1}"><span>上一页</span></a>
			</c:otherwise>
		</c:choose> <c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<a target="content" href="<%=path%>/vote/listVote?page=${pageBean.currentPage+1}"><span>下一页</span></a>
				<a target="content" href="<%=path%>/vote/listVote?page=${pageBean.totalPage}"><span>尾页</span></a>
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
