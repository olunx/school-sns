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
    if (flag == "0") {//退出
        PrivateChat.logout();
        //$("#onlineuser").remove();
        $("#logoutBtn").attr("title", "1");
        $("#logoutBtn").val("开始聊天");
        showSystem("已经退出");
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