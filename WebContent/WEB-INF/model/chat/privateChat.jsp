<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="cn.gdpu.chat.*"%>
<%
	String path = request.getContextPath();
%>
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
    showSystem("正在载入，请稍等...");
    setTimeout("PrivateChat.login(function(data){myuserid = data;});", 1000);
    setTimeout("PrivateChat.getMessageList(function(data){receiveMessage(data)});", 1000);
}

//设置我的userid
function setUserid(id){
    myuserid = id;
}

//接收在线用户数据
function receiveOnlineUser(data){
    var userlist = "";
    var selVal = $("#sendto").selectedValues();
    $("#sendto").removeOption(/./);
    $("#sendto").addOption("", "所有人");
    for (var i in data) {
        userlist = userlist + ", " + (data[i].name);
        if (myuserid != data[i].userid) 
            $("#sendto").addOption(data[i].userid, data[i].name);
    }
    
    $("#sendto").selectOptions(selVal);
    cBtn(1);
    userlist = userlist.substr(2, userlist.length);
    
    $("#onlineuser").remove();
    $("#sidebar").prepend("<div id='onlineuser' class='mod'><h2 onclick='PrivateChat.updateUsersList();'>在线人员(刷新)</h2>" + userlist + "</div>");
    showSystem("更新在线用户成功.");
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
        var sendto = dwr.util.getValue("sendto");
        PrivateChat.sayTo(sendto, msg);
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
    var lastp = $("#showmsg p:last").position();
    if (lastp != null) {
        $("#showmsg").scrollTop(lastp.top + 5000);
    };
    };

//显示操作信息
function showSystem(message){
    $("#showmsg").append("<p>" + message + "</p>");
}

//退出
function logout(){
    var flag = $("#logoutBtn").attr("title");
    if (flag == "0") {
        PrivateChat.logout();
        $("#onlineuser").remove();
        $("#logoutBtn").attr("title", "1");
        $("#logoutBtn").val("登陆");
        cBtn(0);
    }
    else {
        PrivateChat.login();
        $("#logoutBtn").attr("title", "0");
        $("#logoutBtn").val("退出");
    };
    };

init();

//-->
</script>
<div id="showmsg"></div>
<select name="sendto" id="sendto">
	<option value="">所有人</option>
</select>
<input type="text" name="msg" id="msg" onkeypress="" onkeydown="if (event.keyCode==13){sendmsg();};"/>
<input type="button" id="sendBtn" onclick="sendmsg()" disabled="disabled" value="发送" />
<input type="button" id="logoutBtn" onclick="logout()" title="0" value="退出"/>