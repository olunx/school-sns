<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
/*	
	Object isAccess = session.getAttribute("isAccess");
	if (isAccess!=null && isAccess.equals("true")){
		response.sendRedirect(path+"/home");
	}
*/		
%>
<jsp:include page="/WEB-INF/header.jsp"></jsp:include>
<!-- 登录页专用件 -->
<link type="text/css" rel="stylesheet" href="<%=path%>/content/images/login.css" />
<script>
	$(document).ready(function() {
	});
</script>
    <div id="mbox">
      <div id="adbox"></div>
      <div id="loginbox">
        <h3 id="logintitle">请登录：</h3>
        <div id="login">
          <form id="inputform" action="authLogin" method="post">
            <p>
              <label for="username">用户名</label>
              <input type="text" name="username" id="username" class="t_input" value="0707501131" />
            </p>
            <p>
              <label for="password">密　码</label>
              <input type="password" name="password" id="password" class="t_input" value="0707501131" />
            </p>
            <p class="submitrow">
              <input id="submit" type="submit" name="loginsubmit" class="submit" value="登录" />
              <a href="#">忘记密码?</a> </p>
          </form>
        </div>
        <!-- end fof login -->
      </div>
      <!-- end fof loginbox -->
    </div>
    <!-- end fof mbox -->
  <jsp:include page="/WEB-INF/footer.jsp"></jsp:include>