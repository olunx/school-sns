package cn.gdpu.vo;

import java.util.List;
import java.util.Set;

public class Classes {
	private int id;
	private String name;
	private Set<Student> student;
	private List<Course> course;
	private String intro;
	private Teacher assistant;
	private Institute institute;
	private School school;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Student> getStudent() {
		return student;
	}

	public void setStudent(Set<Student> student) {
		this.student = student;
	}

	public List<Course> getCourse() {
		return course;
	}

	public void setCourse(List<Course> course) {
		this.course = course;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Teacher getAssistant() {
		return assistant;
	}

	public void setAssistant(Teacher assistant) {
		this.assistant = assistant;
	}

	public Institute getInstitute() {
		return institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
