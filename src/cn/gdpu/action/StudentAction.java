package cn.gdpu.action;

import java.util.ArrayList;
import java.util.List;

import cn.gdpu.service.StudentService;
import cn.gdpu.util.PageBean;
import cn.gdpu.util.excel.StudentExcel;
import cn.gdpu.vo.Student;

public class StudentAction extends BaseAction {

	private StudentService<Student, Integer> studentService;
	private PageBean pageBean;
	private int page;

	@Override
	public String add() {
		// TODO Auto-generated method stub
		return super.add();
	}

	public String addMany() {
		List<String> fileList = (List<String>) this.getRequest().get("targetsFilePath");
		List<Student> studentList = new ArrayList<Student>();
		if (fileList.size() > 0) {
			studentList = StudentExcel.getStudentExcel().getStudentData(fileList.get(0));
		}
		for (Student s : studentList) {
			studentService.addEntity(s);
		}

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
	public String list() {
		this.pageBean = this.studentService.queryForPage(Student.class, 5, page);
		if(pageBean.getList().isEmpty())
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

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
