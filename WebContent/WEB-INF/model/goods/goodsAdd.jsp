<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
	String path = request.getContextPath();
%>
<script language="javascript" type="text/javascript">
	
	$(document).ready(function() {
		initHighslide("<%=path%>", "840", "640");
	});

	function addNewGoods(obj) {
		var cbox = document.getElementsByName('goods.state');
		if(cbox[0].checked == true){
			var html = '<div id="ex"><label>你想要交换的类型：</label><select name="gsType"><c:forEach items="${goodsType}" var="gst"><option value="${gst.name}">${gst.name}</option></c:forEach></select> <a href="#" onclick="return delGoods(this)">删除</a></div>';
			$("#" + obj).append(html);
			$("#addone").append('<a href="#" onclick="return addGoods(' + obj + ');" >添加一项</a>');
		}
		else{
			$("#" + obj).find("div").remove();
			$("#addone").find("a").remove();
		}
	};
	
	function addGoods(obj) {
		var html = '<div id="ex"><label>你想要交换的类型：</label><select name="gsType"><c:forEach items="${goodsType}" var="gst"><option value="${gst.name}">${gst.name}</option></c:forEach></select> <a href="#" onclick="return delGoods(this)">删除</a></div>';
		$(obj).append(html);
	}

	function delGoods(obj) {
		$(obj).parent().remove();
		return false;
	};
</script>

<form onSubmit="post(this);return false;" class="form" action="<%=path %>/goods/addGoods" method="post">
	<p><label>货品名称：</label>
	<input class="w_long" type="text" name="goods.name" />
	<s:fielderror><s:param>goods.name</s:param></s:fielderror>
	</p>
	<p><label>货品类型：</label>
		<select class="w_middle" name="gtid">  
			<c:forEach items="${goodsType}" var="gst">
				<option value="${gst.id}">${gst.name}</option>
			</c:forEach>
		</select>
	</p>
	<label>货品图片：</label>
	<a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>/image/goUploadImage">上传图片</a>
	<!-- 上传成功后，图片将插到这里。 -->
	<div id="pic">
	<p>
		<img class="m_small" src="<%=path %>/content/images/nopic.jpg" style = "width:150px;height:150px;" />
		<input id="oriFileName" type="hidden" name="image.oriFileName" value=""/>
		<input id="bigFileName" type="hidden" name="image.bigFileName" value=""/>
		<input id="bigFileUrl" type="hidden" name="image.bigFileUrl" value=""/>
		<input id="minFileName" type="hidden" name="image.minFileName" value=""/>
		<input id="minFileUrl" type="hidden" name="image.minFileUrl" value=""/>
		<s:fielderror><s:param>image.oriFileName</s:param></s:fielderror>
	</div>
	<p><label>描述内容：</label>
	<textarea class="textarea" name="goods.content" rows="5" cols="50"></textarea>
	</p>
	<p>	货品数量：
	<input class="w_small" type="text" name="goods.quantity" value="1"/>
	<span class="m_min"> 交换单价：<input class="w_small" type="text" name="goods.value" value="0"/></span>
	</p>
	<s:fielderror><s:param>goods.quantity</s:param></s:fielderror>
	<s:fielderror><s:param>goods.value</s:param></s:fielderror>
	<p id="addone">
	
	<input id="chk_exchange" type="checkbox" name="goods.state" value="1" onclick="return addNewGoods('exchange');"/> <label for="chk_exchange">我还想用来交换</label>
	</p>
	<div id="exchange"></div>
	<input type="submit" value="添加"/>
</form>