<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!-- JQuery库 -->
<script type="text/javascript" src="<%=path%>/content/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/content/js/common.js"></script>

<a target="content" href="<%=path%>/mail/listMail">所有纸条</a>
<a target="content" href="<%=path%>/mail/listMySendMail">发件箱</a>
<a target="content" href="<%=path%>/mail/listMyReceMail">收件箱</a>
<a target="content" href="<%=path%>/mail/goAddMail">传纸条</a>

<br/><br/>
<div id="content">

</div>