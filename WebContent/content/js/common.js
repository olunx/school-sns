$(document).ready(function(){

    //注册事件
    $("a[target='content']").click(function(){
        var href = $(this).attr('href');
        var rev = $(this).attr('rev');
        if (rev != null && rev != "") 
		{loadContent(href,rev);}
        else
		{loadContent(href);}
        
        return false;
    });
    
    $("#content").ajaxError(function(event, request, settings){
        $(this).append("<li>哎呀，出错啦！请刷新一下试试:" + settings.url + "</li>");
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
        var rev = $(this).attr('rev');
        if (rev != null && rev != "") 
		{
			loadContent(href,rev);
			}
        else
		{
			loadContent(href,divid);
		}
        return false;
    });
};

//加载数据
function loadContent(href){
	var divid = arguments[1] || '#content';//更新目标id
	//alert(divid);
    var content = $(divid);
    content.html("");
    onLoading(content);//打开loading
    content.load(href, function(){
        offLoading();//关闭loading
        ajax(divid);
        //content.fadeIn('slow', ajax);
    });
}

//打开loading
function onLoading(target){
	//$('#content').html("");
    $(target).prepend('<span id="loading"><img src="./content/images/loading.gif" /> ::>_<:: 努力处理中......</span>');
    $('#loading').show();
}

//关闭loading
function offLoading(){
    $('#loading').remove();
}

//提交表单数据
function post(obj){
	var divid = arguments[1] || '#content';//更新目标id
	var returl = arguments[2] || '';//返回
    var content = $(divid);
    var urlStr = $(obj).attr('action');
    var dataStr = decodeURIComponent($(obj).serialize());
        onLoading();//打开loading
        $.ajax({
            url: urlStr,
            data: dataStr,
            type: 'POST',
            success: function(result){
				content.slideUp('normal',function(){
					if (returl == "") {
						content.html(result);
						ajax(divid);
					} else {
						content.load(returl,function(){
							ajax(divid);
						});
					};
	                offLoading();//关闭loading
	                content.slideDown('normal');
				});
            },
			error: function(){
				alert("AJAX出错啦！请刷新一下本页面再试试~");
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

//列表页面获取更多
function listMore(more,target) {
	$("a[target='list']").click(function() {
		var href = $(this).attr('href');
		$.scrollTo('+=600px' , 800 );
		$(more).remove();
		
		$.ajax( {
			url : href,
			type : 'GET',
			success : function(result) {
				$(target).append(result);
			}
		});

		return false;
	});

	ajax();
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



