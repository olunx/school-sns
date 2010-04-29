package cn.gdpu.action;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.gdpu.service.AttendanceService;
import cn.gdpu.service.CourseService;
import cn.gdpu.service.StudentService;
import cn.gdpu.util.PageBean;
import cn.gdpu.vo.Attendance;
import cn.gdpu.vo.Course;
import cn.gdpu.vo.People;
import cn.gdpu.vo.Student;

@SuppressWarnings("serial")
public class AttendanceAction extends BaseAction {
	private StudentService<Student, Integer> studentService;
	private CourseService<Course, Integer> courseService;
	private AttendanceService<Attendance, Integer> attendanceService;
	private String week;
	private String day;
	private String courseIds;
	private String students;// 接收传来的学生学号字符串
	private int id;
	private Integer[] ids;//批量删除ID
	private int cmd;

	@Override
	public String add() {
		Attendance attendance = new Attendance();
		attendance.setWeek(week);
		attendance.setDay(day);

		// 获取所有选择的课程对象
		String[] cids = courseIds.split("\\,");
		Set<Course> courses = new HashSet<Course>();
		for (int i = 0; i < cids.length; i++) {
			if (!cids[i].equals("")) {
				courses.add(courseService.getEntity(Course.class, Integer.parseInt(cids[i])));
			}
		}

		// 获取所有选择的学生对象
		String[] stuSnos = students.split("\\,");
		Set<Student> stus = new HashSet<Student>();
		for (int i = 0; i < stuSnos.length; i++) {
			if (!stuSnos[i].equals(""))
				stus.add(studentService.getStudentByNo(stuSnos[i]));
		}

		attendance.setCourse(courses);
		attendance.setStudents(stus);
		attendance.setClerk((Student) this.getSession().get("student"));
		attendance.setTime(new Date());
		attendanceService.addEntity(attendance);
		return super.add();
	}

	@Override
	public String delete() {
		attendanceService.deleteEntity(Attendance.class, id);
		return super.delete();
	}

	@Override
	public String deleteMany() {
		switch (cmd) {
		case 1:
		attendanceService.deleteManyEntity(Attendance.class, ids);
		break;
		};
		return super.deleteMany();
	}

	@Override
	public String goAdd() {
		Student user = (Student) this.getSession().get("student");
		user = studentService.getEntity(Student.class, user.getId());
		int classid = user.getClasses().getId();
		List<Student> students = studentService.getEntity(Student.class, "from People p where p.classes.id = " + classid);
		List<Course> courses = courseService.getAllEntity(Course.class);

		getRequest().put("students", students);
		getRequest().put("courses", courses);

		return super.goAdd();
	}

	@Override
	public String goModify() {
		// TODO Auto-generated method stub
		return super.goModify();
	}

	@Override
	public String list() {
		PageBean pageBean = attendanceService.queryForPage(Attendance.class, 15, 1);
		this.getRequest().put("pageBean", pageBean);
		if (pageBean.getList().isEmpty())
			pageBean.setList(null);
		return super.list();
	}

	@Override
	public String modify() {
		// TODO Auto-generated method stub
		return super.modify();
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

	public void setCourseService(CourseService<Course, Integer> courseService) {
		this.courseService = courseService;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(String courseIds) {
		this.courseIds = courseIds;
	}

	public String getStudents() {
		return students;
	}

	public void setStudents(String students) {
		this.students = students;
	}

	public AttendanceService<Attendance, Integer> getAttendanceService() {
		return attendanceService;
	}

	public void setAttendanceService(AttendanceService<Attendance, Integer> attendanceService) {
		this.attendanceService = attendanceService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public int getCmd() {
		return cmd;
	}

	public void setCmd(int cmd) {
		this.cmd = cmd;
	}

}
