package cn.gdpu.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.gdpu.service.StudentService;
import cn.gdpu.util.Log;
import cn.gdpu.util.excel.StudentExcel;
import cn.gdpu.vo.Student;

@SuppressWarnings("serial")
public class InstallAction extends BaseAction {

	private StudentService<Student, Integer> studentService;

	@Override
	public String execute() throws Exception {

		// 添加学生
		addMany("001.xls");

		return super.SUCCESS;
	}

	private void addMany(String fileName) {

		String filePath = ServletActionContext.getServletContext().getRealPath("\\upload") + "\\" + fileName;

		Log.init(getClass()).info("filePath " + filePath);

		List<Student> studentList = new ArrayList<Student>();
		studentList = StudentExcel.getStudentExcel().getStudentData(filePath);
		for (Student s : studentList) {
			studentService.addEntity(s);
		}

	}

	public StudentService<Student, Integer> getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService<Student, Integer> studentService) {
		this.studentService = studentService;
	}

}
