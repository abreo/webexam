package com.aim.filmstore.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aim.filmstore.domain.Film;
import com.aim.filmstore.domain.User;
import com.aim.filmstore.service.FilmService;
import com.aim.filmstore.service.impl.FilmServiceImpl;
import com.aim.filmstore.util.commons.CommonUtils;
import com.aim.filmstore.util.servlet.BaseServlet;

public class FilmServlet extends BaseServlet{
      private FilmService filmService=new FilmServiceImpl();
      
      public String findAllFilm(HttpServletRequest request, HttpServletResponse response){
    	  request.setAttribute("filmList",filmService.loadAllFile());
    	  return "f:/adminjsps/admin/category/list.jsp";
      }
      
      public String deleteFilm(HttpServletRequest request, HttpServletResponse response){
    	  
    	  int film_id=Integer.parseInt(request.getParameter("fid"));
    	 
    	  filmService.deleteFilm(film_id);
    	  request.setAttribute("filmList",filmService.loadAllFile());
    	  return "f:/adminjsps/admin/category/list.jsp";
      }
      
      public String findAllLanguage(HttpServletRequest request, HttpServletResponse response){
    	   request.setAttribute("languageList",filmService.findAllLanguage());  
    	   return "f:/adminjsps/admin/category/add.jsp";
      }
      
      public String addFilm(HttpServletRequest request, HttpServletResponse response){
    	  Film film =CommonUtils.toBean(request.getParameterMap(),Film.class); 
    	  filmService.addFilm(film);
    	  request.setAttribute("filmList",filmService.loadAllFile());
    	  return "f:/adminjsps/admin/category/list.jsp";
      }
      
      public String editerFilm(HttpServletRequest request, HttpServletResponse response){
    	  int film_id=Integer.parseInt(request.getParameter("fid"));
    	  request.setAttribute("film",filmService.findFilm(film_id));
    	  return "f:/adminjsps/admin/category/mod.jsp";
      }
      
      public String updateFilm(HttpServletRequest request, HttpServletResponse response){
    	  Film film =CommonUtils.toBean(request.getParameterMap(),Film.class);
    	  filmService.upateFilm(film);
    	  request.setAttribute("filmList",filmService.loadAllFile());
    	  return "f:/adminjsps/admin/category/list.jsp";
      }
      
}
