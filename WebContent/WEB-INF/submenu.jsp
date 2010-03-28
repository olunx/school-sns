<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<div id="submenu">
<ul>
	<li><a href="<%=path %>/test/listTest">测试</a>  <a href="<%=path %>/test/goAddTest">添加</a></li>
	<li><a href="<%=path %>/classfee/listClassFee">班费</a> <a href="<%=path %>/classfee/goAddClassFee">添加</a></li>
	<li><a href="<%=path %>/notice/listNotice">公告</a>  <a href="<%=path %>/notice/goAddNotice">添加</a></li>
	<li>菜单二</li>
</ul>
</div>