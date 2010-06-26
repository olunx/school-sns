<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="<%=path %>/content/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
function loadJS(url,callback,charset)
{
	var script = document.createElement('script');
	script.onload = script.onreadystatechange = function ()
	{
		if (script && script.readyState && /^(?!(?:loaded|complete)$)/.test(script.readyState)) return;
		script.onload = script.onreadystatechange = null;
		script.src = '';
		script.parentNode.removeChild(script);
		script = null;
		callback();
	};
	script.charset=charset || document.charset || document.characterSet;
	script.src = url;
	try {document.getElementsByTagName("head")[0].appendChild(script);} catch (e) {}
}
function initEditor()
{
	loadJS('<%=path %>/content/xheditor/xheditor-zh-cn.js',function(){
		$('#elm1').xheditor(true,{upImgUrl:"<%=path %>/editorUpload.action",upImgExt:"jpg,jpeg,gif,png",shortcuts:{'ctrl+enter':submitForm}});$('#btnInit').hide();});
}
function insertUpload(msg)
{
 	$("#uploadList").append('<option value="'+msg.id+'">'+msg.localname+'</option>');
}
function submitForm(){$('#frmDemo').submit();}
</script>

<body>
	<form id="frmDemo" method="post" action="show.php">
	<h3>xhEditor demo11 : 异步加载</h3>
	<textarea id="elm1" name="elm1" onfocus="initEditor()" rows="12" cols="80" style="width: 80%">
		
	</textarea>
	<br/><br />
	<input type="submit" name="save" value="Submit" />
	<input type="reset" name="reset" value="Reset" />
</form>
</body>
</html>