package cn.gdpu.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import cn.gdpu.service.AdminService;
import cn.gdpu.service.PeopleService;
import cn.gdpu.service.ProvinceService;
import cn.gdpu.service.SchoolService;
import cn.gdpu.service.StudentService;
import cn.gdpu.service.TeacherService;
import cn.gdpu.util.Log;
import cn.gdpu.vo.Admin;
import cn.gdpu.vo.Classes;
import cn.gdpu.vo.Institute;
import cn.gdpu.vo.IssueType;
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

	public String auth() {

		Log.init(getClass()).info("username: " + username);
		Log.init(getClass()).info("password: " + password);

		if (username != null && password != null) {
			if (peopleService != null) {
				Log.init(getClass()).info("开始查询: ");
				People people = peopleService.getPeopleByUsernameAndPwd(username, password);
				Log.init(getClass()).info("登录用户对象: " + people);
				if (people != null) {
					Log.init(getClass()).info("验证通过，跳转: ");
					this.getSession().put("user", people);
					int peopleId = people.getId();
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

					this.getSession().put("isAccess", "true");
					return super.SUCCESS;
				}
			}
		}

		// 绕过验证
		this.getSession().put("isAccess", "true");
		return super.SUCCESS;
		// return super.INDEX;
	}
	
	public String logout(){
		this.getSession().remove("isAccess");
		this.getSession().remove("user");
		this.getSession().remove("student");
		this.getSession().remove("admin");
		this.getSession().remove("teacher");
		return super.INDEX;
	}

	public String go() {
		return "goLogin";
	}
	
	public String goRegister(){
		List<Province> provinces = provinceService.getAllEntity(Province.class);
		
		Map<String, Map<String, Object>> map = new LinkedHashMap<String, Map<String, Object>>();
		for(Province province: provinces){
			Map<String, Object> promap = new LinkedHashMap<String, Object>();
			Map<String, Integer> sclmap = new LinkedHashMap<String, Integer>();
			promap.put("key", province.getId());
			promap.put("defaultvalue", province.getSchools().iterator().next().getId());
			for(School school : province.getSchools()){
				sclmap.put(school.getName(), school.getId());
			}
			
			promap.put("values", sclmap);
			map.put(province.getName(), promap);
		}
        JSONObject jo = JSONObject.fromObject(map);
		getRequest().put("schoolmap", jo);
		return "goregister";
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
}
