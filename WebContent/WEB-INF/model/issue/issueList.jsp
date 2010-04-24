<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path %>/content/js/jquery.doubleSelect.min.js"></script>
<script type="text/JavaScript">
 $(document).ready(function()
 {		
		var selectoptions = ${jsonmap};
	    $('#first').doubleSelect('second', selectoptions);      
 });
</script>

<a target="content" href="<%=path %>/issue/listIssue" >全部学生的提问</a>我的提问<br />
<c:choose>
	<c:when test="${pageBean.list == null}">
					没有该学生的提问数据！
			</c:when>
	<c:otherwise>
		<table class="table">
			<tr>
				<td>提问</td>
				<td>描述</td>
				<td>状态</td>
				<td>悬赏</td>
				<td>时间</td>
				<td>删除</td>
			</tr>
			<c:forEach items="${pageBean.list}" var="issue">
				<tr>
					<td><a target="content" href="<%=path%>/issue/viewIssue?id=${issue.id }">${issue.name}</a></td>
					<td>${fn:substring(fn:replace(issue.content,"<","&lt;"),0,20)}...</td>
					<td>${issue.answer == null ? "未解决":"已解决"}</td>
					<td>${issue.value }</td>
					<td><fmt:formatDate value="${issue.airTime}" pattern="yyyy-MM-dd HH:mm" /></td>
					<td><a target="content" href="<%=path %>/issue/deleteIssue?id=${issue.id }">删除</a></td>
				</tr>
			</c:forEach>
		</table>

		<div id="pagecount">
		<p>共  ${pageBean.allRow} 条记录 共 ${pageBean.totalPage} 页 当前第 ${pageBean.currentPage}页</p>
		<c:choose>
			<c:when test="${pageBean.currentPage == 1}">
				<a><span>首页</span></a>
				<a><span>上一页</span></a>
			</c:when>
			<c:otherwise>
				<a target="content" href="<%=path%>/issue/listIssue?page=1"><span>首页</span></a>
				<a target="content" href="<%=path%>/issue/listIssue?page=${pageBean.currentPage-1}"><span>上一页</span></a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<a target="content" href="<%=path%>/issue/listIssue?page=${pageBean.currentPage+1}"><span>下一页</span></a>
				<a target="content" href="<%=path%>/issue/listIssue?page=${pageBean.totalPage}"><span>尾页</span></a>
			</c:when>
			<c:otherwise>
				<a><span>下一页</span></a>
				<a><span>尾页</span></a>
			</c:otherwise>
		</c:choose>
		</div>

	</c:otherwise>
</c:choose>

<br />
热门提问
<br />
<c:choose>
	<c:when test="${issuehot != null}">
				<tr>
					<td>提问</td>
					<td>描述</td>
					<td>状态</td>
					<td>悬赏</td>
					<td>拥有者</td>
					<td>时间</td>
				</tr>
			<c:forEach items="${issuehot}" var="issuehot">
				<tr>
					<td><a target="content" href="<%=path%>/issue/viewIssue?id=${issuehot.id }">${issuehot.name}</a></td>
					<td>${fn:substring(fn:replace(issuehot.content,"<","&lt;"),0,20)}...</td>
					<td>${issuehot.answer == null? "未解决" :"已解决" }</td>
					<td>${issuehot.value }</td>
					<td>${issuehot.owner.name }</td>
					<td><fmt:formatDate value="${issuehot.airTime}" pattern="yyyy-MM-dd HH:mm" /></td>
				</tr>
			</c:forEach>
	</c:when>
	<c:otherwise>
		&nbsp;&nbsp;暂无热门提问
	</c:otherwise>
</c:choose>
<br />
最新提问
<br />
<c:choose>
	<c:when test="${issuenew != null}">
				<tr>
					<td>提问</td>
					<td>描述</td>
					<td>状态</td>
					<td>悬赏</td>
					<td>拥有者</td>
					<td>时间</td>
				</tr>
			<c:forEach items="${issuenew}" var="issuenew">
				<tr>
					<td><a target="content" href="<%=path%>/issue/viewIssue?id=${issuenew.id }">${issuenew.name}</a></td>
					<td>${fn:substring(fn:replace(issuenew.content,"<","&lt;"),0,20)}...</td>
					<td>${issuenew.answer == null? "未解决" :"已解决" }</td>
					<td>${issuenew.value }</td>
					<td>${issuenew.owner.name }</td>
					<td><fmt:formatDate value="${issuenew.airTime}" pattern="yyyy-MM-dd HH:mm" /></td>
				</tr>
			</c:forEach>
	</c:when>
	<c:otherwise>
		&nbsp;&nbsp;暂无最新提问
	</c:otherwise>
</c:choose>
<br />
<br />
<form onSubmit="post(this);return false;" class="form" action="<%=path %>/issue/searchIssue" method="post">
	<select id="first" size="1"><option value="">--</option></select>
	<select id="second" name="itid" size="1"><option value="">--</option></select>
	<input type="text" name="search" />
	<input type="submit" value="搜索"/>
</form>