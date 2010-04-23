<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@	taglib uri="/struts-tags" prefix="s" %>

<%
	String path = request.getContextPath();
%>
<!-- JQuery库 -->
<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>
<!-- JQuery UI 插件 -->
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-ui/jquery-ui-1.8.custom.css" />
<script type="text/javascript" src="<%=path%>/content/jq-ui/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#datepicker").datepicker( {
			dateFormat : 'yy-mm-dd',
			dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
			firstDay : 1,
			monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
			showMonthAfterYear: true,
			minDate : new Date()
		});

	});
	
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
</script>

<form onSubmit="post(this);return false;" class="form" action="<%=path %>/vote/addVote" method="post">
	<p><label>投票主题：</label>
	<input type="text" name="vote.title" />
	<s:fielderror><s:param>vote.title</s:param></s:fielderror>
	</p>
	<label>描述内容：</label>
	<textarea name="vote.summary" id="demo" rows="50" cols="152" style="width: 600px; height: 150px"></textarea>
	<p><label>投票方式：</label>
	<input type="radio" name="vote.type" value="0" checked="checked"/>单选
	<input type="radio" name="vote.type" value="1" />多选
	</p>
	<s:fielderror><s:param>content</s:param></s:fielderror>
	<div id="voteitem">
		<div><label>选项：</label><input class="inputmid" type="text" name="content" /> <a href="#" class="btn_del" onclick="return delVote(this)">删除</a></div>
		<div><label>选项：</label><input class="inputmid" type="text" name="content" /> <a href="#" class="btn_del" onclick="return delVote(this)">删除</a></div>
		<div><label>选项：</label><input class="inputmid" type="text" name="content" /> <a href="#" class="btn_del" onclick="return delVote(this)">删除</a></div>
	</div>
	<p class="paddingmin">
	<a href="#" class="btn_add" onclick="return addNewVote('voteitem');">再添加三项</a>
	</p>
	<p>
	<label>投票期限：</label>
	<input type="text" id="datepicker" name="time" readonly="readonly"/>
	<s:fielderror><s:param>time</s:param></s:fielderror>
	</p>
	<input type="submit" value="发布投票"/>
</form>

