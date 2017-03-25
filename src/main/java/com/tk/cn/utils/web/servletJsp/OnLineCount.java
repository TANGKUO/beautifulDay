package com.tk.cn.utils.web.servletJsp;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnLineCount implements HttpSessionListener{
	
	private int count = 0;
	//session创建之后,容器会调用
	public void sessionCreated(HttpSessionEvent se) {
		count++;
		HttpSession session= se.getSession();
		ServletContext sctx=session.getServletContext();
		sctx.setAttribute("count", count);
	}
	//session销毁后,容器调用
	public void sessionDestroyed(HttpSessionEvent se) {
		count--;
	}
	
}
