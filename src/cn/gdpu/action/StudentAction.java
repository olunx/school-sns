package cn.gdpu.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.gdpu.service.StudentService;
import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;
import cn.gdpu.util.excel.StudentExcel;
import cn.gdpu.vo.Student;

@SuppressWarnings("serial")
public class StudentAction extends BaseAction {

	private int id;
	private String ids;

	private Student student;

	private StudentService<Student, Integer> studentService;
	private PageBean pageBean;
	private int page;

	@Override
	public String add() {
		studentService.addEntity(student);

		return super.add();
	}

	@SuppressWarnings("unchecked")
	public String addMany() {
		List<String> fileList = (List<String>) this.getRequest().get("targetsFilePath");
		Log.init(getClass()).info("fileList" + fileList);
		List<Student> studentList = new ArrayList<Student>();
		if (fileList.size() > 0) {
			studentList = StudentExcel.getStudentExcel().getStudentData(fileList.get(0));
			Log.init(getClass()).info("studentList" + studentList);
		}
		for (Student s : studentList) {
			studentService.addEntity(s);
		}

		return super.add();
	}

	@Override
	public String delete() {
		this.studentService.deleteEntity(Student.class, id);
		return super.delete();
	}

	// 加为好友
	public String follow() {
		Student oneStu = studentService.getEntity(Student.class, id);
		Student me = (Student) this.getSession().get("student");
		if (oneStu != null && me != null) {
			me = studentService.getEntity(Student.class, me.getId());
			Set<Student> myFriends = me.getFriends();
			if (myFriends.contains(oneStu)) {// 如果是朋友就删除
				myFriends.remove(oneStu);
			} else {
				myFriends.add(oneStu);
			}
			me.setFriends(myFriends);
			studentService.updateEntity(me);
		}
		return "list";
	}

	// 检查是否是朋友
	public static Boolean isMyFriend(Set<Student> set, Student stu) {
		if (set.contains(stu))
			return true;
		return false;
	}

	@Override
	public String deleteMany() {
		Log.init(getClass()).info("deleMamy " + ids);
		// TODO Auto-generated method stub
		return super.deleteMany();
	}

	@Override
	public String list() {
		Student stu = (Student) this.getSession().get("student");
		if (stu != null) {
			stu = studentService.getEntity(Student.class, stu.getId());
			this.getRequest().put("friends", stu.getFriends());
		}
		this.pageBean = this.studentService.queryForPage(Student.class, 10, page);
		if (pageBean.getList().isEmpty())
			pageBean.setList(null);

		return super.list();
	}

	@Override
	public String goModify() {
		this.setStudent(studentService.getEntity(Student.class, id));
		return super.goModify();
	}

	@Override
	public String modify() {
		this.studentService.updateEntity(student);
		return super.modify();
	}

	@Override
	public String view() {
		this.setStudent(studentService.getEntity(Student.class, id));
		return super.view();
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
