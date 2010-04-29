<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>

<script type="text/javascript">
	$(document).ready(function() {
		initHighslide("<%=path%>", "840", "640");
		
		$('#inputform').validate({
			submitHandler: function() {
				onLoading('#feed');
				post($('#inputform'),"#feed","<%=path%>/feed/listFeed");
				cancel();
				//commit($('#inputform'), '<%=path%>/home');
			}
		});
        
		var selectoptions = ${jsonmap};
	    $('#first').doubleSelect('second', selectoptions);   
	    
	});

	function initDatePicker() {
		$("#datepicker").datepicker( {
			dateFormat : 'yy-mm-dd',
			dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
			firstDay : 1,
			monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
			showMonthAfterYear: true,
			minDate : new Date()
		});
	}
	
	function addNewVote(obj, no) {
		var html = '<li><label>选项：</label><input class="w_middle" type="text" name="content" /> <a href="#" class="btn_del" onclick="return delVote(this)">删除</a></li>';
		
		for ( var i = 0; i < no; i++) {
		 $("#" + obj).append(html);
		}
		return false;
	};

	function delVote(obj) {
		$(obj).parent().remove();
		return false;
	};

	function addNewGoods(obj) {
		var cbox = document.getElementsByName('goods.state');
		if(cbox[0].checked == true){
			var html = '<p><label>你想要交换的类型：</label><select name="gsType"><c:forEach items="${goodsType}" var="gst"><option value="${gst.name}">${gst.name}</option></c:forEach></select> <a href="#" onclick="return delGoods(this)">删除</a></p>';
			$("#" + obj).append(html);
			$("#addone").append('<a href="#" onclick="return addGoods(' + obj + ');" >添加一项</a>');
		}
		else{
			$("#" + obj).find("div").remove();
			$("#addone").find("a").remove();
			$("#exchange").find("p").remove();
		}
	};
	
	function addGoods(obj) {
		var html = '<div id="ex"><label>你想要交换的类型：</label><select name="gsType"><c:forEach items="${goodsType}" var="gst"><option value="${gst.name}">${gst.name}</option></c:forEach></select> <a href="#" onclick="return delGoods(this)">删除</a></div>';
		$(obj).append(html);
	}

	function delGoods(obj) {
		$(obj).parent().remove();
		return false;
	};

	//新添加的方法
	function displayAll(){
		$("#addition").slideUp("fast");
		$("#addition").html("");
		//清空图片信息
		$("#pic").slideUp("normal");
		$("#oriFileName").attr("value", "");
		$("#bigFileName").attr("value", "");
		$("#bigFileUrl").attr("value", "");
		$("#minFileName").attr("value", "");
		$("#minFileUrl").attr("value", "");
	}

	function cancel() {
		displayAll();
		$("#inputform").attr("action", "<%=path%>/twitter/addTwitter");
		$("#inputtitle").html("大家一起来叽歪一下吧！");
		$("#inputarea").attr("value", "");
		$("#inputarea").attr("name", "twitter.content");
	}

	//分享链接
	function showLink() {
		cancel();
		$("#addition").html($("#links").html());
		$("#addition").slideDown("normal");
		$("#links").append("<input id='twittertype' type='hidden' name='twitter.type' value='link' />");
	}
	
	function showVote() {
		displayAll();
		$("#addition").html($("#vote").html());
		$("#addition").slideDown("normal");
		initDatePicker();//重新初始化日期选择器
		$("#inputtitle").html("投票说明：");
		$("#inputform").attr("action", "<%=path%>/vote/addVote");
		$("#inputarea").attr("name", "vote.summary");
	}
	
	function showIssue() {
		displayAll();
		$("#addition").html($("#issue").html());
		$("#addition").slideDown("normal");
		$("#inputtitle").html("问题描述：");
		$("#inputform").attr("action", "<%=path %>/issue/addIssue");
		$("#inputarea").attr("name", "issue.content");
	}
	
	function showGoods() {
		displayAll();
		$("#addition").html($("#goods").html());
		$("#addition").slideDown("normal");
		$("#inputtitle").html("物品描述：");
		$("#inputform").attr("action", "<%=path %>/goods/addGoods");
		$("#inputarea").attr("name", "goods.content");
	}
	
