package com.aim.filmstore.service.impl;

import com.aim.filmstore.dao.BaseDao;
import com.aim.filmstore.dao.impl.UserDaoImpl;
import com.aim.filmstore.domain.User;
import com.aim.filmstore.message.UserException;
import com.aim.filmstore.service.UserService;
import com.aim.filmstore.util.jdbc.JdbcUtils;

public class UserServiceImpl extends BaseServiceImpl implements UserService {
	private BaseDao userDao;

	public UserServiceImpl() {
		this.userDao = new UserDaoImpl();
		setDao(userDao);
	}

	public User login(User form) throws UserException {
	
        String hql="select * from staff where username=?";
        Object obj=form.getUsername();
		User user = (User) userDao.getEntity(hql, obj);
		if (user == null)
			throw new UserException("用户名不存在");
		if (!user.getPassword().equals(form.getPassword()))
			throw new UserException("用户名或密码不正确");
		return user;
	}
}
