package cn.facebook.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.facebook.dao.FriendDao;
import cn.facebook.dao.NewsFeedDao;
import cn.facebook.dao.UserDao;
import cn.facebook.doman.Friend;
import cn.facebook.doman.NewsFeed;
import cn.facebook.doman.User;
import cn.facebook.service.NewsFeedService;

@Component
public class NewsFeedServiceImpl implements NewsFeedService {

	@Autowired
	NewsFeedDao feedDao;

	@Autowired
	FriendDao friendDao;

	@Autowired
	UserDao userDao;

	@Override
	public boolean create(NewsFeed feed) {
		return feedDao.create(feed);
	}

	@Override
	public NewsFeed query(long id) {
		return feedDao.query(id);
	}

	@Override
	public List<NewsFeed> queryNews(long userId) {
		return feedDao.queryNews(userId);
	}

	public List<Map<Object, Object>> friendNewsFeeds(long myId, int page,
			int size) {
		// 查询所有好友的id，加上自己的id
		List<Map<Object, Object>> newsFeeds = new ArrayList<Map<Object, Object>>();
		List<Friend> friends = friendDao.friends(myId);
		String friendIds = "";
		long[] ids = null;
		if (friends != null) {
			ids = new long[friends.size() + 1];
			for (int i = 0; i < friends.size(); i++) {
				Friend f = friends.get(i);
				friendIds += f.getUserTwo() + ",";
				ids[i] = f.getUserTwo();
			}
		} else {
			ids = new long[1];
		}
		friendIds += myId;
		ids[ids.length - 1] = myId;
		List<NewsFeed> feeds = feedDao.friendNewsFeeds(friendIds.split(","),
				page, size);
		if (feeds != null) {
			List<User> users = userDao.users(ids);
			for (NewsFeed nf : feeds) {
				Map<Object, Object> map = bean2Map(nf);
				User author = selectUser(users, nf.getUserId());
				map.put("author", author.getName());
				map.put("portrait", author.getPortrait());
				newsFeeds.add(map);
			}
		}
		return newsFeeds;
	}

	@SuppressWarnings("unchecked")
	private Map<Object, Object> bean2Map(Object bean) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			map = BeanUtils.describe(bean);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return map;
	}

	private User selectUser(List<User> users, long userId) {
		for (User u : users) {
			if (u.getId() == userId) {
				return u;
			}
		}
		return null;
	}
}
