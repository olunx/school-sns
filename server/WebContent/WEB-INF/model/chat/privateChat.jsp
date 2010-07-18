<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="cn.gdpu.chat.*"%>
<%
	String path = request.getContextPath();
%>
<script type='text/javascript' src='<%=path%>/content/js/private-chat.js'></script>
<div class="chat">
<h2>倾诉</h2>
<div id="sysmsg">没有人是完全没烦恼的。即使你万贯家财，即使你笑意盈盈…… 可是，当我们烦恼时，你有一个可以让你尽情倾诉的对象吗？ 从心理学上来讲，人的情感是需要发泄的。很多的时候，我们烦闷，是因为我们无处言说，找不到情绪的出口。</div>
<div id="showmsg"></div>
<input type="text" name="msg" id="msg" class="msg" onkeypress="" onkeydown="if (event.keyCode==13){sendmsg();};"/>
<input type="button" id="sendBtn" onclick="sendmsg()" disabled="disabled" value="发送" />
<input type="button" id="logoutBtn" onclick="logout()" title="1" value="开始聊天"/>
</div>