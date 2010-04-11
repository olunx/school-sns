<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript">
	function closes() {
		parent.$("*").colorbox.close();
		//$("*").colorbox.close();
	}
</script>
<c:if test="${targetsFileUrl != null}">
	<c:forEach items="${targetsFileUrl}" var="image">
		<img id="photo" src="<%=path%>${image}" />
		<div>
			<form onSubmit="parent.post(this);closes();return false;" action="<%=path %>/avatar/addAvatar" method="post">
			 x1: <input type="text" id="x" name="x" value="0" />
			 y1: <input type="text" id="y" name="y" value="0" />
			 width: <input type="text" id="width" name="width" value="0" />
			 height: <input type="text" id="height" name="height" value="0" />
			 <input type="submit" value="确定" />
			 <input type="hidden" name="targetsFileUrl" value="${image}" />
			 <c:if test="${targetsFileUrl != null}">
				<c:forEach items="${filesFileName}" var="filesFN">
					<input type="hidden" name="filesFileName" value="${filesFN}" />
			 	</c:forEach>
			</c:if>
			 <c:if test="${targetsFileUrl != null}">
				<c:forEach items="${targetsFileName}" var="targetsFN">
					<input type="hidden" name="targetsFileName" value="${targetsFN}" />
			 	</c:forEach>
			</c:if>
			</form>
		</div>
	</c:forEach>
</c:if>