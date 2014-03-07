package cn.facebook.dao;

import java.util.List;

import cn.facebook.doman.User;

public interface UserDao {
	public boolean creatUser(User user);

	public boolean checkEmail(String email);

	public User queryUser(String email);

	public List<User> users(long... ids);
}
