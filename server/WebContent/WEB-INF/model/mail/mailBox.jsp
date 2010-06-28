<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!-- JQuery库 -->
<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/content/js/common.js"></script>

<div class="form">
<ul id="classnav" class="buttons nav">
	<li><a rev="#box" onclick="ajaxload(this);return false;" href="<%=path%>/mail/listMail">所有纸条</a></li>
	<li><a rev="#box" onclick="ajaxload(this);return false;" href="<%=path%>/mail/listMySendMail">发件箱</a></li>
	<li><a rev="#box" onclick="ajaxload(this);return false;" href="<%=path%>/mail/listMyReceMail">收件箱</a></li>
	<li><a rev="#box" onclick="ajaxload(this);return false;" href="<%=path%>/mail/goAddMail">传纸条</a></li>
</ul>
</div>
<br/>
<div id="box"></div>