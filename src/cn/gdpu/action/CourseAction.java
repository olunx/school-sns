package cn.gdpu.action;

import java.util.ArrayList;
import java.util.List;


import cn.gdpu.service.CourseService;
import cn.gdpu.service.PeopleService;
import cn.gdpu.util.Log;
import cn.gdpu.util.excel.StudentExcel;
import cn.gdpu.vo.Classes;
import cn.gdpu.vo.Course;
import cn.gdpu.vo.People;

@SuppressWarnings("serial")
public class CourseAction extends BaseAction {
	private int id;
	private CourseService<Course, Integer> courseService;
	private PeopleService<People, Integer> peopleService;

	@SuppressWarnings("unchecked")
	@Override
	public String add() {
		Log.init(getClass()).info(this.getRequest().get("targetsFilePath"));
		People user = (People) this.getSession().get("user");
		user = peopleService.getEntity(People.class, user.getId());
		Classes classes = user.getClasses();
		Log.init(getClass()).info("classes:"+classes.getName());
		List<String> filelist = (List<String>) this.getRequest().get("targetsFilePath");
		List<Course> courseList = new ArrayList<Course>();
		if (filelist.size() > 0) {
			courseList = StudentExcel.getStudentExcel().getCourseData(filelist.get(0));
		}
		for (Course c:courseList){
			c.setClasses(classes);//设置课程表的班级
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
		People user = (People) this.getSession().get("user");
		Classes classes = user.getClasses();
		Log.init(getClass()).info("classes:"+classes.getName());
		List<Course> courseList = courseService.getEntity(Course.class, "from Course c where c.classes.id="+classes.getId()+" order by c.whatDay asc");
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

	public CourseService<Course, Integer> getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseService<Course, Integer> courseService) {
		this.courseService = courseService;
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

}
