$(document).ready(function(){
	$.ajaxSetup ({
	    cache: false //关闭AJAX相应的缓存
	});
    //注册事件
    $("a[target='content']").click(function(){
        var href = $(this).attr('href');
        var rev = $(this).attr('rev');
        if (rev != null && rev != "") 
            window.location = rev;
        loadContent(href);
        return false;
    });
    
    $("#content").ajaxError(function(event, request, settings){
        $(this).append("<li>出错页面:" + settings.url + "</li>");
    });
    
    $("a[rel='ajaxupload']").each(function(i){
        var ajaxinfo = eval('(' + $(this).attr("rev") + ')');
        myAjaxUploadSetup(this, ajaxinfo.upload, ajaxinfo.complete, ajaxinfo.allowtype);
    });
});

//注册二级事件
function ajax(){
	var divid = arguments[0] || '#content';//更新目标id
    $("a[rel='ajaxupload']").each(function(i){
        var ajaxinfo = eval('(' + $(this).attr("rev") + ')');
        myAjaxUploadSetup(this, ajaxinfo.upload, ajaxinfo.complete, ajaxinfo.allowtype);
    });
	$(divid+" a[target='content']").unbind("click"); 
    $(divid+" a[target='content']").click(function(){
        var href = $(this).attr('href');
        loadContent(href);
        return false;
    });
};

//加载数据
function loadContent(href){
    var content = $('#content');
    content.html("");
    onLoading();//打开loading
    content.load(href, function(){
        offLoading();//关闭loading
        content.fadeIn('slow', ajax);
    });
}

//打开loading
function onLoading(){
	//$('#content').html("");
    $('#content').prepend('<span id="loading"><img src="./content/images/loading.gif" /> ::>_<:: 努力处理中......</span>');
    $('#loading').fadeIn('fast');
}

//关闭loading
function offLoading(){
    $('#loading').remove();
}

//提交表单数据
function post(obj){
	var divid = arguments[1] || '#content';//更新目标id
    var content = $(divid);
    var urlStr = $(obj).attr('action')+"?cachetime="+new Date().getTime();
    var dataStr = decodeURIComponent($(obj).serialize());
        onLoading();//打开loading
        $.ajax({
            url: urlStr,
            data: dataStr,
            type: 'POST',
			cache:false,
            success: function(result){
				content.slideUp('normal',function(){
	                content.html(result);
	                offLoading();//关闭loading
	                content.slideDown('normal', function(){
						ajax(divid);
					});
				});
            },
			error: function(){
				alert("AJAX出错啦！");
			}
        });
}

//提交表单数据返回指定页面
function commit(obj, url){
    var content = $('#content');
    var urlStr = $(obj).attr('action');
    var dataStr = decodeURIComponent($(obj).serialize());
        onLoading();//打开loading
        $.ajax({
            url: urlStr,
            data: dataStr,
            type: 'POST',
            success: function(){
        		if(url != null) {
        			location.href = url;
        		}
            }
        });
}

//配置ajaxUpload
function myAjaxUploadSetup(btnobj, uploadUrl, completeUrl, allowType){
    var button = $(btnobj), interval;
    var button_txt = button.text();
    new AjaxUpload(button, {
        action: uploadUrl,
        name: 'files',
        onSubmit: function(file, ext){
        
            if (!(ext && allowType.test(ext))) {
                alert('不允许的文件格式！');
                return false;
            }
            
            button.text('上传中');
            this.disable();
            // Uploding -> Uploading. -> Uploading...
            interval = window.setInterval(function(){
                var text = button.text();
                if (text.length < 6) {
                    button.text(text + '.');
                }
                else {
                    button.text('上传中');
                }
            }, 200);
        },
        onComplete: function(){
            this.enable();
            $('#content').load(completeUrl, ajax);
            clearInterval(interval);
            button.text(button_txt);
            
        }
    });
};

//更新侧边栏
function updateSidebar(){
	//使用方法updateSidebar(内容,是否在后面添加);或updateSidebar(内容);
	var html = arguments[0] || "";
	var isappend = arguments[1] || 0;//默认是覆盖
    if (isappend) {
    	$("#widget").append(html);
    }
    else {
        $("#widget").html(html);
    }
    $("#widget a[target='content']").click(function(){
        var href = $(this).attr('href');
        loadContent(href);
        return false;
    });
}

/**
 * 获得当前的日期
 */
