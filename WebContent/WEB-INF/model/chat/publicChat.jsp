<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="cn.gdpu.chat.*"%>
<%
	String path = request.getContextPath();
%>
<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=path%>/dwr/interface/PublicChat.js'></script>
<script type='text/javascript' src='<%=path%>/content/js/jquery.selectboxes.min.js'></script>
<script type='text/javascript' src='<%=path%>/content/js/public-chat.js'></script>

<div id="showmsg"></div>
<select name="sendto" id="sendto">
	<option value="">所有人</option>
</select>
<input type="text" name="msg" id="msg" onkeypress="" onkeydown="if (event.keyCode==13){sendmsg();};"/>
<input type="button" id="sendBtn" onclick="sendmsg()" disabled="disabled" value="发送" />
<input type="button" id="logoutBtn" onclick="logout()" title="0" value="退出"/>