<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<div id="submenu">
<ul>
	<li><a target="content" href="<%=path%>/test/listTest">测试</a> <a target="content" href="<%=path%>/test/goAddTest">添加</a></li>
	<li><a target="content" href="<%=path%>/mail/listMail">纸条</a> <a target="content" href="<%=path%>/mail/listMySendMail">发件箱</a> <a target="content" href="<%=path%>/mail/listMyReceMail">收件箱</a> <a target="content" href="<%=path%>/mail/goAddMail">发送</a></li>
	<li><a target="content" href="<%=path%>/topic/listTopic">微博</a> <a target="content" href="<%=path%>/topic/listMyTopic">我的</a> <a target="content" href="<%=path%>/topic/listOtherTopic?otherId=2">某人的</a></li>
	<li><a target="content" href="<%=path%>/student/listStudent">学生</a> <a target="content" href="<%=path%>/student/goAddStudent">添加</a></li>
	<li><a target="content" href="<%=path%>/classfee/listClassfee">班费</a> <a target="content" href="<%=path%>/classfee/goAddClassfee">添加</a></li>
	<li><a target="content" href="<%=path%>/course/listCourse">课程</a> <a target="content" href="<%=path%>/course/goAddCourse">添加</a></li>
	<li><a target="content" href="<%=path%>/score/queryScore">成绩</a> <a target="content" href="<%=path%>/score/goAddScore">添加</a></li>
	<li><a target="content" href="<%=path%>/attendance/listAttendance">考勤</a> <a target="content" href="<%=path%>/attendance/goAddAttendance">添加</a></li>
	<li><a target="content" href="<%=path%>/vote/listVote">投票</a> <a target="content" href="<%=path%>/vote/goAddVote">添加</a></li>
	<li><a target="content" href="<%=path%>/goods/listmeGoods">交换</a> <a target="content" href="<%=path%>/goods/goAddGoods">添加</a></li>
	<li><a target="content" href="<%=path%>/chat/listChat">聊天室</a> </li>

</ul>
</div>