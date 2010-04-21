package cn.gdpu.action;

import com.opensymphony.xwork2.ActionContext;

import cn.gdpu.service.GroupService;
import cn.gdpu.service.PeopleService;
import cn.gdpu.service.StudentService;
import cn.gdpu.util.Log;
import cn.gdpu.vo.Group;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Student;

@SuppressWarnings("serial")
public class HomeAction extends BaseAction {

	private int id;
	private PeopleService<People, Integer> peopleService;
	private StudentService<Student, Integer> studentService;
	private GroupService<Group, Integer> groupService;
	private Student student;

	public String home() {
		People user = (People) this.getSession().get("user");
		if (user instanceof Student) {
			student = studentService.getEntity(Student.class, user.getId());
		}

		return "home";
	}
	
	public String center() {
		return "center";
	}

	@Override
	public String execute() throws Exception {
		
		String username = ActionContext.getContext().getName();
		
		Log.init(getClass()).info("TwitterAction username: " + username);
		
		People people = peopleService.getPeopleByUsername(username);
		
		this.id = people.getId();
		
		Log.init(getClass()).info("TwitterAction id: " + id);
		
		return super.SUCCESS;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PeopleService<People, Integer> getPeopleService() {
		return peopleService;
	}

	public void setPeopleService(PeopleService<People, Integer> peopleService) {
		this.peopleService = peopleService;
	}

}
