package cn.facebook.dao;

import java.util.List;

import cn.facebook.doman.NewsFeed;

public interface NewsFeedDao {
	public boolean create(NewsFeed feed);

	public NewsFeed query(long id);

	public List<NewsFeed> queryNews(long userId);

	public List<NewsFeed> friendNewsFeeds(String[] firendIds, int page, int size);
}
