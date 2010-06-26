<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<style type="text/css">
.attmod {
	margin: 10px 0;
}

.selectable .ui-selecting {
	background: #FECA40;
}

.selectable .ui-selected {
	background: #F39814;
	color: white;
}

.selectable {
	list-style-type: none;
	margin: 0;
	padding: 0;
}

#lessonsel li {
	margin: 3px;
	padding: 1px;
	float: left;
	width: 80px;
	height: 50px;
	font-size: 14px;
	text-align: center;
}

#studentssel li {
	margin: 3px;
	padding: 1px;
	float: left;
	width: 60px;
	height: 30px;
	font-size: 14px;
	text-align: center;
	padding-top: 15px;
}
</style>
<script type="text/javascript">
	$(function() {
		$("#lessonsel").selectable( {
			stop : function() {
				var ret = "";
				$(".ui-selected", this).each(function() {
					if ($(this).attr("title") != "")
						ret += $(this).attr("title") + ",";
				});
				$("#courseIds").val(ret);
			}
		});
		$("#studentssel").selectable( {
			stop : function() {
				var ret = "";
				$(".ui-selected", this).each(function() {
					if ($(this).attr("title") != "")
						ret += $(this).attr("title") + ",";
				});
				$("#students").val(ret);
			}
		});
	});
</script>

<form onSubmit="post(this,'#class');return false;" action="<%=path%>/attendance/addAttendance" method="post">
<div style="float: left">第 <select size="1" name="week">
	<%
		for (int i = 1; i < 21; i++) {
			out.println("<option>" + i + "</option>");
		}
	%>
</select> 周，</div>
<div style="float: left">星期 <select size="1" name="day">
	<option selected="selected">一</option>
	<option>二</option>
	<option>三</option>
	<option>四</option>
	<option>五</option>
	<option>六</option>
	<option>日</option>
</select></div>
<div class="clear"></div>
<div class="attmod">课程(请按住Ctrl键选择)： <input type="hidden" name="courseIds" id="courseIds" />
<c:if test="${empty courses}">还没有课程，请上传课程表</c:if>
<ol id="lessonsel" class="selectable">
	<c:forEach items="${courses}" var="c">
		<li class="ui-state-default" title="${c.id }">周${c.whatDay }, ${c.name }</li>
	</c:forEach>
</ol>
</div>

<div class="clear"></div>
<div class="attmod">逃课学生(请按住Ctrl键选择)： <input type="hidden" name="students" id="students" />
<ol id="studentssel" class="selectable">
	<c:forEach items="${students}" var="stu">
		<li class="ui-state-default" title="${stu.sno }">${stu.name }</li>
	</c:forEach>
</ol>
<input type="hidden" name="clerk" value="${sessionScope.student.sno }" /></div>
<div class="clear"></div>

<input type="submit" value="提交" />
</form>
