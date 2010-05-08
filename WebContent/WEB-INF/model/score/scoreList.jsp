<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path%>/content/jq-highcharts/highcharts.js"></script>
<script type="text/javascript" src="<%=path%>/content/jq-highcharts/highcharts-gridtheme.js"></script>
<script type="text/javascript">
	var data = "${data}";
	//直方图
	$(document).ready(function() {

		if ($('#columnchart').size()==0) return;
		var options = {
				chart: {
			renderTo: 'columnchart',
			width:570,
			margin: [60, 10, 50, 30],
			defaultSeriesType: 'column'
		},
		title: {
			text: '${user.classes.name}-${user.name}的成绩图表'
		},
		subtitle: {
			text: ''
		},
		xAxis: {
			categories: [],
			labels: {
				rotation: -20,
				align: 'centre',
				style: {
					font: 'normal 13px Verdana, sans-serif'
				}
			}
		},
		yAxis: {
			max: 100,
			min: 0,
			title: {
				text: '分数'
			}
		},
		legend: {
			layout: 'vertical',
			backgroundColor: '#FFFFFF',
			style: {
				left: 'auto',
				bottom: 'auto',
				right: '10px',
				top: '10px'
			}
		},
		tooltip: {
			formatter: function() {
				return '<b>'+ this.x +'</b><br/>'+
				this.series.name +': '+ this.y +'分';
			}
		},
		plotOptions: {
			column: {
				pointPadding: 0.2,
				borderWidth: 0
			}
		},
	        series: []
	};

		// Split the lines
		var lines = data.split('/n');
		$.each(lines, function(lineNo, line) {
			var items = line.split(',');
			// header line containes categories
				if (lineNo == 0) {
					$.each(items, function(itemNo, item) {
						options.xAxis.categories.push(item);
					});
				}
				// the rest of the lines contain data with their name in the first position
				else if(lineNo ==1) {
					var series = {
						name: '${user.name}的分数',
						data : []
					};
					$.each(items, function(itemNo, item) {
						series.data.push(parseFloat(item));
					});
					options.series.push(series);
				}
				else {
					var series = {
						name: '${user.classes.name}平均分数',
						color: '#EE0000',
						data : []
					};
					$.each(items, function(itemNo, item) {
						series.data.push(parseFloat(item));
					});
					options.series.push(series);
				};
			});
		var chart = new Highcharts.Chart(options);
	
	});

</script>
<a onclick="ajaxload(this);return false;" href="<%=path %>/score/listScore" >全部学生的成绩</a>
<a rel="ajaxupload" lang="{upload:'<%=path%>/score/scoreUpload',complete:'<%=path%>/score/listScore',allowtype:/^(xls)$/i}" href="javascript:void()">上传成绩</a>
<c:choose>
	<c:when test="${scores == null}">
					没有该学生的成绩数据！
	</c:when>
	<c:otherwise>
		<h2>图表</h2>
		<div id="columnchart" style=""></div>
		<table class="table">
				<tr>
					<td>科目</td>
					<td>分数</td>
				</tr>
			<c:forEach items="${scores }" var="score">
				<tr>
					<td>${score.subject}</td>
					<td>${score.marks}</td>
				</tr>
			</c:forEach>
		</table>
	</c:otherwise>
</c:choose>

