<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://gdpu.cn/functions" prefix="my"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path%>/content/jq-highcharts/highcharts.js"></script>
<script type="text/javascript" src="<%=path%>/content/jq-highcharts/highcharts-gridtheme.js"></script>
<!--[if IE]> 
		<script type="text/javascript" src="<%=path%>/content/jq-highcharts/highcharts-ie.js"></script> 
<![endif]-->
<script type="text/javascript">
	var data = "${data}";
	$(document).ready(function() {
	
		var options = {
			chart : {
				renderTo : 'chart',
				defaultSeriesType : 'bar'
			},
			title : {
				text : '${vote.title }'
			},
			xAxis : {
				categories : []
			},
			yAxis : {
				title : {
					text : ''
				}
			},
			series : []
		};

		/*
		 Load the data from the CSV file. This is the contents of the file:
		 
			Apples,Pears,Oranges,Bananas,Plums
			John,8,4,6,5
			Jane,3,4,2,3
			Joe,86,76,79,77
			Janet,3,16,13,15
			
		 */

		// Split the lines
			var lines = data.split('-');
			$.each(lines, function(lineNo, line) {
				var items = line.split(',');
				// header line containes categories
					if (lineNo == 0) {
						$.each(items, function(itemNo, item) {
							options.xAxis.categories.push(item);
						});
					}
					// the rest of the lines contain data with their name in the first position
					else {
						var series = {
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
<div class="vote">
	<h2>${vote.title }(${vote.author.name })</h2>
	<p class="desc">${vote.summary }</p>
	<p>创建日期：${my:formatDate(vote.airTime) }</p>
	<p>结束日期：${vote.deadline }</p>
	<ul class="voteul">
	<c:forEach items="${vote.items}" var="voteItem" varStatus="i">
		<li><span class="desc">票数：${voteItem.num }</span>${i.count}.${voteItem.content }</li>
	</c:forEach>
	</ul>
	参与人：
	<c:forEach items="${vote.voters}" var="voter" varStatus="i">
	${voter.name }，
	</c:forEach>
	<h2>图表</h2>
</div>
<div id="chart" style=""></div>
