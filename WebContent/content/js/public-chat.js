var myuserid;

function init(){
    cBtn(0);
    dwr.engine.setActiveReverseAjax(true);
    showSystem("正在载入，请稍等...");
    setTimeout("PublicChat.login(function(data){myuserid = data;showSystem('载入成功，开始聊天吧!');});", 1000);
    setTimeout("PublicChat.getMessageList(function(data){receiveMessage(data)});", 800);
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
    $("#sidebar").prepend("<div id='onlineuser' class='mod'><h2 onclick='PublicChat.updateUsersList();'>在线人员(刷新)</h2>" + userlist + "</div>");
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
        PublicChat.sayTo(sendto, msg);
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
        PublicChat.logout();
        $("#onlineuser").remove();
        $("#logoutBtn").attr("title", "1");
        $("#logoutBtn").val("登陆");
        cBtn(0);
    }
    else {
        PublicChat.login(function(data){myuserid = data;});
        $("#logoutBtn").attr("title", "0");
        $("#logoutBtn").val("退出");
    };
    };

init();
