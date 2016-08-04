package com.aim.filmstore.util.ResultSetConwert;

import java.lang.reflect.Field;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.commons.dbutils.ResultSetHandler;

public class BeanHandlers<T> implements ResultSetHandler<T> {

	private Class<?> clazz;

	public BeanHandlers(Class<?> clazz) {
		this.clazz = clazz;
	}

	@Override
	public T handle(ResultSet rs) throws SQLException {
		try {
			if (!rs.next()) {
				return null;
			}
			T bean = (T) clazz.newInstance();
			// 得到结果集元数据
			ResultSetMetaData metadata = rs.getMetaData();
			// 得到结果集中有几列数据
			int columnCount = metadata.getColumnCount();
			for (int i = 0; i < columnCount; i++) {
				// 得到每列的列名
				String coulmnName = metadata.getColumnName(i + 1);
				Field f = clazz.getDeclaredField(coulmnName);
				f.setAccessible(true);
				Object coulmnData = rs.getObject(i + 1);
				String classname = coulmnData.getClass().getName();
				if (classname.equalsIgnoreCase("[B")) {
					Blob blob = rs.getBlob(i + 1);
                    f.set(bean,blob);
				} else {
					// 反射出类上列名对应的属性
					f.set(bean, coulmnData);
				}
			}
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
