<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript" src="<%=path%>/content/jq-highcharts/highcharts.js"></script>
<script type="text/javascript" src="<%=path%>/content/jq-highcharts/highcharts-gridtheme.js"></script>
<script type="text/javascript">
	var data = "${data}";
	//折线图
	$(document).ready(function() {
	
		var options = {
			chart: {
				renderTo: 'linechart',
				defaultSeriesType: 'line',
				margin: [60, 10, 60, 60]
			},
			title: {
				text: '班费收支情况图表',
				style: {
					margin: '10px 100px 0 0' // center it
				}
			},
			subtitle: {
				text: '',
				style: {
					margin: '0 100px 0 0' // center it
				}
			},
			xAxis: {
				categories : [],
				title: {
					text: '日期'
				}
			},
			yAxis: {
				title: {
					text: '费用 (元)'
				},
				plotLines: [{
					value: 0,
					width: 1,
					color: '#808080'
				}]
			},
			tooltip: {
				formatter: function() {
		                return '<b>'+ this.series.name +': '+ this.y +'元' +'</b><br/>'+
						this.x ;
				}
			},
			legend: {
				layout: 'vertical',
				style: {
					left: 'auto',
					bottom: 'auto',
					right: '10px',
					top: '10px'
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
						name: '总班费',
						data : []
					};
					$.each(items, function(itemNo, item) {
						series.data.push(parseFloat(item));
					});
					options.series.push(series);
				}
				else {
					var series = {
						name: '收支班费',
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
<c:if test="${isAdmin}">
	<a onclick="ajaxload(this);return false;" href="<%=path %>/classfee/goAddClassfee">添加班费记录</a>
</c:if>
<c:choose>
	<c:when test="${pageBean.list==null}">
		<a>没有数据</a>
	</c:when>
	<c:otherwise>
		<form onSubmit="post(this);return false;"  action="<%=path%>/classfee/deleteManyClassfee" method="post">
		<table class="table">
			<tr>
				<th><a rel="checkall">全选</a></th>
				<th>班费事件</th>
				<th>类别</th>
				<th>费用</th>
				<th>经手人</th>
				<th>创建时间</th>
				<c:if test="${isAdmin}">
				<th>修改</th>
				<th>删除</th>
				</c:if>
			</tr>
			<c:forEach items="${pageBean.list}" var="classfee">
				<tr>
					<td><input type="checkbox" name="ids" value="${classfee.id }" /></td>
					<td><a onclick="ajaxload(this);return false;" href="<%=path%>/classfee/viewClassfee?id=${classfee.id }">${classfee.event }</a></td>
					<td>${classfee.fee lt 0 ? "支出":"收入" }</td>
					<td>${classfee.fee } 元</td>
					<td>${classfee.cmaker.name }</td>
					<td><fmt:formatDate value="${classfee.time}" pattern="yyyy-MM-dd HH:mm" /></td>
					<c:if test="${isAdmin}">
					<td><a onclick="ajaxload(this);return false;" href="<%=path%>/classfee/goModifyClassfee?id=${classfee.id }" class="btn_edit">修改</a></td>
					<td><a onclick="ajaxload(this);return false;" href="<%=path%>/classfee/deleteClassfee?id=${classfee.id }&page=${page}" class="btn_del">删除</a></td>
					</c:if>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td>总计：</td>
				<td>${totalMoney }元</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		
		<div id="pagecount">
		<p>共 ${pageBean.allRow} 条记录 共 ${pageBean.totalPage} 页 当前第 ${pageBean.currentPage}页</p>
		<c:choose>
			<c:when test="${pageBean.currentPage == 1}">
				<a><span>首页</span></a>
				<a><span>上一页</span></a>
			</c:when>
			<c:otherwise>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/classfee/listClassfee?page=1"><span>首页</span></a>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/classfee/listClassfee?page=${pageBean.currentPage-1}"><span>上一页</span></a>
			</c:otherwise>
		</c:choose> <c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
				<a onclick="ajaxload(this);return false;" href="<%=path%>/classfee/listClassfee?page=${pageBean.currentPage+1}"><span>下一页</span></a>
				<a onclick="ajaxload(this);return false;" href="<%=path%>/classfee/listClassfee?page=${pageBean.totalPage}"><span>尾页</span></a>
			</c:when>
			<c:otherwise>
				<a><span>下一页</span></a>
				<a><span>尾页</span></a>
			</c:otherwise>
		</c:choose></div>

		<select name="cmd">
			<option value="0" selected="selected">批量操作，请选择</option>
			<option value="1">删除</option>
		</select> <input type="submit" value="确定" />
		
		</form>
		<h2>图表</h2>
		<div id="linechart" style=""></div>
	</c:otherwise>
</c:choose>
