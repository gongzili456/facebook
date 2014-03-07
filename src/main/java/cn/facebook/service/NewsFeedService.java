package cn.facebook.service;

import java.util.List;
import java.util.Map;

import cn.facebook.doman.NewsFeed;

public interface NewsFeedService {
	public boolean create(NewsFeed feed);

	public NewsFeed query(long id);

	public List<NewsFeed> queryNews(long userId);

	public List<Map<Object, Object>> friendNewsFeeds(long myId, int page,
			int size);
}
