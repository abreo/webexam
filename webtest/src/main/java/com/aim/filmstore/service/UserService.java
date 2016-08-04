package com.aim.filmstore.service;

import com.aim.filmstore.domain.User;
import com.aim.filmstore.message.UserException;

public interface UserService {
	public User login(User form) throws UserException;
}
