<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
%>
<!-- JQuery库 -->
<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-highslide/highslide.css" />
<script type="text/javascript" src="<%=path%>/content/jq-highslide/highslide-full.min.js"></script>
<script language="javascript" type="text/javascript">
	
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

	function addInstitute(obj) {
		var html = '<div><label>学院名称：</label><input class="inputmid" type="text" name="names" /> <a href="#" class="btn_del" onclick="return delInstitute(this)">删除</a></div>';
		
		for ( var i = 0; i < 3; i++) {
		 $("#" + obj).append(html);
		}
		return false;
	};

	function delInstitute(obj) {
		$(obj).parent().remove();
		return false;
	};
</script>

<form onSubmit="post(this);return false;" class="form" action="<%=path %>/school/addSchool" method="post">
	<p><label>学校名称：</label>
	<input type="text" name="school.name" />
	</p>
	<p><label>所属地区：</label>
		<select name="id">  
			<c:forEach items="${provinces}" var="province">
				<option value="${province.id}">${province.name}</option>
			</c:forEach>
		</select>
	</p>
	<p>
	<label>学校地址：</label>
	<input type="text" name="school.address" />
	</p>
	<label>校徽：</label>
	<a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>/image/goUploadImage">上传图片</a>
	<!-- 上传成功后，图片将插到这里。 -->
	<div id="pic">
		<img src=""></img>
		<input id="oriFileName" type="hidden" name="image.oriFileName" value=""/>
		<input id="bigFileName" type="hidden" name="image.bigFileName" value=""/>
		<input id="bigFileUrl" type="hidden" name="image.bigFileUrl" value=""/>
		<input id="minFileName" type="hidden" name="image.minFileName" value=""/>
		<input id="minFileUrl" type="hidden" name="image.minFileUrl" value=""/>
	</div>
	<p><label>学校简介：</label>
	<textarea name="school.content" id="demo" rows="50" cols="150" style="width: 600px; height: 150px"></textarea>
	</p>
	<p><label>拥有学院：</label></p>
	<div id="institutes">
		<div><label>学院名称：</label><input class="inputmid" type="text" name="names" /> <a href="#" class="btn_del" onclick="return delInstitute(this)">删除</a></div>
		<div><label>学院名称：</label><input class="inputmid" type="text" name="names" /> <a href="#" class="btn_del" onclick="return delInstitute(this)">删除</a></div>
		<div><label>学院名称：</label><input class="inputmid" type="text" name="names" /> <a href="#" class="btn_del" onclick="return delInstitute(this)">删除</a></div>
	</div>
	<p class="paddingmin">
	<a href="#" class="btn_add" onclick="return addInstitute('ids');">再添加三项</a>
	</p>
	<input type="submit" value="添加"/>
</form>