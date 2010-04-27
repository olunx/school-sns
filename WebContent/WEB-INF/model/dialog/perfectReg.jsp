<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
%>
<!-- JQ验证插件 -->
<script type="text/javascript" src="<%=path%>/content/jq-validate/jquery.form.js" ></script>
<script type="text/javascript" src="<%=path%>/content/jq-validate/jquery.validate.pack.js" ></script>
<script type="text/javascript" src="<%=path%>/content/jq-validate/messages_cn.js" ></script>

<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-ui/jquery-ui-1.8.custom.css" />
<script type="text/javascript" src="<%=path%>/content/jq-ui/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/content/js/jquery.doubleSelect.min.js"></script>
<script type="text/JavaScript">
$(document).ready(function(){
	
	$('#inputform').validate({
		submitHandler: function() {
			post($('#inputform'));
		}
	});
	
	var classesselect = ${classesmap};
    $('#first').doubleSelect('second', classesselect);      
       
	$("#datepicker").datepicker( {
		
		dateFormat : 'yy-mm-dd',
		dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
		firstDay : 1,
		monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
		showMonthAfterYear: true,
		changeYear: true,
		maxDate: new Date(),
		minDate: new Date(1980, 1, 1),
		defaultDate: new Date(1986, 1, 1)
	});

	initHighslide("<%=path%>", "840", "640");
})
</script>
<form id="inputform" action="<%=path %>/perfectReg" method="post" class="form" >
	<div id="select">
		<label>选择班级：</label>
		<select id="first" size="1"><option value="-1">--</option></select>
		<select id="second" name="classesId" size="1"><option value="">--</option></select>
		<a target="content" href="<%=path %>/classes/goAddClasses">没有我的班级？去创建一个咯~~</a>
		<s:fielderror><s:param>classesId</s:param></s:fielderror>
	</div>
	<p><label>入学年份：</label>
	<input class="required number" type="text" name="entryYear" />
	<s:fielderror><s:param>entryYear</s:param></s:fielderror>
	</p>
	<p><label>学号：</label>
	<input class="required number" type="text" name="sno" />
	<s:fielderror><s:param>sno</s:param></s:fielderror>
	</p>
	<p><label>出生年月：</label>
	<input id="datepicker" class="required date" type="text" name="birthday" readonly="readonly"/>
	<s:fielderror><s:param>birthday</s:param></s:fielderror>
	</p>
	<label>上传头像：</label>
	<a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>/image/goUploadImage">上传图片</a>
	<!-- 上传成功后，图片将插到这里。 -->
	<div id="pic">
		<img src=""></img>
		<input id="oriFileName" type="hidden" name="image.oriFileName" value=""/>
		<input id="bigFileName" type="hidden" name="image.bigFileName" value=""/>
		<input id="bigFileUrl" type="hidden" name="image.bigFileUrl" value=""/>
		<input id="minFileName" type="hidden" name="image.minFileName" value=""/>
		<input id="minFileUrl" type="hidden" name="image.minFileUrl" value=""/>
		<s:fielderror><s:param>image.oriFileName</s:param></s:fielderror>
	</div>
	<input type="submit" value="申请加入"/>
</form>