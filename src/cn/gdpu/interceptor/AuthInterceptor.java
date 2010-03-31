package cn.gdpu.interceptor;

import java.util.Map;

import cn.gdpu.service.PeopleService;
import cn.gdpu.service.StudentService;
import cn.gdpu.util.Log;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Student;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class AuthInterceptor extends AbstractInterceptor {

	private Map<String, Object> session;
	
	private PeopleService<People, Integer> peopleService;
	private StudentService<Student, Integer> studentService;
	
	private People people;
	
	private String username;
	private String password;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		if(session == null) {
			session = invocation.getInvocationContext().getSession();
		}
		
		Log.init(getClass()).info("session student: " + session.get("student"));
		Log.init(getClass()).info("session isAccess: " + session.get("isAccess"));
		if (session.get("isAccess") != "true") {
			this.username = (String) session.get("username");
			this.password = (String) session.get("password");
			Log.init(getClass()).info("session username: " + username);
			Log.init(getClass()).info("session password: " + password);
			
			if(username != null) {
				if(peopleService != null) {
					Log.init(getClass()).info("开始查询: ");
					people = peopleService.getPeopleByUsernameAndPwd(username, password);
					Log.init(getClass()).info("登录用户对象: " + people);
					if(people != null) {
						Student student = studentService.getStudentByUserName(username);
						String invoke = invocation.invoke();
						Log.init(getClass()).info("验证通过，跳转: " + invoke);
						invocation.getInvocationContext().getSession().put("student", student);
						invocation.getInvocationContext().getSession().put("isAccess", "true");
						return invoke;
					}
				}
			}
		}else {
			return invocation.invoke();
		}
		
		Log.init(getClass()).info("验证失败。");
		return Action.LOGIN;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
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

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
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

}
