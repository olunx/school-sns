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

import cn.gdpu.vo.Issue;
import cn.gdpu.vo.Topic;



public class IssueServiceTest{
	private static IssueService<Issue, Integer> issueService;
	private static TopicService<Topic, Integer> topicService;

	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring/applicationContext.xml");
			issueService = (IssueService<Issue, Integer>) ctx.getBean("issueService");
			topicService = (TopicService<Topic, Integer>) ctx.getBean("topicService");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void add() {
		Issue issue = new Issue();
		issue.setName("请问1+1=?");
		issue.setContent("我是小学生没毕业的，老师问我1+1=几，答不出没饭吃，冰天雪地360度全裸在线跪求答案，救命呀，大虾！！！");
		issue.setAirTime(new Date());
		Topic r1 = new Topic();
		r1.setContent("应该是等于2吧，我记得小学时做过，现在老了，忘了");
		Topic r2 = new Topic();
		r2.setContent("这题难度有点高，我也不会");
		Topic r3 = new Topic();
		r3.setContent("是等于2啦，这么简单");
		List<Topic> rps = new ArrayList<Topic>();
		rps.add(r1);
		rps.add(r2);
		rps.add(r3);
		issue.setReply(rps);
		issue.setAnswer(null);
		issueService.addEntity(issue);
	}
	
	@Test
	public void list() {
		List<Issue> iss = issueService.getAllEntity(Issue.class);
		Issue issue = iss.get(0);
		System.out.println("issue name = " + issue.getName());
		System.out.println("issue content = " + issue.getContent());
		System.out.println("issue reply size= " + issue.getReply().size());

		for(Topic rp : issue.getReply()){
			System.out.println("issue reply = " + rp.getId() + " : " + rp.getContent());
		}

		if(issue.getAnswer() != null){
			System.out.println("issue answer = " + issue.getAnswer().getContent());
		}
		else{
			System.out.println("还没有最佳答案");
		}
	}
	
	@Test
	public void answer() {
		List<Issue> iss = issueService.getAllEntity(Issue.class);
		Issue issue = iss.get(0);
		Topic answer = issue.getReply().get(0);
		issue.setAnswer(answer);
		issueService.updateEntity(issue);
		list();
	}
}
