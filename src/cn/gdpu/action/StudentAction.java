package cn.gdpu.action;


import java.util.ArrayList;
import java.util.List;

import cn.gdpu.service.ImageService;
import cn.gdpu.service.StudentService;
import cn.gdpu.util.Log;
import cn.gdpu.util.PageBean;
import cn.gdpu.util.excel.StudentExcel;
import cn.gdpu.vo.Image;
import cn.gdpu.vo.Student;

@SuppressWarnings("serial")
public class StudentAction extends BaseAction {

	private int id;
	private Integer[] ids;
	private Student student;
	private StudentService<Student, Integer> studentService;
	private PageBean pageBean;
	private int page;
	private Image image;
	private ImageService<Image, Integer> imageService;

	@Override
	public String add() {
		studentService.addEntity(student);

		return super.add();
	}

	@SuppressWarnings("unchecked")
	public String addMany() {
		List<String> fileList = (List<String>) this.getRequest().get("targetsFilePath");
		Log.init(getClass()).info("fileList" + fileList);
		List<Student> peopleList = new ArrayList<Student>();
		if (fileList.size() > 0) {
			peopleList = StudentExcel.getStudentExcel().getStudentData(fileList.get(0));
			Log.init(getClass()).info("peopleList" + peopleList);
		}
		for (Student s : peopleList) {
			studentService.addEntity(s);
		}

		return super.add();
	}
	
	@Override
	public String delete() {
		this.studentService.deleteEntity(Student.class, id);
		return super.delete();
	}

	@Override
	public String deleteMany() {
		Log.init(getClass()).info("deleMamy " + ids);
		studentService.deleteManyEntity(Student.class, ids);
		return super.deleteMany();
	}

	@Override
	public String list() {
		this.pageBean = this.studentService.queryForPage(Student.class, 10, page);
		if (pageBean.getList().isEmpty())
			pageBean.setList(null);

		return super.list();
	}

	@Override
	public String goModify() {
		student = studentService.getEntity(Student.class, id);
		return super.goModify();
	}

	@Override
	public String modify() {
		Log.init(getClass()).info(image);
		Log.init(getClass()).info("image.getMinFileUrl()" + image.getMinFileUrl());
		imageService.addEntity(image);
		student.setAvatar(image);
		studentService.updateEntity(student);
		return super.modify();
	}

	@Override
	public String view() {
		student = studentService.getEntity(Student.class, id);
		Log.init(getClass()).info("student.getAvatar() " + student.getAvatar());
		return super.view();
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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public ImageService<Image, Integer> getImageService() {
		return imageService;
	}

	public void setImageService(ImageService<Image, Integer> imageService) {
		this.imageService = imageService;
	}

}
