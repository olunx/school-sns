<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>

<h2 class="caption">修改班费</h2>
<form onSubmit="post(this,'#class');return false;" class="form" action="<%=path%>/classfee/modifyClassfee" method="post">
<p><label>班费费用：</label> <input type="text" name="classfee.fee" value="${classfee.fee }" /> （例如：支出5元,填写"-5"；收入10元，填写"10"）</p>
<label>班费事件：</label>
<div class="paddingmin"><textarea name="classfee.event" id="demo" rows="50" cols="152" style="width: 500px; height: 195px">${classfee.event }</textarea>
<br />
</div>
<p class="paddingmin"><input type="submit" value="提交" /> <input type="reset" value="重置" /></p>
<input name="classfee.id" type="hidden" value="${classfee.id }" />
</form>
