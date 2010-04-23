package cn.gdpu.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.Preparable;

import net.sf.json.JSONObject;
import cn.gdpu.service.ClassesService;
import cn.gdpu.service.InstituteService;
import cn.gdpu.service.PeopleService;
import cn.gdpu.service.ProvinceService;
import cn.gdpu.service.SchoolService;
import cn.gdpu.service.StudentService;
import cn.gdpu.service.TeacherService;
import cn.gdpu.util.Log;
import cn.gdpu.vo.Classes;
import cn.gdpu.vo.Image;
import cn.gdpu.vo.Institute;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Province;
import cn.gdpu.vo.School;
import cn.gdpu.vo.Student;
import cn.gdpu.vo.Teacher;

@SuppressWarnings("serial")
public class RegisterAction extends BaseAction implements Preparable {

	private PeopleService<People, Integer> peopleService;
	private StudentService<Student, Integer> studentService;
	private TeacherService<Teacher, Integer> teacherService;
	private SchoolService<School, Integer> schoolService;
	private InstituteService<Institute, Integer> instituteService;
	private ProvinceService<Province, Integer> provinceService;
	private ClassesService<Classes, Integer> classesService;
	private Student user;
	private String repassword;
	private String birthday;
	private int entryYear;
	private String sno;
	private String realName;
	private Image image;
	private int schoolId;
	private int classesId;
	private int protocol;
	private int identity;

	
	@Override
	public void prepare() throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) ServletActionContext.getRequest();
		String action=  httpRequest.getServletPath().split("/")[1];
		String[] uri=action.split("\\.");
		if(uri[0].equals("register")){
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
		}
		if(uri[0].equals("perfectReg")){
			Object student = this.getSession().get("student");
			if (student != null) {
				if (student instanceof Student) {
					Student user = (Student) student;
					School school = schoolService.getEntity(School.class, user.getSchool().getId());
					Set<Institute> institutes=  school.getInstitute();
			
					Map<String, Map<String, Object>> map = new LinkedHashMap<String, Map<String, Object>>();
					for(Institute institute: institutes){
						Map<String, Object> insmap = new LinkedHashMap<String, Object>();
						Map<String, Integer> clamap = new LinkedHashMap<String, Integer>();
						insmap.put("key", institute.getId());
						insmap.put("defaultvalue", institute.getClasses().iterator().next().getId());
						for(Classes classes: institute.getClasses()){
							clamap.put(classes.getName(), classes.getId());
						}
						
						insmap.put("values", clamap);
						map.put(institute.getName(), insmap);
					}
			        JSONObject jo1 = JSONObject.fromObject(map);
					getRequest().put("classesmap", jo1);
				}
			}
		}
	}
	@SkipValidation
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
	
	public String register() throws Exception{
		if (user.getUsername() != null && protocol == 1 ) {
			People people = peopleService.getPeopleByUsername(user.getUsername());
			System.out.println("----people---------" + people);
			if(people == null && user.getPassword().trim() != null && repassword.trim() != null && user.getPassword().trim().equals(repassword.trim())){
				
				School school = schoolService.getEntity(School.class, schoolId);
				user.setSchool(school);
				user.setPermission(1);        //普通注册完成是1
				user.setStatus(1);           //普通用户注册成功状态为1
				user.setRegTime(new Date());
				
				if(identity == 1){
					studentService.addEntity(user);
				}
				if(identity == 0){
					Teacher teacher = new Teacher();
					teacher.setUsername(user.getUsername());
					teacher.setPassword(user.getPassword());
					teacher.setSchool(user.getSchool());
					teacher.setName(user.getName());
					teacher.setSex(user.getSex());
					teacher.setEmail(user.getEmail());
					teacher.setPermission(user.getPermission());
					teacher.setStatus(user.getStatus());
					teacher.setRegTime(user.getRegTime());
					teacherService.addEntity(teacher);
				}
				
				Log.init(getClass()).info("用户注册成功：" + user.getUsername());
				return "login";
				
			}
		}
		return INDEX;
	}
	
	@SkipValidation
	public String goPerfectReg(){
		Object student = this.getSession().get("student");
		if (student != null) {
			if (student instanceof Student) {
				Student user = (Student) student;
				School school = schoolService.getEntity(School.class, user.getSchool().getId());
				Set<Institute> institutes=  school.getInstitute();
		
				Map<String, Map<String, Object>> map = new LinkedHashMap<String, Map<String, Object>>();
				for(Institute institute: institutes){
					Map<String, Object> insmap = new LinkedHashMap<String, Object>();
					Map<String, Integer> clamap = new LinkedHashMap<String, Integer>();
					insmap.put("key", institute.getId());
					insmap.put("defaultvalue", institute.getClasses().iterator().next().getId());
					for(Classes classes: institute.getClasses()){
						clamap.put(classes.getName(), classes.getId());
					}
					
					insmap.put("values", clamap);
					map.put(institute.getName(), insmap);
				}
		        JSONObject jo1 = JSONObject.fromObject(map);
				getRequest().put("classesmap", jo1);
				return "goPerfectReg";
			}
		}
		return ERROR;
	}
	
	public String perfectReg() throws Exception{
		Object student = this.getSession().get("student");
		if (student != null) {
			if (student instanceof Student) {
				Student user = (Student) student;
				Classes classes = classesService.getEntity(Classes.class, classesId);
				List<People> admins = classes.getAdmins();
				if(admins.size() == 0){       //如果如加入的班级管理员为空，自动成为管理员
					admins.add(user);
					classes.setAdmins(admins);
					classesService.updateEntity(classes);
					user.setPermission(2);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date =sdf.parse(birthday);
				user.setBirthday(date);
				user.setClasses(classes);
				user.setSno(sno);
				user.setEntryYear(entryYear);
				user.setAvatar(image);
				studentService.updateEntity(user);
				return "classes";
			}
		}
		return ERROR;
	}

	
	
	//getter and setter
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

	public SchoolService<School, Integer> getSchoolService() {
		return schoolService;
	}

	public void setSchoolService(SchoolService<School, Integer> schoolService) {
		this.schoolService = schoolService;
	}

	public InstituteService<Institute, Integer> getInstituteService() {
		return instituteService;
	}

	public void setInstituteService(InstituteService<Institute, Integer> instituteService) {
		this.instituteService = instituteService;
	}

	public ProvinceService<Province, Integer> getProvinceService() {
		return provinceService;
	}

	public void setProvinceService(ProvinceService<Province, Integer> provinceService) {
		this.provinceService = provinceService;
	}

	public Student getUser() {
		return user;
	}

	public void setUser(Student user) {
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

	public int getEntryYear() {
		return entryYear;
	}

	public void setEntryYear(int entryYear) {
		this.entryYear = entryYear;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public int getClassesId() {
		return classesId;
	}

	public void setClassesId(int classesId) {
		this.classesId = classesId;
	}

	public int getProtocol() {
		return protocol;
	}

	public void setProtocol(int protocol) {
		this.protocol = protocol;
	}

	public int getIdentity() {
		return identity;
	}

	public void setIdentity(int identity) {
		this.identity = identity;
	}

	public ClassesService<Classes, Integer> getClassesService() {
		return classesService;
	}

	public void setClassesService(ClassesService<Classes, Integer> classesService) {
		this.classesService = classesService;
	}

}
