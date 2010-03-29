<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
%>
<h2 class="caption">
	批量注册：
</h2>
<form onSubmit="post(this);return false;" action="<%=path %>/student/uploadStudent" method="post" enctype="multipart/form-data">
	<!-- file对应的input必须有name属性,name的值必须和action中的变量对应 -->
	学生数据Excel文件：<input type="file" name="files" />					
	<input type="submit" value="提交" />
	<s:fielderror><s:param>fileTypeAlert</s:param></s:fielderror>
</form>
