package cn.facebook.facebook;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cn.facebook.service.NewsFeedService;

public class NewsFeedServiceTest {
	ApplicationContext ac;
	NewsFeedService s;

	@Before
	public void init() {
		ac = new FileSystemXmlApplicationContext(
				"D:\\Workspaces\\eclipse\\wk2\\facebook\\src\\main\\webapp\\WEB-INF\\spring\\root-context.xml");
		System.out.println("ac: " + ac);

		s = ac.getBean(NewsFeedService.class);
	}

}
