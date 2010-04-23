<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>
<link type="text/css" rel="stylesheet" href="<%=path%>/content/images/twitter.css" />
<script type="text/javascript" src="<%=path %>/content/js/jquery.doubleSelect.min.js"></script>
<script type="text/JavaScript">
 $(document).ready(function()
 {		
		var selectoptions = ${jsonmap};
	    $('#first').doubleSelect('second', selectoptions);      
 });
</script>
<!-- JQuery UI 插件 -->
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-ui/jquery-ui-1.8.custom.css" />
<script type="text/javascript" src="<%=path%>/content/jq-ui/jquery-ui-1.8.custom.min.js" ></script>
<script type="text/javascript">
	$(document).ready(function() {
		initHighslide("<%=path%>", "840", "640");
		
		displayAll();
		
		$("#datepicker").datepicker( {
			dateFormat : 'yy-mm-dd',
			dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
			firstDay : 1,
			monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
			showMonthAfterYear: true,
			minDate : new Date()
		});

	});
	
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
	
	function displayAll(){
		$("#vote").slideUp("normal");
		$("#issue").slideUp("normal");
		$("#goods").slideUp("normal");
	}
	
	function showVote() {
		displayAll();
		$("#vote").slideDown("slow");
		$("#inputtitle").html("投票说明：");
		$("#inputform").attr("action", "<%=path%>/vote/addVote");
		$("#inputarea").attr("name", "vote.summary");
	}
	
	function showIssue() {
		displayAll();
		$("#issue").slideDown("slow");
		$("#inputtitle").html("问题描述：");
		$("#inputform").attr("action", "<%=path %>/issue/addIssue");
		$("#inputarea").attr("name", "issue.content");
	}
	
	function showGoods() {
		displayAll();
		$("#goods").slideDown("slow");
		$("#inputtitle").html("物品描述：");
		$("#inputform").attr("action", "<%=path %>/goods/addGoods");
		$("#inputarea").attr("name", "goods.content");
	}
	
	function cancel() {
		displayAll();
		$("#inputform").attr("action", "<%=path%>/twitter/addTwitter");
		$("#inputtitle").html("大家一起来叽歪一下吧！");
		$("#inputarea").attr("value", "");
		$("#inputarea").attr("name", "twitter.content");
	}
</script>
<body>
<div id="twitter">
    <h2><a id="inputtitle">大家一起来叽歪一下吧！</a></h2>
	<form id="inputform" onSubmit="commit(this, '<%=path%>/home');return false;" action="<%=path%>/twitter/addTwitter" method="post">
    <div id="input" >
      <textarea id="inputarea" name="twitter.content"></textarea>
      <!-- 投票 -->
        <div id="vote">
            <p><label>投票标题：</label><input class="w_long" type="text" name="vote.title" /></p>
            <p><label>投票方式：</label><input type="radio" name="vote.type" value="0" checked="checked"/>单选
            <input type="radio" name="vote.type" value="1" />多选</p>
            <div id="voteitem">
                <li><label>选项：</label><input type="text" name="content" /> <a href="#" onclick="return delVote(this)">删除</a></li>
                <li><label>选项：</label><input type="text" name="content" /> <a href="#" onclick="return delVote(this)">删除</a></li>
                <li><label>选项：</label><input type="text" name="content" /> <a href="#" onclick="return delVote(this)">删除</a></li>
            </div>
            <p>
            <a href="#" onclick="return addNewVote('voteitem', '1');">再添加一项</a>
             | <a href="#" onclick="return addNewVote('voteitem', '3');">再添加三项</a>
            </p>
            <p><label>投票期限：</label><input type="text" id="datepicker" name="time" /></p>
      </div>
        <!-- 投票结束 -->
        <!-- 问答 -->
        <div id="issue">
        	<p><label>问题名称：</label><input type="text" name="issue.name" /></p>
            <p><label>提问类型：</label>
            <select id="first" size="1"><option value="">--</option></select>
            <select id="second" name="itid" size="1"><option value="">--</option></select>
            </p>
            <p><label>悬赏财富：</label><input type="text" name="issue.value" value="0"/></p>
            <p><label>匿名提问：</label><input type="checkbox" name="issue.state" value="1" /><label> 需要匿名提问请打钩</label></p>
      </div>
        <!-- 问答结束 -->
        <!-- 交换 -->
        <div id="goods">
            <p><label>货品名称：</label><input type="text" name="goods.name" /></p>
            <p><label>货品类型：</label>
            <select name="gtid"><c:forEach items="${goodsType}" var="gst"><option value="${gst.id}">${gst.name}</option></c:forEach></select>
            </p>
            <p>	货品数量：<input type="text" name="goods.quantity" value="1"/></p>
            <p>	交换单价：<input type="text" name="goods.value" value="0"/></p>
            <p id="addone">
            <input id="chk_exchange" type="checkbox" name="goods.state" value="1" onclick="return addNewGoods('exchange');"/>
            <label for="chk_exchange">我还想用来交换</label>
            </p>
            <div id="exchange"></div>
            <label>货品图片：</label>
            <a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>/image/goUploadImage">上传图片</a>
        </div>
        <!-- 交换结束 -->
    </div>
	<div id="options" >
   	  <div id="pic">
            <img src="" style="display:none;"></img>
            <!-- 上传成功后，图片将插到这里。 -->
            <input id="oriFileName" type="hidden" name="image.oriFileName" value=""/>
            <input id="bigFileName" type="hidden" name="image.bigFileName" value=""/>
            <input id="bigFileUrl" type="hidden" name="image.bigFileUrl" value=""/>
            <input id="minFileName" type="hidden" name="image.minFileName" value=""/>
            <input id="minFileUrl" type="hidden" name="image.minFileUrl" value=""/>
		</div>
	  <div id="text">
            <a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>/image/goUploadImage">图片</a>
            <a onclick="showVote();" href="#">投票</a>
            <a onclick="showIssue();" href="#">问答</a>
            <a onclick="showGoods();" href="#">交换</a>
        </div>
		<div id="submit">
        	<a onclick="cancel();" href="#">重置</a>
        	<input class="inputbtn" type="submit" value="发表">
        </div>
    </div>
	</form>
</div>
