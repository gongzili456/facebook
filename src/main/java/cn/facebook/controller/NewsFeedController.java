package cn.facebook.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import cn.facebook.constant.UserConstants;
import cn.facebook.doman.NewsFeed;
import cn.facebook.doman.User;
import cn.facebook.service.NewsFeedService;

@Controller
@RequestMapping(value = "/feeds")
public class NewsFeedController {
	@Autowired
	NewsFeedService feedService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public String create(@RequestBody NewsFeed feed, HttpSession session) {
		User user = (User) session.getAttribute(UserConstants.SESSION_USER);
		feed.setUserId(user.getId());
		Date create = new Date();
		feed.setCreated(create);
		feed.setModified(create);
		boolean b = feedService.create(feed);
		JSONObject json = new JSONObject();
		if (b) {
			json.put("ok", "发布成功！");
		} else {
			json.put("error", "发布失败！");
		}
		return json.toString();
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String friendNewsFeeds(
			@RequestParam(defaultValue = "1", required = false) int page,
			@RequestParam(defaultValue = "20", required = false) int size,
			HttpSession session) {
		User user = (User) session.getAttribute(UserConstants.SESSION_USER);
		List<Map<Object, Object>> feeds = feedService.friendNewsFeeds(
				user.getId(), page, size);
		if (feeds == null) {
			return "[]";
		}
		return JSONArray.fromObject(feeds).toString();
	}
}
