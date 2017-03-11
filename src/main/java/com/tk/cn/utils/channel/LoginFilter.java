package com.tk.cn.utils.channel;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter extends HttpServlet  implements Filter   {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
				System.out.println("======filter======");
			 HttpServletRequest request = (HttpServletRequest) arg0;        
	         HttpServletResponse response = (HttpServletResponse) arg1;        
	         HttpSession session = request.getSession();        
	         String url=request.getServletPath();    
	         System.out.println("----获取到url地址"+url);
	         String contextPath=request.getContextPath(); 
	        /* CUsers cuser =  null;
	         cuser = (CUsers) request.getSession().getAttribute(ActionFinal.SessionUser);*/
	         if(url.equals("")) url+="/";    
	         System.out.println(url);
	         if((url.indexOf("!userLogin.action")==-1)&&(url.indexOf("!user_logout.action")==-1)){//若访问后台资源 过滤到login    
	              
	              if(""==null){//转入管理员登陆页面    
	                arg1.setContentType("text/html;charset=utf-8");
	                arg1.getWriter().println("<script>alert('请输入用户名和密码!');window.parent.location='login.html'</script>");
	                   return;    
	              }    
	         } 
	        
	         try{
	           arg2.doFilter(arg0, arg1);  
	         }catch(Exception e){
	        	 e.printStackTrace();
	        	 System.out.println("====================exception===========================");
	         }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
}
