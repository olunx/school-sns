<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!-- JQuery 上传插件 -->
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-ajaxupload/ajaxupload.css" />
<script type="text/javascript" src="<%=path%>/content/jq-ajaxupload/ajaxupload.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var button = $('#button'), interval;
	new AjaxUpload(button,{
		action: '<%=path %>/course/courseUpload', 
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
			$('#content').load('<%=path %>/course/listCourse', ajax);
		}
	});
});
</script>


<div id="dialogbox">
	<h2 class="caption">上传学生：</h2>
	<div id="button" class="button">上传</div> 
</div>