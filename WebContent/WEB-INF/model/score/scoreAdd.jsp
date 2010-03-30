<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>

	<h2 >成绩发送</h2>
		<form action="<%=path %>/score/scoreUpload" method="post" enctype="multipart/form-data">
			<!-- file对应的input必须有name属性,name的值必须和action中的变量对应 -->
			成绩Excel文件：<input type="file" name="files" />
			<input type="submit" value="提交" />
		</form>
