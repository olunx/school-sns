package cn.gdpu.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

public class NoCacheFilter extends HttpServlet implements Filter {
	private static final long serialVersionUID = 1L;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
	     ((HttpServletResponse)response).setHeader("Pragma","No-cache");
	     ((HttpServletResponse)response).setHeader("Cache-Control","no-cache");
	     ((HttpServletResponse)response).setHeader("Expires","0");//禁止缓存 
	     filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		super.init();
	}

}
