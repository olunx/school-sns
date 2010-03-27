<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>登录</title>
</head>
<body>
      <div id="loginbox">
        <h3 id="logintitle">请登录：</h3>
        <div id="login">
          <form rel="validate" action="<%=path%>/login/authLogin" method="post">
            <p>
              <label for="username">用户名</label>
              <input type="text" name="username" id="username" class="t_input validate[required]" value="" />
            </p>
            <p>
              <label for="password">密　码</label>
              <input type="password" name="password" id="password" class="t_input validate[required]" value="" />
            </p>
            <p class="submitrow">
              <input type="submit" name="loginsubmit" class="submit" value="登录" />
              <a href="#">忘记密码?</a> </p>
          </form>
        </div>
        <!-- end fof login -->
      </div>
      <!-- end fof loginbox -->
</body>
</html>