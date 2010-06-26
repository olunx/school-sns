<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="cn.gdpu.chat.*"%>
<%
	String path = request.getContextPath();
%>
<script type='text/javascript' src='<%=path%>/content/js/private-chat.js'></script>

<div id="sysmsg">点击下方的开始聊天畅聊吧~</div>
<div id="showmsg"></div>
<input type="text" name="msg" id="msg" onkeypress="" onkeydown="if (event.keyCode==13){sendmsg();};"/>
<input type="button" id="sendBtn" onclick="sendmsg()" disabled="disabled" value="发送" />
<input type="button" id="logoutBtn" onclick="logout()" title="1" value="开始聊天"/>