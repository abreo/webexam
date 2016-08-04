package com.aim.filmstore.servlet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aim.filmstore.domain.Film;
import com.aim.filmstore.domain.User;
import com.aim.filmstore.service.FilmService;
import com.aim.filmstore.service.impl.FilmServiceImpl;
import com.aim.filmstore.util.commons.CommonUtils;
import com.aim.filmstore.util.servlet.BaseServlet;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FilmServlet extends BaseServlet {
	private FilmService filmService = new FilmServiceImpl();

	public String findAllFilm(HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("filmList", filmService.loadAllFile());
		return "f:/adminjsps/admin/category/list.jsp";
	}

	public String deleteFilm(HttpServletRequest request,
			HttpServletResponse response) {

		int film_id = Integer.parseInt(request.getParameter("fid"));

		filmService.deleteFilm(film_id);
		request.setAttribute("filmList", filmService.loadAllFile());
		return "f:/adminjsps/admin/category/list.jsp";
	}

	public String findAllLanguage(HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("languageList", filmService.findAllLanguage());
		return "f:/adminjsps/admin/category/add.jsp";
	}

	public String addFilm(HttpServletRequest request,
			HttpServletResponse response) {
		Film film = CommonUtils.toBean(request.getParameterMap(), Film.class);
		filmService.addFilm(film);
		request.setAttribute("filmList", filmService.loadAllFile());
		return "f:/adminjsps/admin/category/list.jsp";
	}

	public String editerFilm(HttpServletRequest request,
			HttpServletResponse response) {
		int film_id = Integer.parseInt(request.getParameter("fid"));
	    
		String path=this.getServletConfig().getServletContext().getRealPath("/");
		
		// request.setAttribute("film", filmService.findFilm(film_id));
		try {
			Configuration cfg = new Configuration();

			cfg.setDirectoryForTemplateLoading(new File(path+"template"));

			Map<String, Object> root = new HashMap<String, Object>();

			root.put("film", filmService.findFilm(film_id));

			Template t1 = cfg.getTemplate("mod.ftl");
            File file=new File(path+"adminjsps/admin/category","mod.html");
            if(!file.exists()){
            	file.createNewFile();
            }
			FileOutputStream outstream=new FileOutputStream(file);
			BufferedOutputStream buffout=new BufferedOutputStream(outstream);
			
			Writer out = new OutputStreamWriter(buffout);
			t1.process(root, out);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "r:/adminjsps/admin/category/mod.html";
	}

	public String updateFilm(HttpServletRequest request,
			HttpServletResponse response) {
		Film film = CommonUtils.toBean(request.getParameterMap(), Film.class);
		filmService.upateFilm(film);
		request.setAttribute("filmList", filmService.loadAllFile());

		return "f:/adminjsps/admin/category/list.jsp";
	}

}
