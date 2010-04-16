package cn.gdpu.vo;

import java.util.List;
import java.util.Set;

public class Classes {
	private int id;
	private String name;
	private Set<Student> student;
	private List<Course> course;
	private int entryYear;
	private String intro;
	private Teacher assistant;
	private Institute institute;
	private List<ClassFee> classfees;

	

	public List<ClassFee> getClassfees() {
		return classfees;
	}

	public void setClassfees(List<ClassFee> classfees) {
		this.classfees = classfees;
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEntryYear() {
		return entryYear;
	}

	public void setEntryYear(int entryYear) {
		this.entryYear = entryYear;
	}

}
