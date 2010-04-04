package cn.gdpu.action;

import java.util.List;

import cn.gdpu.service.CourseService;
import cn.gdpu.service.StudentService;
import cn.gdpu.vo.Course;
import cn.gdpu.vo.Student;


@SuppressWarnings("serial")
public class AttendanceAction extends BaseAction {
	private StudentService<Student, Integer> studentService;
	private CourseService<Course, Integer> courseService;

	@Override
	public String add() {
		// TODO Auto-generated method stub
		return super.add();
	}

	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return super.delete();
	}

	@Override
	public String deleteMany() {
		// TODO Auto-generated method stub
		return super.deleteMany();
	}

	@Override
	public String goAdd() {
		List<Student> students = studentService.getAllEntity(Student.class);
		List<Course> courses = courseService.getAllEntity(Course.class);
		
		getRequest().put("students", students);
		getRequest().put("courses",courses);
		
		return super.goAdd();
	}

	@Override
	public String goModify() {
		// TODO Auto-generated method stub
		return super.goModify();
	}

	@Override
	public String list() {
		// TODO Auto-generated method stub
		return super.list();
	}

	@Override
	public String modify() {
		// TODO Auto-generated method stub
		return super.modify();
	}

}
