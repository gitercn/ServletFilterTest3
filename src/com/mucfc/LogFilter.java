package com.mucfc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName="log", urlPatterns= {"/*"})
public class LogFilter implements Filter{
	private FilterConfig filterConfig;
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		filterConfig = arg0;
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		//，获取ServletContext对象，用于记录日志
		ServletContext servletContext = filterConfig.getServletContext();
		long before = System.currentTimeMillis();
		System.out.println("开始过滤");
		
		HttpServletRequest httpServletRequest = (HttpServletRequest)arg0;
		
		//，输出信息
		System.out.println("Filter已经截获到用户的请求地址"+httpServletRequest.getServletPath());
		//，放行
		arg2.doFilter(arg0, arg1);
		//，输出到服务器相应执行后处理
		long after = System.currentTimeMillis();
		System.out.println("过滤结束");
		System.out.println("请求被定位到"+httpServletRequest.getRequestURI()+ " 时间"+(after-before));
	}

	@Override
	public void destroy() {
		filterConfig=null;
	}

}
