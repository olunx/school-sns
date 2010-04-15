<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
%>
<form onSubmit="post(this);return false;" action="<%=path%>/mail/addMail" method="post">
<label>递小纸条：</label><br/>
<label>接收者：</label><input type="text" name="receiverId" value="${receiverId}" />
<br/>
<label>标题：</label><input type="text" name="mail.title" />
<div class="paddingmin"><textarea name="mail.content" id="demo" rows="10" cols="50" style="width: 500px; height: 150px"></textarea>
<br />
</div>
<p class="paddingmin"><input type="submit" value="提交" /> <input type="reset" value="重置" /></p>
</form>
