package cn.gdpu.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import cn.gdpu.service.AdminService;
import cn.gdpu.service.ClassesService;
import cn.gdpu.service.CourseService;
import cn.gdpu.service.GoodsTypeService;
import cn.gdpu.service.InstituteService;
import cn.gdpu.service.IssueTypeService;
import cn.gdpu.service.ProvinceService;
import cn.gdpu.service.SchoolService;
import cn.gdpu.service.StudentService;
import cn.gdpu.util.Log;
import cn.gdpu.util.excel.StudentExcel;
import cn.gdpu.vo.Admin;
import cn.gdpu.vo.Classes;
import cn.gdpu.vo.Course;
import cn.gdpu.vo.GoodsType;
import cn.gdpu.vo.Institute;
import cn.gdpu.vo.IssueType;
import cn.gdpu.vo.Province;
import cn.gdpu.vo.School;
import cn.gdpu.vo.Student;

@SuppressWarnings("serial")
public class InstallAction extends BaseAction {

	private StudentService<Student, Integer> studentService;
	private GoodsTypeService<GoodsType, Integer> goodsTypeService;
	private IssueTypeService<IssueType, Integer> issueTypeService;
	private CourseService<Course, Integer> courseService;
	private ProvinceService<Province, Integer> provinceService;
	private SchoolService<School, Integer> schoolService;
	private InstituteService<Institute, Integer> instituteService;
	private ClassesService<Classes, Integer> classesService;
	private AdminService<Admin, Integer> adminService;

	@Override
	public String execute() throws Exception {

		// 添加学生
		addMany("001.xls");
		// 添加课程表
		addCourse("kecheng.xls");
		// 添加交换类型
		addGoodType();
		// 添加提问类型
		addIssueType();
		//添加学校与对应学院（简单实现先）
		addProvince();
		addSchool();
		addInstitute();
		addClasses();
		
		//添加个管理员
		addAdmin();
		return super.SUCCESS;
	}

	private void addMany(String fileName) {

		String filePath = ServletActionContext.getServletContext().getRealPath("upload") + "/" + fileName;

		Log.init(getClass()).info("filePath " + filePath);

		List<Student> peopleList = new ArrayList<Student>();
		peopleList = StudentExcel.getStudentExcel().getStudentData(filePath);
		for (Student s : peopleList) {
			studentService.addEntity(s);
		}
		Log.init(getClass()).info("学生添加完成.......");
	}

	// 添加课程表
	private void addCourse(String fileName) {
		String filePath = ServletActionContext.getServletContext().getRealPath("upload") + "/" + fileName;
		Log.init(getClass()).info("filePath " + filePath);
		List<Course> courseList = new ArrayList<Course>();
		courseList = StudentExcel.getStudentExcel().getCourseData(filePath);
		for (Course c : courseList) {
			c.setClasses(null);// 设置课程表的班级
			courseService.addEntity(c);
		}
	}
	
	//添加管理员
	private void addAdmin(){
		Admin admin = new Admin();
		admin.setUsername("admin");
		admin.setPassword("admin");
		admin.setName("管理员");
		admin.setRegTime(new Date());
		adminService.addEntity(admin);
		Log.init(getClass()).info("管理员添加完成.......");
	}
	
	//添加地区
	private void addProvince(){
		Province province1 = new Province();
		province1.setName("广东省");
		provinceService.addEntity(province1);
		
		Province province2 = new Province();
		province2.setName("北京");
		provinceService.addEntity(province2);
	}
	
