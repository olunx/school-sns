package cn.gdpu.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.gdpu.service.CourseService;
import cn.gdpu.util.excel.StudentExcel;
import cn.gdpu.vo.Course;
import cn.gdpu.vo.Notice;

@SuppressWarnings("serial")
public class CourseAction extends BaseAction {
	private Log logger = LogFactory.getLog(this.getClass());
	private int id;
	private CourseService<Course, Integer> courseService;

	@Override
	public String add() {
		logger.info(this.getRequest().get("targetsFilePath"));
		List<String> filelist = (List<String>) this.getRequest().get("targetsFilePath");
		List<Course> courseList = new ArrayList<Course>();
		if (filelist.size() > 0) {
			courseList = StudentExcel.getStudentExcel().getCourseData(filelist.get(0));
		}
		for (Course c:courseList){
			c.setClasses(null);//设置课程表的班级
			courseService.addEntity(c);
		}
		return super.add();
	}

	@Override
	public String goAdd() {
		return super.goAdd();
	}

	@Override
	public String delete() {
		this.courseService.deleteEntity(Course.class, id);
		return super.delete();
	}

	@Override
	public String deleteMany() {
		// TODO Auto-generated method stub
		return super.deleteMany();
	}

	@Override
	public String list() {
		List<Course> courseList = courseService.getAllEntity(Course.class);
		getRequest().put("courseList", courseList);
		return super.list();
	}

	@Override
	public String modify() {
		// TODO Auto-generated method stub
		return super.modify();
	}

	@Override
	public String goModify() {
		// TODO Auto-generated method stub
		return super.goModify();
	}

	public CourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
