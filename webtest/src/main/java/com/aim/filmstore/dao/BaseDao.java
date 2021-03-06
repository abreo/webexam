package com.aim.filmstore.dao;

import java.util.List;

public interface BaseDao<T> {
	// 写操作
	public void saveEntity(String hql, Object... objects);

	public void updateEntity(String hql, Object... objects);

	public void deleteEntity(String hql, Object... objects);

	public void batchEntityByHQL(String hql, Object[][] objects);

	// 读操作
	public T getEntity(String hql, Object... objects);

	public List<T> findEntityByHQL(String hql, Object... objects);

	public int getCount(String hql);

}
