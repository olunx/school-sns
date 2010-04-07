<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
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
</script>
<p>日期: <input type="text" id="datepicker" size="30" /></p>

测试主页
<a rel="ajax" href="<%=path%>/test/goModifyTest">修改</a>

<style type="text/css"> 
 
.example {	
	padding: 0 20px;
	float: left;		
	width: 230px;
}
 
div.button {
	height: 29px;	
	width: 133px;
	background: #fff;
	
	font-size: 14px;
	color: #C7D92C;
	text-align: center;
	padding-top: 15px;
}
</style> 
<script type="text/javascript" src="<%=path%>/content/js/ajaxupload.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	/* example 1 */
	var button = $('#button1'), interval;
	new AjaxUpload(button,{
		action: '<%=path%>/avatar/avatarUpload', 
		name: 'files',
		onSubmit : function(file, ext){
			
            if (! (ext && /^(jpg|png|jpeg|gif)$/i.test(ext))){
                alert('不允许的文件格式！');
                return false;
       		}
      		 
			button.text('正在上传');
			
			this.disable();
			
			// Uploding -> Uploading. -> Uploading...
			interval = window.setInterval(function(){
				var text = button.text();
				if (text.length < 13){
					button.text(text + '.');
				} else {
					button.text('正在上传');	
				}
			}, 200);
		},
		onComplete: function(){
			$('#content').load('<%=path%>/avatar/goModifyAvatar', ajax);		
		}
	});
});
</script>


<br/>
<br/>
<h2 class="caption">上传头像：</h2>
<ul>
<li id="example1" class="example"> 
	<div class="wrapper"> 
		<div id="button1" class="button">上传</div> 
	</div>
</li>
</ul>

<br/><br/><br/><br/>

<form action="<%=path%>/avatar/avatarUpload" enctype="multipart/form-data" method="post">
<input id="uploadify" type="file" name="files"/><br/>
<input type="submit" value="确定"></input>
</form>