<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>
<!-- JQuery 图片选择 插件 -->
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-imgareaselect/css/imgareaselect-animated.css" />
<script type="text/javascript" src="<%=path%>/content/jq-imgareaselect/jquery.imgareaselect.min.js"></script>

<!-- JQuery 上传插件 -->
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-ajaxupload/ajaxupload.css" />
<script type="text/javascript" src="<%=path%>/content/jq-ajaxupload/ajaxupload.js"></script>
<script type="text/javascript">
function imgselect() {
	$('#photo').imgAreaSelect( {
		aspectRatio : '',
		handles : true,
		fadeSpeed : 200,
		maxWidth: 120,
		maxHeight: 120,
		x1: 10,
		y1: 10,
		x2: 80,
		y2: 80,
		onSelectChange: function(img, selection) {
		    $('#x').val(selection.x1);
		    $('#y').val(selection.y1);
		    $('#width').val(selection.width);
		    $('#height').val(selection.height);    
		}
	});
}
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
		onComplete: function(file, response){
			//$('#dialogbox').load('<%=path%>/avatar/goModifyAvatar', ajax);
			//$('#dialogbox').load(response, ajax);
			//location.href = '<%=path%>/avatar/goModifyAvatar';
			//alert(response);
			$('#dialogbox').html(response);
			imgselect();
		}
	});
});

</script>


<div id="dialogbox">
	<h2 class="caption">上传头像：</h2>
	<div id="button" class="button">上传</div> 
</div>
