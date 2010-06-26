<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List,java.io.*,java.awt.image.BufferedImage"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<c:if test="${targetsFileUrl != null}">
	<c:forEach items="${targetsFileUrl}" var="image">
		<img id="photo" style="width:500px;" src="<%=path%>${image}" />
		<div>
			<form rel="validate" action="<%=path %>/image/uploadImage" method="post">
			 x1: <input type="text" id="x" name="x" class="validate[required]" />
			 y1: <input type="text" id="y" name="y" class="validate[required]" />
			 width: <input type="text" id="width" name="width" class="validate[required]" />
			 height: <input type="text" id="height" name="height" class="validate[required]" />
			 <input type="submit" value="确定" />
			 <input type="hidden" name="targetsFileUrl" value="${image}" />
			 <c:if test="${targetsFileUrl != null}">
				<c:forEach items="${filesFileName}" var="filesFN">
					<input type="hidden" name="filesFileName" value="${filesFN}" />
			 	</c:forEach>
			</c:if>
			 <c:if test="${targetsFileUrl != null}">
				<c:forEach items="${targetsFileName}" var="targetsFN">
					<input type="hidden" name="targetsFileName" value="${targetsFN}" />
			 	</c:forEach>
			</c:if>
			<%
				if (request.getAttribute("targetsFilePath")!=null){
					String imagepath = ((List<String>)request.getAttribute("targetsFilePath")).get(0);
					InputStream is = new FileInputStream(imagepath);
					BufferedImage src = javax.imageio.ImageIO.read(is); //构造Image对象
					int srcWidth = src.getWidth(null); //得到源图宽
					int srcHeight = src.getHeight(null); //得到源图长
					request.setAttribute("imgWidth",srcWidth);
					request.setAttribute("imgHeight",srcHeight);
				}
				
			%>
			
			</form>
		</div>
	</c:forEach>
	<script type="text/javascript">
	<!--
	//图片选择事件
	function imgselect() {
	    $('#x').val(0);
	    $('#y').val(0);
	    $('#width').val(50);
	    $('#height').val(50);    

		$('#photo').imgAreaSelect( {
			aspectRatio : '1:1',
			handles : true,
			fadeSpeed : 200,
			x1: 0,
			y1: 0,
			x2: 50,
			y2: 50,
			imageWidth:${imgWidth},
			imageHeight:${imgHeight},
			onSelectChange: function(img, selection) {
			    $('#x').val(selection.x1);
			    $('#y').val(selection.y1);
			    $('#width').val(selection.width);
			    $('#height').val(selection.height);    
			}
		});
	}
	imgselect();
	//-->
	</script>
</c:if>