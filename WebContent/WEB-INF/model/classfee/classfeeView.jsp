<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>

查看班费<br />
<div>
	<ul>
		<li>班费事件：${classfee.event }</li>
		<li>费用：${classfee.fee }</li>
		<li>备注：${classfee.remarks }</li>
		<li>经手人：${classfee.cmaker.name }</li>
		<li>创建时间：${classfee.time }</li>
	</ul>
</div>
