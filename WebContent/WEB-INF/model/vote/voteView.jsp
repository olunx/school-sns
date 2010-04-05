<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path%>/content/js/highcharts.js"></script>
<script type="text/javascript">
	var data = "${data}";
	$(document).ready(function() {
	
		var options = {
			chart : {
				renderTo : 'chart',
				defaultSeriesType : 'bar'
			},
			title : {
				text : 'Fruit Consumption'
			},
			xAxis : {
				categories : []
			},
			yAxis : {
				title : {
					text : 'Units'
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
<ul class="ul">
	<li>投票主题：${vote.title }</li>
	<li>描述内容：${vote.summary }</li>
	<li>投票类型：${vote.type==0 ? "单选" : "多选" }</li>
	<c:forEach items="${vote.items}" var="voteItem" varStatus="i">
		<li>投票选项${i.count}：${voteItem.content } 票数：${voteItem.num } <br />
		</li>
	</c:forEach>
	<li>创建人：${vote.author.name }</li>
	<li>创建日期：${vote.airTime }</li>
	<li>结束日期：${vote.deadline }</li>
	<li>参与人： <c:forEach items="${vote.voters}" var="voter"
		varStatus="i">
							${voter.name } ;
					</c:forEach></li>
</ul>
<a target="content" href="<%=path%>/vote/deleteVote?vid=${vote.id}"
	class="btn_del">删除</a>
<div id="chart" style=""></div>
