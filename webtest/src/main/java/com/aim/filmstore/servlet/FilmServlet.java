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
import com.aim.filmstore.domain.PageBean;
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
		
		/*
		 * 1.获取页面传递的pc
		 * 2.给定ps的值
		 * 3.使用pc和ps调用service方法，得到PageBean,保存到request域
		 * 4.转发到list.jsp
		 */
		int pc=getPc(request);
		int ps=10;
		PageBean<Film> pb=filmService.loadAllFile(pc,ps);
		
		//设置url
		
	    pb.setUrl(getUrl(request));
		request.setAttribute("pb",pb);
		
		return "f:/adminjsps/admin/category/list.jsp";
	}

	public String deleteFilm(HttpServletRequest request,
			HttpServletResponse response) {

		int film_id = Integer.parseInt(request.getParameter("fid"));

		filmService.deleteFilm(film_id);
		
		findAllFilm(request,response);
		
		return "f:/adminjsps/admin/category/list.jsp";
	}

	public String findAllLanguage(HttpServletRequest request,
			HttpServletResponse response) {
		
		request.setAttribute("languageList",filmService.findAllLanguage());
		return "f:/adminjsps/admin/category/add.jsp";
	}
	
	

	/*
	 * 截取url
	 *
	 */
	private String getUrl(HttpServletRequest request){
		String contextPath=request.getContextPath();//获取项目名
		String servletPath=request.getServletPath();//获取servletPath
		String queryString=request.getQueryString();//获取问号后的参数
		//判断参数后是否包含&pc=，包含便截取掉
		if(queryString.contains("&pc="))
		{
			int index=queryString.indexOf("&pc=");
		    queryString=queryString.substring(0,index);
		}
		return contextPath+servletPath+"?"+queryString;
	}
	
	
	/**
	 * 获取pc
	 * @param request
	 * @return
	 */
	private int getPc(HttpServletRequest request){
		/*1.得到pc
		 *   如果pc参数不存在，说明pc=1
		 *   如果pc存在，转换为int
		 * 
		 */
		 String value=request.getParameter("pc");
		 if(value==null||value.trim().isEmpty())
		 {
			 return 1;
		 }
		 return Integer.parseInt(value);
	}
	

	public String addFilm(HttpServletRequest request,
			HttpServletResponse response) {
		Film film = CommonUtils.toBean(request.getParameterMap(), Film.class);
		filmService.addFilm(film);
		findAllFilm(request,response);
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
		findAllFilm(request,response);
		return "f:/adminjsps/admin/category/list.jsp";
	}

}
