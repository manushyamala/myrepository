package com.emaratech.client.usermanagement;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserManagementRequestFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		boolean shouldContinueNextFilter = true;
		if (request instanceof HttpServletRequest) {
			 String url = ((HttpServletRequest)request).getRequestURI();
			 System.out.println("The resource path "+url );
			 if(null != url && !url.contains("/login")  && !url.contains("/validatelogin") && !url.contains("/css/") && !url.contains("/js/")  && !url.contains("/img/")  && !url.contains("/fonts/")){
				 HttpSession session = ((HttpServletRequest)request).getSession(false);
				 if(null == session){
					 HttpServletResponse httpResponse = ((HttpServletResponse)response);
					 httpResponse.sendRedirect("/login");
					 shouldContinueNextFilter= false;
				 }
			 }
		}		
		if(shouldContinueNextFilter){
			chain.doFilter(request, response);
		}
		
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
