<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
%>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="submenu.jsp"></jsp:include>
<div id="main" class="corner">
	<div>
		<a href="<%=path %>/t/${people.username}"><img src="<%=path %>/avatar/${people.id}" width="124"></img><br/></a>
		${people.name }<br>
		<a href="<%=path %>/t/${people.username}"><%=path %>/t/${people.username}</a><br/>
		他广播的：00xx |
		他关注的：${fn:length(people.friends)}人 |
		关注他的：${fn:length(people.follower)}人 |
		最近来访：${fn:length(people.visitors)}人
		<br />
		
		 <c:choose>
		 	<c:when test="${my:isMyFriend(friends,people)}">取消关注</c:when>
		 	<c:otherwise>设为关注</c:otherwise>
		 </c:choose>
	</div>

<script type="text/javascript">
$(function() {
	$("#content").load("<%=path%>/twitter/listOtherTwitter?otherId=${id}", ajax);
});
</script>
<div id="content">

</div>

 	<div>
	 <p>他关注的：<br/>
			<c:forEach items="${people.friends}" var="friends">
			<ul>
				<li>
					<a target="content" href="<%=path%>/student/viewStudent?id=${friends.id }"><img src="<%=path %>/avatar/${friends.id}" width="50"></img></a><br/>
					<a target="content" href="<%=path%>/student/viewStudent?id=${friends.id }">${friends.name}</a><br/>
				</li>
			</ul>
			</c:forEach>
	 </p>
	 </div>
	 
	 <div>
	 <p>最近访问：<br/>
		<c:forEach items="${people.visitors}" var="visitor">
		<ul>
			<li>
				<a target="content" href="<%=path%>/student/viewStudent?id=${visitor.people.id }"><img src="<%=path %>/avatar/${visitor.people.id}" width="50"></img></a><br/>
				<a target="content" href="<%=path%>/student/viewStudent?id=${visitor.people.id }">${visitor.people.name}</a><br/>
				<fmt:formatDate value="${visitor.time }" pattern="HH:mm MM-dd" />	
			</li>
		</ul>
		</c:forEach>
	 </p>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>