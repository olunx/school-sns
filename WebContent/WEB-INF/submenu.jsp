<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<ul>
	<li class="on"><a onclick="ajaxload(this);return false;" href="<%=path%>/center">我的大厅</a></li>
	<li><a onclick="ajaxload(this);return false;" href="<%=path%>/twitter/listTwitter">微博</a>  <span><a rev="<%=path%>/home#${user.id}" onclick="ajaxload(this);return false;" href="<%=path%>/twitter/listMyTwitter">我的</a></span> <span><a rev="<%=path%>/home#2" onclick="ajaxload(this);return false;" href="<%=path%>/twitter/listOtherTwitter?otherId=2">某人的</a></span></li>
	<li><a onclick="ajaxload(this);return false;" href="<%=path%>/topic/listTopic">主题</a>  <span><a rev="<%=path%>/home#${user.id}" onclick="ajaxload(this);return false;" href="<%=path%>/topic/listMyTopic">我的</a></span> </li>
	<li><a class="letterspacing" onclick="ajaxload(this);return false;" href="<%=path%>/group/listGroup">群组</a> <span><a onclick="ajaxload(this);return false;" href="<%=path%>/group/goAddGroup">添加</a></span></li>
	<li><a class="letterspacing" onclick="ajaxload(this);return false;" href="<%=path%>/people/listPeople">人员</a> <span><a onclick="ajaxload(this);return false;" href="<%=path%>/people/goAddPeople">添加</a></span></li>
	<li><a class="letterspacing" onclick="ajaxload(this);return false;" href="<%=path%>/student/listStudent">学生</a> <span><a onclick="ajaxload(this);return false;" href="<%=path%>/student/goAddStudent">添加</a></span></li>
	<c:if test="${student.classes != null && student.permission != 1}">
	<li><a class="letterspacing" onclick="ajaxload(this);return false;" href="<%=path%>/classfee/listClassfee">班费</a></li>
	</c:if>
	<li><a class="letterspacing" onclick="ajaxload(this);return false;" href="<%=path%>/course/listCourse">课程</a> <span><a rel="ajaxupload" rev="{upload:'<%=path %>/course/courseUpload',complete:'<%=path %>/course/listCourse',allowtype:/^(xls)$/i}" href="javascript:void()">添加</a></span></li>
	<li><a class="letterspacing" onclick="ajaxload(this);return false;" href="<%=path%>/score/queryScore">成绩</a> <span><a rel="ajaxupload" rev="{upload:'<%=path%>/score/scoreUpload',complete:'<%=path%>/score/listScore',allowtype:/^(xls)$/i}" href="javascript:void()">添加</a></span></li>
	<li><a class="letterspacing" onclick="ajaxload(this);return false;" href="<%=path%>/attendance/listAttendance">考勤</a> <span><a onclick="ajaxload(this);return false;" href="<%=path%>/attendance/goAddAttendance">添加</a></span></li>
	<li><a class="letterspacing" onclick="ajaxload(this);return false;" href="<%=path%>/vote/listVote">投票</a> <span><a onclick="ajaxload(this);return false;" href="<%=path%>/vote/goAddVote">添加</a></span></li>
	<li><a class="letterspacing" onclick="ajaxload(this);return false;" href="<%=path%>/goods/listmeGoods">交换</a> <span><a onclick="ajaxload(this);return false;" href="<%=path%>/goods/goAddGoods">添加</a></span></li>
	<li><a class="letterspacing" onclick="ajaxload(this);return false;" href="<%=path%>/issue/listmeIssue">问答</a> <span><a onclick="ajaxload(this);return false;" href="<%=path%>/issue/goAddIssue">添加</a></span></li>
	<li><a onclick="ajaxload(this);return false;" href="<%=path%>/chat/pubChat">聊天室</a> <a onclick="ajaxload(this);return false;" href="<%=path%>/chat/priChat">匿名聊天</a></li>
	<c:if test="${admin != null }">
	<li><a class="letterspacing" onclick="ajaxload(this);return false;" href="<%=path%>/admin/viewAdmin">管理面板</a></li>
	</c:if>
</ul>
