<%@ page info="Random Image Show" pageEncoding="UTF-8" contentType="image/jpg" autoFlush="true" buffer="16kb" session="false"
	import="java.io.FileInputStream"%>
<%
try{
	ServletOutputStream sos = null;
	if (sos == null) sos = response.getOutputStream();
	FileInputStream fis = new FileInputStream((String) request.getAttribute("filename"));
	byte[] buf = new byte[1024]; //缓冲区大小  
	int len = 0;
	while ((len = fis.read(buf)) != -1) {
		sos.write(buf, 0, len);
	}
	sos.flush();
	sos.close();
	fis.close();
	out.clear();
	out = pageContext.pushBody();
}catch(Exception e){
	out.clear();
	out = pageContext.pushBody();
}
%>
