package com.aim.filmstore.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.aim.filmstore.dao.BaseDao;
import com.aim.filmstore.domain.User;
import com.aim.filmstore.util.ResultSetConwert.BeanHandlers;
import com.aim.filmstore.util.jdbc.TxQueryRunner;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	private Class<T> clazz;
	private QueryRunner qr = new TxQueryRunner();
	private Map<String, Field> fieldsMap = null;

	public BaseDaoImpl() {

		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];

		fieldsMap = new HashMap<String, Field>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			String name = f.getName();
			System.out.println(f.getName());
			fieldsMap.put(f.getName(), f);
		}
	}

	//写操作
	public void saveEntity(String hql, Object... objects) {
		try {
			qr.update(hql, objects);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateEntity(String hql, Object... objects) {
		try {
			qr.update(hql, objects);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteEntity(String hql, Object... objects) {
		try {
			qr.update(hql, objects);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void batchEntityByHQL(String hql, Object[][] objects) {
		try {
			qr.batch(hql, objects);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//读操作
	@Override
	public T getEntity(String hql, Object... objects) {
		try {

			return (T) qr.query(hql, new BeanHandlers<T>(clazz), objects);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<T> findEntityByHQL(String hql, Object... objects) {
		List<T> tab = null;
		try {
			tab = qr.query(hql, new BeanListHandler<T>(clazz), objects);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tab;
	}

	public int getCount(String hql) {
		Number tab=0;
		try {
			tab= (Number) qr.query(hql,new ScalarHandler());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int tab1=tab.intValue();
		return tab1;
	}

}
