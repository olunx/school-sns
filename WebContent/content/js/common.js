$(document).ready(function(){
    $("#content").ajaxError(function(event, request, settings){
        $(this).append("<li>服务器繁忙！请刷新一下试试:" + settings.url + "</li>");
    });
});

//ajax加载链接
function ajaxload(obj){
        var href = $(obj).attr('href');
        var divid = $(obj).attr('rev');
		loadContent(href,divid);
		return false;
}

//加载数据
function loadContent(href){
	var divid = arguments[1] || '#content';//更新目标id
	if (divid==null || divid == "") divid = "#content";
	//alert(divid);
    var content = $(divid);
	if (content.size()==0) content = $('#content');
	
   	content.html("");
    onLoading(content);//打开loading
    content.load(href, function(){
        offLoading();//关闭loading
        loadContentComplete(content,divid);
        return false;
    });
}

//处理加载完后的链接
function loadContentComplete(content,divid){
	//给a标签加入rev
	$("a",content).each(function(){
		if ($(this).attr("rev") == "" ){
			$(this).attr("rev",divid);
		}
	});
	//绑定上传
    $("a[rel='ajaxupload']",content).each(function(i){
        var ajaxinfo = eval('(' + $(this).attr("lang") + ')');
        myAjaxUploadSetup(this, ajaxinfo.upload, ajaxinfo.complete, ajaxinfo.allowtype,divid);
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
	if (content.size()==0) content = $('#content');
    var urlStr = $(obj).attr('action');
    var dataStr = decodeURIComponent($(obj).serialize());
        onLoading(divid);//打开loading
        $.ajax({
            url: urlStr,
            data: dataStr,
            type: 'POST',
            success: function(result){
				content.slideUp('normal',function(){
					if (returl == "") {
						content.html(result);
						loadContentComplete(content,divid);
					} else {
						loadContent(returl,divid);
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
		//$.scrollTo('+=600px' , 800 );
		
		$.ajax( {
			url : href,
			type : 'GET',
			success : function(result) {
				$(target).append(result);
				$(more).remove();
			}
		});

		return false;
	});
}

//配置ajaxUpload
function myAjaxUploadSetup(btnobj, uploadUrl, completeUrl, allowType,divid){
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
			loadContent(completeUrl,divid);
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



