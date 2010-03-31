package cn.gdpu.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.gdpu.vo.*;



public class PeopleServiceTest{
	private static PeopleService<People, Integer> peopleService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring/applicationContext.xml");
			System.out.println("ctx: " + ctx);
			peopleService = (PeopleService<People, Integer>) ctx.getBean("peopleService");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getAllPeople() {
		List<People> list = peopleService.getAllEntity(People.class);
		for(People p : list) {
			System.out.println(p.getUsername());
		}
		System.out.println(peopleService.getPeopleByUsernameAndPwd("3c", "3c").getQq());
//		peopleService.getEntity(People.class, "from People");
	}
}
