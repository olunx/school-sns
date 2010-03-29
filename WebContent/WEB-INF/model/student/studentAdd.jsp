<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
%>
<h2 class="caption">
	添加学生
</h2>
<form onSubmit="post(this);return false;" action="<%=path %>/student/addStudent" method="post">
	<p>
		<label>
			头像：
		</label>
		<input type="text" name="avatar" />
	</p>
	<p>
		<label>
			用户名：
		</label>
		<input type="text" name="username" />
		<s:fielderror><s:param>username</s:param></s:fielderror>
	</p>
	<p>
		<label>
			密码：
		</label>
		<input type="text" name="password" />
		<s:fielderror><s:param>password</s:param></s:fielderror>
	</p>
	<p>
		<label>
			学号：
		</label>
		<input type="text" name="sno" />
		<s:fielderror><s:param>sno</s:param></s:fielderror>
	</p>
	<p>
		<label>
			姓名：
		</label>
		<input type="text" name="realName" />
		<s:fielderror><s:param>realName</s:param></s:fielderror>
	</p>
	<p>
		<label>
			宿舍：
		</label>
		<input type="text" name="dorm" />
		<s:fielderror><s:param>doem</s:param></s:fielderror>
	</p>
	<p>
		<label>
			手机：
		</label>
		<input type="text" name="phoneNo" />
	</p>
	<p>
		<label>
			QQ：
		</label>
		<input type="text" name="qq" />
	</p>
	<p>
		<label>
			邮箱：
		</label>
		<input type="text" name="mail" />
	</p>
	<p class="paddingmin">
		<input type="submit" value="提交" />
		<input type="reset" value="重置" />
	</p>
</form>