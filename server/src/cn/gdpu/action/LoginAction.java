package cn.gdpu.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import cn.gdpu.service.AdminService;
import cn.gdpu.service.PeopleService;
import cn.gdpu.service.ProvinceService;
import cn.gdpu.service.SchoolService;
import cn.gdpu.service.StudentService;
import cn.gdpu.service.TeacherService;
import cn.gdpu.util.Log;
import cn.gdpu.vo.Admin;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Province;
import cn.gdpu.vo.School;
import cn.gdpu.vo.Student;
import cn.gdpu.vo.Teacher;

@SuppressWarnings("serial")
public class LoginAction extends BaseAction {

	private String username;
	private String password;
	private PeopleService<People, Integer> peopleService;
	private StudentService<Student, Integer> studentService;
	private TeacherService<Teacher, Integer> teacherService;
	private AdminService<Admin, Integer> adminService;
	private SchoolService<School, Integer> schoolService;
	private ProvinceService<Province, Integer> provinceService;
	private People user;
	private String repassword;
	private String birthday;
	private String referUrl = "";// Cookies登陆后跳转的地址
	private int schoolId;
	private int protocol;
	HttpServletResponse hsresponse = ServletActionContext.getResponse();
	HttpServletRequest hsrequest = ServletActionContext.getRequest();

	public String auth() {

		Log.init(getClass()).info("登陆 username: " + username);
		Log.init(getClass()).info("登陆 password: " + password);

		if (username != null && password != null && this.getSession().get("isAccess")==null) {
			if (peopleService != null) {
				Log.init(getClass()).info("开始查询: ");
				People people = peopleService.getPeopleByUsernameAndPwd(username, password);
				Log.init(getClass()).info("登录用户对象: " + people);
				if (people != null) {
					Log.init(getClass()).info("验证通过，跳转: ");
					this.getSession().put("user", people);
					int peopleId = people.getId();
					// 写入cookies
					int maxAge = 60 * 60 * 24 * 30;
					Cookie cookieUsername = new Cookie("username", username);
					cookieUsername.setMaxAge(maxAge);
					Cookie cookiePassword = new Cookie("password", password);
					cookiePassword.setMaxAge(maxAge);
					hsresponse.addCookie(cookieUsername);
					hsresponse.addCookie(cookiePassword);

					Student student = studentService.getEntity(Student.class, peopleId);
					if (student != null) {
						this.getSession().put("student", student);
					} else {
						Admin admin = adminService.getEntity(Admin.class, peopleId);
						if (admin != null) {
							this.getSession().put("admin", admin);
						} else {
							Teacher teacher = teacherService.getEntity(Teacher.class, peopleId);
							if (teacher != null) {
								this.getSession().put("teacher", teacher);
							}
						}
					}
					if(people.getLastlogin()!= null ){		//根据登陆增加活跃度，每日只计算一次
						String toDay = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						String lastLogin = new SimpleDateFormat("yyyy-MM-dd").format(people.getLastlogin());
						if(!lastLogin.equals(toDay)){
							people.setActivity(people.getActivity() + 1);
						}
					}
					else{
						people.setActivity(1);
					}
					people.setLastlogin(new Date());
					System.out.println("------------------" + people.getActivity());
					peopleService.updateEntity(people);
					
					this.getSession().put("isAccess", "true");
					if (referUrl != null && !referUrl.equals(""))
						return "redirect";
					return super.SUCCESS;
				}
			} else {
				// 登陆不成功
				cookieClean();// 清除cookies
			}
		}

		Object access = this.getSession().get("isAccess");
		if (access!=null &&!access.equals("")){
			return super.SUCCESS;
		}
		// 绕过验证
		// this.getSession().put("isAccess", "true");
		// return super.SUCCESS;
		return super.INDEX;
	}

	@SkipValidation
	private void cookieClean() {
		// 清除cookies
		Cookie[] cookies = hsrequest.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie c : cookies) {
				if (c.getName().equals("username") || c.getName().equals("password")) {
					c.setValue("");
					c.setMaxAge(0);
					hsresponse.addCookie(c);
				}
			}
		}
	}

	@SkipValidation
	public String cookieAuth() {
		// 检查cookies
		Cookie[] cookies = hsrequest.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("username"))
					username = c.getValue();
				if (c.getName().equals("password"))
					password = c.getValue();
			}
		}
		referUrl = (String) this.getSession().get("referurl");
		this.getSession().remove("referurl");
		return auth();
	}

	@SkipValidation
	public String logout() {
		this.getSession().remove("isAccess");
		this.getSession().remove("user");
		this.getSession().remove("student");
		this.getSession().remove("admin");
		this.getSession().remove("teacher");
		cookieClean();// 清除cookies
		return super.INDEX;
	}

	@SkipValidation
	public String go() {
		return "goLogin";
	}

	@SkipValidation
	public String login() {
		Cookie[] cookies = hsrequest.getCookies();
		if (cookies != null) {
			return cookieAuth();
		}
		return super.INDEX;
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

	public TeacherService<Teacher, Integer> getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(TeacherService<Teacher, Integer> teacherService) {
		this.teacherService = teacherService;
	}

	public AdminService<Admin, Integer> getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService<Admin, Integer> adminService) {
		this.adminService = adminService;
	}

	public SchoolService<School, Integer> getSchoolService() {
		return schoolService;
	}

	public void setSchoolService(SchoolService<School, Integer> schoolService) {
		this.schoolService = schoolService;
	}

	public ProvinceService<Province, Integer> getProvinceService() {
		return provinceService;
	}

	public void setProvinceService(ProvinceService<Province, Integer> provinceService) {
		this.provinceService = provinceService;
	}

	public People getUser() {
		return user;
	}

	public void setUser(People user) {
		this.user = user;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getProtocol() {
		return protocol;
	}

	public void setProtocol(int protocol) {
		this.protocol = protocol;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public String getReferUrl() {
		return referUrl;
	}

	public void setReferUrl(String referUrl) {
		this.referUrl = referUrl;
	}
}