function getCurrentDate(){
    var today, hour, second, minute, year, month, date;
    today = new Date();
    year = addZero(today.getFullYear());
    month = addZero(today.getMonth() + 1);
    date = addZero(today.getDate());
    hour = addZero(today.getHours());
    minute = addZero(today.getMinutes());
    second = addZero(today.getSeconds());
    var currentDateStr = year + "年" + month + "月" + date + "日" + " " + hour + ":" + minute + ":" + second; // 显示时间
    document.getElementById('header_datetime').innerHTML = "时间：" + currentDateStr;
    setTimeout("getCurrentDate();", 1000); // 设定函数自动执行时间为 1000 ms(1 s)
};

function addZero(num){
    if (num < 10) {
        return "0" + num;
    }
    else {
        return num
    };
    };

function stringToDateTime(postdate){
    var second = 1000;
    var minutes = second * 60;
    var hours = minutes * 60;
    var days = hours * 24;
    var months = days * 30;
    var twomonths = days * 365;
    var myDate = new Date(Date.parse(postdate));
    if (isNaN(myDate)) {
        myDate = new Date(postdate.replace(/-/g, "/"));
    }
    var nowtime = new Date();
    var longtime = nowtime.getTime() - myDate.getTime();
    var showtime = 0;
    if (longtime > days * 4) {
        return postdate;
    }
    else 
        if (longtime > days * 2) {
            return (Math.floor(longtime / days) + "天前");
        }
        else 
            if (longtime > days) {
                return (Math.floor(longtime / days) + "昨天");
            }
            else 
                if (longtime > hours) {
                    return (Math.floor(longtime / hours) + "小时前");
                }
                else 
                    if (longtime > minutes) {
                        return (Math.floor(longtime / minutes) + "分钟前");
                    }
                    else 
                        if (longtime > second) {
                            return (Math.floor(longtime / second) + "秒前");
                        }
                        else {
                            return (longtime + " error ");
                        }
}

/*

 function addNewActivity(obj) {

 var html = '<div>原因：<input class="inputmid" type="text" name="reason" /> 分数：<input type="text" name="mark" />';

 html += ' <select name="type"><option value="德育"> 德育</option><option value="活动">活动</option>';

 html += '<option value="文体">文体</option></select> <a href="#" class="btn_del" onclick="delActivity(this)">删除</a></div>';

 for ( var i = 0; i < 3; i++) {

 $("#" + obj).append(html);

 }

 };

 function delActivity(obj) {

 $(obj).parent().remove();

 };

 function addNewVote(obj) {

 var html = '<div><label>选项：</label><input class="inputmid" type="text" name="content" /> <a href="#" class="btn_del" onclick="return delVote(this)">删除</a></div>';

 for ( var i = 0; i < 3; i++) {

 $("#" + obj).append(html);

 }

 return false;

 };

 function delVote(obj) {

 $(obj).parent().remove();

 return false;

 };

 function showInBox(url,title,width,height){

 var obj = document.body;

 if ($(obj).find("#dialogbox").size()==0){

 $(obj).append("<div id='dialogbox' style='display:none;'></div>");

 }

 if ($(obj).find("#dialogbox-loading").size()==0){

 $(obj).append("<div id='dialogbox-loading'>正在加载，请稍后...</div>");

 }

 $("#dialogbox-loading",obj).show(500);

 $("#dialogbox",obj).empty();

 $("#dialogbox",obj).load(url,function(){

 $("#dialogbox",obj).attr("title",title);

 $("#dialogbox",obj).dialog({

 bgiframe: true,

 autoOpen: false,

 modal: true,

 width:width,

 height:height

 });

 $("#dialogbox-loading",obj).hide(500);

 $("#dialogbox",obj).dialog("open");

 });

 return false;

 }

 $(function() {

 // 选中checkbox变色

 $(".table tr :checkbox").click(function() {

 if ($(this).attr("checked")) {

 $(this).parent().parent().addClass("trselected");

 } else {

 $(this).parent().parent().removeClass("trselected");

 }

 });

 // 鼠标移上表格变色

 $(".table tr").hover(function() {

 $(this).addClass("trhover");

 }, function() {

 $(this).removeClass("trhover");

 });

 //偶数行变色变色，添加样式

 $("tr:nth-child(3n)").addClass("odd");

 

 //选中所有CheckBox

 $("a[rel='checkall']").click(function() {

 $(":checkbox").each(function(i) {

 if ($(this).attr("checked")) {

 $(this).attr("checked", false);

 $(this).parent().parent().removeClass("trselected");

 } else {

 $(this).attr("checked", true);

 $(this).parent().parent().addClass("trselected");

 }

 })

 });

 

 //解决IE6中无法显示无内容A标签的问题

 if ($.browser.msie) {

 $(".btn_edit").append("☺");

 $(".btn_del").append("☻");

 }

 });

 */


