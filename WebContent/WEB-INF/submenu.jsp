<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<div id="submenu">
<ul>
	<li><a target="content" href="<%=path%>/test/listTest">测试</a> <a target="content" href="<%=path%>/test/goAddTest">添加</a></li>
	<li><a target="content" href="<%=path%>/classfee/listClassfee">班费</a> <a target="content" href="<%=path%>/classfee/goAddClassfee">添加</a></li>
	<li><a target="content" href="<%=path%>/notice/listNotice">公告</a> <a target="content" href="<%=path%>/notice/goAddNotice">添加</a></li>
	<li><a target="content" href="<%=path%>/course/listCourse">课程</a> <a target="content" href="<%=path%>/course/goAddCourse">添加</a></li>
</ul>
</div>