</script>
<body>
<div id="twitter">
    <h2><a id="inputtitle">大家一起来叽歪一下吧！</a></h2>
	<form id="inputform" action="<%=path%>/twitter/addTwitter" method="post">
	<div id="input" >
		<textarea id="inputarea" class="required" minlength="2" maxlength="140" name="twitter.content"></textarea>
		<div id="addition"></div>
        <div id="pic" style="display:none;">
            <!-- 上传成功后，图片将插到这里。 -->
            <input id="oriFileName" type="hidden" name="image.oriFileName" value=""/>
            <input id="bigFileName" type="hidden" name="image.bigFileName" value=""/>
            <input id="bigFileUrl" type="hidden" name="image.bigFileUrl" value=""/>
            <input id="minFileName" type="hidden" name="image.minFileName" value=""/>
            <input id="minFileUrl" type="hidden" name="image.minFileUrl" value=""/>
		</div>
    </div>
	<div id="options" >
		<div id="text">
		<a onclick="cancel();" href="javascript:;">微博</a>
		<a onclick="showLink();" href="javascript:;">链接</a>
		<a onclick="displayAll();return hs.htmlExpand(this, { objectType: 'iframe' } );" href="<%=path%>/image/goUploadImage">图片</a>
		<a onclick="showVote();" href="javascript:;">投票</a>
		<a onclick="showIssue();" href="javascript:;">问答</a>
		<a onclick="showGoods();" href="javascript:;">交换</a>
		</div>
		<div id="submit">
			<input class="inputbtn" type="submit" value="发表">
		</div>
    </div>
	</form>
	
	<div id="temp" style="display:none;">
		<!-- 链接 -->
		<div id="links">
			<label>链接地址：</label><input class="required url" id="lk1" type="text" name="twitter.link" />
		</div>
		<!-- 投票 -->
		<div id="vote">
            <p><label>投票标题：</label><input id="vt1" class="required" type="text" name="vote.title" /></p>
            <p><label>投票方式：</label>
            <input type="radio" name="vote.type" value="0" checked="checked"/>单选
            <input type="radio" name="vote.type" value="1" />多选
            </p>
            
            <ul id="voteitem">
                <li><label>选项：</label><input class="required" id="vt2" type="text" name="content" /> <a href="#" onclick="return delVote(this)">删除</a></li>
                <li><label>选项：</label><input class="required" id="vt3" type="text" name="content" /> <a href="#" onclick="return delVote(this)">删除</a></li>
                <li><label>选项：</label><input class="required" id="vt4" type="text" name="content" /> <a href="#" onclick="return delVote(this)">删除</a></li>
            </ul>
            <p>
            <a href="#" onclick="return addNewVote('voteitem', '1');">再添加一项</a>
             | <a href="#" onclick="return addNewVote('voteitem', '3');">再添加三项</a>
            </p>
            <p><label>投票期限：</label><input id="datepicker" class="required date" type="text" name="time" /></p>
		</div>
        <!-- 投票结束 -->
        <!-- 问答 -->
        <div id="issue">
        	<p><label>问题名称：</label><input id="is1"  class="required" type="text" name="issue.name" /></p>
            <p><label>提问类型：</label>
            <select id="first" size="1"><option value="">--</option></select>
            <select id="second" name="itid" size="1"><option value="">--</option></select>
            </p>
            <p><label>悬赏财富：</label><input  id="is2" class="required number" type="text" name="issue.value" value="0"/></p>
            <p><label>匿名提问：</label><input type="checkbox" name="issue.state" value="1" /><label> 需要匿名提问请打钩</label></p>
		</div>
        <!-- 问答结束 -->
        <!-- 交换 -->
        <div id="goods">
            <p><label>货品名称：</label><input id="gd1" class="required" type="text" name="goods.name" /></p>
            <p><label>货品类型：</label>
            <select name="gtid"><c:forEach items="${goodsType}" var="gst"><option value="${gst.id}">${gst.name}</option></c:forEach></select>
            </p>
            <p>	货品数量：<input id="gd2" class="required number" type="text" name="goods.quantity" value="1"/></p>
            <p>	物品估价：<input id="gd3" class="required number" type="text" name="goods.value" value="0"/></p>
            <p id="addone">
            <input id="chk_exchange" type="checkbox" name="goods.state" value="1" onclick="return addNewGoods('exchange');"/>
            <label for="chk_exchange">我想用来和别人交换 | </label>
            </p>
            <div id="exchange"></div>
            <p>
            <label>货品图片：</label>
            <a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>/image/goUploadImage">上传图片</a>
        	</p>
        </div>
        <!-- 交换结束 -->	
	</div>
	<!-- end of temp -->
	
</div>
