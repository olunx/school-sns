<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<div id="submenu">
<ul>
	<li><a rel="ajax" href="<%=path %>/test/listTest">测试</a>  <a rel="ajax" href="<%=path %>/test/goAddTest">添加</a></li>
	<li><a href="<%=path %>/classfee/listClassFee">班费</a> <a href="<%=path %>/classfee/goAddClassFee">添加</a></li>
	<li><a rel="ajax"  href="<%=path %>/notice/listNotice">公告</a>  <a rel="ajax"  href="<%=path %>/notice/goAddNotice">添加</a></li>
	<li>菜�??�?/li>
</ul>
</div>