	// 添加学校
	private void addSchool() {
		Province province1 = provinceService.getEntity(Province.class, 1);
		School school1 = new School();
		school1.setName("广东药学院");
		school1.setAddress("广东省广州大学城广东药学院");
		school1.setProvince(province1);
		schoolService.addEntity(school1);
		
		School school2 = new School();
		school2.setName("中山大学");
		school2.setAddress("广东省广州大学城中山大学");
		school2.setProvince(province1);
		schoolService.addEntity(school2);
		
		Province province2 = provinceService.getEntity(Province.class, 2);
		School school3 = new School();
		school3.setName("北京大学");
		school3.setAddress("北京");
		school3.setProvince(province2);
		schoolService.addEntity(school3);
	}
	// 添加学院
	private void addInstitute() {
		//第一个学校
		School school1 = schoolService.getAllEntity(School.class).get(0); 
		Institute institute1 = new Institute();
		institute1.setName("医药信息工程学院");
		institute1.setSchool(school1);
		instituteService.addEntity(institute1);
		
		Institute institute2 = new Institute();
		institute2.setName("医药商学院");
		institute2.setSchool(school1);
		instituteService.addEntity(institute2);

		
		Institute institute3 = new Institute();
		institute3.setName("药科学院");
		institute3.setSchool(school1);
		instituteService.addEntity(institute3);
		
		//第二个学校
		School school2 = schoolService.getAllEntity(School.class).get(1);
		Institute institute4 = new Institute();
		institute4.setName("文学院");
		institute4.setSchool(school2);
		instituteService.addEntity(institute4);
		
		Institute institute5 = new Institute();
		institute5.setName("数学学院");
		institute5.setSchool(school2);
		instituteService.addEntity(institute5);

		//第三个学校
		School school3 = schoolService.getAllEntity(School.class).get(2);
		Institute institute6 = new Institute();
		institute6.setName("XX学院");
		institute6.setSchool(school3);
		instituteService.addEntity(institute6);
	}
	//添加班级
	public void addClasses() {	
		Institute institute1 = instituteService.getAllEntity(Institute.class).get(0);
		
		Classes classes1 = new Classes();
		classes1.setName("信息管理与信息系统（医药软件工程方向）07");
		classes1.setEntryYear(2007);
		classes1.setInstitute(institute1);
		
		classesService.addEntity(classes1);
		
		
		Classes classes2 = new Classes();
		classes2.setName("信息管理与信息系统（医药信息方向）07");
		classes2.setEntryYear(2007);
		classes2.setInstitute(institute1);
		
		classesService.addEntity(classes2);
		
		Institute institute2 = instituteService.getAllEntity(Institute.class).get(1);

		Classes classes3 = new Classes();
		classes3.setName("医药电子商务");
		classes3.setEntryYear(2007);
		classes3.setInstitute(institute2);
		
		classesService.addEntity(classes3);
		
		Institute institute3 = instituteService.getAllEntity(Institute.class).get(2);

		Classes classes4 = new Classes();
		classes4.setName("制药工程08");
		classes4.setEntryYear(2008);
		classes4.setInstitute(institute3);
		
		classesService.addEntity(classes4);
		
		Institute institute4 = instituteService.getAllEntity(Institute.class).get(3);

		Classes classes5 = new Classes();
		classes5.setName("历史");
		classes5.setEntryYear(2008);
		classes5.setInstitute(institute4);
		
		classesService.addEntity(classes5);
		
		Institute institute5 = instituteService.getAllEntity(Institute.class).get(4);

		Classes classes6 = new Classes();
		classes6.setName("高数班");
		classes6.setEntryYear(2008);
		classes6.setInstitute(institute5);
		
		classesService.addEntity(classes6);
		
		Institute institute6 = instituteService.getAllEntity(Institute.class).get(5);

		Classes classes7 = new Classes();
		classes7.setName("XX班");
		classes7.setEntryYear(2008);
		classes7.setInstitute(institute6);
		
		classesService.addEntity(classes7);
		Log.init(getClass()).info("班级初始化完成.......");
	}

	private void addGoodType() {
		GoodsType goodsType = new GoodsType();
		goodsType.setName("数码产品");
		goodsTypeService.addEntity(goodsType);
		GoodsType goodsType1 = new GoodsType();
		goodsType1.setName("玩具");
		goodsTypeService.addEntity(goodsType1);
		GoodsType goodsType2 = new GoodsType();
		goodsType2.setName("体育用品");
		goodsTypeService.addEntity(goodsType2);
		Log.init(getClass()).info("交换品类型添加完成.......");
	}

	private void addIssueType() {
		IssueType it1 = new IssueType();
		it1.setName("文学");
		IssueType it2 = new IssueType();
		it2.setName("语文");
		it2.setIsleaf(true);
		IssueType it3 = new IssueType();
		it3.setName("数学");
		it3.setIsleaf(true);
		Set<IssueType> its1 = new HashSet<IssueType>();
		its1.add(it2);
		its1.add(it3);
		it1.setChildType(its1);
		IssueType it4 = new IssueType();
		it4.setName("体育");
		IssueType it5 = new IssueType();
		it5.setName("足球");
		it5.setIsleaf(true);
		IssueType it6 = new IssueType();
		it6.setName("篮球");
		it6.setIsleaf(true);
		Set<IssueType> its2 = new HashSet<IssueType>();
		its2.add(it5);
		its2.add(it6);
		it4.setChildType(its2);
		issueTypeService.addEntity(it1);
		issueTypeService.addEntity(it4);
		Log.init(getClass()).info("提问类型添加完成.......");
	}

	public StudentService<Student, Integer> getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService<Student, Integer> studentService) {
		this.studentService = studentService;
	}

	public CourseService<Course, Integer> getCourseService() {
		return courseService;
	}

	public GoodsTypeService<GoodsType, Integer> getGoodsTypeService() {
		return goodsTypeService;
	}

	public void setGoodsTypeService(GoodsTypeService<GoodsType, Integer> goodsTypeService) {
		this.goodsTypeService = goodsTypeService;
	}

	public IssueTypeService<IssueType, Integer> getIssueTypeService() {
		return issueTypeService;
	}

	public void setIssueTypeService(IssueTypeService<IssueType, Integer> issueTypeService) {
		this.issueTypeService = issueTypeService;
	}

	public void setCourseService(CourseService<Course, Integer> courseService) {
		this.courseService = courseService;
	}

	public ProvinceService<Province, Integer> getProvinceService() {
		return provinceService;
	}

	public void setProvinceService(ProvinceService<Province, Integer> provinceService) {
		this.provinceService = provinceService;
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

	public ClassesService<Classes, Integer> getClassesService() {
		return classesService;
	}

	public void setClassesService(ClassesService<Classes, Integer> classesService) {
		this.classesService = classesService;
	}

	public AdminService<Admin, Integer> getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService<Admin, Integer> adminService) {
		this.adminService = adminService;
	}
	
}
