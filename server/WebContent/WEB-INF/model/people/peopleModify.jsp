<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%
	String path = request.getContextPath();
%>
<script type="text/javascript">
$(document).ready(function() {
	initHighslide("<%=path%>", "840", "640");
});
</script>
<div>
<h2 class="caption">个人资料修改</h2>
<form onSubmit="post(this);return false;" action="<%=path%>/people/modifyPeople" method="post">
<p><label> ${people.username } 用户资料修改</label></p>
<p><label> 真实姓名： </label> <input type="text" name="people.name" value="${people.name}" /></p>
<p><s:fielderror><s:param>people.name</s:param></s:fielderror></p>
<p><label> 昵称： </label> <input type="text" name="people.nickname" value="${people.nickname}" /></p>
<p><label> 宿舍： </label> <input type="text" name="people.dorm" value="${people.dorm}" /></p>
<p><label> 手机： </label> <input type="text" name="people.phoneNo" value="${people.phoneNo}" /></p>
<p><label> QQ： </label> <input type="text" name="people.qq" value="${people.qq}" /></p>
<p><label> 邮箱： </label> <input type="text" name="people.email" value="${people.email}" /></p>
<p><s:fielderror><s:param>people.email</s:param></s:fielderror></p>
<p class="paddingmin"><input type="submit" value="提交" /> <input type="reset" value="重置" /><c:if test="${modifysuc}">保存成功！</c:if></p>
<input type="hidden" name="people.id" value="${people.id}"/>
</form>
</div>

<div>
<h2 class="caption">用户密码更改</h2>
<form onSubmit="post(this);return false;" action="<%=path%>/people/modifyPSWPeople" method="post">
<p><label> 旧密码： </label> <input type="text" name="oldPassword" /><s:fielderror><s:param>oldPassword</s:param></s:fielderror></p>
<p><label> 新密码： </label> <input type="text" name="people.password" /><s:fielderror><s:param>people.password</s:param></s:fielderror></p>
<p><label> 确认密码： </label> <input type="text" name="rePassword" /><s:fielderror><s:param>rePassword</s:param></s:fielderror></p>
<p class="paddingmin"><input type="submit" value="提交" /> <input type="reset" value="重置" /><c:if test="${modifypswsuc}">保存成功！</c:if></p>
<input type="hidden" name="people.id" value="${people.id}"/>
</form>
</div>

<div>
<h2 class="caption">用户头像修改</h2>
<form onSubmit="post(this);return false;" action="<%=path%>/people/modifyAvatarPeople" method="post">
<a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>/image/goUploadImage">上传头像</a>
<!-- 上传成功后，图片将插到这里。 -->
<div id="pic">
<img src="<%=path %>/${people.avatar.minFileUrl}"></img>
<input id="oriFileName" type="hidden" name="image.oriFileName" value="${people.avatar.oriFileName}"/>
<input id="bigFileName" type="hidden" name="image.bigFileName" value="${people.avatar.bigFileName}"/>
<input id="bigFileUrl" type="hidden" name="image.bigFileUrl" value="${people.avatar.bigFileUrl}"/>
<input id="minFileName" type="hidden" name="image.minFileName" value="${people.avatar.minFileName}"/>
<input id="minFileUrl" type="hidden" name="image.minFileUrl" value="${people.avatar.minFileUrl}"/>
</div>
<p><s:fielderror><s:param>image.minFileUrl</s:param></s:fielderror></p>
<p class="paddingmin"><input type="submit" value="提交" /> <input type="reset" value="重置" /><c:if test="${modifyavatarsuc}">保存头像成功！请手动按F5刷新一下页面</c:if></p>
<input type="hidden" name="people.id" value="${people.id}"/>
</form>
</div>
