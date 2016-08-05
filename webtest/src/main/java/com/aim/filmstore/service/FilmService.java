package com.aim.filmstore.service;

import java.util.List;

import com.aim.filmstore.domain.Film;
import com.aim.filmstore.domain.Language;
import com.aim.filmstore.domain.PageBean;

public interface FilmService {
	public PageBean<Film> loadAllFile(int pc,int ps);
	public void deleteFilm(int film_id);
	public List<Language> findAllLanguage();
	public void addFilm(Film film);
	public Film findFilm(int id);
	public void upateFilm(Film film);
}
