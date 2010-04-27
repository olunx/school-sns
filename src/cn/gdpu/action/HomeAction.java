package cn.gdpu.action;

import cn.gdpu.service.PeopleService;
import cn.gdpu.service.StudentService;
import cn.gdpu.util.PageBean;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Student;

@SuppressWarnings("serial")
public class HomeAction extends BaseAction {

	private int id;
	private PeopleService<People, Integer> peopleService;
	private StudentService<Student, Integer> studentService;
	private Student student;
	private PageBean pageBean;
	private int page;

	public String home() {
		//重新设置session对象
		People user = (People) this.getSession().get("user");
		if (user instanceof Student) {
			student = studentService.getEntity(Student.class, user.getId());
		}
		
		return "home";
	}
	
	public String center() {
		home();
		return "center";
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
