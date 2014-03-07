package cn.facebook.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.facebook.dao.UserDao;
import cn.facebook.doman.User;
import cn.facebook.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public boolean creatUser(User user) {
		return userDao.creatUser(user);
	}

	@Override
	public boolean checkEmail(String email) {
		return userDao.checkEmail(email);
	}

	@Override
	public User queryUser(String email) {
		return userDao.queryUser(email);
	}

	@Override
	public List<User> users(long... ids) {
		return userDao.users(ids);
	}

}
