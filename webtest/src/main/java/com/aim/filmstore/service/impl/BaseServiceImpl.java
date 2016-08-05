package com.aim.filmstore.service.impl;

import java.util.List;

import com.aim.filmstore.dao.BaseDao;
import com.aim.filmstore.service.BaseService;

public class BaseServiceImpl<T> implements BaseService<T> {
	private BaseDao baseDao;

	public void setDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public BaseServiceImpl() {
	}

	@Override
	public void saveEntity(String hql, Object... objects) {
         baseDao.saveEntity(hql, objects);
	}

	@Override
	public void updateEntity(String hql, Object... objects) {
         baseDao.updateEntity(hql, objects);
	}

	@Override
	public void deleteEntity(String hql, Object... objects) {
         baseDao.deleteEntity(hql, objects);
	}

	@Override
	public void batchEntityByHQL(String hql, Object[][] objects) {
          baseDao.batchEntityByHQL(hql, objects);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getEntity(String hql, Object... objects) {
		return (T) baseDao.getEntity(hql, objects);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findEntityByHQL(String hql, Object... objects) {
		return baseDao.findEntityByHQL(hql, objects);
	}

	@Override
	public int getCount(String hql) {
		return baseDao.getCount(hql);
	}

}
