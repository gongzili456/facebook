package cn.facebook.dao;

import java.util.List;

import cn.facebook.doman.Friend;

public interface FriendDao {
	public List<Friend> friends(long userId);

	public boolean create(long myId, long friendId);

	public boolean unfriend(long myId, long friendId);
}
