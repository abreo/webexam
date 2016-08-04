package com.aim.filmstore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.aim.filmstore.domain.User;

public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String method = httpRequest.getParameter("method");

		User user = (User) httpRequest.getSession()
				.getAttribute("session_user");
		if (user != null) {
			chain.doFilter(request, response);
		}else if(method!=null&&method.equals("login")){
			chain.doFilter(request, response);
		}else {
			httpRequest.setAttribute("msg", "你还未登陆");
			httpRequest.getRequestDispatcher("/adminjsps/login.jsp").forward(
					httpRequest, response);
		}

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
