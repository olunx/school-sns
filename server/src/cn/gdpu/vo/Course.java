package cn.gdpu.vo;

import java.util.Set;

public class Course {
	private int id;
	private String name;
	private int startLesson;
	private int endLesson;
	private int whatDay;
	private int year;
	private int term;
	private Classes classes;
	private Set<Attendance> attendances;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartLesson() {
		return startLesson;
	}

	public void setStartLesson(int startLesson) {
		this.startLesson = startLesson;
	}

	public int getEndLesson() {
		return endLesson;
	}

	public void setEndLesson(int endLesson) {
		this.endLesson = endLesson;
	}

	public int getWhatDay() {
		return whatDay;
	}

	public void setWhatDay(int whatDay) {
		this.whatDay = whatDay;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	@Override
	public String toString() {
		return "name:" + name + " startLesson:" + startLesson + " endLesson:" + endLesson + " whatDay:" + whatDay + " year:" + year
				+ " term:" + term;
	}

	public Set<Attendance> getAttendances() {
		return attendances;
	}

	public void setAttendances(Set<Attendance> attendances) {
		this.attendances = attendances;
	}
}
