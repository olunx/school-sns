﻿<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://gdpu.cn/functions" prefix="my"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path%>/content/jq-highcharts/highcharts.js"></script>
<script type="text/javascript" src="<%=path%>/content/jq-highcharts/highcharts-gridtheme.js"></script>
<script type="text/javascript">
	var data = "${data}";
	//直方图
	$(document).ready(function() {
	
		var options = {
			chart : {
				renderTo : 'barchart',
				margin: [80, 20, 60, 100],
				width:570,
				defaultSeriesType : 'bar'
			},
			title : {
				text : '${vote.summary }',
				style: {
					textAlign: 'center',
					margin: '30px 0 0 0'
				}
			},
			xAxis : {
				categories : []
			},
			yAxis : {
				title : {
					text : ''
					
				}
			},
			tooltip: {
			      formatter: function() {
			         return this.series.name +' 票数: '+ this.y ;
			      }
			   },
			   plotOptions: {
			      bar: {
			         dataLabels: {
			            enabled: true,
			            color: 'auto',
			            style: {
			            	fontSize: '15pt',
			            	padding: '5px'
			            }
			         }
			      }
			   },
			   legend: {
			      layout: 'vertical',
			      style: {
			         left: '10px',
			         bottom: 'auto',
			         right: 'auto',
			         top: '10px'
			      },
			      borderWidth: 1,
			      backgroundColor: '#FFFFFF'
			   },
			   credits: {
			      enabled: false
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
						name: '${vote.title }',
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

	//饼图
	$(document).ready(function() {
		
		var options = {
			chart: {
				renderTo: 'piechart',
				width:570,
				margin: [80, 20, 60, 20]
			},
			title: {
				text : '${vote.summary }',
				style: {
					textAlign: 'center',
					margin: '30px 0 0 0'
				}
			},
			plotArea: {
				shadow: null,
				borderWidth: null,
				backgroundColor: null
			},
			tooltip: {
				formatter: function() {
					return '<b>'+ this.point.name +'</b>: '+ this.y +' %';
				}
			},
			plotOptions: {
				pie: {
					allowPointSelect: true,
					dataLabels: {
						enabled: true,
						formatter: function() {
							if (this.y > 5) return this.point.name;
						},
						color: 'white',
						style: {
							font: '13px Trebuchet MS, Verdana, sans-serif'
						}
					}
				}
			},
			legend: {
				layout: 'vertical',
				style: {
					left: '20px',
					bottom: 'auto',
					right: 'auto',
					top: '80px'
				}
			},
		        series: []
		};
			var test = ${data2};
			var series = { 
				type: 'pie',
				name: '${vote.title }',
				data:  []
			};
			series.data=test;
			options.series.push(series);
			var chart2 = new Highcharts.Chart(options);

	});

	$(function() {
		$("#barchart").slideUp("normal");
	});

	function showPie() {
		$("#barchart").slideUp("normal");
		$("#piechart").slideDown("normal");
	}

	function showBar() {
		$("#piechart").slideUp("normal");
		$("#barchart").slideDown("normal");
	}
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
</div>
<br/>
<h2>图表</h2>
<div class="buttons">
 <a onclick="showPie();return false;" href="#" class="regular">饼图</a>
 <a onclick="showBar();return false;" href="#" class="regular">直方图</a>
</div>
<div id="piechart" style=""></div>
<div id="barchart" style=""></div>
<div>
<br/>
<h2>用户评论</h2>
<c:choose>
	<c:when test="${empty vote.reply}">
		<a onclick="ajaxload(this);return false;" href="<%=path %>/vote/goReplyVote?vid=${vote.id}&rid=-1">还没有评论哦！我来抢沙发^o^</a> 
	</c:when>
	<c:otherwise>
		<div class="class_msg_list">
		<c:forEach items="${vote.reply}" var="reply">
		<div class="class_msg">
			<div class="avatar">
				<img src="<%=path %>/avatar/${reply.author.id}" />
			</div>
			<div class="msg">
			<div class="operate">
			<p class="time" title="${reply.time }"><a onclick="ajaxload(this);return false;" href="<%=path %>/vote/goReplyVote?vid=${vote.id}&rid=${reply.id != null ? reply.id : -1 }">回复</a> ${my:formatDate(reply.time)}</p>
			</div>
				<p class="text">${reply.author.name }： ${reply.content}</p>
				
				<c:if test="${! empty reply.reply}">
				<div class="reply">
					<c:forEach items="${reply.reply}" var="subreply">
					<p>
						${subreply.author.name }： ${subreply.content}
						${my:formatDate(subreply.time)}
					</p>
					</c:forEach>
				</div>
				</c:if>
			</div>
		<div class="clear"></div>
		</div>
		</c:forEach>
		</div>
		<a onclick="ajaxload(this);return false;" href="<%=path %>/vote/goReplyVote?vid=${vote.id}&rid=-1">我也来说几句</a> 
	</c:otherwise>
</c:choose>
</div>
