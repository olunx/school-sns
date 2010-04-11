<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
%>
<script language="javascript" type="text/javascript">
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
	<input type="text" name="goods.name" />
	</p>
	<p><label>货品类型：</label>
		<select name="gtid">  
			<c:forEach items="${goodsType}" var="gst">
				<option value="${gst.id}">${gst.name}</option>
			</c:forEach>
		</select>
	</p>
	<p><label>描述内容：</label>
	<textarea name="goods.content" id="demo" rows="50" cols="150" style="width: 600px; height: 150px"></textarea>
	</p>
	货品数量：
	<input type="text" name="goods.quantity" value="1"/>
	交换单价：
	<input type="text" name="goods.value" value="0"/>
	<p id="addone">
	<input type="checkbox" name="goods.state" value="1" onclick="return addNewGoods('exchange');"/>我还想用来交换
	</p>
	<div id="exchange"></div>
	<input type="submit" value="添加"/>
</form>