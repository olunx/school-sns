<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
%>
<h2 class="caption">添加学生</h2>
<form onSubmit="post(this);return false;" action="<%=path%>/student/addStudent" method="post">
<p><label> 头像： </label> <input type="text" name="student.avatar" /></p>
<p><label> 用户名： </label> <input type="text" name="student.username" /></p>
<p><label> 密码： </label> <input type="text" name="student.password" /></p>
<p><label> 学号： </label> <input type="text" name="student.sno" /></p>
<p><label> 昵称： </label> <input type="text" name="student.nickname" /></p>
<p><label> 姓名： </label> <input type="text" name="student.name" /></p>
<p><label> 宿舍： </label> <input type="text" name="student.dorm" /></p>
<p><label> 手机： </label> <input type="text" name="student.phoneNo" /></p>
<p><label> QQ： </label> <input type="text" name="student.qq" /></p>
<p><label> 邮箱： </label> <input type="text" name="student.mail" /></p>
<p class="paddingmin"><input type="submit" value="提交" /> <input type="reset" value="重置" /></p>
</form>
<h2 class="caption">批量注册：</h2>
<form action="<%=path%>/student/studentUpload" method="post" enctype="multipart/form-data">学生数据Excel文件：<input type="file"
	name="files" /> <input type="submit" value="提交" /> <s:fielderror>
	<s:param>fileTypeAlert</s:param>
</s:fielderror></form>