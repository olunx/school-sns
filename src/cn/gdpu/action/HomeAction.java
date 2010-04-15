package cn.gdpu.action;

import cn.gdpu.service.GroupService;
import cn.gdpu.service.StudentService;
import cn.gdpu.vo.Group;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Student;

@SuppressWarnings("serial")
public class HomeAction extends BaseAction {

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

}
