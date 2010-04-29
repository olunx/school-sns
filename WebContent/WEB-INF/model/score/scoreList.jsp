<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>

<a target="content" href="<%=path %>/score/listScore" >全部学生的成绩</a>
<a rel="ajaxupload" rev="{upload:'<%=path%>/score/scoreUpload',complete:'<%=path%>/score/listScore',allowtype:/^(xls)$/i}" href="javascript:void()">上传成绩</a>
<c:choose>
	<c:when test="${scores == null}">
					没有该学生的成绩数据！
	</c:when>
	<c:otherwise>
		<table class="table">
				<tr>
					<td>科目</td>
					<td>分数</td>
				</tr>
			<c:forEach items="${scores }" var="score">
				<tr>
					<td>${score.subject}</td>
					<td>${score.marks}</td>
				</tr>
			</c:forEach>
		</table>
	</c:otherwise>
</c:choose>

