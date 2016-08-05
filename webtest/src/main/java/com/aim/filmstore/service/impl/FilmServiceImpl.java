package com.aim.filmstore.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.aim.filmstore.dao.BaseDao;
import com.aim.filmstore.dao.impl.FilmDaoImpl;
import com.aim.filmstore.dao.impl.LanguageDaoImpl;
import com.aim.filmstore.domain.Film;
import com.aim.filmstore.domain.Language;
import com.aim.filmstore.domain.PageBean;
import com.aim.filmstore.service.FilmService;
import com.aim.filmstore.util.jdbc.JdbcUtils;

public class FilmServiceImpl extends BaseServiceImpl implements FilmService {
	private BaseDao filmDao;

	private BaseDao languageDao;

	public FilmServiceImpl() {
		this.filmDao = new FilmDaoImpl();
		this.languageDao = new LanguageDaoImpl();
		setDao(filmDao);
		setDao(languageDao);
	}

	public PageBean<Film> loadAllFile(int pc, int ps) {

		/*
		 * 1.设置PagerBean对象 2.设置pb的pc和ps 3.得到tr，设置给pb 4.得到beanList，设置给pb 5.返回pb
		 */
		PageBean<Film> pb = new PageBean<Film>();
		pb.setPc(pc);
		pb.setPs(ps);

		/*
		 * 得到tr
		 */
		String hql = "select count(*) from film";
		int tr = filmDao.getCount(hql);
		pb.setTr(tr);

		String hql1 = "SELECT * FROM (SELECT f.film_id,f.title,f.description,l.name FROM film f,LANGUAGE l WHERE f.language_id=l.language_id)f LIMIT ?,?";
		Object obj[]={(pc-1)*ps,ps};
		List<Film> beanList = filmDao.findEntityByHQL(hql1,obj);
		pb.setBeanList(beanList);
		return pb;
	}

	public void deleteFilm(int film_id) {
		try {
			JdbcUtils.beginTransaction();

			String hql = "DELETE FROM rental WHERE inventory_id IN (SELECT inventory_id FROM inventory WHERE film_id=?)";
			filmDao.deleteEntity(hql, film_id);

			String hql1 = "DELETE FROM inventory WHERE film_id=?";
			filmDao.deleteEntity(hql1, film_id);

			String hql2 = "DELETE FROM film_actor WHERE film_id=?";
			filmDao.deleteEntity(hql2, film_id);

			String hql3 = "DELETE FROM film_category WHERE film_id=?";
			filmDao.deleteEntity(hql3, film_id);

			String hql4 = "DELETE FROM film WHERE film_id=?";
			filmDao.deleteEntity(hql4, film_id);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public List<Language> findAllLanguage() {
		String hql = "SELECT *  FROM LANGUAGE";
		return languageDao.findEntityByHQL(hql);
	}

	public void addFilm(Film film) {
		String hql = "INSERT INTO film(title,description,language_id) VALUES(?,?,?)";
		Object obj[] = { film.getTitle(), film.getDescription(),
				film.getLanguage_id() };
		filmDao.saveEntity(hql, obj);
	}

	public Film findFilm(int id) {
		String hql = "select f.film_id,f.title,f.description,l.name,l.language_id from film f,LANGUAGE l where f.language_id=l.language_id and film_id=?";
		Object obj = id;
		return (Film) filmDao.getEntity(hql, obj);
	}

	public void upateFilm(Film film) {
		String hql = "UPDATE film f,LANGUAGE l SET f.title=?,f.description=?,l.name=? WHERE f.language_id=l.language_id and f.film_id=?";
		Object obj[] = { film.getTitle(), film.getDescription(),
				film.getName(), film.getFilm_id() };
		filmDao.updateEntity(hql, obj);
	}
}
