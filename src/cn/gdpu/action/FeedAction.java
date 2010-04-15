package cn.gdpu.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.gdpu.service.FeedService;
import cn.gdpu.service.StudentService;
import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;
import cn.gdpu.vo.Feed;
import cn.gdpu.vo.Goods;
import cn.gdpu.vo.Group;
import cn.gdpu.vo.Issue;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Student;
import cn.gdpu.vo.Topic;
import cn.gdpu.vo.Vote;

@SuppressWarnings("serial")
public class FeedAction extends BaseAction {

	private static FeedService<Feed, Integer> service;
	private FeedService<Feed, Integer> feedService;
	private static FeedAction feedAction;
	private StudentService<Student, Integer> studentService;
	private Student student;
	private PageBean pageBean;
	private int page;

	public final static String ADD_TWITTER ="add_twitter";
	public final static String REPLY ="reply_topic";
	public final static String ADD_GROUP ="add_group";
	public final static String JOIN_GROUP ="join_group";
	public final static String Quit_GROUP ="quit_group";
	public final static String ADD_VOTE ="add_vote";
	public final static String JOIN_VOTE ="join_vote";
	public final static String ADD_GOODS ="add_goods";
	public final static String JOIN_GOODS ="join_goods";
	public final static String ADD_ISSUE ="add_issue";
	public final static String JOIN_ISSUE ="join_issue";
	
	@SuppressWarnings("unchecked")
	public static FeedAction init() {
		if (feedAction == null) {
			feedAction = new FeedAction();
		}
		if (service == null) {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring/applicationContext.xml");
			service = (FeedService<Feed, Integer>) ctx.getBean("feedService");
		}

		return feedAction;
	}

	public String add(Topic topic, String type) {

		Log.init(getClass()).info("FeedAction add");
		Log.init(getClass()).info("FeedService " + feedService);

		Feed feed = new Feed();
		feed.setAuthor(topic.getAuthor());
		feed.setType(type);
		feed.setMessage(topic.getContent());
		feed.setTime(topic.getTime());
		service.addEntity(feed);

		return super.add();
	}

	public String add(Group group, People author, String type) {
		
		Feed feed = new Feed();
		feed.setAuthor(author);
		feed.setType(type);
		feed.setMessage(group.getName());
		feed.setTime(new Date());
		service.addEntity(feed);
		
		return super.add();
	}
	
	public String add(Student student, String type) {
		
		return super.add();
	}
	
	public String add(Vote vote, String type) {
		
		Feed feed = new Feed();
		feed.setAuthor(vote.getAuthor());
		feed.setType(type);
		feed.setMessage(vote.getTitle());
		feed.setTime(new Date());
		service.addEntity(feed);
		
		return super.add();
	}
	
	public String add(Goods goods, String type) {
		
		Feed feed = new Feed();
		feed.setAuthor(goods.getOwner());
		feed.setType(type);
		feed.setMessage(goods.getName());
		feed.setTime(new Date());
		service.addEntity(feed);
		
		return super.add();
	}
	
	public String add(Issue issue, String type) {
		Feed feed = new Feed();
		feed.setAuthor(issue.getOwner());
		feed.setType(type);
		feed.setMessage(issue.getName());
		feed.setTime(new Date());
		service.addEntity(feed);
		
		return super.add();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String list() {
		People user = (People) this.getSession().get("user");
		if (user instanceof Student) {
			student = studentService.getEntity(Student.class, user.getId());

			List<Feed> feeds = new ArrayList<Feed>();
			Set<Student> friends = student.getFriends();
			for (Student f : friends) {
				// feeds.addAll(feedService.getEntity(Feed.class,
				// "from Feed f where f.author = '" + f.getId() + "'"));
				pageBean = feedService.queryForPage("from Feed f where f.author = '" + f.getId() + "'", 10, page);
				feeds.addAll(pageBean.getList());
				//feeds.addAll(feedService.queryForPage("from Feed f where f.author = '" + f.getId() + "'", 10, page).getList());
			}
			if (pageBean.getList().isEmpty()) {
				pageBean.setList(null);
			}else {
				pageBean.setList(feeds);
			}
			
		}

		return super.list();
	}

	public FeedService<Feed, Integer> getFeedService() {
		return feedService;
	}

	public void setFeedService(FeedService<Feed, Integer> feedService) {
		this.feedService = feedService;
	}

	public StudentService<Student, Integer> getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService<Student, Integer> studentService) {
		this.studentService = studentService;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
