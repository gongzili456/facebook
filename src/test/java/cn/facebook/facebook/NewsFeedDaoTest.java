package cn.facebook.facebook;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cn.facebook.dao.NewsFeedDao;
import cn.facebook.doman.NewsFeed;

public class NewsFeedDaoTest {
	ApplicationContext ac;
	NewsFeedDao d;

	@Before
	public void init() {
		ac = new FileSystemXmlApplicationContext(
				"D:\\Workspaces\\eclipse\\wk2\\facebook\\src\\main\\webapp\\WEB-INF\\spring\\root-context.xml");
		System.out.println("ac: " + ac);

		d = ac.getBean(NewsFeedDao.class);
	}

	@Test
	public void creat() {
		NewsFeed nf = new NewsFeed();
		nf.setUserId(1l);
		nf.setTitle("测试标题");
		nf.setContent("内容测试，，，");

		nf.setCreated(new Date());
		nf.setModified(new Date());
		d.create(nf);
	}

	@Test
	public void query() {
		System.out.println(d.query(1));
		System.out.println(d.queryNews(1));
	}

	@Test
	public void string() {

		String[] ss = new String[] { "1", "2", "3", "4", "5", "6" };
		System.out.println(ss.toString());
	}
}
