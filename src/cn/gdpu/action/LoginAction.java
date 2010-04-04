package cn.gdpu.action;

import cn.gdpu.service.PeopleService;
import cn.gdpu.service.StudentService;
import cn.gdpu.util.Log;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Student;

@SuppressWarnings("serial")
public class LoginAction extends BaseAction {

	private String username;
	private String password;
	private PeopleService<People, Integer> peopleService;
	private StudentService<Student, Integer> studentService;

	public String auth() {

		Log.init(getClass()).info("username: " + username);
		Log.init(getClass()).info("password: " + password);

		if (username != null && password != null) {
			if (peopleService != null) {
				Log.init(getClass()).info("开始查询: ");
				People people = peopleService.getPeopleByUsernameAndPwd(username, password);
				Log.init(getClass()).info("登录用户对象: " + people);
				if (people != null) {
					Student student = studentService.getStudentByUserName(username);
					Log.init(getClass()).info("验证通过，跳转: ");
					this.getSession().put("student", student);
					this.getSession().put("isAccess", "true");
					return super.SUCCESS;
				}
			}
		}

		// 绕过验证
		 this.getSession().put("isAccess", "true");
		 return super.SUCCESS;
		//return super.INDEX;
	}

	public String go() {
		return "goLogin";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PeopleService<People, Integer> getPeopleService() {
		return peopleService;
	}

	public void setPeopleService(PeopleService<People, Integer> peopleService) {
		this.peopleService = peopleService;
	}

	public StudentService<Student, Integer> getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService<Student, Integer> studentService) {
		this.studentService = studentService;
	}
}
