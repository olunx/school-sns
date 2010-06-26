<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="../content/images/content.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="../content/js/jquery.min.js"></script>
		<script language="javascript" type="text/javascript" src="../content/js/common.js"></script>
		<script language="javascript" type="text/javascript" src="../content/js/jquery.tablesorter.min.js"></script>
		<script type="text/javascript">
	$(function() {
		$(".table").tablesorter();
	});
</script>
		<title>成绩管理</title>
	</head>
	<body>
	<h2 class="caption">成绩发送</h2>
		<form action="sendScore" method="post">
			<input type="hidden" value="${fileName}" name="fileName" />
			手机号：
			<input type="text" name="phone" />
			飞信密码：
			<input type="password" name="pwd" />
			<input type="submit" value="确定" />
		</form>
		<div>
			${fileData}
		</div>
	</body>
</html>