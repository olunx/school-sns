<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
%>

<script type="text/JavaScript">
</script>
<form action="<%=path %>/classes/addClasses" method="post" onSubmit="post(this);return false;"  Class="form" >
	<p><label>选择学院：</label>
		<select name="id">  
			<c:forEach items="${institutes}" var="institute">
				<option value="${institute.id}">${institute.name}</option>
			</c:forEach>
		</select>
	</p>
	<p><label>班级名称：</label>
	<input type="text" name="classes.name" />
	</p>
	<p><label>入学年份：</label>
	<input type="text" name="classes.entryYear" />
	</p>
	<input type="submit" value="提交"/>
</form>