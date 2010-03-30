<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2 class="caption">
			<div class="float_right">
					<a class="btn" href="<%=path %>/classfee/listClassfee">返回列表</a>
			</div>
			新建班费
		</h2>
		
		<form onSubmit="post(this);return false;" class="form" action="<%=path %>/classfee/modifyClassfee" method="post">
			<input name="id" type="hidden" value="${classfee.id }" />
			<p><label>班费费用：</label><s:fielderror><s:param>fee</s:param></s:fielderror>
			<input type="text" name="fee" value="${classfee.fee }"/> （例如：支出5元,填写"-5"；收入10元，填写"10"）</p>
			<label>班费事件：</label><s:fielderror><s:param>classfee.event</s:param></s:fielderror>
				<div class="paddingmin">
					 <textarea name="classfee.event" id="demo" rows="50" cols="152" style="width: 600px; height: 195px">${classfee.event }</textarea>	<br />
				</div>
			         <p class="paddingmin"> <input type="submit" value="提交" />
			          <input type="reset" value="重置"/></p>
		</form>
		
		
		<!-- 2. 页尾引入 editor js and init code -->
		<script type="text/javascript" src="<%=path %>/content/kissy/editor-aio.js"></script>
		<script type="text/javascript">
		    new KISSY.Editor("demo", {
		        base: "<%=path %>/content/",
		        pluginsConfig: {
		            image: {
		                tabs          : ["local", "link"], //"album"],
		                upload: {
		                    actionUrl : "<%=path %>/kissyUpload.action"
		                }
		            },
		            smiley: {
		                tabs          : ["wangwang"]
		            }
		        }
		    });
		</script>
</body>
</html>