<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="my" uri="http://gdpu.cn/functions"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript">
	$(function() {
		listMore("#people_more_list", "#tab");
	});
</script>
<c:choose>
	<c:when test="${pageBean.list == null}">
		没有任何班级成员！
	</c:when>
	<c:otherwise>
		<c:if test="${fn:length(pageBean.list) <= 1}">
			<a rel="ajaxupload" lang="{upload:'<%=path %>/people/peopleUpload',complete:'<%=path %>/people/classesPeople?id=1',allowtype:/^(xls)$/i}" href="javascript:void()">上传学生信息</a>
		</c:if>
		<c:forEach items="${pageBean.list}" var="people">
			<div class="ilist clearfix">
				<div class="iavatar"><img src="<%=path %>/avatar/${people.id}" /></div>
				<div class="imsg">
				<div class="iname">
				<a onclick="ajaxload(this);return false;" href="<%=path%>/people/followPeople?id=${people.id}&page=${page}">
					 <c:choose>
					 	<c:when test="${my:isMyFriend(friends,people)}">删除好友</c:when>
					 	<c:otherwise>加为好友</c:otherwise>
					 </c:choose>
				</a>
				</div>
				<p class="icontent">
				<a onclick="ajaxload(this);return false;" href="<%=path%>/t/${people.username }">姓名：${people.name} <span class="money">（${people.school.name}）</span></a>
				</p>
				<p class="desc">学院：${people.institute.name} | 班级：${people.classes.name} <br/> 宿舍：${people.dorm} | 手机号码：${people.phoneNo}</p>
				
				<div class="ioperate">
				 状态：${people.status} | 上次登录：
				 <c:choose>
				 	<c:when test="${people.lastlogin != null}">
				 		${my:formatDate(people.lastlogin) }
				 	</c:when>
				 	<c:otherwise>
				 		从未
				 	</c:otherwise>
				 </c:choose>
				</div>
				</div>
			</div>
		</c:forEach>

		<div id="people_more_list"><c:choose>
			<c:when test="${pageBean.currentPage != pageBean.totalPage}">
			<div class="buttons">
			<a class="regular long center" target="list" href="<%=path%>/people/${moreAction }?page=${pageBean.currentPage+1}&id=${id}" >更多...</a>
			</div>
			</c:when>
			<c:otherwise>
				<div class="buttons">
				<a class="negative long center" href="#" onClick="$.scrollTo(0 , 800 );">没有了！回到顶部</a>
				</div>
			</c:otherwise>
		</c:choose>
		</div>

	</c:otherwise>
</c:choose>

