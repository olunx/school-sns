<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
%>
<h2 class="caption">添加用户</h2>
<form onSubmit="post(this);return false;" action="<%=path%>/student/addPeople" method="post">
<p><label> 头像： </label> <input type="text" name="people.avatar" /></p>
<p><label> 用户名： </label> <input type="text" name="people.username" /></p>
<p><label> 密码： </label> <input type="text" name="people.password" /></p>
<p><label> 昵称： </label> <input type="text" name="people.nickname" /></p>
<p><label> 姓名： </label> <input type="text" name="people.name" /></p>
<p><label> 宿舍： </label> <input type="text" name="people.dorm" /></p>
<p><label> 手机： </label> <input type="text" name="people.phoneNo" /></p>
<p><label> QQ： </label> <input type="text" name="people.qq" /></p>
<p><label> 邮箱： </label> <input type="text" name="people.mail" /></p>
<p class="paddingmin"><input type="submit" value="提交" /> <input type="reset" value="重置" /></p>
</form>

<!-- JQuery 上传插件 -->
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-ajaxupload/ajaxupload.css" />
<script type="text/javascript" src="<%=path%>/content/jq-ajaxupload/ajaxupload.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var button = $('#button'), interval;
	new AjaxUpload(button,{
		action: '<%=path%>/people/peopleUpload', 
		name: 'files',
		onSubmit : function(file, ext){
			
            if (! (ext && /^(xls)$/i.test(ext))){
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
			$('#content').load('<%=path%>/people/listPeople', ajax);
		}
	});
});
</script>


<div id="dialogbox">
	<h2 class="caption">上传用户：</h2>
	<div id="button" class="button">上传</div> 
</div>
