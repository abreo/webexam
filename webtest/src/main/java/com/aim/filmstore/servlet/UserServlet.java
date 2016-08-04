package com.aim.filmstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.aim.filmstore.domain.User;
import com.aim.filmstore.message.UserException;
import com.aim.filmstore.service.UserService;
import com.aim.filmstore.service.impl.UserServiceImpl;
import com.aim.filmstore.util.commons.CommonUtils;
import com.aim.filmstore.util.servlet.BaseServlet;



public class UserServlet extends BaseServlet{
	
	private UserService userService=new UserServiceImpl();
	
	public String login(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
		
    	
    	User form =CommonUtils.toBean(request.getParameterMap(),User.class);
    	try {
			User user=userService.login(form);
			String name=user.getUsername();
			System.out.println(name);
			request.getSession().setAttribute("session_user",user);
			return "r:/adminjsps/admin/index.jsp";
		} catch (UserException e) {
			request.setAttribute("msg",e.getMessage());
			request.setAttribute("form",form);
			return "f:/adminjsps/login.jsp";
		}
    }
    
  /**
   
   * @param request
   * @param response
   * @return
   * @throws ServletException
   * @throws IOException
   */
    public String quit(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	request.getSession().invalidate();
    	return "r:/index.jsp";
    }

}
