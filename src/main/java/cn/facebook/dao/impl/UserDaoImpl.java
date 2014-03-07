package cn.facebook.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cn.facebook.dao.UserDao;
import cn.facebook.doman.User;

@Component
public class UserDaoImpl implements UserDao {

	@Autowired
	JdbcTemplate jt;

	public boolean creatUser(User user) {
		String sql = "INSERT INTO users(NAME, email, PASSWORD, birthday, sex) VALUES(?, ?, ?, ?, ?)";
		Object[] args = new Object[] { user.getName(), user.getEmail(),
				user.getPassword(), user.getBirthday(), user.getSex() };
		int row = jt.update(sql, args);
		return row > 0 ? true : false;
	}

	public boolean checkEmail(String email) {
		String sql = "SELECT id FROM users WHERE email = ?";
		List<Long> l = jt.queryForList(sql, Long.class, email);
		return l.size() > 0 ? true : false;
	}

	public User queryUser(String email) {
		String sql = "SELECT * FROM users WHERE email = ?";

		List<User> users = jt.query(sql, new BeanPropertyRowMapper<User>(
				User.class), email);
		return users.size() > 0 ? users.get(0) : null;
	}

	public List<User> users(long... ids) {
		String placeholder = "";
		Object[] args = new Object[ids.length];
		for (int i = 1; i <= ids.length; i++) {
			args[i - 1] = ids[i - 1];
			if (i != ids.length) {
				placeholder += "?,";
			} else {
				placeholder += "?";
			}
		}
		String sql = "SELECT * FROM users WHERE id IN (" + placeholder + ") ";
		List<User> users = jt.query(sql, new BeanPropertyRowMapper<User>(
				User.class), args);
		return users.size() > 0 ? users : null;
	}
}
