<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>
<link type="text/css" rel="stylesheet" href="<%=path%>/content/images/twitter.css" />
<div id="twitter">
    <a>大家一起来叽歪一下吧！</a>
	<form onSubmit="post(this);return false;" action="<%=path%>/twitter/addTwitter" method="post">
    <div id="input" >
      <textarea class="inputarea" name="twitter.content"></textarea>
    </div>
	<div id="options" >
    	<div id="text">
            <a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>/image/goUploadImage">图片</a>
            <a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>">视频</a>
            <a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>">链接</a>
            <a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>">文件</a>
            <a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>/vote/goAddVote">投票</a>
            <a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>/issue/goAddIssue">问答</a>
            <a onclick="return hs.htmlExpand(this, { objectType: 'iframe' } )" href="<%=path%>/goods/goAddGoods">交换</a>
        </div>
       <div id="submit"><input class="inputbtn" type="submit" value="发表"></div>
       <div id="pic">
            <img src=""></img>
            <!-- 上传成功后，图片将插到这里。 -->
            <input id="oriFileName" type="hidden" name="image.oriFileName" value=""/>
            <input id="bigFileName" type="hidden" name="image.bigFileName" value=""/>
            <input id="bigFileUrl" type="hidden" name="image.bigFileUrl" value=""/>
            <input id="minFileName" type="hidden" name="image.minFileName" value=""/>
            <input id="minFileUrl" type="hidden" name="image.minFileUrl" value=""/>
		</div>
    </div>
	</form>
</div>


