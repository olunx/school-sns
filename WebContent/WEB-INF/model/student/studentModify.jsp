<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
%>
<h2 class="caption">添加学生</h2>
<form onSubmit="post(this);return false;" action="<%=path%>/student/modifyStudent" method="post">
<p><label> 头像： </label> <input type="text" name="student.avatar" value="${student.avatar}" /></p>
<p><label> 用户名： </label> <input type="text" name="student.username" value="${student.username}" /></p>
<p><label> 密码： </label> <input type="text" name="student.password" value="${student.password}" /></p>
<p><label> 学号： </label> <input type="text" name="student.sno" value="${student.sno}" /></p>
<p><label> 昵称： </label> <input type="text" name="student.nickname" value="${student.nickname}" /></p>
<p><label> 姓名： </label> <input type="text" name="student.name" value="${student.name}" /></p>
<p><label> 宿舍： </label> <input type="text" name="student.dorm" value="${student.dorm}" /></p>
<p><label> 手机： </label> <input type="text" name="student.phoneNo" value="${student.phoneNo}" /></p>
<p><label> QQ： </label> <input type="text" name="student.qq" value="${student.qq}" /></p>
<p><label> 邮箱： </label> <input type="text" name="student.email" value="${student.email}" /></p>
<p class="paddingmin"><input type="submit" value="提交" /> <input type="reset" value="重置" /></p>
<input type="hidden" name="student.id" value="${student.id}"/>
</form>
