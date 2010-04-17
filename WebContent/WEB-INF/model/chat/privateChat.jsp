<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="cn.gdpu.chat.*"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>
<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=path%>/dwr/interface/PrivateChat.js'></script>
<script type='text/javascript' src='<%=path%>/content/js/jquery.selectboxes.min.js'></script>
<script type="text/javascript">
<!--
var myuserid;

function init(){
    cBtn(0);
    dwr.engine.setActiveReverseAjax(true);
    PrivateChat.updateUsersList();
}

//设置我的userid
function setUserid(id){
    myuserid = id;
}

/**
 * 对Date的扩展，将 Date 转化为指定格式的String
 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
 * eg:
 * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04
 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04
 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04
 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
 */
Date.prototype.pattern=function(fmt) {
	var o = {
	"M+" : this.getMonth()+1, //月份
	"d+" : this.getDate(), //日
	"h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时
	"H+" : this.getHours(), //小时
	"m+" : this.getMinutes(), //分
	"s+" : this.getSeconds(), //秒
	"q+" : Math.floor((this.getMonth()+3)/3), //季度
	"S" : this.getMilliseconds() //毫秒
	};
	var week = {
	"0" : "\u65e5",
	"1" : "\u4e00",
	"2" : "\u4e8c",
	"3" : "\u4e09",
	"4" : "\u56db",
	"5" : "\u4e94",
	"6" : "\u516d"
	};
	if(/(y+)/.test(fmt)){
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	}
	if(/(E+)/.test(fmt)){
		fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);
	}
	for(var k in o){
		if(new RegExp("("+ k +")").test(fmt)){
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
		}
	}
	return fmt;
}

function receiveSystemMessage(cmd,msg){
	switch(cmd)
	   {
	   case 0:
		 showSystem("系统消息："+msg);
	     break;
	   case 1:
		showSystem(msg);
	   	cBtn(1);
	   	break;
	   case 2:
		 showSystem("配对失败："+msg);
	     break;
	   default:
		   showSystem("系统消息："+msg);
	   }
}


//接收在线用户数据
function receiveOnlineUser(data){
    $("#onlineuser").remove();
    $("#sidebar").prepend("<div id='onlineuser' class='mod'><h2 onclick='PrivateChat.updateUsersList();'>匿名聊天在线人数:"+data+"</h2></div>");
}

//设置button状态
function cBtn(state){
    if (state == 1) {
        $("#sendBtn").attr("disabled", "");
        $("#msg").attr("disabled", "");
    }
    else {
        $("#sendBtn").attr("disabled", "disabled");
        $("#msg").attr("disabled", "disabled");
    }
}

//发送信息
function sendmsg(){
    var msg = $("#msg").val();
    if (msg != "") {
        PrivateChat.say(msg);
        var mymsg = [{from: "你", fromid: "", text: msg, time: (new Date()).pattern("hh:mm:ss"), to: "", toid: ""}];
        receiveMessage(mymsg);
        $("#msg").val("").focus();
    }
}

//接收信息
function receiveMessage(messageList){
    for (var data in messageList) {
        var message = messageList[data];
        var tmpstr = "";
        if (message.fromid == myuserid) {
            message.from = "我";
            tmpstr = tmpstr + "<p class='my'>";
        }
        else {
            tmpstr = tmpstr + "<p class='other'>";
        };
        if (message.toid == myuserid) {
            message.to = "我";
        };
        if (message.to == "") 
            tmpstr = tmpstr + message.from + "说：" + message.text + " ";
        else 
            tmpstr = tmpstr + message.from + "对" + message.to + "说：" + message.text + " ";
        tmpstr = tmpstr + "<span class='chat_time'>" + message.time + "</span>";
        tmpstr = tmpstr + "</p>";
        $("#showmsg").append(tmpstr);
    }
    scrollBottom();
};

//显示操作信息
function showSystem(message){
    $("#showmsg").append("<p>" + message + "</p>");
    scrollBottom();
}

//滚动到最下方
function scrollBottom(){
    var lastp = $("#showmsg p:last").position();
    if (lastp != null) {
        $("#showmsg").scrollTop(lastp.top + 5000);
    };
}

//退出
function logout(){
    var flag = $("#logoutBtn").attr("title");
    if (flag == "0") {
        PrivateChat.logout();
        //$("#onlineuser").remove();
        $("#logoutBtn").attr("title", "1");
        $("#logoutBtn").val("开始聊天");
        cBtn(0);
    }
    else {
    	$("#showmsg").html("");
    	showSystem("连接服务器...");
    	PrivateChat.login(function(data){myuserid = data;showSystem("连接服务器成功，正在为您配对...");});
    	PrivateChat.updateUsersList();
        $("#logoutBtn").attr("title", "0");
        $("#logoutBtn").val("断开");
    };
};

/*对方退出*/
function onelogout(){
	showSystem("对方已经断开了，请重新寻找下一位");
    $("#logoutBtn").attr("title", "1");
    $("#logoutBtn").val("开始聊天");
    cBtn(0);
}


init();

//-->
</script>
<div id="sysmsg">点击下方的开始聊天畅聊吧~</div>
<div id="showmsg"></div>
<input type="text" name="msg" id="msg" onkeypress="" onkeydown="if (event.keyCode==13){sendmsg();};"/>
<input type="button" id="sendBtn" onclick="sendmsg()" disabled="disabled" value="发送" />
<input type="button" id="logoutBtn" onclick="logout()" title="1" value="开始聊天"/>