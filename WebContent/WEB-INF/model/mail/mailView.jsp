<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>
<label>发送者：</label><input type="text" name="mail.sender.name" value="${mail.sender.name}" /><br/>
<label>发送时间：</label><input type="text" name="mail.time" value="<fmt:formatDate value="${mail.time}" pattern="yyyy-MM-dd HH:mm" />" /><br/>
<label>标题：</label><input type="text" name="mail.title" value="${mail.title}" />
<div class="paddingmin"><textarea name="mail.content" id="demo" rows="10" cols="50" style="width: 600px; height: 195px">
${mail.content}
</textarea>
<br />
<c:choose>
	<c:when test="${mail.reply != null}">
		<c:forEach items="${mail.reply}" var="reply">
			 ${reply.sender.name} 回复： ${reply.content}
		</c:forEach>
	</c:when>
</c:choose>
</div>
