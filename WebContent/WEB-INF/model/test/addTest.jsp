<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<br />
<a rel="ajax" href="<%=path %>/test/addTest">确定</a>
<a href="<%=path%>/test/uploadTest">测试上传组件</a>
