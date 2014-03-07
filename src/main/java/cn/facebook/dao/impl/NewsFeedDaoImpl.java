package cn.facebook.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cn.facebook.dao.NewsFeedDao;
import cn.facebook.doman.NewsFeed;

@Component
public class NewsFeedDaoImpl implements NewsFeedDao {

	@Autowired
	JdbcTemplate jt;

	public boolean create(NewsFeed feed) {
		String sql = "INSERT INTO newsfeed(user_id, title, content, created, modified, comment_count, shart_count, favour_count, STATUS) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] args = new Object[] { feed.getUserId(), feed.getTitle(),
				feed.getContent(), feed.getCreated(), feed.getModified(),
				feed.getCommentCount(), feed.getShareCount(),
				feed.getFavourCount(), feed.getStatus() };
		int rs = jt.update(sql, args);
		return rs > 0 ? true : false;
	}

	public NewsFeed query(long id) {
		String sql = "SELECT * FROM newsfeed WHERE id = ?";
		List<NewsFeed> feeds = jt.query(sql,
				new BeanPropertyRowMapper<NewsFeed>(NewsFeed.class), id);
		return feeds.size() > 0 ? feeds.get(0) : null;
	}

	public List<NewsFeed> queryNews(long userId) {
		String sql = "SELECT * FROM newsfeed WHERE user_id = ?";
		List<NewsFeed> feeds = jt.query(sql,
				new BeanPropertyRowMapper<NewsFeed>(NewsFeed.class), userId);
		return feeds.size() > 0 ? feeds : null;
	}

	public List<NewsFeed> friendNewsFeeds(String[] firendIds, int page, int size) {
		page = (page - 1) * size;
		String placeholder = "";
		Object[] args = new Object[firendIds.length + 2];
		for (int i = 1; i < firendIds.length; i++) {
			placeholder += "?,";
			args[i - 1] = firendIds[i];
		}
		placeholder += "?";
		args[firendIds.length - 1] = firendIds[0];
		args[args.length - 2] = page;
		args[args.length - 1] = size;
		String sql = "SELECT * FROM newsfeed WHERE user_id IN (" + placeholder
				+ ") ORDER BY created DESC LIMIT ?, ?";
		List<NewsFeed> feeds = jt.query(sql,
				new BeanPropertyRowMapper<NewsFeed>(NewsFeed.class), args);
		return feeds.size() > 0 ? feeds : null;
	}
}
