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
	}

	function updateOnlineUser(data){
		var userlist = "";
		for (var i in data) {
			userlist = userlist +", "+ (data[i].name);
		}
		userlist = userlist.substr(2,userlist.length);
		dwr.util.setValue("userlist",userlist);
	}

	function show(msg){
		msg = msg +"<br/>"+ dwr.util.getValue("showmsg",{escapeHtml:false});
		dwr.util.setValue("showmsg",msg,{escapeHtml:false});
		//alert(msg);
	}

	function sendmsg(){
		var msg = dwr.util.getValue("msg");
		if (msg!=""){
			var sendto = dwr.util.getValue("sendto");
				MyChat.sayTo(sendto,msg);
				dwr.util.setValue("msg","");
		}
	}

	init();
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
<input type="text" name="username" id="username" /><button onclick="MyChat.updateUsersList(dwr.util.getValue('username'));">进入</button>
<button onclick="MyChat.updateUsersList('');">刷新在线用户</button>
<select name="sendto" id="sendto">
	<option value="">所有人</option>
</select>
<input type="text" name="msg" id="msg" onkeypress="dwr.util.onReturn(event, sendmsg)"/>
<button onclick="sendmsg()">发送</button>
<div id="showmsg"></div>