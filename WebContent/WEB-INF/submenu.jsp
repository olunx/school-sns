<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<div id="submenu">
<ul>
	<li><a target="content" href="<%=path%>/test/listTest">测试</a> <a target="content" href="<%=path%>/test/goAddTest">添加</a></li>
	<li><a target="content" href="<%=path%>/student/listStudent">学生</a> <a target="content" href="<%=path%>/student/goAddStudent">添加</a></li>
	<li><a target="content" href="<%=path%>/classfee/listClassfee">?���?/a> <a target="content" href="<%=path%>/classfee/goAddClassfee">添加</a></li>
	<li><a target="content" href="<%=path%>/notice/listNotice">公告</a> <a target="content" href="<%=path%>/notice/goAddNotice">添加</a></li>
	<li><a target="content" href="<%=path%>/course/listCourse">课程</a> <a target="content" href="<%=path%>/course/goAddCourse">添加</a></li>
	<li><a target="content" href="<%=path%>/score/listScore">成绩</a> <a target="content" href="<%=path%>/score/goAddScore">添加</a></li>
	<li><a target="content" href="<%=path%>/attendance/listAttendance">考勤</a> <a target="content" href="<%=path%>/attendance/goAddAttendance">添加</a></li>
</ul>
</div>