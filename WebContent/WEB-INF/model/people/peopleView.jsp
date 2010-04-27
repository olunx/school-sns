<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
	String url = request.getServerName();
%>
<div>
		<a href="<%=path %>/t/${people.username}"><img src="<%=path%>/avatar/${people.id}" width="124"></img><br/></a>
		${people.name }<br>
		<a href="<%=path %>/t/${people.username}">http://<%=url %>/t/${people.username}</a><br/>
		他广播的：<a target="content" href="<%=path %>/twitter/listOtherTwitter?otherId=${people.id}">${fn:length(pageBean.list)}</a>条 |
		他关注的：${fn:length(people.friends)}人 |
		关注他的：${fn:length(people.follower)}人 |
		最近来访：${fn:length(people.visitors)}人
		<br />
	</div>
<br/>
<br/>
<p><label> id： </label> <input type="text" name="people.id" value="${people.id}" /></p>
<p><label> 用户名： </label> <input type="text" name="people.username" value="${people.username}" /></p>
<p><label> 密码： </label> <input type="text" name="people.password" value="${people.password}" /></p>
<p><label> 昵称： </label> <input type="text" name="people.nickname" value="${people.nickname}" /></p>
<p><label> 姓名： </label> <input type="text" name="people.name" value="${people.name}" /></p>
<p><label> 宿舍： </label> <input type="text" name="people.dorm" value="${people.dorm}" /></p>
<p><label> 手机： </label> <input type="text" name="people.phoneNo" value="${people.phoneNo}" /></p>
<p><label> QQ： </label> <input type="text" name="people.qq" value="${people.qq}" /></p>
<p><label> 邮箱： </label> <input type="text" name="people.email" value="${people.email}" /></p>
<p><label> 参加的小组： </label>
<c:if test="${people.groups != null}">
	<c:forEach items="${people.groups}" var="group">
		 <input type="text" value="${group.name}" />
	</c:forEach>
</c:if>
</p>