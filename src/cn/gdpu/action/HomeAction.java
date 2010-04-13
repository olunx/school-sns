package cn.gdpu.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.gdpu.service.FeedService;
import cn.gdpu.service.GroupService;
import cn.gdpu.service.StudentService;
import cn.gdpu.vo.Feed;
import cn.gdpu.vo.Group;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Student;

@SuppressWarnings("serial")
public class HomeAction extends BaseAction {

	private StudentService<Student, Integer> studentService;
	private GroupService<Group, Integer> groupService;
	private FeedService<Feed, Integer> feedService;
	private Student student;
	private List<Feed> feeds;

	public String home() {
		People user = (People) this.getSession().get("user");
		if (user instanceof Student) {
			student = studentService.getEntity(Student.class, user.getId());
			
			feeds = new ArrayList<Feed>();
			Set<Student> friends = student.getFriends();
			for (Student f : friends) {
				feeds.addAll(feedService.getEntity(Feed.class, "from Feed f where f.author = '" + f.getId() + "'"));
				//feeds.add();
			}
		}

		return "home";
	}

	public StudentService<Student, Integer> getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService<Student, Integer> studentService) {
		this.studentService = studentService;
	}

	public GroupService<Group, Integer> getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService<Group, Integer> groupService) {
		this.groupService = groupService;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public FeedService<Feed, Integer> getFeedService() {
		return feedService;
	}

	public void setFeedService(FeedService<Feed, Integer> feedService) {
		this.feedService = feedService;
	}

	public List<Feed> getFeeds() {
		return feeds;
	}

	public void setFeeds(List<Feed> feeds) {
		this.feeds = feeds;
	}

}
