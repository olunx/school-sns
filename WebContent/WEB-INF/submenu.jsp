<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<div id="submenu">
<ul>
	<li><a href="<%=path %>/test/listTest">测试</a>  <a href="<%=path %>/goAddTest">添加</a></li>
	<li>菜单二</li>
	<li>菜单三</li>
</ul>
</div>