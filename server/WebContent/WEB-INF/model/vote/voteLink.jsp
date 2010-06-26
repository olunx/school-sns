<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@	taglib uri="/struts-tags" prefix="s" %>

<%
String path = request.getContextPath();
%>

<form onSubmit="post(this);return false;" action="<%=path %>/vote/votingVote" method="post">
	<c:choose>
		<c:when test="${!voterexist && !timeout}">
		<div class="vote">
			<input type="hidden" name="vid" value="${vote.id}">	
			<h2>${vote.title }</h2>
			<span class="desc">${vote.summary }</span>
			<ul class="voteul">
			<c:forEach items="${vote.items}" var="voteItem" varStatus="i">
			<li><input type="${vote.type==0 ? 'radio' : 'checkbox'}" name="viid" value="${voteItem.id}" id="vitem${i.count}" /> <label for="vitem${i.count}">${i.count}.${voteItem.content}</label></li>
			</c:forEach>
			</ul>
			<label>我还要说些什么：</label>
			<div class="paddingmin"><textarea name="reply.content" id="demo" rows="10" cols="50" style="width: 500px; height: 150px"></textarea>
			</div>
			<input type="submit" value="投票" >
		</div>
		</c:when>	

		<c:otherwise>						
			<div>
                     <c:if test="${timeout}">投票已经结束了。结束日期：${vote.deadline }</c:if>
				<ul>
					<li>投票主题：${vote.title }</li>
					<c:forEach items="${vote.items}" var="voteItem" varStatus="i">
							<li>投票选项${i.count}：${voteItem.content } (${voteItem.num }) <br /></li>
					</c:forEach>	
					<li>描述内容：${vote.summary }</li>
					<li>创建人：${vote.author.name }</li>
					<li>创建日期：${vote.airTime }</li>
					<li>结束日期：${vote.deadline }</li>
					<li>参与人：
						<c:forEach items="${vote.voters}" var="voter" varStatus="i">
								${voter.name } ;
						</c:forEach>	
					</li>
				</ul>
			</div>
		</c:otherwise>
	</c:choose>
</form>
