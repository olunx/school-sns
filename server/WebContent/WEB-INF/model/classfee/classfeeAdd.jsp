<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<h2 class="caption">新建班费</h2>
<form class="form" onSubmit="post(this,'#class');return false;" action="<%=path%>/classfee/addClassfee" method="post">
<p><label>班费费用：</label><input type="text" name="classfee.fee"  /> （例如：支出5元,填写"-5"；收入10元，填写"10"）</p>
<p>
<label>班费事件：</label>
<textarea name="classfee.event" class="textarea" id="demo" rows="10" cols="40"></textarea>
</p>
<p class="paddingmin"><input type="submit" value="提交" /> <input type="reset" value="重置" /></p>
</form>
