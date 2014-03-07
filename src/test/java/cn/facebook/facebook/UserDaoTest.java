package cn.facebook.facebook;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cn.facebook.dao.UserDao;
import cn.facebook.doman.User;

public class UserDaoTest {

	ApplicationContext ac;
	UserDao d;

	@Before
	public void init() {
		ac = new FileSystemXmlApplicationContext(
				"D:\\Workspaces\\eclipse\\wk2\\facebook\\src\\main\\webapp\\WEB-INF\\spring\\root-context.xml");
		System.out.println("ac: " + ac);

		d = ac.getBean(cn.facebook.dao.UserDao.class);
	}

	@Test
	public void creatUser() {

		User user = new User();

		user.setName("Tom");
		user.setEmail("abc@123.com");
		user.setPassword("123456");
		user.setSex(1);
		user.setBirthday(new Date());

		d.creatUser(user);

	}

	@Test
	public void checkEmail() {
		boolean b = d.checkEmail("tom1@163.com");
		System.out.println(b);
	}

	@Test
	public void queryUser() {
		User u = d.queryUser("tom@163.com");
		System.out.println(u);
	}
}
