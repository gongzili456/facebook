package cn.facebook.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cn.facebook.dao.FriendDao;
import cn.facebook.doman.Friend;

@Component
public class FriendDaoImpl implements FriendDao {

	@Autowired
	JdbcTemplate jt;

	public List<Friend> friends(long userId) {
		String sql = "SELECT * FROM friends WHERE user_one = ? ";
		List<Friend> friends = jt.query(sql, new BeanPropertyRowMapper<Friend>(
				Friend.class), userId);
		return friends.size() > 0 ? friends : null;
	}

	public boolean create(long myId, long friendId) {
		String sql = "INSERT INTO friends(user_one, user_two) VALUES(?, ?)";
		int rs = jt.update(sql, myId, friendId);
		return rs > 0 ? true : false;
	}

	public boolean unfriend(long myId, long friendId) {
		String sql = "DELETE FROM friends WHERE user_one = ? AND user_two = ? ";
		int rs = jt.update(sql, myId, friendId);
		return rs > 0 ? true : false;
	}

}
