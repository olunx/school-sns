<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
%>
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-ui/jquery-ui-1.8.custom.css" />
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-highslide/highslide.css" />
<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/content/jq-highslide/highslide-full.min.js"></script>
<script type="text/javascript" src="<%=path%>/content/jq-ui/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="<%=path %>/content/js/jquery.doubleSelect.min.js"></script>
<script type="text/JavaScript">
	 $(document).ready(function()
	 {		
			var classesselect = ${classesmap};
		    $('#first').doubleSelect('second', classesselect);      
	        
	 });
	 $(document).ready(function() {
		    hs.graphicsDir = '<%=path%>/content/jq-highslide/graphics/';
		    hs.align = 'center';
		    hs.outlineType = 'rounded-white';
		    hs.wrapperClassName = 'draggable-header';
		    hs.transitions = ['expand', 'crossfade'];
			hs.useBox = true;
			hs.width = 680;
			hs.height = 450;
		});
		
	 $(document).ready(function() {

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

		});
</script>
<form  action="<%=path %>/perfectReg" method="post" onSubmit="post(this);return false;"  Class="form" >
	<div id="select">
		<label>选择班级：</label>
		<select id="first" size="1"><option value="">--</option></select>
		<select id="second" name="classesId" size="1"><option value="">--</option></select>
		<a target="content" href="<%=path %>/classes/goAddClasses?id=${user.school.id}">没有我的班级？去创建一个咯~~</a>
	</div>
	<p><label>入学年份：</label>
	<input type="text" name="entryYear" />
	</p>
	<p><label>学号：</label>
	<input type="text" name="sno" />
	</p>
	<p><label>出生年月：</label>
	<input id="datepicker" type="text" name="birthday" />
	</p>
	<label>上传头像：</label>
	<a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>/image/goAddImage">上传图片</a>
	<!-- 上传成功后，图片将插到这里。 -->
	<div id="pic">
		<img src=""></img>
		<input id="oriFileName" type="hidden" name="image.oriFileName" value=""/>
		<input id="bigFileName" type="hidden" name="image.bigFileName" value=""/>
		<input id="bigFileUrl" type="hidden" name="image.bigFileUrl" value=""/>
		<input id="minFileName" type="hidden" name="image.minFileName" value=""/>
		<input id="minFileUrl" type="hidden" name="image.minFileUrl" value=""/>
	</div>
	<input type="submit" value="申请加入"/>
</form>