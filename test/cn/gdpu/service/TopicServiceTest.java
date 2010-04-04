package cn.gdpu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.gdpu.vo.*;

public class TopicServiceTest {
	private static TopicService<Topic, Integer> topicService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring/applicationContext.xml");
			System.out.println("ctx: " + ctx);
			topicService = (TopicService<Topic, Integer>) ctx.getBean("topicService");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void add() {

		Topic t = new Topic();
		t.setTitle("我是主题");
		t.setIstopic(true);
		t.setTime(new Date());

		Topic t2 = new Topic();
		t2.setTitle("我是帖子1");
		t2.setIstopic(false);
		t2.setTime(new Date());
		topicService.addEntity(t2);

		Topic t3 = new Topic();
		t3.setTitle("我是帖子2");
		t3.setIstopic(false);
		t3.setTime(new Date());
		topicService.addEntity(t3);

		List<Topic> post = new ArrayList<Topic>();
		post.add(t2);
		post.add(t3);

		t.setPost(post);
		topicService.addEntity(t);

	}

	@Test
	public void getAllStu() {
		List<Topic> topics = topicService.getAllEntity(Topic.class);
		for (Topic t : topics)
			System.out.println(t.getTitle());

		Topic t = topicService.getEntity(Topic.class, 3);
		System.out.println(t.getTitle());
		
		List<Topic> ts = topicService.getAllTopic();
		for (Topic t2 : ts)
			System.out.println(t2.getTitle());
	}
}
