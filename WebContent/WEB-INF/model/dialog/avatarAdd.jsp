<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>

<!-- JQuery 上传插件 -->
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-ajaxupload/ajaxupload.css" />
<script type="text/javascript" src="<%=path%>/content/jq-ajaxupload/ajaxupload.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var button = $('#button'), interval;
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
			//$('#dialogbox').load('<%=path%>/avatar/goModifyAvatar', ajax);
			location.href = '<%=path%>/avatar/goModifyAvatar';
		}
	});
});
</script>


<div id="dialogbox">
	<h2 class="caption">上传头像：</h2>
	<div id="button" class="button">上传</div> 
</div>
