<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="cn.gdpu.chat.*"%>
<%
	String path = request.getContextPath();
%>
<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=path%>/dwr/interface/MyChat.js'></script>
<script language="javascript">
	function init() {
	  dwr.engine.setActiveReverseAjax(true);
	  setTimeout("MyChat.updateUsersList();",1000);
	}
	init();


	//接收在线用户数据
	function receiveOnlineUser(data){
		var userlist = "";
		for (var i in data) {
			userlist = userlist +", "+ (data[i].name);
		}
		userlist = userlist.substr(2,userlist.length);
		dwr.util.setValue("userlist",userlist);
	}

	function show(msg){
		msg = msg +"<br/>"+ dwr.util.getValue("showmsg",{escapeHtml:false});
		//alert(msg);
		dwr.util.setValue("showmsg",msg,{escapeHtml:false});
		
	}

	function sendmsg(){
		var msg = dwr.util.getValue("msg");
		if (msg!=""){
			var sendto = dwr.util.getValue("sendto");
				MyChat.sayTo(sendto,msg);
				dwr.util.setValue("msg","");
		}
	}

	function receiveMessage(message){
		var tmpstr = dwr.util.getValue("showmsg",{escapeHtml:false});
		if (message.to == "所有人")
			tmpstr = tmpstr +"<br/>"+ message.from +" 说："+ message.text+" ";
		else 
			tmpstr = tmpstr +"<br/>"+ message.from +" 对 "+message.to +" 说："+ message.text+" ";
		tmpstr = tmpstr + "<span class='chat_time'>"+message.time+"</span>";
		dwr.util.setValue("showmsg",tmpstr,{escapeHtml:false});
	}
/*
	function receiveMessages(messages) {
	  var chatlog = "";
	  for (var data in messages) {
	    chatlog = "" + dwr.util.escapeHtml(messages[data].text) + "<br/>" + chatlog;
	  }
	  dwr.util.setValue("chatlog", chatlog, { escapeHtml:false });
	}
	*/
</script>
<div id="userlist"></div>
<button id="refresh" onclick="MyChat.updateUsersList();">刷新在线用户</button>
<select name="sendto" id="sendto">
	<option value="">所有人</option>
</select>
<input type="text" name="msg" id="msg" onkeypress="dwr.util.onReturn(event, sendmsg)"/>
<button onclick="sendmsg()">发送</button>
<div id="showmsg"></div>