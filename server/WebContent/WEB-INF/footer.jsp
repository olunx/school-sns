<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
</div><!--container-->

  <div id="footer">
    <p>&copy; 2010 3C-Team</p>
  </div>

<script type="text/javascript">
	$(".menu li[rel=submenu]").mouseover(function(){
		$(this).addClass("menu_hover");
	});
	$(".menu li[rel=submenu]").mouseout(function(){
		$(this).removeClass("menu_hover");
	});	
	
    //$(".logo").pngFix(); 
</script>


<!--[if lt IE 7.]>
<script type="text/javascript" src="<%=path%>/content/js/pngfix.js"></script>
<![endif]-->

<!-- JQuery 上传插件 -->
<script type="text/javascript" src="<%=path%>/content/jq-ajaxupload/ajaxupload.js"></script>

<!-- JQ验证插件 -->
<script type="text/javascript" src="<%=path%>/content/jq-validate/jquery.form.js" ></script>
<script type="text/javascript" src="<%=path%>/content/jq-validate/jquery.validate.pack.js" ></script>
<script type="text/javascript" src="<%=path%>/content/jq-validate/messages_cn.js" ></script>

<!-- highslide -->
<link type="text/css" rel="stylesheet" href="<%=path%>/content/jq-highslide/highslide.css" />
<script type="text/javascript" src="<%=path%>/content/jq-highslide/highslide-full.min.js"></script>
<script type="text/javascript" src="<%=path%>/content/js/highslide-init.js"></script>

<script type="text/javascript" src="<%=path%>/content/js/jquery.scrollTo-min.js"></script>

<script type="text/javascript" src="<%=path%>/content/js/jquery.easydrag.js"></script>


<!-- dwr -->
<script type='text/javascript' src='<%=path%>/content/js/jquery.selectboxes.min.js'></script>
<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
<!-- <script type='text/javascript' src='<%=path%>/dwr/interface/PublicChat.js'></script> -->
<script type='text/javascript' src='<%=path%>/dwr/interface/PrivateChat.js'></script>


<script type="text/javascript">
	initHighslide("<%=path%>", "640", "480");
</script>

<!--[if IE]> 
		<script type="text/javascript" src="<%=path%>/content/jq-highcharts/highcharts-ie.js"></script> 
<![endif]-->
</body>
</html>