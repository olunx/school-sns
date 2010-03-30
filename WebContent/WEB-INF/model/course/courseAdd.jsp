<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>

		<form action="<%=path %>/course/courseUpload" method="post" enctype="multipart/form-data">
			<!-- file对应的input必须有name属性,name的值必须和action中的变量对应 -->
			课程Excel文件：<input type="file" name="files" />
			<input type="submit" value="提交" />
		</form>