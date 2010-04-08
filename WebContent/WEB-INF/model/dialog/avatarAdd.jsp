<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>
<style type="text/css"> 
.example {	
	padding: 0 20px;
	float: left;		
	width: 230px;
}
 
div.button {
	height: 29px;	
	width: 133px;
	background: #000;
	
	font-size: 14px;
	color: #fff;
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
			//$('#dialogbox').load('<%=path%>/avatar/goModifyAvatar', ajax);
			location.href = '<%=path%>/avatar/goModifyAvatar';
		}
	});
});
</script>


<div id="dialogbox">
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
	<br/>
	<br/>
	<br/>
</div>
