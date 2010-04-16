package cn.gdpu.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import cn.gdpu.service.CourseService;
import cn.gdpu.service.GoodsTypeService;
import cn.gdpu.service.IssueTypeService;
import cn.gdpu.service.StudentService;
import cn.gdpu.util.Log;
import cn.gdpu.util.excel.StudentExcel;
import cn.gdpu.vo.Course;
import cn.gdpu.vo.GoodsType;
import cn.gdpu.vo.IssueType;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Student;

@SuppressWarnings("serial")
public class InstallAction extends BaseAction {

	private StudentService<Student, Integer> studentService;
	private GoodsTypeService<GoodsType, Integer> goodsTypeService;
	private IssueTypeService<IssueType, Integer> issueTypeService;
	private CourseService<Course, Integer> courseService;

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
		return super.SUCCESS;
	}

	private void addMany(String fileName) {

		String filePath = ServletActionContext.getServletContext().getRealPath("\\upload") + "\\" + fileName;

		Log.init(getClass()).info("filePath " + filePath);

		List<People> peopleList = new ArrayList<People>();
		peopleList = StudentExcel.getStudentExcel().getStudentData(filePath);
		Student s;
		for (People p : peopleList) {
			s = (Student) p;
			studentService.addEntity(s);
		}
		Log.init(getClass()).info("学生添加完成.......");
	}

	// 添加课程表
	private void addCourse(String fileName) {
		String filePath = ServletActionContext.getServletContext().getRealPath("\\upload") + "\\" + fileName;
		Log.init(getClass()).info("filePath " + filePath);
		List<Course> courseList = new ArrayList<Course>();
		courseList = StudentExcel.getStudentExcel().getCourseData(filePath);
		for (Course c : courseList) {
			c.setClasses(null);// 设置课程表的班级
			courseService.addEntity(c);
		}
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
}
