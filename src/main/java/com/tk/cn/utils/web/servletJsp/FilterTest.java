package com.tk.cn.utils.web.servletJsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilterTest implements Filter{
	private int length=0;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("doFilter");
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		
		req.setCharacterEncoding("utf-8");
		String str = req.getParameter("content");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		if(str.length()<length){
			pw.write(str);
		}else{
			pw.write("你输入的长度过长");
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		length=Integer.parseInt(filterConfig.getInitParameter("length"));
	}
	
}
